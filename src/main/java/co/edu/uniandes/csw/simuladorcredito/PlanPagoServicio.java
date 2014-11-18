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
import co.edu.uniandes.csw.simuladorcredito.utils.RegistroException;
import java.util.HashMap;
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
    }
    
    @Path("/planesPago/{cuantos}/{primero}")
    @GET
    public ScanResult getPlanesPago(@PathParam("cuantos") Integer cuantos, @PathParam("primero") boolean primero){
        String sessionId=null;
        for (Cookie c:request.getCookies()){
            if (c.getName().equals("sessionId")){
                sessionId = c.getValue();
                break;
            }
        }
        if (primero){
            LoginServicio.sesion2.remove(sessionId);
        }
        HashMap<String, AttributeValue> pps=null;
        if (LoginServicio.sesion2.get(sessionId)!=null){
            pps=(HashMap<String, AttributeValue>)LoginServicio.sesion2.get(sessionId);
        }
        ScanResult sr=new PlanPagoDAO().leer("PlanPago", cuantos, pps);
        LoginServicio.sesion2.put(sessionId, sr.getLastEvaluatedKey());
        return sr;
    }
    
    @Path("/planPago/{id}")
    @GET
    public PlanPago getPlan(@PathParam("id") Long id){
        return new PlanPagoDAO().leer(PlanPago.class, id);
    }  
    
    /*@Path("/cuotas/{id}")
    @GET
    public PlanPago getCuotas(@PathParam("id") Long id){
        return new PlanPagoDAO().leer(PlanPago.class, id);
    } */ 
    
}
