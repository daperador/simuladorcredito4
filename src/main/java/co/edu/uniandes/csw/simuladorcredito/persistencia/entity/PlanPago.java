/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito.persistencia.entity;


import java.util.Date;
import java.util.List;

/**
 *
 * @author Fredy
 */

public class PlanPago extends SuperPojo{

    private Long id;
    private Date fechaCreacion;
    private String documento;
    private Integer valor;
    private Integer plazo;
    private String estado;
    private Double nivelRiesgo;
    private Date fechaNacimiento;
    private Date fechaModificacion;
    private Linea linea;
    private List<Cuota> cuotas;

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(Double nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea Linea) {
        this.linea = Linea;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    
}
