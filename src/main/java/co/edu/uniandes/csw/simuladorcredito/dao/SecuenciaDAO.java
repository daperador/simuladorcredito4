/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.simuladorcredito.dao;

import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Secuencia;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.HashMap;

/**
 *
 * @author Fredy
 */
public class SecuenciaDAO extends SuperDAO{
    private static final Long TAMANO_CACHE = 20L;
    private static final SecuenciaDAO instancia;
    private static final HashMap<String, Long[]> cache=new HashMap<>();
    
    static{
        instancia = new SecuenciaDAO();
    }
    
    private SecuenciaDAO(){
        col = db.getCollection("Secuencia");
    }

    public static SecuenciaDAO getInstancia() {
        return instancia;
    }
    
    public Secuencia leer(String llave){
        BasicDBObject query = new BasicDBObject("tabla", llave);
        DBCursor cursor = col.find(query);
        try {
           if(cursor.hasNext()) {
               Secuencia s = new Secuencia();
               DBObject obj=cursor.next();
               s.setTabla((String)obj.get("tabla"));
               s.setSecuencia((Long)obj.get("secuencia"));
               return s;
           }else{
               return null;
           }
        } finally {
           cursor.close();
        }
    }     
   
    
    public Long getSiguiente(Class entidad){
        String tabla=entidad.getName();
        System.out.print(tabla+": ");
        Long numeros[]=cache.get(tabla);
        if (numeros!=null){
            // existe en caché
            numeros[0]++;
            if (numeros[0]==numeros[1]){
                Secuencia s=leer(tabla);
                numeros[0]=s.getSecuencia()+1;
                numeros[1]=s.getSecuencia()+TAMANO_CACHE;
                s.setSecuencia(s.getSecuencia()+TAMANO_CACHE);
                actualizar(s);
                
                cache.put(tabla, numeros);
                System.out.println(numeros[0]);
                return numeros[0];
            } else{
                System.out.println(numeros[0]);
                return numeros[0];
            }
        }else{
            // no existe en caché
            Secuencia s=leer(tabla);
            if (s==null){
                // No existe el registro de esa entidad en la tabla secuencia
                s=new Secuencia();
                s.setTabla(tabla);
                s.setSecuencia(TAMANO_CACHE);
                Long ns[]=new Long[]{1L, TAMANO_CACHE};
                insertar(s);
                cache.put(tabla, ns);
                System.out.println(1L);
                return 1L;
            }else{
                // Existe el registro en la tabla secuencia
                Long ns[]=new Long[]{s.getSecuencia()+1, s.getSecuencia()+TAMANO_CACHE};
                s.setSecuencia(s.getSecuencia()+TAMANO_CACHE);
                actualizar(s);
                cache.put(tabla, ns);
                System.out.println(ns[0]);
                return ns[0];
            }
                
        }
        
    }
    
    public void actualizar(Secuencia s){
        BasicDBObject doc = new BasicDBObject("tabla", s.getTabla());
        BasicDBObject doc2 = new BasicDBObject("tabla", s.getTabla()).append("secuencia", s.getSecuencia());
        col.update(doc, doc2);
    }
    
    public void insertar(Secuencia s){
        BasicDBObject doc = new BasicDBObject("tabla", s.getTabla()).append("secuencia", s.getSecuencia());
        col.insert(doc);
    }
}
