/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmb.facturacion.backend.utils;

/**
 *
 * @author jose
 */
public class EncryptToMd5 {
    public EncryptToMd5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            
            //sb = null;
            
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            this.md5 = sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {}
    }
    
    public String getMd5() {
        return this.md5;
    }
    
    private String md5 = null;
}
