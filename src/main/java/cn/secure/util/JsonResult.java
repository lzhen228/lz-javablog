/**  
 * Project Name		batch  
 * File Name		JsonResult.java  
 * Package Name		cn.secure.util
 * Date				2018年5月28日下午9:19:41  
 * Copyright (c) 2018, cert@cnic.cn All Rights Reserved.  
 */  
package cn.secure.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @Description	返回结果
 * @ClassName	JsonResult json结果类
 * @author		ygc
 * @date		2018年5月28日 上午9:26:10
 * @version		V1.0
 * @since		JDK 1.8
 */
@XmlRootElement(name = "jsonResult")
public class JsonResult
{

	/**
	 * 默认是成功的
	 */
	private int resultCode = 1;// 0-失败,1-成功
	private Map<String, Object> result;// 传到前台的数据
	private String url;// 需要跳转的URI
	private String message;// 成功的消息,如果要显示的话
	private String errormsg;// 失败的消息
	private String errorCode;// 失败代码,用于restAPI
	private boolean validateFlag; // 前台校验

	public JsonResult()
	{
	}

	public JsonResult(int resultCode)
	{
		this.resultCode = resultCode;
	}

	public static JsonResult buildFailureJsonResult()
	{
		return new JsonResult(BaseConstantUtil.D3_FAIL);
	}

	public static JsonResult buildSuccessJsonResult()
	{
		return new JsonResult(BaseConstantUtil.D3_OK);
	}

	public JsonResult(int resultCode, String errorCode, String errormsg)
	{
		this.resultCode = resultCode;
		this.errormsg = errormsg;
		this.errorCode = errorCode;
	}

	public int getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(int resultCode)
	{
		this.resultCode = resultCode;
	}

	public Map<String, Object> getResult()
	{
		return result;
	}

	public void setResult(String key, Object value)
	{
		if (null == this.result)
		{
			this.result = new HashMap<String, Object>();
		}
		this.result.put(key, value);
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getErrormsg()
	{
		return errormsg;
	}

	public void setErrormsg(String errormsg)
	{
		this.errormsg = errormsg;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public boolean isValidateFlag()
	{
		return validateFlag;
	}

	public void setValidateFlag(boolean validateFlag)
	{
		this.validateFlag = validateFlag;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
