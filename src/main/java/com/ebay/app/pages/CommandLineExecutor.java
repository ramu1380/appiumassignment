package com.ebay.app.pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.SystemUtils;

public class CommandLineExecutor{
	 public static Process process;
	public static String exec(String command,int timeSeconds) throws IOException  {
		
	if(SystemUtils.IS_OS_LINUX||SystemUtils.IS_OS_MAC)	{
		process=Runtime.getRuntime().exec(new String[] {"/bin/sh","-c",command});
	}
	if(SystemUtils.IS_OS_WINDOWS) {
		 process=Runtime.getRuntime().exec(new String[] {"cmd","/c",command});
	}
	StringBuilder builder= new StringBuilder();
	InputStream is=process.getInputStream();
	InputStreamReader ir= new InputStreamReader(is);
	BufferedReader br =new BufferedReader(ir);
	
	while(br.readLine()!=null) {
		String line=br.readLine();
		builder.append(line);
		builder.append("\n");
	}
	return builder.toString();
	}
}