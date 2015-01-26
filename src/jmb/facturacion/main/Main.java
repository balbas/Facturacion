/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.main;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import jmb.facturacion.frontend.views.WebMenu;

/**
 *
 * @author jose
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {        
        // Abrimos interface nativa
        NativeInterface.open();
        
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                WebMenu webMenu = new WebMenu();
            }
        });
        
        NativeInterface.runEventPump();
    }
}
