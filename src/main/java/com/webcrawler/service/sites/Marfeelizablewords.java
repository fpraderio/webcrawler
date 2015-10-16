package com.webcrawler.service.sites;

import java.util.ArrayList;
import java.util.List;


public class Marfeelizablewords {
    private List<Marfeelizableword> marfeelizableWords;

    public Marfeelizablewords (){
        marfeelizableWords = new ArrayList<Marfeelizableword>();
    }
    
    public List<Marfeelizableword> getMarfeelizableWords() {
        return marfeelizableWords;
    }

    public void setMarfeelizableWords(List<Marfeelizableword> marfeelizableWords) {
        this.marfeelizableWords = marfeelizableWords;
    }
}
