/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Fishie
 */
public class DateXMLAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, Article.DATE_FORMAT);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.format(Article.DATE_FORMAT);
    }
    
}
