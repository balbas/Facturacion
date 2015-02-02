/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.controller.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class EncryptString {
    public EncryptString(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            this.md5 = sb.toString();
        } catch (java.security.NoSuchAlgorithmException ex) {
            Logger.getLogger(EncryptString.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getMd5() {
        return this.md5;
    }
    
    private String md5 = null;
}
