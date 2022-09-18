package com.jumpee.commerce.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime 
{
	public String getTimestamp()
	{
		LocalDateTime myDateObj = LocalDateTime.now();  
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss");   
	    String formattedDate = myDateObj.format(myFormatObj);  
		return formattedDate; 
	}
	 
}
