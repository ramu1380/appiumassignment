package com.cogni.core.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ebay.app.pages.CommandLineExecutor;

import groovyjarjarpicocli.CommandLine.Command;

public class IOSUtils {

	public List<AppiumDevice> realDevices = new ArrayList();
	public List<AppiumDevice> simulatedDevices = new ArrayList();
	public static HashMap<String, String> realDevicesMap = new HashMap();
	public static HashMap<String, String> simulatedDevicesMap = new HashMap();

	public List<AppiumDevice> getConnectedDevices() throws IOException {
		String device = CommandLineExecutor.exec("/usr/bin/instruments -s devices", 60);
		if (device != null) {
			List<String> lines = Arrays.asList(device.split("\n"));
			List<AppiumDevice> realDevices = new ArrayList();
			Iterator var4 = lines.iterator();
			while (var4.hasNext()) {
				String line = (String) var4.next();

				Pattern p = Pattern.compile(
						"((.{0,})\\((.{0,})\\)\\s{0,}\\s\\[(.{0,})])|(((.{0,})\\((.{0,})\\))(\\s-*\\s*(.*)))|((\\w+ \\w+ -)(\\s-*\\s*(.*)))|((\\w*\\s\\-)(.*))");
				Matcher matcher = p.matcher(line);
				while (matcher.find()) {
					System.out.println(matcher.group());
					int systemPort = 8100;
					String udid = matcher.group(4);
					String version = matcher.group(3);
					String deviceName = matcher.group(2);
					IOSDevice iosDevices = new IOSDevice(udid, systemPort, deviceName, version, DeviceStatus.AVAILABLE);
					realDevices.add(iosDevices);
					++systemPort;
					System.out.println(udid);
					System.out.println(version);
					System.out.println(deviceName);
					System.out.println(matcher.group());
				}
			}
		}
		return realDevices;
	}

	public static String getUdidOfDevice(String deviceName) throws IOException {
		String deviceList = CommandLineExecutor.exec("/usr/bin/xcrun simctl list devices", 60);
		List<String> lines = Arrays.asList(deviceList.split("/n"));
		Iterator ir= lines.iterator();
		while(ir.hasNext()) {
			String line =(String) ir.next();
		Pattern pattern = Pattern.compile("");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {

		      String  version = matcher.group(1);
		          }
		          Pattern pattern2 = Pattern.compile("\\s*(.*)(\\((([\\w*\\-])*)\\).*)\\s*(Booted)");
		          Matcher matcher2 = pattern2.matcher(line);
		          if (matcher2.find()) {
		            String udid = matcher2.group(3);
		             String devicename = matcher2.group(1).trim();
		             if (devicename.equals(deviceName)) {
		                return udid;
		             }  
		}
		
	}
		return null;
}
	public static void uninstallApp(String udid, String bundleId) throws IOException {
		if(simulatedDevicesMap.containsKey(udid)) {
			CommandLineExecutor.exec(String.format("/usr/bin/xcrun simctl uninstall %s %s",udid,bundleId), 60);
		}
		else {
			CommandLineExecutor.exec(String.format("/usr/local/bin/ios-deploy --id %s --uninstall_only --bundle_id %s",udid,bundleId), 60);		
		}
		
	}
}