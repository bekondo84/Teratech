/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Commercial_2
 */
public class NetWorkHelper {
    
    private static Map<String , InetAddress> adresses = new HashMap<String , InetAddress>();

    private static NetWorkHelper instance = null ;
    /**
     * 
     */
    private NetWorkHelper() throws SocketException {
        
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()){
                NetworkInterface current = interfaces.nextElement();
                Enumeration<InetAddress> adresses = current.getInetAddresses();
                while(adresses.hasMoreElements()){
                    InetAddress adress = adresses.nextElement();
                    add(adress);                       
                }
            }
       
        //return null;
    }
    
    /**
     * 
     * @param inet 
     */
    public static void add(InetAddress inet){        
        if(!adresses.containsKey(inet.getHostAddress())){
            adresses.put(inet.getHostAddress(), inet);
        }
    }
    
    
    /**
     * 
     * @param ipAdress
     * @return 
     */
    public static synchronized InetAddress getInetAddress(String ipAdress){
        
        if(instance==null){
            try {
                instance = new NetWorkHelper();
            } catch (SocketException ex) {
                instance = null;
                Logger.getLogger(NetWorkHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return adresses.get(ipAdress);
    }
}
