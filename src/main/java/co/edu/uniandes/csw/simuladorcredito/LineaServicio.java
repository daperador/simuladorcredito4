/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import co.edu.uniandes.csw.simuladorcredito.dao.LineaDAO;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Linea;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel
 */
@Path("linea")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LineaServicio {
    
    @Path("/administrador/{id}")
    @GET
    public List<Linea> getLineasAdministrador(@PathParam("id") Long id){
        return new LineaDAO().getLineaAdministrador(id);
    }

    @Path("/linea/{id}")
    @GET
    public Linea getLinea(@PathParam("id") Long id){
        return new LineaDAO().leer(id);
    }

    /*@Path("/linea/{id}")
    @DELETE
    public void eliminarLinea(@PathParam("id") Long id){
        new LineaDAO().eliminar(id);
    }*/

    @Path("/linea/")
    @POST
    public Linea crearLinea(Linea linea){
        return (Linea)new LineaDAO().insertar(linea);
    }

    @Path("/linea/")
    @PUT
    public void actualizarLinea(Linea linea){
        new LineaDAO().actualizar(linea);
    }
}
