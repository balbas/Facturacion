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
public class Sqlite {
    private final String url = ""; // 
    private Connection connect;

    public void connect() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
            if (connect != null) {
                System.out.println("Conectado");
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
            Logger.getLogger(Sqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
