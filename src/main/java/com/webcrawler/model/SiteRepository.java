package com.webcrawler.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {
    Site save(Site site);
    List<Site> findAll();
    Optional<Site> findByUrl(String url);
    List<Site> findByIsMarfeelizable(boolean isMarfeelizable);

}
