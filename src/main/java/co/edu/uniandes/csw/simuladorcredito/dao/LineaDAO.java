/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito.dao;

import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Linea;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fredy
 */
public class LineaDAO extends SuperDAO<Linea>{
    
    public LineaDAO(){
        col = db.getCollection("Linea");
    }
    
    public Linea leer(Long llave){
        DBObject doc=super.leerBD("id",llave);
        Linea a=new Linea();
        a.setId((Long)doc.get("id"));
        a.setNombre((String)doc.get("nombre"));
        a.setTasa((Double)doc.get("tasa"));
        a.setAdministrador((Long)doc.get("administrador"));
        return a;
    }

    public void actualizar(Linea s){
        BasicDBObject doc = new BasicDBObject("id", s.getId());
        BasicDBObject doc2 = new BasicDBObject("id", s.getId()).append("nombre", s.getNombre()).append("tasa", s.getTasa()).append("administrador", s.getAdministrador());
        col.update(doc, doc2);
    }
    
    public Linea insertar(Linea s){
        s.setId(SecuenciaDAO.getInstancia().getSiguiente(s.getClass()));
        BasicDBObject doc = new BasicDBObject("id", s.getId()).append("nombre", s.getNombre()).append("tasa", s.getTasa()).append("administrador", s.getAdministrador());
        col.insert(doc);
        return s;
    }
    
    
    public List<Linea> getLineaAdministrador(Long idAdministrador){
        List<DBObject> documentos=super.leerVariosBD("administrador", idAdministrador);
        List<Linea> lineas = new ArrayList();
        for (DBObject doc:documentos){
            Linea a=new Linea();
            a.setId((Long)doc.get("id"));
            a.setNombre((String)doc.get("nombre"));
            a.setTasa((Double)doc.get("tasa"));
            a.setAdministrador((Long)doc.get("administrador"));
            lineas.add(a);
        }
        return lineas;
    }
}
