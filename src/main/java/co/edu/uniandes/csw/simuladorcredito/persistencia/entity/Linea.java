/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito.persistencia.entity;


/**
 *
 * @author Fredy
 */
public class Linea extends SuperPojo{

    private Long id;
    private String nombre;
    private Double tasa;
    private Long administrador;

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTasa() {
        return tasa;
    }

    public void setTasa(Double tasa) {
        this.tasa = tasa;
    }

    public Long getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Long administrador) {
        this.administrador = administrador;
    }
    
    
}
