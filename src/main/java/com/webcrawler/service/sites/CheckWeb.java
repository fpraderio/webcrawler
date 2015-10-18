package com.webcrawler.service.sites;

import com.webcrawler.model.Site;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CheckWeb implements Runnable{

	Site site;
        Marfeelizablewords marfeelizablewords;
        
	public CheckWeb(Site site, Marfeelizablewords marfeelizablewords){
            this.site = site;
            this.marfeelizablewords = marfeelizablewords;
	}
	
	@Override
	public void run() {
		
            System.out.println(site.getUrl() + " is running");
		
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDate = sdf.format(dt);
            
            Document doc;
            try {
                
                doc = Jsoup.connect(site.getUrl()).get();
                // check element of web 
                
                boolean isMarfeelizable = false;
                for (Marfeelizableword marfeelizableword : marfeelizablewords.getMarfeelizableWords()){
                    if (marfeelizableword.isEmpty()){
                        isMarfeelizable = true;
                    }else {
                        Elements tags = doc.getElementsByTag(marfeelizableword.getTag());
                        for (Element tag : tags){
                            //is marfeelizable if contains one of the keywords
                            isMarfeelizable = (marfeelizableword.getOrs().isEmpty());
                            for (String word: marfeelizableword.getOrs()){
                                isMarfeelizable = tag.text().toUpperCase().contains(word.toUpperCase());
                                if (isMarfeelizable) break;
                            }
                            if (!isMarfeelizable) break;
                            //is marfeelizable if contains all of the keywords
                            isMarfeelizable = (marfeelizableword.getAnds().isEmpty());
                            for (String word: marfeelizableword.getAnds()){
                                isMarfeelizable = tag.text().toUpperCase().contains(word.toUpperCase());
                                if (!isMarfeelizable) break;
                            }
                            if (!isMarfeelizable) break;
                        }
                    }
                }
                site.setIsMarfeelizable(isMarfeelizable);
                site.setCheckedDate(currentDate);
            } catch (Exception ex) {
                Logger.getLogger(SitesLookupServiceImp.class.getName()).log(Level.WARNING, "ERROR url: "+site.getUrl(), ex);
            }
		
	}

}