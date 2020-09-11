package cn.secure.entity;

import java.io.Serializable;


public class Announcement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String time;
	private String content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String string) {
		this.time = string;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
