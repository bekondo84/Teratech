/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import com.megatim.common.enumeration.JavaTypeConnection;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class CheckConnectionDataBase {
    
    public static boolean check(String dbHost, String dbName, String dbUser, String dbPassword, String dbPort, JavaTypeConnection type){
        Connection conn = null;
        String typeDataBase = null;
        
        try {
            
            if(type.equals(JavaTypeConnection.MYSQL)){
                typeDataBase = "mysql";
                conn = DriverManager.getConnection("jdbc:"+typeDataBase+"://"+dbHost+"/"+dbName, dbUser, dbPassword);
            }else if(type.equals(JavaTypeConnection.ORACLE)){            
                typeDataBase = "oracle";
                conn = DriverManager.getConnection("jdbc:"+typeDataBase+":thin:@"+dbHost+":"+dbPort+"/"+dbName, dbUser, dbPassword);
            }else if(type.equals(JavaTypeConnection.SQL_SERVER)){            
                typeDataBase = "sqlserver";
                conn = DriverManager.getConnection("jdbc:"+typeDataBase+"://"+dbHost+":"+dbPort+";databaseName="+dbName, dbUser, dbPassword);
            }else if(type.equals(JavaTypeConnection.POSGRES_SQL)){
                typeDataBase = "postgresql";
                conn = DriverManager.getConnection("jdbc:"+typeDataBase+"://"+dbHost+"/"+dbName, dbUser, dbPassword);
            }
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
