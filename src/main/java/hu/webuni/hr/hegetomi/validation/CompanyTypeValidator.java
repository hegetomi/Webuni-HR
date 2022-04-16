package hu.webuni.hr.hegetomi.validation;

import hu.webuni.hr.hegetomi.model.company.CompanyType;
import hu.webuni.hr.hegetomi.service.company.CompanyTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

public class CompanyTypeValidator implements ConstraintValidator<CompanyTypeValidation, String> {

    @Autowired
    CompanyTypeService companyTypeService;


    Logger logger = LoggerFactory.getLogger(CompanyTypeValidator.class);

    @Override
    public void initialize(CompanyTypeValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {

        logger.warn(type);
        List<CompanyType> types = companyTypeService.findAll();

        Optional<CompanyType> existingType = types.stream().filter(t -> t.getType().equals(type)).findFirst();
        if (existingType.isPresent()) {
            return true;
        } else {
            constraintValidatorContext.buildConstraintViolationWithTemplate(type + " is not found in the database.").addConstraintViolation();
            return false;
        }
    }
}
