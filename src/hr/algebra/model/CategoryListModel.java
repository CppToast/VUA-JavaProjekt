/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Fishie
 */
public class CategoryListModel extends AbstractListModel {

    public List<Category> categories;

    public CategoryListModel() {
        this.categories = Collections.<Category>emptyList();
    }

    public CategoryListModel(List<Category> categories) {
        this.categories = categories;
    }
    
    public void setCategories(List<Category> categories){
        this.categories = categories;
        super.fireContentsChanged(this, 0, getSize()-1);
    }
    
    @Override
    public int getSize() {
        return categories.size();
    }

    @Override
    public String getElementAt(int index) {
        return categories.get(index).toString();
    }
    
    public Category getCategoryAt(int index) {
        return categories.get(index);
    }
}
