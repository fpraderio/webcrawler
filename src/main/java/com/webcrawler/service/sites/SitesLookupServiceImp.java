package com.webcrawler.service.sites;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webcrawler.model.Site;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class SitesLookupServiceImp implements SitesLookupService{
    
    
    @Autowired
    private final ThreadPoolTaskExecutor taskExecutor;
    
    @Autowired
    public SitesLookupServiceImp(ThreadPoolTaskExecutor taskExecutor){
        this.taskExecutor = taskExecutor;
    }

    public List<Site> isMarfeelizable(List<Site> sites) {
        
        Marfeelizablewords marfeelizablewords = getMarfeelizableWords();
        for (Site site : sites){            
            taskExecutor.execute(new CheckWeb(site, marfeelizablewords));
        }
        //check active thread
	for (;;) {
            int count = taskExecutor.getActiveCount();
            System.out.println("Active Threads : " + count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 0) {
                break;
            }
	}
        
        return sites;
    }
   
    
    private Marfeelizablewords getMarfeelizableWords() {

        ObjectMapper mapper = new ObjectMapper();
        Marfeelizablewords marfeelizablewords;
        String fileName = "/site/marfeelizableWords.json";
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            marfeelizablewords = mapper.readValue(file, Marfeelizablewords.class);
        } catch (IOException ex) {
            marfeelizablewords = new Marfeelizablewords();
            Logger.getLogger(SitesLookupServiceImp.class.getName()).log(Level.SEVERE, "ERROR: "+fileName);
        }
	
	return marfeelizablewords;

    }

    
}
