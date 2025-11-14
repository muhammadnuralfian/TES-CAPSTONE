/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import javax.swing.SwingUtilities;
import view.MenuUtamaForm;
/**
 *
 * @author aliya
 */
public class MenuUtama {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuUtamaForm().setVisible(true);
        });
    }
}