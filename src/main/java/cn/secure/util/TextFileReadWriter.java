/**  
 * Project Name		batch  
 * File Name		TextFileReadWriter.java  
 * Package Name		cn.secure.util
 * Date				2018年5月28日下午9:19:41  
 * Copyright (c) 2018, cert@cnic.cn All Rights Reserved.  
 */  
package cn.secure.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.swing.plaf.synth.SynthSplitPaneUI;
/**
 * @Description	批处理进度条工具
 * @ClassName	TextFileReadWriter
 * @author		ygc
 * @date		2018年5月28日 上午9:19:45
 * @version		V1.0
 * @since		JDK 1.8
 */
public class TextFileReadWriter {

		public static void main(String[] args) throws IOException {
			TextFileReadWriter.batchRunState("20");
		}
	  private static String filenameTemp;  
	  /**
	   * textReadState	(读文本进度)<br/>
	   * @return 进度
	   * @throws IOException 异常
	   * @author	ygc  
	   * @since	JDK 1.8
	   */
	  public static String textReadState(String fileName) throws IOException {
	    	String filePath=Commons.UPLOAD_PATH;
//	    	String fileName="batchRun";
//	    	String filePath="D:\\cnic";
	    	//文件写入
	    	TextFileReadWriter.creatTxtFile(fileName,filePath);
	    	//保存路径
	    	String savePath=filePath+"\\"+fileName+".txt";
	    String size=TextFileReadWriter.readByChars(savePath);
	    return size;
	  };
	  /**
	   * textRunState	(设置文本运行进度)<br/>
	   * @param runState 运行状态
	   * @throws IOException 异常
	   * @author	ygc  
	   * @since	JDK 1.8
	   */
	  public static void batchRunState(String runState) throws IOException {
		 	String filePath=Commons.UPLOAD_PATH;
	    	String fileName="batchRun";
//	    	String filePath="D:\\cnic";
	    	//文件写入
	    	TextFileReadWriter.creatTxtFile(fileName,filePath);
	    	//运行进度
//	    	String runState="20";
	    	//保存路径
	    	String savePath=filePath+"\\"+fileName+".txt";
	        //文本写入进度 
	    	TextFileReadWriter.saveAs(runState,savePath);
	  }
	  /**
	   * hostRunState	(设置主机运行进度)<br/>
	   * @param runState 运行状态
	   * @throws IOException 异常
	   * @author	ygc  
	   * @since	JDK 1.8
	   */
	  public static void hostRunState(String runState) throws IOException {
		 	String filePath=Commons.UPLOAD_PATH;
	    	String fileName="hostBatchRun";
//	    	String filePath="D:\\cnic";
	    	//文件写入
	    	TextFileReadWriter.creatTxtFile(fileName,filePath);
	    	//运行进度
//	    	String runState="20";
	    	//保存路径
	    	String savePath=filePath+"\\"+fileName+".txt";
	        //文本写入进度 
	    	TextFileReadWriter.saveAs(runState,savePath);
	  }
	  /**
	   * webRunState	(设置文本运行进度)<br/>
	   * @param runState 运行状态
	   * @throws IOException 异常
	   * @author	ygc  
	   * @since	JDK 1.8
	   */
	  public static void webRunState(String runState) throws IOException {
		 	String filePath=Commons.UPLOAD_PATH;
	    	String fileName="webBatchRun";
//	    	String filePath="D:\\cnic";
	    	//文件写入
	    	TextFileReadWriter.creatTxtFile(fileName,filePath);
	    	//运行进度
//	    	String runState="20";
	    	//保存路径
	    	String savePath=filePath+"\\"+fileName+".txt";
	        //文本写入进度 
	    	TextFileReadWriter.saveAs(runState,savePath);
	  }
	  
	  
	  
	  /**
	   * 创建txt文件
	   * @param name 文件名
	   * @param path 路径
	   * @author	ygc  
	   * @return 是/否
	   * @throws IOException 异常
	   */
    public static boolean creatTxtFile(String name,String path) throws IOException {  
        boolean flag = false;  
        filenameTemp = path +"\\"+ name + ".txt";  
        File filename = new File(filenameTemp);  
        if (!filename.exists()) {  
        	File file=new File(path);
        	file.mkdirs();
        	File file1=new File(path,name+".txt");
            file1.createNewFile();  
            flag = true;  
        }  
        return flag;  
    }  
    
    /**
     *  以字节为单位读取文件，通常用于读取二进制文件，如图片
     * @param path 路径
     * @author	ygc  
     * @return 内容
     */
    public static String readByBytes(String path) {    
        String content = null;
        
        try {
            InputStream inputStream = new FileInputStream(path);
            StringBuffer sb = new StringBuffer();
            int c = 0;
            byte[] bytes = new byte[1024];
            /*
             * InputStream.read(byte[] b)
             * 
             * Reads some number of bytes from the input stream and stores them into the buffer array b. 从输入流中读取一些字节存入缓冲数组b中
             * The number of bytes actually read is returned as an integer.  返回实际读到的字节数
             * This method blocks until input data is available, end of file is detected, or an exception is thrown. 
             * 该方法会一直阻塞，直到输入数据可以得到、或检测到文件结束、或抛出异常  -- 意思是得到数据就返回
             */
            while ((c = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, c, "utf-8"));
            }
            
            content = sb.toString();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return content;
    } 
    
    /**
     *  以行为单位读取文件，常用于读取面向行的格式化文件
     * @param path 路径
     * @author	ygc  
     * @return 内容
     */
    public static String readByLines(String path) {
        String content = null;
        
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            
            StringBuffer sb = new StringBuffer();
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                sb.append(temp);
            }
            
            content = sb.toString();
            bufferedReader.close();
        } catch (UnsupportedEncodingException  e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return content;
    }
    
    /**
     *  以字符为单位读取文件，常用于读取文本文件
     * @author	ygc   
     * @param path
     * @return 内容
     */
    public static String readByChars(String path) {
        String content = null;
        
        try {
            
            Reader reader = new InputStreamReader(new FileInputStream(path), "utf-8");
            StringBuffer sb = new StringBuffer();
            

            char[] tempchars = new char[1024];
            while (reader.read(tempchars) != -1) {
                sb.append(tempchars);
            }
            
            content = sb.toString();
            reader.close();    
        } catch (Exception e) {
            e.printStackTrace();
        }    
        return content;
    }

    /**
     *  把内容content写的path文件中
     * @param content 内容
     * @param path 路径
     * @author	ygc  
     * @return 是/否
     */
    public static boolean saveAs(String content, String path) {
        
        FileWriter fw = null;
        
        //System.out.println("把内容：" + content + "， 写入文件："  + path);
        
        try {
            /**
             * Constructs a FileWriter object given a File object. 
             * If the second argument is true, then bytes will be written to the end of the file rather than the beginning.
             * 根据给定的File对象构造一个FileWriter对象。 如果append参数为true, 则字节将被写入到文件的末尾（向文件中追加内容）
             *
             *    Parameters:
             *        file,  a File object to write to 带写入的文件对象
             *        append,  if true, then bytes will be written to the end of the file rather than the beginning
             *    Throws:
             *        IOException - 
             *        if the file exists but is a directory rather than a regular file, 
             *            does not exist but cannot be created, 
             *            or cannot be opened for any other reason
             *      报异常的3种情况：
             *          file对象是一个存在的目录（不是一个常规文件）
             *          file对象是一个不存在的常规文件，但不能被创建
             *          file对象是一个存在的常规文件，但不能被打开
             *
             */
            fw = new FileWriter(new File(path), false);
            if (content != null) {
                fw.write(content);
            }    
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fw != null) {
                try {
                    fw.flush();
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}