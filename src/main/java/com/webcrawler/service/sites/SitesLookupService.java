package com.webcrawler.service.sites;

import com.webcrawler.domain.sites.Site;
import java.util.List;
import java.util.concurrent.Future;


public interface SitesLookupService {
    
    public List<Site> isMarfeelizable(List<Site> sites);
    
//    public Future<Site> isMarfeelizable2(Site site) throws InterruptedException;
    
}
