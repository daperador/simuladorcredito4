/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import co.edu.uniandes.csw.simuladorcredito.dao.AdministradorDAO;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Administrador;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel
 */
@Path("/registro")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegistroServicio {
    
    @POST
    public Administrador registrarUsuario(Administrador registro){
        AdministradorDAO dao=new AdministradorDAO();
        Administrador a = (Administrador)dao.insertar(registro);
        return a;
    }
    
}
