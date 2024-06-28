package ma.inetum.brique.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = MonthFormatValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MonthFormat {
	String format();
	String message() default "Invalid month";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
