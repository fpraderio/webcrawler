package com.webcrawler.web.validators;

import com.webcrawler.model.Site;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;


public class SitesObjectValidator implements ConstraintValidator<SitesObject, List<Site>> {

    @Override
    public void initialize(SitesObject constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<Site> sites, ConstraintValidatorContext context) {
        for (Site site : sites){
            if (site.getUrl() == null || StringUtils.isEmpty(site.getUrl())) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{site.invalid.url}")
                        .addConstraintViolation();
                site.setUrl("vilaweb.cat");
                return false;
            } if (!site.getUrl().startsWith("http")){
                //TODO url validation in a better way
                site.setUrl("http://"+site.getUrl());
            }
        }
        
        return true;
    }
}