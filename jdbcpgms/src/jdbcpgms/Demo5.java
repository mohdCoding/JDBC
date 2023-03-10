package jdbcpgms;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Demo5 {

	public static void main(String[] args)  throws Exception {
		
		String date = "2003-04-04";
        
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date udate = sdf.parse(date);
		
		
	
		sdf =new SimpleDateFormat("dd-MM-yyyy");
		String sdate = sdf.format(udate);
		System.out.println(sdate);
		
        
        
	}

}
