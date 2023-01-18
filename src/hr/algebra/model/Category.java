/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Fishie
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Category {
    
    //FIXME: Figure out the ID clash
    private int id;
    
    @XmlElement(name = "category")
    private String name;

    
    public int getId() {
        return id;
    }

    public void setId(int idCategory) {
        this.id = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
}
