package com.webcrawler.web.controller;

import com.webcrawler.model.Site;
import com.webcrawler.model.SiteRepository;
import java.nio.charset.Charset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.After;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/rest-test-servlet.xml"})
@WebAppConfiguration
public class SitesControllerTest {
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private SiteRepository siteRepository;
       

    
    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        siteRepository.deleteAllInBatch();
        siteRepository.deleteAll();
    }
    
    @After
    public void tearDown() {
        
        cleanDB();
    }
    

   @Test
    public void readSites() throws Exception {
        
        Site site = new Site("centrallecheraasturiana.es", 834987, false, "2015-10-15 13:57:09.0");
        siteRepository.save(site);
        
        mockMvc.perform(get("/sites"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].url", is(site.getUrl())))
                .andExpect(jsonPath("$[0].rank", is(site.getRank())))
                .andExpect(jsonPath("$[0].checkedDate", is(site.getCheckedDate())))
                .andExpect(jsonPath("$[0].isMarfeelizable", is(site.isIsMarfeelizable())));
        
        cleanDB();
    }
    
   @Test
    public void createSites() throws Exception {
        
       String sitesJson = "{ " +
                            "    \"sites\" : [" +
                            "         {" +
                            "            \"url\": \"centrallecheraasturiana.es\"," +
                            "            \"rank\": 834987" +
                            "        }," +
                            "        {" +
                            "            \"url\": \"guiafull.com\"," +
                            "            \"rank\": 571272" +
                            "        }," +
                            "        {" +
                            "            \"url\": \"leasing4business.co.uk\"," +
                            "            \"rank\": 435035" +
                            "        }" +
                            "    ]" +
                            "}";
        mockMvc.perform(post("/sites")
                .contentType(contentType)
                .content(sitesJson))
                .andExpect(status().isCreated());
        
        cleanDB();
    }
    
    @Test
    public void createSiteMarfeelizable() throws Exception {
        
       String sitesJson = "{ " +
                            "    \"sites\" : [" +
                            "         {" +
                            "            \"url\": \"healthplans.com\"," +
                            "            \"rank\": 834987" +
                            "        }" +
                            "    ]" +
                            "}";
        mockMvc.perform(post("/sites")
                .contentType(contentType)
                .content(sitesJson))
                .andExpect(status().isCreated());
        
        mockMvc.perform(get("/sites"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].isMarfeelizable", is(true)));
        
        cleanDB();
    }
    
    @Test
    public void createSiteNoMarfeelizable() throws Exception {
        
        String sitesJson = "{ " +
                            "    \"sites\" : [" +
                            "         {" +
                            "            \"url\": \"centrallecheraasturiana.es\"," +
                            "            \"rank\": 834987" +
                            "        }" +
                            "    ]" +
                            "}";
        mockMvc.perform(post("/sites")
                .contentType(contentType)
                .content(sitesJson))
                .andExpect(status().isCreated());
        
        mockMvc.perform(get("/sites"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].isMarfeelizable", is(false)));
        
        cleanDB();
    }
     
    private void cleanDB(){
        siteRepository.deleteAllInBatch();
        siteRepository.deleteAll();
    }
    
}
