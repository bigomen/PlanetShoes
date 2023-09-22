/*
Copyright Luxfacta Solucoes de TI Ltda
 */
package com.luxfacta.planetshoes.api.message;

/**
 *
 * @author rcerqueira
 */
public interface IBaseMessage {
    
    public String getMessage();

    public String getStatus();

    public boolean getError();

}
