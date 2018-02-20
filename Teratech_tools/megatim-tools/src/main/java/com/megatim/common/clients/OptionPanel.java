
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OptionPanel.java
 *
 * Created on 9 juin 2016, 16:48:37
 */
package com.megatim.common.clients;

import com.megatim.common.utilities.MessagesBundle;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author DEV_4
 */
public class OptionPanel extends javax.swing.JPanel implements ActionListener{

    private List<JButton> options = new ArrayList<JButton>();   
    
    //private String[][] suboptionsNames = new String[][]{{"Modèle Saisie","Saisie Opération","Saisie par modèle"},{"Services","Affectations","Personels","Partenaires","Catégories"},{},{"A Propos","Utilisateurs","Exercices budgétaire","Niveau Validation","Circuit Validation"}};
    
    //private int[][] subactionIndex = new int[][]{{10,11,12},{5,6,7,8,9},{},{0,1,2,3,4}};
    
    //private String[] optionNames = new String[]{"         Opérations       ","       Référentiels       ","          Reporting        ","     Administration     "};
    
    private List<ActionGroup> groupes = null;
    
    private Box box = null ;
    
    private JPanel internalPanel = null;
    
    private JPanel southPanel=null;
    
    private JPanel northPanel=null;
    
    private JPanel centerPanel=null;
    
    
    
    /** Creates new form OptionPanel */
    public OptionPanel() {
        initComponents();
        initViewsComponents();
    }
    
    public OptionPanel(List<ActionGroup> groupes) {
        this.groupes = new ArrayList<ActionGroup>();
        
        for(ActionGroup group : groupes){
            
            if(!group.isVmenu())
                           continue;
            
            if(group.getActions()!=null&&!group.getActions().isEmpty())
                this.groupes.add(group);
            for(ActionGroup groupe : group.getGroupes()){             
                         
              if(groupe.isIndependent())  
                   this.groupes.add(groupe);
              else {
                  for(ActionDetail detail : groupe.getActions()){
                      group.getActions().add(detail);
                  }
                 
                  if(!this.groupes.contains(group))
                         this.groupes.add(group);
              }//end if(groupe.isIndependent()) 
            }
        }
        initComponents();
        initViewsComponents();
    }

    
    
    /**
     * 
     * @param optionNames 
     */
    private void initViewsComponents(){
        //System.out.println("::================================================== "+groupes);
        if(groupes==null||groupes.isEmpty())
            return ;
        
        box = Box.createVerticalBox();
        
        setLayout(new BorderLayout(5, 2));
        
        //add(box);
        
        southPanel = new JPanel();
        
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
                
        northPanel = new JPanel();
        
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        
        centerPanel = new JPanel();
        
        int index = 0 ;
        
        for(ActionGroup optionName : groupes){
            
            JButton button = new JButton(MessagesBundle.getMessage(optionName.getLabel())); //System.out.println("=============*************** "+optionName.getLabel()+"====="+optionName.getName());
            button.setName(optionName.getName());
             //button.setBorder(BorderFactory.createBevelBorder(1));
           
             //button.setPreferredSize(new Dimension(160, 90)); //Preferred
             
             button.addActionListener(this);
             
            options.add(button);             
            
            if(index>0){
                southPanel.add(button);
                //add(northPanel,BorderLayout.NORTH);
            }else{
                northPanel.add(button);                
                //add(southPanel,BorderLayout.SOUTH);
            } 
            
            if(index==0)
                add(buildInternalPanel(optionName.getActions()),BorderLayout.CENTER);
            
            index++ ;
        }
        add(northPanel,BorderLayout.NORTH);
        add(southPanel,BorderLayout.SOUTH);
    }
   
    /**
     * 
     * @param evt 
     */
    public void actionPerformed(ActionEvent evt){
        
        int selectIndex = options.indexOf(evt.getSource());
        
        if(selectIndex>=0){
            //System.out.println("OptionPanel.actionPerformed(ActionEvent evt) :::::::::: "+selectIndex);
            remove(internalPanel);
            remove(southPanel);
            remove(northPanel);
             
            setLayout(new BorderLayout(5, 2));
            
            southPanel = new JPanel();
        
            southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));

            northPanel = new JPanel();

            northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

            //centerPanel = new JPanel();
            
            int index = 0 ;
        
            for(ActionGroup optionName: groupes){
               
                if(index>selectIndex){
                    southPanel.add(options.get(index));
                    //add(northPanel,BorderLayout.NORTH);
                }else{
                    northPanel.add(options.get(index));                
                   // add(southPanel,BorderLayout.SOUTH);
                } 

                if(index==selectIndex){                    
                    add(buildInternalPanel(optionName.getActions()),BorderLayout.CENTER);
                }

                index++ ;
           }
            add(northPanel,BorderLayout.NORTH);
            add(southPanel,BorderLayout.SOUTH);
        }
        this.paintAll(this.getGraphics());
    }
    /**
     * 
     * @param names
     * @return 
     */
    public JPanel buildInternalPanel(List<ActionDetail> actions){
        
        internalPanel = new InternalOptionPanel(actions);
        ((InternalOptionPanel)internalPanel).setParentContainer(this);
        if(internalPanel!=null)
                 ((InternalOptionPanel)internalPanel).enableOptions();
        
        return internalPanel;
    }
    
    /**
     * Applique les regles de securites
     */
    public void enableOptions(){
        
        if(internalPanel==null)
                        return ;
        ((InternalOptionPanel)internalPanel).enableOptions();
    }

    public List<JButton> getOptions() {
        return options;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
