/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.simuladorcredito.dao;

import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.SuperPojo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fredy
 */
public class SuperDAO <T extends SuperPojo> {
    private static MongoURI mongoClient ;
    protected static DB db ;
    static{
        try {
            //ResourceBundle rb=ResourceBundle.getBundle("config");
            mongoClient = new MongoURI(System.getenv("MONGOHQ_URL"));
            db = mongoClient.connectDB();
            db.authenticate(mongoClient.getUsername(), mongoClient.getPassword());
        } catch (MongoException ex) {
            Logger.getLogger(SuperDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(SuperDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected DBCollection col;
    
    protected DBObject leerBD(String campo, Object valor){
        BasicDBObject query = new BasicDBObject(campo, valor);
        DBCursor cursor = col.find(query);
        try {
           if(cursor.hasNext()) {
               return cursor.next();
           }else{
               return null;
           }
        } finally {
           cursor.close();
        }        
    }
    
    protected List<DBObject> leerVariosBD(String campo, Object valor){
        List<DBObject> lista = new ArrayList();
        BasicDBObject query = new BasicDBObject(campo, valor);
        DBCursor cursor = col.find(query);
        try {
           while(cursor.hasNext()) {
               lista.add(cursor.next());
           }
        } finally {
           cursor.close();
        }   
        return lista;
    }
    
}
