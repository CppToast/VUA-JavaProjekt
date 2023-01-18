/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Article;
import hr.algebra.model.Category;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Fishie
 */
public class SqlRepository implements Repository {
    
    private static final String DATABASE_DELETE = "{ CALL deleteAllData }";
    
    /* USERS */
    
    /* unused:
    private static final String USER_ID = "IDUser";
    private static final String USER_USERNAME = "Username";
    private static final String USER_PASSWORD = "Pass";
    */
    
    private static final String USER_CREATE = "{ CALL createUser (?,?,?) }";
    private static final String USER_AUTHENTICATE_USER = "{ CALL authenticateAsUser (?,?,?) }";
    private static final String USER_AUTHENTICATE_ADMIN = "{ CALL authenticateAsAdmin (?,?,?) }";
    
    
    /* CATEGORIES */
    private static final String CATEGORY_ID = "IDCategory";
    private static final String CATEGORY_NAME = "Name";
    
    private static final String CATEGORY_CREATE = "{ CALL createCategory (?,?) }";
    private static final String CATEGORY_UPDATE = "{ CALL updateCategory (?,?) }";
    private static final String CATEGORY_DELETE = "{ CALL deleteCategory (?) }";
    private static final String CATEGORY_GET = "{ CALL selectCategory (?) }";
    private static final String CATEGORY_GET_ALL = "{ CALL selectAllCategories }";
    private static final String CATEGORY_SEARCH_BY_NAME = "{ CALL searchCategoriesByName (?) }";
    
    
    /* ARTICLES */
    private static final String ARTICLE_ID = "IDArticle";
    private static final String ARTICLE_TITLE = "Title";
    private static final String ARTICLE_BODY = "Body";
    private static final String ARTICLE_PUBLISHED_DATE = "PublishedDate";
    private static final String ARTICLE_IMAGE_PATH = "ImagePath";
    
    private static final String ARTICLE_CREATE = "{ CALL createArticle (?,?,?,?,?) }";
    private static final String ARTICLE_UPDATE = "{ CALL updateArticle (?,?,?,?,?) }";
    private static final String ARTICLE_DELETE = "{ CALL deleteArticle (?) }";
    private static final String ARTICLE_GET = "{ CALL selectArticle (?) }";
    private static final String ARTICLE_GET_ALL = "{ CALL selectAllArticles }";
    
    private static final String ARTICLE_GET_CATEGORIES = "{ CALL selectCategoryByArticle (?) }";
    private static final String ARTICLE_REMOVE_CATEGORIES = "{ CALL deleteAllAssignmentsOfArticle (?) }";
    private static final String ARTICLE_ASSIGN_CATEGORY = "{ CALL createCategoryAssignment (?,?,?) }";
    
    
    
    
    @Override
    public void deleteDatabase() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DATABASE_DELETE)) {
            stmt.executeUpdate();
        }
    }

    
    /* USERS */
    @Override
    public int authenticateAsUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(USER_AUTHENTICATE_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public int authenticateAsAdmin(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(USER_AUTHENTICATE_ADMIN)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public int createUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(USER_CREATE)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }
    

    
    /* CATEGORIES */
    @Override
    public List<Category> getAllCategories() throws Exception {
        List<Category> categories = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CATEGORY_GET_ALL);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt(CATEGORY_ID),
                        rs.getString(CATEGORY_NAME)
                ));
            }
            return categories;
        }
    }

    @Override
    public Optional<Category> getCategory(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(CATEGORY_GET)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Category(
                            rs.getInt(CATEGORY_ID),
                            rs.getString(CATEGORY_NAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }
    
    @Override
    public List<Category> getCategoriesByName(String name) throws Exception {
        List<Category> categories = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CATEGORY_SEARCH_BY_NAME)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(new Category(
                            rs.getInt(CATEGORY_ID),
                            rs.getString(CATEGORY_NAME)
                    ));
                }
            }
        }
        return categories;
    }

    @Override
    public int createCategory(Category category) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CATEGORY_CREATE)) {
            stmt.setString(1, category.getName());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(2);
        }
    }

    @Override
    public void updateCategory(Category category) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CATEGORY_UPDATE)) {
            stmt.setInt(1, category.getId());
            stmt.setString(2, category.getName());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCategory(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CATEGORY_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    
    
    @Override
    public List<Category> getAllCategoriesOfArticle(int id) throws Exception {
        List<Category> categories = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(ARTICLE_GET_CATEGORIES)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(new Category(
                            rs.getInt(CATEGORY_ID),
                            rs.getString(CATEGORY_NAME)
                    ));
                }
            }
        }
        return categories;
    }

    
    
    /* ARTICLES */
    @Override
    public List<Article> getAllArticles() throws Exception {
        List<Article> articles = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ARTICLE_GET_ALL);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                articles.add(new Article(
                        rs.getInt(ARTICLE_ID),
                        rs.getString(ARTICLE_TITLE),
                        rs.getString(ARTICLE_BODY),
                        LocalDate.parse(rs.getString(ARTICLE_PUBLISHED_DATE), Article.DATE_FORMAT),
                        rs.getString(ARTICLE_IMAGE_PATH)
                ));
            }
            return articles;
        }
    }

    @Override
    public Optional<Article> getArticle(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(ARTICLE_GET)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Article(
                            rs.getInt(ARTICLE_ID),
                            rs.getString(ARTICLE_TITLE),
                            rs.getString(ARTICLE_BODY),
                            LocalDate.parse(rs.getString(ARTICLE_PUBLISHED_DATE), Article.DATE_FORMAT),
                            rs.getString(ARTICLE_IMAGE_PATH)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public int createArticle(Article article) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ARTICLE_CREATE)) {
            stmt.setString(1, article.getTitle());
            stmt.setString(2, article.getBody());
            stmt.setString(3, article.getPublishedDate().format(Article.DATE_FORMAT));
            stmt.setString(4, article.getImagePath());
            stmt.registerOutParameter(5, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(5);
        }
    }

    @Override
    public void updateArticle(Article article) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ARTICLE_UPDATE)) {
            stmt.setInt(1, article.getId());
            stmt.setString(2, article.getTitle());
            stmt.setString(3, article.getBody());
            stmt.setString(4, article.getPublishedDate().format(Article.DATE_FORMAT));
            stmt.setString(5, article.getImagePath());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteArticle(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ARTICLE_DELETE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeArticleCategories(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ARTICLE_REMOVE_CATEGORIES)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void assignCategoryToArticle(int id, Category category) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ARTICLE_ASSIGN_CATEGORY)) {
            stmt.setInt(1, id);
            stmt.setInt(2, category.getId());
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.executeUpdate();
        }
    }
    
}
