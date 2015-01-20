/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.frontend.views;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.eclipse.swt.*;

/**
 *
 * @author jmbalbas
 */
public class WebMenu extends JPanel {
    public WebMenu() {
        NativeInterface.open();
        
        JFrame frame = new JFrame("Facturación v1.0");	
        frame.setLayout(new BorderLayout());
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                NativeInterface.close();
                e.getWindow().dispose();
            }
        });
        
        // Web Browser
        JPanel webBrowserPanel = new JPanel(new BorderLayout());
        final JWebBrowser webBrowser = new JWebBrowser();
        webBrowser.navigate("file:/C:/Users/jmbalbas/Documents/NetBeansProjects/Facturacion/facturacion_web/index.html");
        webBrowser.setBarsVisible(false);
        webBrowser.setButtonBarVisible(false);
        webBrowser.setStatusBarVisible(true);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        
        frame.add(webBrowserPanel, BorderLayout.CENTER);
        
        //frame.getContentPane().add(webBrowser, BorderLayout.CENTER);
        frame.setSize(1280, 720);
        new Login(frame, true).setVisible(true);
        frame.setVisible(true);
        
        //NativeInterface.close();
    }
}
