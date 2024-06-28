package ma.inetum.brique.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidateurUtils {

	public static boolean isDate(Integer date, SimpleDateFormat sdf) {
		if(date == null)
			return true;
		try {
			sdf.parse(date.toString());
		} catch (ParseException e) {
			return false;
		}
		return true;
	}	
}
