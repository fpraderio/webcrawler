package com.webcrawler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="sites")
public class Site {
    
    @Id
    private String url;
    private int rank;
    private boolean isMarfeelizable;
    private String checkedDate;

    protected Site(){
    }
    
    public Site(String url, int rank, boolean isMarfeelizable, String checkedDate){
        this.url = url;
        this.rank = rank;
        this.isMarfeelizable = isMarfeelizable;
        this.checkedDate = checkedDate;
    }
   

    @Override
    public String toString() {
     return "Site: "+getUrl();
    }
    
    @JsonIgnore
    public boolean isEmpty() {
        return (this.getUrl() == null);
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
}
