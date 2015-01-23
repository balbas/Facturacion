/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.main;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import java.awt.EventQueue;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import jmb.facturacion.frontend.views.WebMenu;

/**
 *
 * @author jose
 */
public class Main {
    public static String getArchFilename(String prefix) { 
        return prefix + "-" + getOSName() + "-" + getArchName() + ".jar"; 
    } 

    private static String getOSName() { 
        String osNameProperty = System.getProperty("os.name"); 

        if (osNameProperty == null) { 
            throw new RuntimeException("os.name property is not set"); 
        } else {
            osNameProperty = osNameProperty.toLowerCase(); 
        } 

        if (osNameProperty.contains("win")) {
            return "win";
        } else if (osNameProperty.contains("mac")) {
            return "mac";
        } else if (osNameProperty.contains("linux") || osNameProperty.contains("nix")) {
            return "linux";
        } else {
            throw new RuntimeException("Unknown OS name: " + osNameProperty); 
        } 
    } 
    
    private static String getArchName() { 
        String osArch = System.getProperty("os.arch"); 

        if (osArch != null && osArch.contains("64")) { 
            return "64"; 
        } else {
            return "32"; 
        } 
    }
    
    private static void addJarToClasspath(File jarFile) { 
        try { 
            URL url = jarFile.toURI().toURL(); 
            URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader(); 
            Class<?> urlClass = URLClassLoader.class; 
            Method method = urlClass.getDeclaredMethod("addURL", new Class<?>[] { URL.class }); 
            method.setAccessible(true);         
            method.invoke(urlClassLoader, new Object[] { url });             
        } catch (MalformedURLException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        File swtJar = new File(getArchFilename("lib/swt")); 
        addJarToClasspath(swtJar);
        
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
