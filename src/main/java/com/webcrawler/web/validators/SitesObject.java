package com.webcrawler.web.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = SitesObjectValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SitesObject {

    String message() default "{SitesObject}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}