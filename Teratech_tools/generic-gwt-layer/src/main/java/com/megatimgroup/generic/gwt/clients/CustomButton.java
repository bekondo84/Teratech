/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.gwt.clients;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 *
 * @author Commercial_2
 */
public class CustomButton extends com.google.gwt.user.client.ui.Button{

    private PopupPanel popUp = null ;
    
    private String toolTipMsg =null;
    
    public CustomButton() {        
        
        addMouseOverHandler(new MouseOverHandler() {

            @Override
            public void onMouseOver(MouseOverEvent event) {
                //To change body of generated methods, choose Tools | Templates.
               if(popUp==null)
                  return ;
                //Un popup existe et il faut l'afficher
                popUp.setPopupPosition(event.getX(), event.getY());
                popUp.show();
                Timer timer = new Timer() {

                   @Override
                   public void run() {
                       //To change body of generated methods, choose Tools | Templates.
                       if(popUp!=null){
                           popUp.hide();
                       }
                   }
               };
            }
        });
    }

    public CustomButton(String html) {
          super(html);        
          
    }

    public CustomButton(String html, ClickListener listener) {
        super(html, listener);        
        
    }

    public CustomButton(String html, ClickHandler handler) {
        super(html, handler);       
    }

    public CustomButton(Element element) {
        super(element);       
    }
    
    /**
     * 
     * @param msg 
     */
    protected void initPopUp(String msg){
        popUp = new PopupPanel(true);
        Label label = new Label(msg);
        popUp.add(label);
    }
    
    /**
     * 
     * @param evt
     */
    protected void onMouseOver(MouseOverEvent evt){
        
        if(popUp==null)
                  return ;
        //Un popup existe et il faut l'afficher
        popUp.setPopupPosition(evt.getClientX(), evt.getClientY());
        popUp.show();
        //Ajout d'un muniteru pour faire
    }
    
    /**
     * Souris hors du composant
     * @param evt 
     */
    protected void onMouseout(MouseOutEvent evt){
        
         if(popUp==null)
                  return ;
         if(popUp.isShowing())
             popUp.hide();
    }

    public String getToolTipMsg() {
        return toolTipMsg;
    }

    public void setToolTipMsg(String toolTipMsg) {
        this.toolTipMsg = toolTipMsg;
        initPopUp(toolTipMsg);
    }
    
    
    
}
