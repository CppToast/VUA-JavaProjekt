/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import javax.swing.JOptionPane;

/**
 *
 * @author dnlbe
 */
public class MessageUtils {

    public static void showInformationMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, "<html><body><p style='width: 300px;'>"+message+"</p></body></html>", title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, "<html><body><p style='width: 300px;'>"+message+"</p></body></html>", title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static int showConfirmDialog(String title, String message) {
        return JOptionPane.showConfirmDialog(null, "<html><body><p style='width: 300px;'>"+message+"</p></body></html>", title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

}
