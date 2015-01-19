/*
 * To change this license header, choose License Headers in Project Session.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.backend.utils;

/**
 *
 * @author jose
 */
public class Session {
    public Session(String company, String date, String hour) {
        this.COMPANY = company;
        this.DATE = date;
        this.HOUR = hour;
    }
    
    public void sessionRegister() {
        
    }
    
    public String getCompany() {
        return this.COMPANY;
    }
    
    public String getDate() {
        return this.DATE;
    }
    
    public String getHour() {
        return this.HOUR;
    }
    
    public String COMPANY = null;
    public String DATE = null;
    public String HOUR = null;
}
