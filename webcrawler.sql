CREATE DATABASE webcrawler;

USE webcrawler;

CREATE TABLE sites(url varchar(45) NOT NULL, rank int(11) DEFAULT NULL, isMarfeelizable BOOLEAN DEFAULT 0, checkedDate DATETIME DEFAULT NULL, PRIMARY KEY (url) );