/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.model.bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmb.facturacion.view.screens.menu.billing.CheckInvoices;

/**
 *
 * @author Jose
 */
public class QueryManager {
    public QueryManager(String query) {
        try {
            // Preparamos la conexión
            this.connection = new ConnectionBBDD();
            this.connection.connect();
            this.statement = this.connection.getConnection().createStatement();
           
            // Según el tipo de query
            switch (query.substring(0, 6)) {
                case "select":
                    this.resultset = this.statement.executeQuery(query);
                    int i = 0;            
                    while (this.resultset.next()) {
                        values[i] = this.resultset.getObject(i+1);
                        i++;
                    }
                
                case "update":
                    System.out.println(query);
                    this.statement.executeUpdate(query);
                    
                case "insert":
                    
                case "delete":
                    
            }                
        } catch (SQLException ex) {
            Logger.getLogger(CheckInvoices.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (this.statement != null) this.statement.close();
                if (this.resultset != null) this.resultset.close();
                if (this.connection != null) this.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(QueryManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Object[] getValues() {
        return this.values;
    }
    
    public Object getValues(Integer index) {
        return this.values[index];
    }
    
    private ConnectionBBDD connection;
    private Statement statement;
    private ResultSet resultset;
    private Object[] values;
}
