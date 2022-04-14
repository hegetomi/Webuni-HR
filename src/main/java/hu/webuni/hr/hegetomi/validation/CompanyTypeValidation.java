package hu.webuni.hr.hegetomi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CompanyTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyTypeValidation {

    String message() default "Company type is not found in database";
    Class<?>[] groups() default {};
    Class<? extends Payload>[]  payload() default {};
}
