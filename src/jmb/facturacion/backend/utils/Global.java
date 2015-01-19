/*
 * To change this license header, choose License Headers in Project Global.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.backend.utils;

/**
 *
 * @author jose
 */
public class Global {
    public Global() {}
    
    public void setCorporate(String corporate) {
        this.CORPORATE = corporate;
    }
    
    public void setDate(String date) {
        this.DATE = date;
    }
    
    public void setHour(String hour) {
        this.HOUR = hour;
    }
    
    public String getCorporate() {
        return this.CORPORATE;
    }
    
    public String getDate() {
        return this.DATE;
    }
    
    public String getHour() {
        return this.HOUR;
    }
    
    public String CORPORATE = null;
    public String DATE = null;
    public String HOUR = null;
}
