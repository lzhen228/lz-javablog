package cn.secure.entity;

import java.io.Serializable;

public class Recharge implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;
	private String img;
	private String smallImg;
	private Integer percent;
	private String toBig;
	private String toSmall;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSmallImg() {
		return smallImg;
	}

	public void setSmallImg(String smallImg) {
		this.smallImg = smallImg;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public String getToBig() {
		return toBig;
	}

	public void setToBig(String toBig) {
		this.toBig = toBig;
	}

	public String getToSmall() {
		return toSmall;
	}

	public void setToSmall(String toSmall) {
		this.toSmall = toSmall;
	}

}
