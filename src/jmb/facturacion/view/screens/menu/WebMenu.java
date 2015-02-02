/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.view.screens.menu;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jmb.facturacion.controller.utils.Parameters;
import jmb.facturacion.model.bbdd.QueryManager;

/**
 *
 * @author jmbalbas
 */
public class WebMenu extends JFrame implements ActionListener {
    public WebMenu() {      
        // Iniciamos la ventana principal
        this.jFrameMenu = new JFrame();

        // Lanzamos el login
        this.login = new Login(this, true);
        this.login.setVisible(true);
        
        // Comprobamos si hay sesión
        if (this.login.returnIdSession() != 0) {
            // Cargamos los parámetros de la aplicación
            this.parameters = new Parameters();
            
            // Título de la aplicación
            this.jFrameMenu.setTitle("Facturación " + parameters.getVersion());
            
            // Establecemos el layout
            this.jFrameMenu.setLayout(new BorderLayout());
            
            // Variable para el manejo de las vistas
            viewByType = new HashMap<>();

            // Web Browser
            this.jPanelMenu = new JPanel(new BorderLayout());
            this.jWebBrowserMenu = new JWebBrowser();
            this.jWebBrowserMenu.navigate(this.parameters.getRutaIndex());
            this.jWebBrowserMenu.setBarsVisible(false);
            this.jWebBrowserMenu.setButtonBarVisible(false);
            this.jWebBrowserMenu.setStatusBarVisible(true);
            this.jPanelMenu.add(this.jWebBrowserMenu, BorderLayout.CENTER);
            // Añadimos listener para capturar los enlaces
            this.jWebBrowserMenu.addWebBrowserListener(new WebBrowserAdapter() {     
                @Override
                public void locationChanging(WebBrowserNavigationEvent e) {
                    if (e.getNewResourceLocation().startsWith("facturacion")) {
                        e.consume();                        
                        final String url = e.getNewResourceLocation().substring(14, e.getNewResourceLocation().length()-1);
                        JDialog view = viewByType.get(url);
                        
                        if (view == null) {
                            // Cargamos la vista una vez y la añadimos a nuestro Map
                            try {
                                Class clase = Class.forName(url);
                                view = (JDialog) clase.newInstance();
                                Method setViewVisibleMethod = clase.getMethod("setViewVisible", Integer.class);
                                setViewVisibleMethod.invoke(view, login.getCompanySession());
                                viewByType.put(url, view);
                                view.addWindowListener(new WindowAdapter() {
                                    @Override
                                    public void windowClosed(WindowEvent evt) {
                                        viewByType.remove(url);
                                    }
                                });
                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                                Logger.getLogger(WebMenu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            // La vista existe, por lo que la traemos al frente
                            view.toFront();
                        }
                    }
                }
            });
            this.jFrameMenu.add(this.jPanelMenu);
            
            // Menu
            this.jMenuBarMenu = new JMenuBar();
            this.jFrameMenu.setJMenuBar(this.jMenuBarMenu);
            this.jMenuAplicacion = new JMenu("Aplicación");
            this.jMenuBarMenu.add(this.jMenuAplicacion);
            this.jMenuItemDesconectar = new JMenuItem("Desconectar");
            this.jMenuItemDesconectar.addActionListener(this);
            this.jMenuAplicacion.add(this.jMenuItemDesconectar);
            this.jMenuItemParametros = new JMenuItem("Parámetros");
            this.jMenuItemParametros.addActionListener(this);
            this.jMenuAplicacion.add(this.jMenuItemParametros);
            this.jMenuAplicacion.addSeparator();
            this.jMenuItemSalir = new JMenuItem("Salir");
            this.jMenuItemSalir.addActionListener(this);
            this.jMenuAplicacion.add(this.jMenuItemSalir);
            
            this.jFrameMenu.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (JOptionPane.showConfirmDialog(rootPane, "¿Desea cerrar del programa?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        NativeInterface.close();
                        System.exit(0);
                    }
                }
            });
            
            this.jFrameMenu.setSize(1280, 720);
            this.jFrameMenu.setVisible(true);
        } else {
            NativeInterface.close();
            System.exit(0);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jMenuItemDesconectar) {
            this.login = new Login(this, true);
            this.login.setVisible(true);
            if (this.login.returnIdSession() != 0) {
                this.jWebBrowserMenu.navigate(this.parameters.getRutaIndex());
            } else {
                NativeInterface.close();
                System.exit(0);
            }
        }
        if (e.getSource() == this.jMenuItemParametros) {
            String ruta = JOptionPane.showInputDialog(getRootPane(), "Ruta index.html:", "Parámetros de la Aplicación", 1);
            if ((ruta != null) && (!ruta.isEmpty())) {
                new QueryManager("update parametros set ruta_index='" + ruta + "' where id=1;");
                this.jWebBrowserMenu.reloadPage();
            }
        }
        if (e.getSource() == this.jMenuItemSalir) {
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea cerrar del programa?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                NativeInterface.close();
                System.exit(0);
            }
        }
    }
    
    private Parameters parameters = null;
    private Login login;
    private final JFrame jFrameMenu;
    private Map<String, JDialog> viewByType;
    private JPanel jPanelMenu;
    private JWebBrowser jWebBrowserMenu;
    private JMenuBar jMenuBarMenu;
    private JMenu jMenuAplicacion;
    private JMenuItem jMenuItemDesconectar;
    private JMenuItem jMenuItemParametros;
    private JMenuItem jMenuItemSalir;
}
