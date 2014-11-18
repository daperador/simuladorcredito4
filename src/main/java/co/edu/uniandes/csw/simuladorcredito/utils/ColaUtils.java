/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.simuladorcredito.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class ColaUtils {
    
    public static void addMensaje(String mensaje){
        try {
            String uri = System.getenv("CLOUDAMQP_URL");
            if (uri == null) uri = "amqp://yzjaoeou:y7GG2F7D95cJ9on-1Ozswe-NCRhLO8g3@turtle.rmq.cloudamqp.com/yzjaoeou";
            
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri(uri);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.queueDeclare("hello", false, false, false, null);
            channel.basicPublish("", "hello", null, mensaje.getBytes());
            System.out.println(" [x] Sent '" + mensaje + "'");
        } catch (URISyntaxException ex) {
            Logger.getLogger(ColaUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ColaUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(ColaUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ColaUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
}
