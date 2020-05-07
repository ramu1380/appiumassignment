package com.cogni.core.utils;

public enum DeviceStatus {

	AVAILABLE("available"), BUSY("BUSY");
	public final String value;
	DeviceStatus(String status){
		this.value=status;
	}
	
	public String getValue() {
		return this. value;
	}
}
