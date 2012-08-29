package com.exchange.domain;

import java.io.Serializable;

public class DataOutputOfProcess implements Serializable {
	private static final long serialVersionUID = 6294908650136240147L;
	private String serviceReturn;

	public String getServiceReturn() {
		return serviceReturn;
	}

	public void setServiceReturn(String serviceReturn) {
		this.serviceReturn = serviceReturn;
	}
}
