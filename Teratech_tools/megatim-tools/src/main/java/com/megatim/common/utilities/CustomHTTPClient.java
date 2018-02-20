/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import com.megatim.common.clients.Messages;
import com.megatim.common.clients.NotificationType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author user
 */
public class CustomHTTPClient {
    
    /**
     * 
     * @param url
     * @param user
     * @param password
     * @return 
     */
    public static String getHTTPClient(String url, String user, String password) {

        //Creation d'une instance String Builder
        StringBuilder stringBuilder = new StringBuilder();
        
        //Creation instance d'un CredentialsProvider
        CredentialsProvider provider = new BasicCredentialsProvider();
        
        //Creation instance UsernamePasswordCredentials
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, password);
        
        //On met à jour le rpovider
        provider.setCredentials(AuthScope.ANY, credentials);
        
        //On recupere une instance d'un
        HttpClient httpClient =  HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        
        
        HttpGet httpGet = new HttpGet(url);
        
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
                Messages.Messages(null, true, NotificationType.ERROR, "UNE ERREUR PRODUITE", stringBuilder.toString(),"");
            }
        } catch (Exception e) {
            Messages.Messages(null, true, NotificationType.ERROR, "UNE ERREUR PRODUITE", e.getMessage(),"");
        }
        return stringBuilder.toString();
    }
    
    public static Boolean ifLoginHTTPClient(String url, String user, String password) {

        //Creation d'une instance String Builder
        StringBuilder stringBuilder = new StringBuilder();
        
        //Creation instance d'un CredentialsProvider
        CredentialsProvider provider = new BasicCredentialsProvider();
        
        //Creation instance UsernamePasswordCredentials
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, password);
        
        //On met à jour le rpovider
        provider.setCredentials(AuthScope.ANY, credentials);
        
        //On recupere une instance d'un
        HttpClient httpClient =  HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        
        
        HttpGet httpGet = new HttpGet(url);
        
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            
            if (statusCode == 200) {
                return true;
            } else {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
                
                if(stringBuilder.toString().contains("This request requires HTTP authentication")){
                    Messages.Messages(null, true, NotificationType.ERROR, "UNE ERREUR PRODUITE", "Login ou mot de passe incorrect","");
                }else{
                    Messages.Messages(null, true, NotificationType.ERROR, "UNE ERREUR PRODUITE", stringBuilder.toString(),"");
                }
                return false;
            }
        } catch (Exception e) {
            Messages.Messages(null, true, NotificationType.ERROR, "UNE ERREUR PRODUITE", e.getMessage(),"");
            return false;
        }
    }
    
    public static int getStatusRequest(String url, String user, String password) throws IOException {

        //Creation instance d'un CredentialsProvider
        CredentialsProvider provider = new BasicCredentialsProvider();
        
        //Creation instance UsernamePasswordCredentials
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, password);
        
        //On met à jour le rpovider
        provider.setCredentials(AuthScope.ANY, credentials);
        
        //On recupere une instance d'un
        HttpClient httpClient =  HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        
        //On lance l'execution de la requete
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        
        //On recupere le statut de la requete
        StatusLine statusLine = response.getStatusLine();
        
        return statusLine.getStatusCode();
    }
}
