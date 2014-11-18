/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import co.edu.uniandes.csw.simuladorcredito.dao.AdministradorDAO;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Administrador;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Fredy
 */
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginServicio {
    
    private static Hashtable<String, Long> sesion = new Hashtable();
    public static Hashtable<String, Map<String, String>> sesion2 = new Hashtable();
    
    @Context 
    private HttpServletResponse response;
    
    @Context 
    private HttpServletRequest request;
    
    @POST
    public boolean login(Administrador usuario){

        AdministradorDAO dao=new AdministradorDAO();
        Administrador a = dao.login(usuario);
        if (a==null){
            response.addCookie(new Cookie("sessionId",null));
            return false;
        }else{
            if (a.getPassword().equals(usuario.getPassword())){
                String sessionId=""+(Math.random()*1000000000000000L);
                response.addCookie(new Cookie("sessionId",sessionId));
                sesion.put(sessionId, a.getId());
                //set en elasticCache sessionId y a.getId
            }else{
                response.addCookie(new Cookie("sessionId",null));    
            }
            return a.getPassword().equals(usuario.getPassword());
        }
    }
    
    @Path("administrador")
    @GET
    public Long getAdministrador(){
        for (Cookie c:request.getCookies()){
            if (c.getName().equals("sessionId")){
                Long idAdministrador=sesion.get(c.getValue());
                return idAdministrador;
            }
        }
        return -1L;
    }
    
    
}
