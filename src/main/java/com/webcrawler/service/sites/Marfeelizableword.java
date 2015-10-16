package com.webcrawler.service.sites;

import java.util.ArrayList;
import java.util.List;


public class Marfeelizableword {
    private String tag;
    private List<String> ors;
    private List<String> ands;

    public Marfeelizableword(){
        tag = "";
        ors = new ArrayList<String>();
        ands = new ArrayList<String>();
    }
    
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getOrs() {
        return ors;
    }

    public void setOrs(List<String> ors) {
        this.ors = ors;
    }

    public List<String> getAnds() {
        return ands;
    }

    public void setAnds(List<String> ands) {
        this.ands = ands;
    }
    
    public boolean isEmpty(){
        return tag.isEmpty();
    }
}
