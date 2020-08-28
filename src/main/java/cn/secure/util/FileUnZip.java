/**  
 * Project Name		batch  
 * File Name		FileUnZip.java  
 * Package Name		cn.secure.util
 * Date				2018年5月25日下午2:51:41  
 * Copyright (c) 2018, cert@cnic.cn All Rights Reserved.  
 */  
package cn.secure.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipInputStream;

import org.apache.pdfbox.util.filetypedetector.FileType;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/**
 * @Description	批处理上传zip工具
 * @ClassName	FileUnZip
 * @author		ygc
 * @date		2018年5月25日 下午2:38:54
 * @version		V1.0
 * @since		JDK 1.8
 */
public class FileUnZip {
	/**
	 * zipToFile	(解压zip文件)<br/>
	 * @param sourceFile 待解压的zip文件
	 * @param toFolder 解压后的存放路径
	 * @throws Exception 异常
	 * @author	ygc  
	 * @since	JDK 1.8
	 */
	public static void zipToFile(String sourceFile, String toFolder) throws Exception {
	
			String toDisk = toFolder;// 接收解压后的存放路径
			ZipFile zfile = new ZipFile(sourceFile);// 连接待解压文件 "utf-8"会乱码
			Enumeration<? extends ZipEntry> zList = zfile.entries();// 得到zip包里的所有元素
			ZipEntry ze = null;
			byte[] buf = new byte[1024];
			while (zList.hasMoreElements()) {
			ze = zList.nextElement();
			if (ze.isDirectory()) {
				// log.info("打开zip文件里的文件夹:"+ ze.getName() +"skipped...");
				continue;
			}
			OutputStream outputStream = null;
			InputStream inputStream = null;
			try {
				// 以ZipEntry为参数得到一个InputStream，并写到OutputStream中
				outputStream = new BufferedOutputStream(new FileOutputStream(getRealFileName(toDisk, ze.getName())));
				inputStream = new BufferedInputStream(zfile.getInputStream(ze));
				int readLen = 0;
				while ((readLen = inputStream.read(buf, 0, 1024)) != -1) {
					outputStream.write(buf, 0, readLen);
				}
				inputStream.close();
				outputStream.close();
			} catch (Exception e) {
				// log.info("解压失败："+e.toString());
				throw new IOException("解压失败：" + e.toString());
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException ex) {

					}
				}
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				inputStream = null;
				outputStream = null;
			
				
			}

		}
		zfile.close();
	}
	/**
	 * getRealFileName	(给定根目录，返回一个相对路径所对应的实际文件名)<br/>
	 * @param zippath zip路径
	 * @param absFileName   相对路径名，来自于ZipEntry中的name
	 * @return
	 * @author	ygc  
	 * @since	JDK 1.8
	 */
    private static File getRealFileName(String zippath, String absFileName) {
        // log.info("文件名："+absFileName);
        String[] dirs = absFileName.split("/", absFileName.length());
        File ret = new File(zippath);// 创建文件对象
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                ret = new File(ret, dirs[i]);
            }
        }
        if (!ret.exists()) {// 检测文件是否存在
            ret.mkdirs();// 创建此抽象路径名指定的目录
        }
        ret = new File(ret, dirs[dirs.length - 1]);// 根据 ret 抽象路径名和 child
                                                    // 路径名字符串创建一个新 File 实例
        return ret;
    }
    

    public static void listJarFile(String zipPathName)throws Exception{
    	ZipFile zipFile = new ZipFile(zipPathName);
    	Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
    	while(zipEntrys.hasMoreElements()){
    	ZipEntry zipEntry = zipEntrys.nextElement();
    	System.out.println(zipEntry.getName());
    	}
    	}
    /**
	 * zip解压
	 * @param srcFile        zip源文件
	 * @param destDirPath	  解压后的目标文件夹
	 * @throws RuntimeException 解压失败会抛出运行时异常
	 */
	public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
		long start = System.currentTimeMillis();
		// 判断源文件是否存在
		if (!srcFile.exists()) {
			throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
		}
		// 开始解压
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(srcFile);
			Enumeration<?> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				System.out.println("解压" + entry.getName());
				// 如果是文件夹，就创建个文件夹
				if (entry.isDirectory()) {
					String dirPath = destDirPath + "/" + entry.getName();
					File dir = new File(dirPath);
					dir.mkdirs();
				} else {
					// 如果是文件，就先创建一个文件，然后用io流把内容copy过去
					File targetFile = new File(destDirPath + "/" + entry.getName());
					// 保证这个文件的父文件夹必须要存在
					if(!targetFile.getParentFile().exists()){
						targetFile.getParentFile().mkdirs();
					}
					targetFile.createNewFile();
					// 将压缩文件内容写入到这个文件中
					InputStream is = zipFile.getInputStream(entry);
					FileOutputStream fos = new FileOutputStream(targetFile);
					int len;
					byte[] buf = new byte[2048];
					while ((len = is.read(buf)) != -1) {
						fos.write(buf, 0, len);
					}
					// 关流顺序，先打开的后关闭
					fos.close();
					is.close();
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("解压完成，耗时：" + (end - start) +" ms");
		} catch (Exception e) {
			throw new RuntimeException("unzip error from ZipUtils", e);
		} finally {
			if(zipFile != null){
				try {
					zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * unZipFiles	(非压缩文件)<br/>
	 * @param zipFile 文件
	 * @param descDir 磁盘目录
	 * @return 是/否
	 * @throws IOException 异常
	 * @author	ygc  
	 * @since	JDK 1.8
	 */

	public static boolean unZipFiles(File zipFile, String descDir) throws IOException {
		ZipFile zip;
		try{
			zip = new ZipFile(zipFile);
			//logger.info("zip="+zip.getName());
		}
		catch(Exception e){
			
			return false;
		}
		try{
			ZipEntry testEntry=(ZipEntry) zip.entries().nextElement();
		}
		catch(Exception e){
			Charset gbk = Charset.forName(BaseConstantUtil.TRACK_CODE_TYPE_GBK);
			zip = new ZipFile(zipFile,gbk);
			
		}
		
//		File pathFile = new File(descDir);
//		if (!pathFile.exists()) {
//			pathFile.mkdirs();
//		}
		for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = null;
			try{
				entry=(ZipEntry) entries.nextElement();
			}
			catch(Exception e){
				return false;
			}
			String zipEntryName = entry.getName();
			//logger.info("zipEntryName="+zipEntryName);
			InputStream in = zip.getInputStream(entry);
			String outPath=descDir+"//"+zipEntryName;
//			String outPath = (descDir + zipEntryName).replaceAll("\\*", BaseConstantUtil.TRACK_DIAGONAL);
//			String str=outPath.substring(0, outPath.lastIndexOf(BaseConstantUtil.TRACK_DIAGONAL));
//			File file = new File(descDir);
//			if (!file.exists()) {
//				file.mkdirs();
//			}

			if (new File(outPath).isDirectory()) {
				continue;
			}

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
			
		}
		zip.close();
		return true;
	}
	/**
	 * deleteDirectory	(删除目录)<br/>
	 * @param sPath
	 * @return
	 * @author	ygc  
	 * @since	JDK 1.8
	 */
    public boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
      boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
  

    
  
    /**
     * deleteFile	(删除文件)<br/>
     * @param sPath 路径
     * @return 是/否
     * @author	ygc  
     * @since	JDK 1.8
     */
    private boolean deleteFile(String sPath) {
       boolean flag = false;
       File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }


	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @author ygc
     * @return 成功/失败
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {	
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete(); 
    }
}

