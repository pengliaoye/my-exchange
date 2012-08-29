package com.exchange.domain;

import java.io.Serializable;

public class DataInputOfProcess implements Serializable {
	private static final long serialVersionUID = 5731280077048212636L;
	private String request;

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

}
