/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.backend.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmb.facturacion.backend.utils.PropertiesFile;

/**
 *
 * @author jose
 */
public class ConnectionBBDD {
    public ConnectionBBDD() {
        this.url = PropertiesFile.getInstance().getProperty(PropertiesFile.BBDD_URL);
        this.user = PropertiesFile.getInstance().getProperty(PropertiesFile.BBDD_USER);
        this.password = PropertiesFile.getInstance().getProperty(PropertiesFile.BBDD_PASSWORD);
    }

    public void connect() {
        try {
            this.connect = DriverManager.getConnection("jdbc:" + PropertiesFile.getInstance().getProperty(PropertiesFile.BBDD_TYPE).toLowerCase() + ":" + this.url, this.user, this.password);
            if (connect != null) {
                connect.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection() {
        return connect;
    }
     
    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private final String url;
    private final String user;
    private final String password;
    private Connection connect;
}
