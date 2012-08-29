package com.exchange.domain;

import java.io.Serializable;
import java.util.Date;

public class Leave implements Serializable{
	private static final long serialVersionUID = -225620525246727708L;
	private String id;
	private String leavename;
	private String leavereason;
	private Date begintime;
	private Date endtime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLeavename() {
		return leavename;
	}
	public void setLeavename(String leavename) {
		this.leavename = leavename;
	}
	public String getLeavereason() {
		return leavereason;
	}
	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
}
