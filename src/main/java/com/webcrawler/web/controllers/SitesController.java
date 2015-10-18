package com.webcrawler.web.controllers;

import com.webcrawler.model.Site;
import com.webcrawler.web.forms.SitesForm;
import com.webcrawler.model.SiteRepository;
import com.webcrawler.service.sites.SitesLookupServiceImp;
import com.webcrawler.web.forms.SitesFilterForm;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sites")
public class SitesController {
            
    private final SiteRepository siteRepository;
    private final SitesLookupServiceImp sitesLookupService;
    
    @Autowired
    SitesController(SiteRepository siteRepository, SitesLookupServiceImp sitesLookupServiceImp) {
            this.siteRepository = siteRepository;
            this.sitesLookupService = sitesLookupServiceImp;
    }

    @RequestMapping(value="", method = RequestMethod.POST, headers="Content-Type=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> setSites(@Valid @RequestBody SitesForm form, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        List<Site> sitesList = sitesLookupService.isMarfeelizable(form.getSites());
        siteRepository.save(sitesList);
        
        return null;

    }


    @RequestMapping(value="", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Site> getSites( @ModelAttribute SitesFilterForm form) {
        List<Site> sites = null;
        sites = siteRepository.findAll();
        switch (form.getFilter()){
            case "marfeelizable":
                sites = siteRepository.findByIsMarfeelizable(true);
                break;
            case "nomarfeelizable":
                sites = siteRepository.findByIsMarfeelizable(false);
                break;
            default:
                sites = siteRepository.findAll();
        }

        return sites;

    }
    
	
}