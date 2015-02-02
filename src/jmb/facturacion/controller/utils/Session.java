/*
 * To change this license header, choose License Headers in Project Session.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.controller.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmb.facturacion.model.bbdd.ConnectionBBDD;

/**
 *
 * @author jose
 */
public class Session {
    public Session() {
        this.CONNECTION = new ConnectionBBDD();
        this.STATEMENT = null;
        this.RESULTSET = null;
        this.ID_SESSION = 0;
        this.COMPANY = 0;
        this.DATE = "";
        this.HOUR = "";
    }
    
    public void register(Integer company, String date, String hour) {
        this.COMPANY = company;
        this.DATE = date;
        this.HOUR = hour;
        
        try {
            this.STATEMENT = this.CONNECTION.getConnection().createStatement();
            this.STATEMENT.executeUpdate("INSERT INTO sesiones VALUES(NULL, " + this.COMPANY + ", '" + this.DATE + "', '" + this.HOUR + "');");
            this.CONNECTION.getConnection().commit();
            this.RESULTSET = this.STATEMENT.executeQuery("SELECT MAX(id) FROM sesiones;");
            if (this.RESULTSET.next()) {
                this.ID_SESSION = this.RESULTSET.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close();
        }
    }
    
    public void close() {
        try {
            if (this.STATEMENT != null) this.STATEMENT.close();
            if (RESULTSET != null) this.RESULTSET.close();
            this.CONNECTION.close();
        } catch (SQLException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Integer getIdSession() {
        return this.ID_SESSION;
    }
    
    public Integer getCompany() {
        return this.COMPANY;
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
