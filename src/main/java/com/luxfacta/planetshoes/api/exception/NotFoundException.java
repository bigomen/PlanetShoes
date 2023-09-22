/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luxfacta.planetshoes.api.exception;

/**
 *
 * @author rcerqueira
 */
public class NotFoundException extends Exception {
    
    public NotFoundException() {
        super("ID nao encontrado.");
    }
    
}
