/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.controller.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmbalbas
 */
public class PropertiesFile {
    private PropertiesFile() {        
        this.properties = new Properties();
        try {
            this.properties.load(new FileInputStream(url));
        } catch (IOException ex) {
            Logger.getLogger(PropertiesFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Implementando Singleton
     * @return
     */
    public static PropertiesFile getInstance() {
        return PropertiesFilesHolder.INSTANCE;
    }
 
    private static class PropertiesFilesHolder { 
        private static final PropertiesFile INSTANCE = new PropertiesFile();
    }
 
    /**
     * Retorna la propiedad de parametrización solicitada
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
    
    /**
     * Nuevo valor a la propiedad de parametrización solicitada.
     * @param key
     * @param value
     */    
    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);        
        try {
             this.properties.store(new FileOutputStream(new File(url)), null);
        } catch(FileNotFoundException ex) {
            Logger.getLogger(PropertiesFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex) {
            Logger.getLogger(PropertiesFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Properties properties;
    public final static String PROPERTIES_FILE_NAME = "database.properties";
    public final static String BBDD_TYPE = "TipoBaseDatos";
    public final static String BBDD_URL = "UrlBaseDatos";
    public final static String BBDD_USER = "UsuarioBaseDatos";
    public final static String BBDD_PASSWORD = "ContraseñaBaseDatos";
    
// Ruta de acceso al fichero properties    
    private final static String url = System.getProperty("user.dir") + File.separator + PROPERTIES_FILE_NAME;
}
