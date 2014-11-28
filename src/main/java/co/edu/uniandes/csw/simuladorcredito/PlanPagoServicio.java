/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import co.edu.uniandes.csw.simuladorcredito.dao.LineaDAO;
import co.edu.uniandes.csw.simuladorcredito.dao.PlanPagoDAO;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Linea;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.PlanPago;
import co.edu.uniandes.csw.simuladorcredito.utils.ColaUtils;
import co.edu.uniandes.csw.simuladorcredito.utils.RegistroException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

/**
 *
 * @author Daniel
 */
@Path("planPago")
public class PlanPagoServicio {

    @Context 
    private HttpServletRequest request;    
    
    @POST
    public PlanPago insertarPlanPagos(PlanPago planPago){
        try {
            planPago.setEstado("En proceso");
            PlanPago plan=(PlanPago)new PlanPagoDAO().insertar(planPago);
            //insertar en la cola
            ColaUtils.addMensaje(""+plan.getId());
            return plan;
        } catch (Exception ex) {
            Logger.getLogger(PlanPagoServicio.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegistroException("Error al guardar el plan de pagos");
        }
    }
    
    /*@Path("/planesPago")
    @GET
    public PaginatedScanList getPlanesPago(){
        return new PlanPagoDAO().leer(PlanPago.class);
    }*/
    
    @Path("/planesPago/{desde}/{cuantos}")
    @GET
    public List<PlanPago> getPlanesPago(@PathParam("desde") Integer desde, @PathParam("cuantos") Integer cuantos){
        return new PlanPagoDAO().leerPagina(desde, cuantos);
    }
    
    @Path("/planPago/{id}")
    @GET
    public PlanPago getPlan(@PathParam("id") Long id){
        return new PlanPagoDAO().leer(id);
    }  
    
    @Path("/cuotas/{id}")
    @GET
    public PlanPago getCuotas(@PathParam("id") Long id){
        return new PlanPagoDAO().leer(id);
    } 
    
}
