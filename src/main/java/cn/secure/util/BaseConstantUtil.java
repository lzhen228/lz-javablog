/**  
 * Project Name		batch  
 * File Name		BaseConstantUtil.java  
 * Package Name		cn.secure.util
 * Date				2018年5月25日下午2:57:41  
 * Copyright (c) 2018, cert@cnic.cn All Rights Reserved.  
 */  
package cn.secure.util;

import java.text.SimpleDateFormat;
/**
 * @Description	基础常量工具
 * @ClassName	BaseConstantUtil
 * @author		ygc
 * @date		2018年5月25日 下午2:40:36
 * @version		V1.0
 * @since		JDK 1.8
 */
public class BaseConstantUtil {

	public static String TRACK_ABOUT_US = "track_about_us_templete";
	public static String TRACK_NOTIFICATION = "track_notification";
	public static String TRACK_NOTIFICATION_TEMPLETE = "track_notification_bulletin_templete";
	public static String TRACK_NOTIFICATION_NOTIFY_TEMPLETE = "track_notification_notify_templete";
	public static String TRACK_NOTIFICATION_DETAIL = "track_notification_detail";
	public static String TRACK_NOTIFICATION_LEAK = "track_notification_leak";
	public static String TRACK_NOTIFICATION_LEAK_RELATE = "track_notification_leak_relate";
	public static String TRACK_NOTIFICATION_SOURCE = "track_notification_source";
	public static String TRACK_NOTIFICATION_STATUS = "track_notification_status";

	//IMG转base64字符串头
	public static final String TRACK_BASE64 = "data:image/jpg;base64,";
	
	public static final String FILE_POSTFIX_PDF =".pdf";
	public static final String FILE_POSTFIX_DOC =".doc";

	public static final String FONT_SIMHEI="simhei.ttf";
	public static final String FONT_STFANGSO="STFANGSO.TTF";
	public static final String FONT_STSONG="STSONG.TTF";

	public static final String FILE_TYPE_NOTICE_PDF="notice.pdf";
	public static final String FILE_TYPE_NOTICE_DOC="notice.doc";

	public static final String FILE_TYPE_DANGER_DOC="danger.doc";
	public static final String FILE_TYPE_ALL="notice.danger";


	public static final int D3_OK = 1;

	public static final int D3_FAIL = 0;


	// 漏洞追踪系统 start
	// 定义时间格式(yyyy-MM-dd)
	public static final String TRACK_TIME_FOEMATE = "yyyy-MM-dd";
	/**
	 * 斜线:"/"
	 */
	public static final String TRACK_DIAGONAL = "/";
	/**
	 * 反斜线:"\"
	 */
	public static final String TRACK_OPPOSITE_DIAGONAL = "\\";
	/**
	 * 双反斜线:"\\"
	 */
	public static final String TRACK_DOUBLE_OPPOSITE_DIAGONAL = "\\\\";
	/**
	 * 生成的reportToExcel.xls文件夹相对路径:"reports/template/"
	 */
	public static final String TRACK_REPORTS_TEMPLATE_PATH = "reports/template/";
	/**
	 * EXCEL文件名:"reportToExcel"
	 */
	public static final String TRACK_EXCEL_NAME_SECU = "reportToExcelSecu.xls";
	/**
	 * EXCEL文件名:"reportToExcel"
	 */
	public static final String TRACK_EXCEL_NAME = "reportToExcel.xls";
	/**
	 * EXCEL文件名:"reportToExcel1"
	 */
	public static final String TRACK_EXCEL_NAME1 = "reportToExcel1.xls";
	/**
	 * EXCEL文件名:"reportToExcel2"
	 */
	public static final String TRACK_EXCEL_NAME2 = "reportToExcel2.xls";
	/**
	 * EXCEL文件名:"reportToExcel3"
	 */
	public static final String TRACK_EXCEL_NAME3 = "reportToExcel3.xls";
	// 定义返回字符集
	public static final String TRACK_CONTENT_TYPE = "textml;charset=UTF-8";
	// 定义返回字符集
	public static final String SYSM_CONTENT_TYPE = "textml;charset=UTF-8";
	// 定义时间格式(yyyyMM)
	public static final String TRACK_TIME_FOEMATE_1 = "yyyyMM";
	// 定义时间格式(yyyy-MM-dd HH:mm:ss)
	public static final String TRACK_TIME_FOEMATE_2 = "yyyy-MM-dd HH:mm:ss";
	// 定义时间格式(yyyy-MM-dd HH:mm:ss SSS)
	public static final String TRACK_TIME_FOEMATE_3 = "yyyy-MM-dd HH:mm:ss SSS";
	// 定义时间格式(yyyy_MM_dd_HH_mm_ss)
	public static final String TRACK_TIME_FOEMATE_4 = "yyyy年MM月dd日HH时mm分ss秒";
	// 定义时间格式(yyyy-MM-dd)
	public static final String TRACK_TIME_FOEMATE_5 = "yyyy-MM-dd";

	//wsjie:begin
	//TrackBatchReportService report process
	public static final String TRACK_CODE_TYPE_UTF8 = "UTF8";
	public static final String TRACK_CODE_TYPE_GBK = "GBK";
	public static final String TRACK_HOST_H_MARKER="host";
	public static final String TRACK_HOST_H_MARKER_NEW="targets";
	public static final String TRACK_HOST_I_MARKER="index";
	public static final String TRACK_HOST_I_MARKER_NEW="HostReport";
	public static final String TRACK_WEB_MARKER="web";
	public static final String TRACK_DATE_FORMAT="yyyyMMddHHmmss";
	public static final String TRACK_WEB_IN="所内";
	public static final String TRACK_WEB_OUT="所外";
	public static final String TRACK_ERROR_FILE="error_file";
	public static final String TRACK_ERROR_HTML="error_html";
	public static final String TRACK_VALID_FILE="valid_file";
	public static final String TRACK_FILE_PATH="file_path";
	public static final String TRACK_ERROR_HOST_MAP="error_host_map";
	public static final String TRACK_ERROR_WEB_MAP="error_web_map";
	
	
	public static final String TRACK_IMAGES="images";
	public static final String TRACK_JS="js";
	public static final String TRACK_CSS="css";
	public static final String TRACK_INDEX_HTML="index.html";
	public static final String TRACK_INDEX_HTML_NEW="HostReport.html";
	public static final String TRACK_HTML=".html";

	public static final String TRACK_UNPACK="unpack/";
	public static final String TRACK_INDEX="index/";
	public static final String TRACK_INDEX_NEW="HostReport/";
	public static final String TRACK_HOSTS="hosts/";
	public static final String TRACK_HOSTS_NEW="targets/";

	public static final String HOST_ASSET_TABLE="exhibit_cnic_host_asset";
	public static final String HOST_BUG_TABLE="exhibit_cnic_host_bugs";
	public static final String HOST_BUG_LIST_TABLE="exhibit_cnic_host_bugs_list";
	public static final String HOST_RISK_TABLE="exhibit_cnic_host_level";
	public static final String WEB_BUG_TABLE="exhibit_cnic_web_bugs";
	public static final String WEB_RISK_TABLE="exhibit_cnic_web_level";
	public static final String BATCH_MARK_TABLE="exhibit_cnic_batch_mark";

	public static final String WEBRAVOR_TASK_TABLE="v_taskinfo";
	public static final String WEBRAVOR_TASK_RUN_TABLE="v_taskruninfo";
	public static final String WEBRAVOR_VUL_TABLE="v_vulinfo";

	public static final String HTTP_SPLITER="://";
	public static final String STR_COLON=":";
	public static final String STR_SEASON_SCAN="季度扫描";
	public static final String STR_CNIC_MARKER="所内";
	public static final int CNIC_ID=51;

	//public static final String REDIS_ADDR="159.226.16.34";
	public static final String REDIS_ADDR="159.226.16.157";
	

	//wsjie:end
	// 漏洞追踪系统 end
	
}
