package com.webcrawler.web.forms;

import com.webcrawler.domain.sites.Site;
import com.webcrawler.web.validators.SitesObject;
import java.util.List;


public class SitesForm {
    @SitesObject
    private List<Site> sites;
    
    public SitesForm(){
        
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

}
