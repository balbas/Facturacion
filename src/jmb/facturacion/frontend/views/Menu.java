/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.frontend.views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import jmb.facturacion.backend.utils.WebViewer;

/**
 *
 * @author jmbalbas
 */
public class Menu { 
    // Contructor
    public Menu() {
        JFrame menu = new JFrame();
        this.createAll(menu.getContentPane());
        menu.pack();
        new Login(menu, true).setVisible(true);
        menu.setVisible(true);
        menu.setSize(1280, 720);
        //menu.setLocationRelativeTo(null);
    }

    private void createAll(Container container) {
        createNavigation();
        createWebViewer();
        addAllToPanel(container);
        jButtonGo.addActionListener(goTo);
        jTextFieldUrl.addActionListener(goTo);
    }
    
    /**
     * Listener para el JTextField y el JButton.
     * Pulsando ENTER en el JTextField o pulsando el JButton, se pasa la página
     * que contiene el JTextField al visor web.
     */
    private final ActionListener goTo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                webViewerHtml.setPage(jTextFieldUrl.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };

    /**
     * Instancia el WebViewer y le pone la página inicial.
     */
    private void createWebViewer() {
        webViewerHtml = new WebViewer();
        try {
            webViewerHtml.setPage("http://www.google.com");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Mete el panel de control y el visor de html con su correspondiente scroll
     * en el contenedor que se le pasa.
     * @param contenedor El contenedor donde se quiere construir todo.
     */
    private void addAllToPanel(Container contenedor) {
        jScrollPaneWeb = new JScrollPane();
        jPanelWeb = new JPanel(new BorderLayout());
        jPanelWeb.add(jPanelNavigation, BorderLayout.NORTH);
        jScrollPaneWeb.setViewportView(webViewerHtml);
        jPanelWeb.add(jScrollPaneWeb, BorderLayout.CENTER);
        contenedor.add(jPanelWeb);
    }

    /**
     * Crea el panel con el JTextField y el JButton
     */
    private void createNavigation() {
        jPanelNavigation = new JPanel(new FlowLayout());
        jTextFieldUrl = new JTextField(50);
        jButtonGo = new JButton("Ir");
        jPanelNavigation.add(jTextFieldUrl);
        jPanelNavigation.add(jButtonGo);
    }
    
    // Variables
    private JTextField jTextFieldUrl;   // JTextField para indicar la url
    private WebViewer webViewerHtml;    // Componente en el que se ve la página web
    private JPanel jPanelNavigation;    // Panel que contiene el JTextField y el JButton
    private JButton jButtonGo;          // Botón para ir a la página web
    private JScrollPane jScrollPaneWeb; // Scroll para el visor web
    private JPanel jPanelWeb;           // Panel que lo contiene todo
}
