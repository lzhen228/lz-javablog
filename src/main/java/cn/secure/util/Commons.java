/**  
 * Project Name		batch  
 * File Name		Commons.java  
 * Package Name		cn.secure.util
 * Date				2018年5月25日下午2:57:41  
 * Copyright (c) 2018, cert@cnic.cn All Rights Reserved.  
 */  
package cn.secure.util;
import java.util.Hashtable;
import java.util.ResourceBundle;
/**
 * @Description	批处理上传ZIP文件路径
 * @ClassName	Commons
 * @author		Administrator
 * @date		2018年5月25日 下午2:26:08
 * @version		V1.0
 * @since		JDK 1.8
 */
public class Commons {
	
	
    public static String UPLOAD_PATH;
    
    public static String HOST_UPLOAD_PATH;
    
    public static String HOST_FILE_PATH;
    
    public static String WEB_UPLOAD_PATH;
    //静态区域块读取 properties 
    static{
        ResourceBundle resource=ResourceBundle.getBundle("init");
        UPLOAD_PATH=resource.getString("upload_path").trim();
        HOST_UPLOAD_PATH=resource.getString("hostReportFilePath").trim();
        WEB_UPLOAD_PATH=resource.getString("webReportFilePath").trim();
        HOST_FILE_PATH=resource.getString("hostFilePath").trim();
        
    }    
}
