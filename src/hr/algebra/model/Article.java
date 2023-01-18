/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Fishie
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Article {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    
    //FIXME: Figure out the ID clash
    private int id;
    
    @XmlElement(name = "title")
    private String title;
    
    @XmlElement(name = "atom:summary")
    private String body;
    
    @XmlElement(name = "pubDate")
    private LocalDate publishedDate;
    
    @XmlElement(name = "media:thumbnail") //FIXME: get 'url' attribute from inside this element
    private String imagePath;

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    
    
    

    public Article(int idArticle, String title, String body, LocalDate publishedDate, String imagePath) {
        this.id = idArticle;
        this.title = title;
        this.body = body;
        this.publishedDate = publishedDate;
        this.imagePath = imagePath;
    }
    
    
}
