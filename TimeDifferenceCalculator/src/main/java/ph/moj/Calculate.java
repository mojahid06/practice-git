package ph.moj;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calculate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String time1 = "03:10:03,934";
		String time2 = "03:10:57,934";

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss,SSS");
		Date date1;
		Date date2;
		double difference = 0.00;
		try {
			date1 = format.parse(time1);
			date2 = format.parse(time2);
			long diff = date2.getTime() - date1.getTime();
			difference = diff / 1000.00;
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		System.out.println(difference);
		
	}

}
