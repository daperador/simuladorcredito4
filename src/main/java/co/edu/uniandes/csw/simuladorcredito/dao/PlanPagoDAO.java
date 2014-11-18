/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito.dao;

import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Cuota;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Linea;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.PlanPago;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.Date;

/**
 *
 * @author Fredy
 */
public class PlanPagoDAO extends SuperDAO<PlanPago>{

    public PlanPagoDAO(){
        col = db.getCollection("PlanPago");
    }
    
    public PlanPago leer(Long llave){
        DBObject doc=super.leerBD("id",llave);
        PlanPago a=new PlanPago();
        a.setId((Long)doc.get("id"));
        a.setFechaCreacion((Date)doc.get("fechaCreacion"));
        a.setDocumento((String)doc.get("documento"));
        a.setValor((Integer)doc.get("valor"));
        a.setPlazo((Integer)doc.get("plazo"));
        a.setEstado((String)doc.get("estado"));
        a.setNivelRiesgo((Double)doc.get("nivelRiesgo"));
        a.setFechaNacimiento((Date)doc.get("fechaNacimiento"));
        a.setFechaModificacion((Date)doc.get("fechaModificacion"));
        a.setLinea(new Linea());
        DBObject l=(DBObject)doc.get("linea");
        a.getLinea().setId((Long)l.get("id"));
        a.getLinea().setNombre((String)l.get("nombre"));
        a.getLinea().setTasa((Double)l.get("tasa"));
        
        a.setCuotas(new CuotaDAO().leer(a.getId()));
        
        return a;
    }

    public void actualizar(PlanPago s){
        BasicDBObject doc = new BasicDBObject("id", s.getId());
        BasicDBObject doc3 = new BasicDBObject("id", s.getLinea().getId()).append("nombre", s.getLinea().getNombre()).append("tasa", s.getLinea().getTasa());
        BasicDBObject doc2 = new BasicDBObject("id", s.getId()).append("fechaCreacion", s.getFechaCreacion()).append("documento", s.getDocumento()).append("valor", s.getValor()).append("plazo", s.getPlazo()).append("estado", s.getEstado()).append("nivelRiesgo", s.getNivelRiesgo()).append("fechaNacimiento", s.getFechaNacimiento()).append("fechaModificacion", s.getFechaModificacion()).append("linea", doc3);
        col.update(doc, doc2);
    }
    
    public PlanPago insertar(PlanPago s){
        s.setId(SecuenciaDAO.getInstancia().getSiguiente(s.getClass()));
        s.setFechaCreacion(new Date());
        BasicDBObject doc3 = new BasicDBObject("id", s.getLinea().getId()).append("nombre", s.getLinea().getNombre()).append("tasa", s.getLinea().getTasa());
        BasicDBObject doc2 = new BasicDBObject("id", s.getId()).append("fechaCreacion", s.getFechaCreacion()).append("documento", s.getDocumento()).append("valor", s.getValor()).append("plazo", s.getPlazo()).append("estado", s.getEstado()).append("nivelRiesgo", s.getNivelRiesgo()).append("fechaNacimiento", s.getFechaNacimiento()).append("fechaModificacion", s.getFechaModificacion()).append("linea", doc3);
        col.insert(doc2);
        if (s.getCuotas()!=null){
            for (Cuota c:s.getCuotas()){
                c.setIdPlan(s.getId());
            }
        }
        
        new CuotaDAO().insertar(s.getCuotas());
        
        return s;
    }
    
}
