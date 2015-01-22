/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.frontend.views;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jmb.facturacion.backend.utils.Parameters;

/**
 *
 * @author jmbalbas
 */
public class WebMenu extends JFrame implements ActionListener {
    public WebMenu() {
        // Abrimos interface nativa
        NativeInterface.open();
        
        // Cargamos los parámetros de la aplicación
        Parameters parameters = new Parameters();
        
        // Iniciamos la ventana principal
        jFrameMenu = new JFrame("Facturación v" + parameters.getVersion());

        // Lanzamos el login
        login = new Login(this, true, parameters.getVersion());
        login.setVisible(true);
        
        // Comprobamos si hay sesión
        if (login.returnIdSession() != 0) {            
            // Establecemos el layout
            jFrameMenu.setLayout(new BorderLayout());         

            // Web Browser
            jPanelMenu = new JPanel(new BorderLayout());
            jWebBrowserMenu = new JWebBrowser();
            jWebBrowserMenu.navigate(parameters.getRutaIndex());
            jWebBrowserMenu.setBarsVisible(false);
            jWebBrowserMenu.setButtonBarVisible(false);
            jWebBrowserMenu.setStatusBarVisible(true);
            jPanelMenu.add(jWebBrowserMenu, BorderLayout.CENTER);
            // Añadimos listener para capturar los enlaces
            jWebBrowserMenu.addWebBrowserListener(new WebBrowserAdapter() {     
                @Override
                public void locationChanging(WebBrowserNavigationEvent e) {
                    if (e.getNewResourceLocation().startsWith("quiter")) {
                        System.out.println(e.getNewResourceLocation());
                        e.consume();
                    }
                }
            });
            jFrameMenu.add(jPanelMenu);           
            
            // Menu
            jMenuBarMenu = new JMenuBar();
            jFrameMenu.setJMenuBar(jMenuBarMenu);
            jMenuAplicacion = new JMenu("Aplicación");
            jMenuBarMenu.add(jMenuAplicacion);
            jMenuItemDesconectar=new JMenuItem("Desconectar");
            jMenuItemDesconectar.addActionListener(this);
            jMenuAplicacion.add(jMenuItemDesconectar);
            jMenuAplicacion.addSeparator();
            jMenuItemSalir = new JMenuItem("Salir");
            jMenuItemSalir.addActionListener(this);
            jMenuAplicacion.add(jMenuItemSalir);
            
            jFrameMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (JOptionPane.showConfirmDialog(rootPane, "¿Desea cerrar del programa?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        NativeInterface.close();
                        System.exit(0);
                    }
                }
            });
            
            jFrameMenu.setSize(1280, 720);
            jFrameMenu.setVisible(true);
        } else {
            NativeInterface.close();
            System.exit(0);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jMenuItemDesconectar) {
            
        }
        if (e.getSource() == jMenuItemSalir) {
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea cerrar del programa?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                NativeInterface.close();
                System.exit(0);
            }
        }
    }
    
    private final Login login;
    private final JFrame jFrameMenu;
    private JPanel jPanelMenu;
    private JWebBrowser jWebBrowserMenu;
    private JMenuBar jMenuBarMenu;
    private JMenu jMenuAplicacion;
    private JMenuItem jMenuItemDesconectar;
    private JMenuItem jMenuItemSalir;
}
