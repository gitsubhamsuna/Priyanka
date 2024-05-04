package com.genaricLibUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtilities {
	/**
	 * This method is used for generate Random Integer number 
	 * @return
	 */
	public int getRandom() {
		Random ran=new Random();
		int random=ran.nextInt(1000);
		return random;
	}
	
	/**
	 * this method is used for generate the System date 
	 * @return
	 */
	public String getSystemDate() {
		Date dt=new Date();
		String date=dt.toString();
		return date;
	}
	
	/**
	 * This method is used for converting the system date format to simaple date format
	 * @return
	 */
	public String getSystemDateinFormat() {
		SimpleDateFormat sdf=new SimpleDateFormat(" dd-MM-yyyy HH-mm-ss");
		Date dt=new Date();
//		String date=dt.toString();
		
		String system_Date_In_Format = sdf.format(dt);
		return system_Date_In_Format;
	}
}
