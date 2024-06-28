package ma.inetum.brique.utilities;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;


@Component
public class Convert {

    public static LocalDate convertDateToLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
    public static Integer convertStringToInteger(String str) {
        Integer val = 0;
        if(str != null){
            val = Integer.parseInt(str);
        }
        return val;
    }

}
