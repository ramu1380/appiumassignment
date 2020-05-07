package com.cogni.core.utils;

public class IOSDevice extends AppiumDevice {
	public String deviceName;
	public String platformVersion;
	public int systemPort;

	IOSDevice(String udid, int systemPort, DeviceStatus status) {
		super(udid, systemPort, status);
	}

	public IOSDevice(String udid, int systemPort, String deviceName, String plaltformVersion, DeviceStatus status) {
		super(udid, systemPort, status);
		this.deviceName = deviceName;
		this.platformVersion = platformVersion;
	}
}
