package com.cogni.core.utils;

public class AppiumDevice {
	public String udid;
	public int systemPort;
	public DeviceStatus status;

	public String getUdid() {
		return udid;
	}

	public AppiumDevice(String udid, Integer systemPort, DeviceStatus status) {
		this.udid = udid;
		this.systemPort = systemPort;
		this.status = status;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public int getSystemPort() {
		return systemPort;
	}

	public void setSystemPort(int systemPort) {
		systemPort = systemPort;
	}

	public DeviceStatus getStatus() {
		return status;
	}

	public void setStatus(DeviceStatus status) {
		this.status = status;
	}

}
