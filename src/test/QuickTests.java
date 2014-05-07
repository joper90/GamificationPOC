package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class QuickTests {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		new QuickTests();
	}
	
	public QuickTests() throws ParseException
	{
		System.out.println("Tests");
		String cal = "2013-07-05T10:49:25.183Z";
		String cal2 = "2013-07-05T10:51:37.183Z";
		//yyyymmddhhmmss.mmmmmmsutc
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date = formatter.parse(cal);
		
		Date date2 = formatter.parse(cal2);
		
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		
		System.out.println(date + "\n" + date2);
		
		long diff = getDateDiff(date,date2,TimeUnit.MINUTES);
		
		
		System.out.println(diff);
		
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
}
