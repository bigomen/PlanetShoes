/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luxfacta.planetshoes.api.exception;

/**
 *
 * @author rcerqueira
 */
public class BusinessSecurityException extends Exception {

    public BusinessSecurityException(String message) {
        super(message);
    }
    
    public BusinessSecurityException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
