/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito.dao;

import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Cuota;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fredy
 */
public class CuotaDAO extends SuperDAO<Cuota>{
    
    public CuotaDAO(){
        col = db.getCollection("Cuota");
    }
    public List<Cuota> leer(Long planId){
        List<DBObject> docs=super.leerVariosBD("idPlan",planId);
        List<Cuota> cuotas=new ArrayList();
        for (DBObject doc:docs){
            Cuota a=new Cuota();
            a.setId((Long)doc.get("id"));
            a.setNumeroCuota((Integer)doc.get("numeroCuota"));
            a.setIntereses((Double)doc.get("intereses"));
            a.setCapital((Double)doc.get("capital"));
            a.setTotal((Double)doc.get("total"));
            a.setSaldo((Double)doc.get("saldo"));
            a.setIdPlan((Long)doc.get("idPlan"));
            cuotas.add(a);
        }
        return cuotas;
    }

    
    public List<Cuota> insertar(List<Cuota> cuotas){
        if (cuotas!=null){
            for (Cuota s:cuotas){
                s.setId(SecuenciaDAO.getInstancia().getSiguiente(s.getClass()));
                BasicDBObject doc = new BasicDBObject("id", s.getId()).append("numeroCuota", s.getNumeroCuota()).append("intereses", s.getIntereses()).append("capital", s.getCapital()).append("total", s.getTotal()).append("saldo", s.getSaldo()).append("idPlan", s.getIdPlan());
                col.insert(doc);
            }
        }
        return cuotas;
    }

}
