/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Fishie
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Article {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    
    @XmlElement(name = "id")
    private int id = -1;
    
    @XmlElement(name = "title")
    private String title;
    
    @XmlElement(name = "body")
    private String body;
    
    @XmlJavaTypeAdapter(DateXMLAdapter.class)
    @XmlElement(name = "pubDate")
    private LocalDate publishedDate;
    
    @XmlElement(name = "image") 
    private String imagePath;

    @XmlElementWrapper
    @XmlElement(name = "category")
    private List<Category> categories;
    
    
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
    
    public void addCategory(Category category){
        this.categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    

    
    
    public Article() {
        this.id = -1;
        this.title = "";
        this.body = "";
        this.publishedDate = LocalDate.now();
        this.imagePath = "";
        this.categories = new ArrayList<>();
    }
    
    
    
    

    public Article(int idArticle, String title, String body, LocalDate publishedDate, String imagePath) {
        this.id = idArticle;
        this.title = title;
        this.body = body;
        this.publishedDate = publishedDate;
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return String.valueOf(id)+": "+title;
    }
    
    
    
    
    
}
