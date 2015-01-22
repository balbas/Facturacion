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

/**
 *
 * @author jose
 */
public class ConnectionBBDD {
    private final String url = "C:\\Users\\jmbalbas\\Documents\\NetBeansProjects\\Facturacion\\facturacion.db"; // Ruta a la base de datos
    private Connection connect;

    public void connect() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (connect != null) {
                connect.setAutoCommit(false);
                System.out.println("Opened database successfully \n");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos \n" + ex.getMessage());
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
}
