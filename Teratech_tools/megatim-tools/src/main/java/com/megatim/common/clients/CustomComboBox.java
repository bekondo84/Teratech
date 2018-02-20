/*
 * To ch:ange this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.annotations.SearchType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author DEV_4
 */
public class CustomComboBox extends JComboBox{

    private GenericManager manager ;
    
    //Vecteur des clef de recherche
    private String keyWord[] = null;
    
    private Vector myVector = new Vector();
    
    private RestrictionsContainer container = null ;
    
    private ICommand command ;
    
    public CustomComboBox(ComboBoxModel aModel) {
        super(aModel);
        this.setEditable(true);
    }

    public CustomComboBox(Object[] items) {
        super(items);
        this.setEditable(true);
    }

    public CustomComboBox(Vector items) {
        super(items);
        this.setEditable(true);
    }

    public CustomComboBox() {
        super();
        this.setEditable(true);
        setModel(new DefaultComboBoxModel(myVector));
        setSelectedIndex(-1);
        setEditable(true);
        JTextField text = (JTextField) this.getEditor().getEditorComponent();
        text.setFocusable(true);
        text.setText("");
        text.addKeyListener(new ComboListener(this, myVector));
        loadData();
    }

    public GenericManager getManager() {
        return manager;
    }

    public void setManager(GenericManager manager) {
        this.manager = manager;
    }
    
    /**
     * 
     * @param fieldName
     * @param value
     * @param type 
     */
    public void addPredicat(String fieldName,Object value , SearchType type){
        
        if(container==null)
            container =  RestrictionsContainer.newInstance();
        
        if(type==SearchType.LIKE){
            
            if(value instanceof String){
                container.addLike(fieldName, (String) value);
            }
                
        }else if(type==SearchType.EQUAL){
            container.addEq(fieldName, (Comparable)value);
             //System.out.println("LigneTitreRecetteEditPanel().chapitreActionPerformed() ::::::  "+fieldName+"  :::: "+value);       
        }else if(type==SearchType.GE){
            container.addGe(fieldName, (Comparable)value);
        }else if(type==SearchType.GT){
            container.addGe(fieldName, (Comparable)value);
        }else if(type==SearchType.ISEMPTY){
            container.addIsEmpty(fieldName, (Comparable)value);
        }else if(type==SearchType.ISFALSE){
            container.addIsFalse(fieldName);
        }else if(type==SearchType.ISNOTEMPTY){
            container.addIsNotEmpty(fieldName, (Boolean)value);
        }else if(type==SearchType.ISNOTNULL){
            container.addNotNull(fieldName, (Comparable)value);
        }else if(type==SearchType.ISNULL){
            container.addIsNull(fieldName, (Comparable)value);
        }else if(type==SearchType.ISTRUE){
            container.addIsTrue(fieldName);
        }else if(type==SearchType.LE){
            container.addLe(fieldName, (Comparable)value);
        }else if(type==SearchType.LT){
            container.addLt(fieldName, (Comparable)value);
        }else if(type==SearchType.NOTEQ){
            container.addNotEq(fieldName, (Comparable)value);
        }else if(type==SearchType.NOTLIKE){
            if(value instanceof String){
                container.addNotLike(fieldName, (String)value);
            }
        }
    }
    public void clearItems(){
        this.removeAllItems();
        container = null;
    }
    
    
    /**
     * 
     */
    public void loadData(){
        myVector.clear();
        if(manager==null)
                    return ;
        
        List datas = null;
        
        if(container!=null){
            datas = manager.filter(container.getPredicats(), null, null, 0, -1);
        }else {
            datas = manager.findAll();
        }
        if(datas.size()<=0)
                      return ;
        
            keyWord = new String[datas.size()];
        //Initialisation
            int index = 0 ;
        for(Object data: datas){
            keyWord[index]=datas.toString();
            myVector.add(data);
            index ++;
        }
        
        /*if(this.getItemCount()>0)
            this.setSelectedIndex(0);*/
    }  
    
     class ComboListener extends KeyAdapter {
    
         private JComboBox cbListener;
         
         private Vector vector ;

         /**
          * 
          * @param cbListenerParam
          * @param vector 
          */
        public ComboListener(JComboBox cbListenerParam, Vector vector) {
            this.cbListener = cbListenerParam;
            this.vector = vector;
        }

        @Override
        public void keyReleased(KeyEvent e) {
           
            if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_N){
                visite(command);
            }else{
                String text = ((JTextField)e.getSource()).getText();

                cbListener.setModel(new DefaultComboBoxModel(getFilteredList(text)));

                cbListener.setSelectedIndex(-1);
                ((JTextField)cbListener.getEditor().getEditorComponent()).setText(text);

                cbListener.showPopup();
            }
        }
         
        public Vector getFilteredList(String text){
            
            Vector v =  new Vector();
            
            for(int i=0 ; i<vector.size();i++){
                
                /*if(text==null||text.trim().isEmpty()){
                    
                    if(!v.contains(vector.get(i))){
                        v.add(vector.get(i));
                    }
                }*/
                
                if(vector.get(i).toString().startsWith(text)){
                    v.add(vector.get(i));
                }else if(vector.get(i).toString().startsWith(text.toLowerCase())){
                     v.add(vector.get(i));
                }else if(vector.get(i).toString().startsWith(text.toUpperCase())){
                     v.add(vector.get(i));
                }else if(vector.get(i).toString().matches(text+"%")){
                     v.add(vector.get(i));
                }
            }
            return v ;
        }  
         
     }     
    

    public void setCommand(ICommand command) {
        this.command = command;
    }
    
    public void visite(ICommand vis){
        if(vis!=null)
            vis.execute();
    }
    
    public void setDatas(ArrayList<Object> listData){
    	clearItems();
    	for(Object o :listData){
    		myVector.add(o);
    	}
    }
}
