/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Article;
import hr.algebra.model.Category;
import hr.algebra.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Fishie
 */
public interface Repository {
    
    /* DATABASE */
    void deleteDatabase() throws Exception;
    
    
    /* USERS */
    int authenticateAsUser(User user) throws Exception;
    int authenticateAsAdmin(User user) throws Exception;
    int createUser(User user) throws Exception;
    
    
    /* CATEGORIES */
    List<Category> getAllCategories() throws Exception;
    Optional<Category> getCategory(int id) throws Exception;
    List<Category> getCategoriesByName(String name) throws Exception;
    int createCategory(Category category) throws Exception;
    void updateCategory(Category category) throws Exception;
    void deleteCategory(int id) throws Exception;
    
    List<Category> getAllCategoriesOfArticle(int id) throws Exception;
    
    
    /* ARTICLES */
    List<Article> getAllArticles() throws Exception;
    Optional<Article> getArticle(int id) throws Exception;
    int createArticle(Article article) throws Exception;
    void updateArticle(Article article) throws Exception;
    void deleteArticle(int id) throws Exception;
    
    void removeArticleCategories(int id) throws Exception;
    void assignCategoryToArticle(int id, Category category) throws Exception;
    
}
