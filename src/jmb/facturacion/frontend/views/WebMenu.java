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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
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
        // Cargamos los parámetros de la aplicación
        this.parameters = new Parameters();
        
        // Iniciamos la ventana principal
        this.jFrameMenu = new JFrame("Facturación v" + parameters.getVersion());

        // Lanzamos el login
        this.login = new Login(this, true, parameters.getVersion());
        this.login.setVisible(true);
        
        // Comprobamos si hay sesión
        if (this.login.returnIdSession() != 0) {
            // Establecemos el layout
            this.jFrameMenu.setLayout(new BorderLayout());         

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
                        String url = e.getNewResourceLocation().substring(14, e.getNewResourceLocation().length()-1);
                        System.out.println(url);
                        
                        try {
                            Class clase = Class.forName(url);
                            Constructor[] constructors = clase.getConstructors();
                            constructors[1].setAccessible(true);
                            Object object = constructors[1].newInstance(jFrameMenu, true);
                            
                        } catch (ClassNotFoundException | SecurityException | IllegalArgumentException ex) {
                            Logger.getLogger(WebMenu.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                            Logger.getLogger(WebMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            this.jFrameMenu.add(this.jPanelMenu);
            
            //******************************************************************
            // Recorremos los metodos
            System.out.println("\nLista de metodos:\n");
            Class clase = null;
            try {
                clase = Class.forName("jmb.facturacion.frontend.views.ParametersBBDD");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(WebMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            Constructor[] constructores = clase.getConstructors();
            for (int i=0; i < constructores.length; i++) {
                System.out.print("\t" + constructores[i].getName() + " (");
                
                Class[] params = constructores[i].getParameterTypes();
            if (params.length > 0)
            {
                for (int iPar = 0; iPar < params.length; iPar++)
                {
                    Field fields[] = params[iPar].getDeclaredFields();
                    System.out.println("param: "+params[iPar]);
                    for (int iFields = 0; iFields < fields.length; iFields++)
                    {
                        String fieldName = fields[i].getName();
                        System.out.println("field: "+fieldName);
                    }                                       
                }
            }
            }
            //******************************************************************
            
            // Menu
            this.jMenuBarMenu = new JMenuBar();
            this.jFrameMenu.setJMenuBar(this.jMenuBarMenu);
            this.jMenuAplicacion = new JMenu("Aplicación");
            this.jMenuBarMenu.add(this.jMenuAplicacion);
            this.jMenuItemDesconectar = new JMenuItem("Desconectar");
            this.jMenuItemDesconectar.addActionListener(this);
            this.jMenuAplicacion.add(this.jMenuItemDesconectar);
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
            this.login = new Login(this, true, parameters.getVersion());
            this.login.setVisible(true);
            if (this.login.returnIdSession() != 0) {
                this.jWebBrowserMenu.navigate(this.parameters.getRutaIndex());
            } else {
                NativeInterface.close();
                System.exit(0);
            }
        }
        if (e.getSource() == this.jMenuItemSalir) {
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea cerrar del programa?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                NativeInterface.close();
                System.exit(0);
            }
        }
    }
    
    private final Parameters parameters;
    private Login login;
    private final JFrame jFrameMenu;
    private JPanel jPanelMenu;
    private JWebBrowser jWebBrowserMenu;
    private JMenuBar jMenuBarMenu;
    private JMenu jMenuAplicacion;
    private JMenuItem jMenuItemDesconectar;
    private JMenuItem jMenuItemSalir;
}
