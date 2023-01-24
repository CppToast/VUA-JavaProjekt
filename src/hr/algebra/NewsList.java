/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.model.ArticleHolder;
import cfg.Properties;
import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Article;
import hr.algebra.model.ArticleTableModel;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.JAXBUtils;
import hr.algebra.utils.MessageUtils;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fishie
 */
public class NewsList extends javax.swing.JFrame {

    private ArticleTableModel tableModel;
    
    private Repository repository;
    
    private void initRepository() {
        try {
            repository = RepositoryFactory.getRepository();
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            if (Properties.DEBUG == 1) MessageUtils.showErrorMessage("Error", ex.getLocalizedMessage());
        }
    }
    
    private void initTable() {
        try {
            tableModel = new ArticleTableModel(repository.getAllArticles());
            tblArticleList.setModel(tableModel);
        } catch (Exception ex) {
            Logger.getLogger(NewsList.class.getName()).log(Level.SEVERE, null, ex);
            if (Properties.DEBUG == 1) MessageUtils.showErrorMessage("Error", ex.getLocalizedMessage());
        }
    }
    
    private void updateModel() {
        try {
            tableModel.setArticles(this.repository.getAllArticles());
        } catch (Exception ex) {
            Logger.getLogger(NewsList.class.getName()).log(Level.SEVERE, null, ex);
            if (Properties.DEBUG == 1) MessageUtils.showErrorMessage("Error", ex.getLocalizedMessage());
        }
    }
    
    /**
     * Creates new form NewsList
     */
    public NewsList() {
        initComponents();
    }
    
    public NewsList(String username) {
        initComponents();
        lblUsername.setText(username);
        
        initRepository();
        initTable();
        updateModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblArticleList = new javax.swing.JTable();
        btnExportXML = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCategories = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RetroGameNews Manager");
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));

        jScrollPane1.setVerifyInputWhenFocusTarget(false);

        tblArticleList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblArticleList.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        tblArticleList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblArticleList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArticleListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblArticleList);

        btnExportXML.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/export_xml.png"))); // NOI18N
        btnExportXML.setPreferredSize(new java.awt.Dimension(64, 64));
        btnExportXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportXMLActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/remove.png"))); // NOI18N
        btnDelete.setToolTipText("");
        btnDelete.setPreferredSize(new java.awt.Dimension(64, 64));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/edit.png"))); // NOI18N
        btnEdit.setPreferredSize(new java.awt.Dimension(64, 64));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/add.png"))); // NOI18N
        btnAdd.setToolTipText("");
        btnAdd.setPreferredSize(new java.awt.Dimension(64, 64));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/log_out.png"))); // NOI18N
        btnLogOut.setToolTipText("");
        btnLogOut.setPreferredSize(new java.awt.Dimension(64, 64));
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        jLabel1.setText("Logged in as:");

        lblUsername.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblUsername.setText("<user>");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/banner.png"))); // NOI18N

        btnCategories.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/categories.png"))); // NOI18N
        btnCategories.setPreferredSize(new java.awt.Dimension(64, 64));
        btnCategories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExportXML, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblUsername)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsername)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportXML, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportXMLActionPerformed
        File file = FileUtils.uploadFile("XML File", "xml");        
        if(file == null) return;
                 
        try {
            List<Article> articles = repository.getAllArticles();
            for (Article article : articles) {
                article.setCategories(repository.getAllCategoriesOfArticle(article.getId()));
            }
            
            ArticleHolder articleHolder = new ArticleHolder(articles);
            JAXBUtils.save(articleHolder, file.getAbsolutePath());
        } catch (Exception ex) {
            Logger.getLogger(NewsList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExportXMLActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        
        if(tblArticleList.getSelectedRow() < 0) {
            MessageUtils.showErrorMessage("Error", "No article selected.");
            return;
        }
        
        int choice = MessageUtils.showConfirmDialog("Confirm", "Do you really want to delete this article?");
        if(choice != JOptionPane.YES_OPTION) return;
        
        int id = (int)tblArticleList.getValueAt(tblArticleList.getSelectedRow(), 0);
        try {
            repository.removeArticleCategories(id);
            repository.deleteArticle(id);
            updateModel();
        } catch (Exception ex) {
            Logger.getLogger(NewsList.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to delete article.");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        
        if(tblArticleList.getSelectedRow() < 0) {
            MessageUtils.showErrorMessage("Error", "No article selected.");
            return;
        }
        int id = (int)tblArticleList.getValueAt(tblArticleList.getSelectedRow(), 0);
        ArticleEditor window = new ArticleEditor(this, true, id);
        window.setVisible(true);
        updateModel();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        ArticleEditor window = new ArticleEditor(this, true, -1);
        window.setVisible(true);
        updateModel();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        this.dispose();
        Login window = new Login();
        window.setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void tblArticleListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArticleListMouseClicked
        if (evt.getClickCount() == 2){
            ArticleViewer window = new ArticleViewer((int)tblArticleList.getValueAt(tblArticleList.getSelectedRow(), 0));
            window.setVisible(true);
        }
    }//GEN-LAST:event_tblArticleListMouseClicked

    private void btnCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriesActionPerformed
        CategoryEditor window = new CategoryEditor(this, true);
        window.setVisible(true);
    }//GEN-LAST:event_btnCategoriesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (Properties.THEME.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewsList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCategories;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExportXML;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable tblArticleList;
    // End of variables declaration//GEN-END:variables

    
}
