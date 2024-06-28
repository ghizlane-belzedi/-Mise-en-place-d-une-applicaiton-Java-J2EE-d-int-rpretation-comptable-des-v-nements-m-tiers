package ma.inetum.brique.annotation;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class MonthFormatValidator implements 
ConstraintValidator<MonthFormat, String>{
	private  MonthFormat monthFormat;
		@Override
	    public void initialize(MonthFormat monthFormat) {
			this.monthFormat = monthFormat;
	    }
	 	
	    @Override
	    public boolean isValid(String date, ConstraintValidatorContext cxt) {
	    	if(StringUtils.isEmpty(date))
	    		return true;
	    	try {
	    		YearMonth.parse(date,  DateTimeFormatter.ofPattern(monthFormat.format()));
			} catch (DateTimeParseException e) {
				return false;
			}
	        return true ;
	    }
}
