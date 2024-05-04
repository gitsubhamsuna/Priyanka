package com.genaricLibUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtilities{
	FileOutputStream fos;
	Properties pr;
	
	public Properties filePath(String path) throws IOException {
		FileInputStream fis=new FileInputStream(path);
		Properties pr = new Properties();
		pr.load(fis);
		return pr;
	}
	
	/**
	 * This method is use for reading the data from properties file 
	 * @author subham
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readPropertiesData(String key) throws IOException {
		return filePath(IpathConstant.PROPERTIES_FILE_PATH).getProperty(key);
	}
	
	/**
	 * this method is used for write the data in properties file
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public void writePropertiesData(String key,String value) throws IOException {
		pr=new Properties();
		pr.setProperty(key, value);
		fos=new FileOutputStream(IpathConstant.PROPERTIES_FILE_PATH);
		pr.store(fos, "update");
	}
}
