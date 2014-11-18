/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.simuladorcredito.dao;

import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Administrador;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 *
 * @author Fredy
 */
public class AdministradorDAO extends SuperDAO{
    
    
    public AdministradorDAO(){
        col = db.getCollection("Administrador");
    }
    
    public Administrador leer(Long llave){
        DBObject doc=super.leerBD("id",llave);
        Administrador a=new Administrador();
        a.setId((Long)doc.get("id"));
        a.setApellidos((String)doc.get("apellidos"));
        a.setEmail((String)doc.get("email"));
        a.setNombres((String)doc.get("nombres"));
        a.setPassword((String)doc.get("password"));
        return a;
    }

    public void actualizar(Administrador s){
        BasicDBObject doc = new BasicDBObject("id", s.getId());
        BasicDBObject doc2 = new BasicDBObject("id", s.getId()).append("apellidos", s.getApellidos()).append("email", s.getEmail()).append("nombres", s.getNombres()).append("password", s.getPassword());
        col.update(doc, doc2);
    }
    
    public Administrador insertar(Administrador s){
        s.setId(SecuenciaDAO.getInstancia().getSiguiente(s.getClass()));
        BasicDBObject doc = new BasicDBObject("id", s.getId()).append("apellidos", s.getApellidos()).append("email", s.getEmail()).append("nombres", s.getNombres()).append("password", s.getPassword());
        col.insert(doc);
        return s;
    }
    
    public Administrador login(Administrador usr){
        DBObject doc=super.leerBD("email",usr.getEmail());
        Administrador a=new Administrador();
        a.setId((Long)doc.get("id"));
        a.setApellidos((String)doc.get("apellidos"));
        a.setEmail((String)doc.get("email"));
        a.setNombres((String)doc.get("nombres"));
        a.setPassword((String)doc.get("password"));
        return a;
    }
    
    
    
    
}
