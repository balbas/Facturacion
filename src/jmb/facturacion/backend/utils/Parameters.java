/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.backend.utils;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmbalbas
 */
public class Parameters {
    public Parameters() {
        // Inicializamos las variables
        VERSION = "";
        RUTA_INDEX = "";
        RUTA_ARCHIVOS = "";
        GENERAR_LOG = false;
        session = new Session();
        
        // Lanzamos selección
        try {
            session.CONNECTION.connect();
            session.STATEMENT = session.CONNECTION.getConnection().createStatement();
            session.RESULTSET = session.STATEMENT.executeQuery("SELECT * FROM parametros;");
            if (session.RESULTSET.next()) {
                VERSION = session.RESULTSET.getString(2);
                RUTA_INDEX = session.RESULTSET.getString(3);
                RUTA_ARCHIVOS = session.RESULTSET.getString(4);
                if (session.RESULTSET.getInt(5) == 1) GENERAR_LOG = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Parameters.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
        } 
    }
    
    public String getVersion() {
        return VERSION;
    }
    
    public String getRutaIndex() {
        return RUTA_INDEX;
    }
    
    public String getRutaArchivos() {
        return RUTA_ARCHIVOS;
    }
    
    public boolean getGenerarLog() {
        return GENERAR_LOG;
    }
    
    private Session session;
    // Variables para almacenar los parámetros
    private String VERSION;
    private String RUTA_INDEX;
    private String RUTA_ARCHIVOS;
    private boolean GENERAR_LOG;
}
