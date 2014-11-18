/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito.utils;

import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Daniel
 */
public class RegistroException extends WebApplicationException{

    
    public RegistroException(String mensaje) {
        super(mensaje,520);
    }
    
    
    
}
