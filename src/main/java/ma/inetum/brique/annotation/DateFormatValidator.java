package ma.inetum.brique.annotation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class DateFormatValidator implements 
ConstraintValidator<DateFormat, String>{
	private  DateFormat dateFormat;
		@Override
	    public void initialize(DateFormat dateFormat) {
			this.dateFormat = dateFormat;
	    }
	 	
	    @Override
	    public boolean isValid(String date, ConstraintValidatorContext cxt) {
	    	if(StringUtils.isEmpty(date))
	    		return true;
	    	try {
	    		System.out.println(date + " format " + dateFormat.format());
				LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat.format()));
			} catch (DateTimeParseException e) {
				return false;
			}
	        return true ;
	    }
}
