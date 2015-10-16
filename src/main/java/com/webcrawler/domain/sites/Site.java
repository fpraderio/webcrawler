package com.webcrawler.domain.sites;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Site {
    private int id;
    private String url;
    private int rank;
    private boolean isMarfeelizable;
    private String checkedDate;

    public Site(){
        url = "";
        rank = 0;
        isMarfeelizable = false;
    }
    public Site(String url, int rank, boolean isMarfeelizable, String checkedDate){
        this.url = url;
        this.rank = rank;
        this.isMarfeelizable = isMarfeelizable;
        this.checkedDate = checkedDate;
    }
   

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
 
    @Override
    public String toString() {
     return "Site: "+getUrl();
    }

    public boolean isIsMarfeelizable() {
        return isMarfeelizable;
    }

    public void setIsMarfeelizable(boolean isMarfeelizable) {
        this.isMarfeelizable = isMarfeelizable;
    }

    public String getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(String checkedDate) {
        this.checkedDate = checkedDate;
    }
    
    @JsonIgnore
    public boolean isEmpty() {
        return (this.id == 0 || this.url == null);
    }
}
