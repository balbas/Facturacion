/*
 * To change this license header, choose License Headers in Project Session.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.backend.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmb.facturacion.backend.bbdd.ConnectionBBDD;

/**
 *
 * @author jose
 */
public class Session {
    public Session() {
        CONNECTION = new ConnectionBBDD();
        STATEMENT = null;
        RESULTSET = null;
        ID_SESSION = 0;
        COMPANY = 0;
        DATE = "";
        HOUR = "";
    }
    
    public void register(Integer company, String date, String hour) {
        COMPANY = company;
        DATE = date;
        HOUR = hour;
        
        try {
            STATEMENT = CONNECTION.getConnection().createStatement();
            STATEMENT.executeUpdate("INSERT INTO sesiones VALUES(NULL, " + COMPANY + ", '" + DATE + "', '" + HOUR + "');");
            CONNECTION.getConnection().commit();
            RESULTSET = STATEMENT.executeQuery("SELECT MAX(id) FROM sesiones;");
            if (RESULTSET.next()) {
                ID_SESSION = RESULTSET.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
    }
    
    public void close() {
        try {
            if (STATEMENT != null) STATEMENT.close();
            if (RESULTSET != null) RESULTSET.close();
            CONNECTION.close();
        } catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Integer getIdSession() {
        return ID_SESSION;
    }
    
    public Integer getCompany() {
        return COMPANY;
    }
    
    // Variables de conexión
    public ConnectionBBDD CONNECTION;
    public Statement STATEMENT;
    public ResultSet RESULTSET;
    // Variables de inicio de sesión
    public Integer ID_SESSION;
    public Integer COMPANY;
    public String DATE;
    public String HOUR;
}
