package cn.secure.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Talk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer aid;
	private Integer tid;
	private String talk;
	private String href;
	private String name;
	private String toHref;
	private String toName;
	private String time;
	private ArrayList<Talk> replyTalk;
	public Talk() {
		this.replyTalk = new ArrayList<Talk>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTalk() {
		return talk;
	}
	public void setTalk(String talk) {
		this.talk = talk;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToHref() {
		return toHref;
	}
	public void setToHref(String toHref) {
		this.toHref = toHref;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public ArrayList<Talk> getReplyTalk() {
		return replyTalk;
	}
	public void setReplyTalk(ArrayList<Talk> replyTalk) {
		this.replyTalk = replyTalk;
	}
	public void addReplyTalk(Talk item) {
		this.replyTalk.add(item);
	}
	

}
