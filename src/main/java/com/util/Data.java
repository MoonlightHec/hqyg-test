package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface Data {
	
	
	 Date currentTime = new Date();
	 SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	 String dateString = formatter.format(currentTime);
   //  String	url="http://10.60.46.88:27000/api/iss-dev";
     String	url="http://10.60.46.88:27000/api/iss-dev/";
	
}
