import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class utilitymethods {

	public static String month(String date) {
		String monthvalues[] = {" ","January", "February", "March", "April",
				"May", "June", "July", "August", "September", "October",
				"November", "December" };
		int i = 0;
		while (date.charAt(i) != '/') {
			i++;

		
		}

		int h=Integer.parseInt(date.substring(0, i));
		String fulldate=monthvalues[h]+","+date.substring(date.length()-4,date.length());		
		return fulldate;
	}
	
}
