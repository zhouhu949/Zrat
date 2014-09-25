package com.zrat.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

public class IpUtil {
	private static final String GETIPURL = "http://www.ip138.com/ip2city.asp";
	private static final String UPDATEDOMAINURL = "http://{0}:{1}@members.3322.org/dyndns/update?system=dyndns&hostname={2}&myip={3}&wildcard=ON&mx=&backmx=NO&offline=NO";
	private static String uName;
	private static String pwd;
	private static String domainName;
	
	public static boolean updateDomain(String userName,String passWord,String domain,String ip){
		uName = userName;
		pwd = passWord;
		domainName = domain;
		
		updateIp(ip);
		System.out.println("update domain done");
		return false;
		
	}
	
	public static void updateIp(String ip){
		String url = MessageFormat.format(UPDATEDOMAINURL,uName,pwd,domainName,ip);
		System.out.println(url);
		try {
			processUpdateDomain(new URL(url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private static void processUpdateDomain(URL url) throws IOException{
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestProperty("user-agent","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; en-US; rv:1.9.2.8) Gecko/20100722 Firefox/3.6.8"); 
		http.setRequestProperty("Connection","keep-alive"); 
		
		InputStream in = http.getInputStream();
		BufferedReader breader =
		new BufferedReader(new InputStreamReader(in,"GBK"));
		String str=null;
		while((str = breader.readLine())!= null){
			System.out.println(str);
		} 
	}
	
	private static String processGetIpRequest(URL url) throws IOException{
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestProperty("user-agent","Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; en-US; rv:1.9.2.8) Gecko/20100722 Firefox/3.6.8"); 
		http.setRequestProperty("Connection","keep-alive"); 
		InputStream in = http.getInputStream();
		BufferedReader breader =
		new BufferedReader(new InputStreamReader(in,"GBK"));
		String str=null;
		String ip = "";
		while((str = breader.readLine())!= null){
			if (str.indexOf('[') > 0
					&& str.indexOf(']') > 0) {
			ip = str.substring(str
					.indexOf('[') + 1, str.indexOf(']'));
			}
		} 
		in.close();
		return ip;
	}
	
	
	public static String getIp(){
		URL uri = null;
		String ip = "";
		try {
			uri = new URL(GETIPURL);
			ip = processGetIpRequest(uri);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ip;
	}

	public static void main(String[] args){
		IpUtil.getIp();
	}
	
}
