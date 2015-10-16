package com.webcrawler.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.webcrawler.domain.sites.Site;
import com.webcrawler.utility.DBUtility;
import org.springframework.stereotype.Repository;

@Repository
public class SiteServiceDAO {
 
    private Connection connection;

    public SiteServiceDAO() {
       connection = DBUtility.getConnection();
    }
  

    public List<Site> getAllSites() {
        List<Site> sites = new ArrayList<Site>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from site");
            while (rs.next()) {
                Site site = new Site();
                site.setId(rs.getInt("id"));
                site.setUrl(rs.getString("url"));
                site.setRank(rs.getInt("rank"));
                site.setIsMarfeelizable(rs.getBoolean("isMarfeelizable"));
                try {
                    //TODO get date
                    site.setCheckedDate(rs.getString("checkedDate"));
                } catch(SQLException e){
                    site.setCheckedDate("NULL");
                }
                sites.add(site);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sites;
     }
 
    public void addSite(Site site) {
        try {

            PreparedStatement preparedStatement = connection
                            .prepareStatement("insert into site(id,url,rank,isMarfeelizable,checkedDate) values (?,?,?,?,?)");

            preparedStatement.setInt(1, site.getId());
            preparedStatement.setString(2, site.getUrl());
            preparedStatement.setInt(3, site.getRank());
            preparedStatement.setBoolean(4, site.isIsMarfeelizable());
            preparedStatement.setString(5, site.getCheckedDate());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSite(Site site){
        try {
            PreparedStatement preparedStatement = connection
                            .prepareStatement("update site set url=?,rank=?,isMarfeelizable=?,checkedDate=?" +
                                            "where id=?");
            
            preparedStatement.setString(1, site.getUrl());
            preparedStatement.setInt(2, site.getRank());			
            preparedStatement.setBoolean(3, site.isIsMarfeelizable());	
            preparedStatement.setString(4, site.getCheckedDate());	
            preparedStatement.setInt(5, site.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void addUpdateSite(Site site) {

        Site existSite = getSiteByUrl(site.getUrl());
        if (!existSite.isEmpty()){
            site.setId(existSite.getId());
            updateSite(site);

        }else{
            addSite(site);
        }
    }

    public Site getSiteByUrl(String url) {
        Site site = new Site();
        try {
            PreparedStatement preparedStatement = connection.
                            prepareStatement("select * from site where url=?");
            preparedStatement.setString(1, url);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                site.setId(rs.getInt("id"));
                site.setUrl(rs.getString("url"));
                site.setRank(rs.getInt("rank"));
                site.setIsMarfeelizable(rs.getBoolean("isMarfeelizable"));
                site.setCheckedDate(rs.getString("checkedDate"));
            } 
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return site;
    }
    
    public List<Site> getSiteByIsMarfeelizable(boolean isMarfeelizable) {
        List<Site> sites = new ArrayList<Site>();
        try {
            PreparedStatement preparedStatement = connection.
                            prepareStatement("select * from site where isMarfeelizable=?");
            preparedStatement.setString(1, isMarfeelizable ? "1": "0");
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Site site = new Site();
                site.setId(rs.getInt("id"));
                site.setUrl(rs.getString("url"));
                site.setRank(rs.getInt("rank"));
                site.setIsMarfeelizable(rs.getBoolean("isMarfeelizable"));
                try {
                    //TODO get date
                    site.setCheckedDate(rs.getString("checkedDate"));
                } catch(SQLException e){
                    site.setCheckedDate("NULL");
                }
                sites.add(site);
            } 
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return sites;
    }

}