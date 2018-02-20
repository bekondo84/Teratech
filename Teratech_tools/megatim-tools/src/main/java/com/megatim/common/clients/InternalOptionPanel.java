/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InternalOptionPanel.java
 *
 * Created on 9 juin 2016, 17:08:21
 */
package com.megatim.common.clients;

//import com.megatim.client.views.users.ConnectedUser;
import com.megatim.common.utilities.PrincipalFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author DEV_4
 */
public class InternalOptionPanel extends javax.swing.JPanel implements ActionListener
               ,MouseListener{

    private List<JButton> options = new ArrayList<JButton>();
    
    private PaginationStep pagination ;
    
    private JPanel parentContainer = null;
    
    //private String[] optionNames = null;
    
    //private int[] actionsIndex = null ;
    
    private List<ActionDetail> actions= null ;
    
    private Map<Integer , ActionDetail> cacheActions = new HashMap<Integer , ActionDetail>();
    
    private Box box = null;
    
    private JLabel downLabel =null ;
    
    private JLabel upLabel =null;
   
    
    /** Creates new form InternalOptionPanel 
    public InternalOptionPanel(String[] names , int[] actionsIndex) {
        initComponents();
        initViewsComponents(names);
        //setSize(100, 50);
        this.actionsIndex = actionsIndex;
        optionNames = names;
    }
    */
     /** Creates new form InternalOptionPanel */
    public InternalOptionPanel(List<ActionDetail> actions) {
        initComponents();       
        initViewsComponents(actions);
        //setSize(100, 50);
        this.actions = actions;
        //optionNames = names;
    }


    /**
     * 
     * @param optionNames 
     */
    private void initViewsComponents(List<ActionDetail> actions){
        
        //Initialisation de la liste
        int index = 0;
        for(ActionDetail action : actions){            
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/megatim/tools/images/option_icon.png"));
            RoundButton button = new RoundButton(icon);            
            button.addActionListener(this);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            options.add(button);
            cacheActions.put(index, action);
            index=index+1;
        }
        
        box = Box.createVerticalBox();
        
        setLayout(new BorderLayout());
        scrollPane = new JScrollPane(box);
        add(scrollPane);
        
         this.actions = actions;
         
         pagination = new PaginationStep(actions.size()>=6 ? 6 : actions.size());
         pagination.setSize(actions.size());
         //Affichage de la liste
         if(pagination.iterator().hasPrevious()){
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/megatim/tools/images/up.png"));         
            upLabel = new JLabel(icon);
            upLabel.addMouseListener(this);            
            upLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            //options.add(downLabel);    
            box.add(upLabel);   
            box.add(Box.createVerticalStrut(15));
         }
         for(int i=pagination.getOffset();i<pagination.getStepSize();i++){

            /*ImageIcon icon = new ImageIcon(getClass().getResource("/com/megatim/tools/images/option_icon.png"));
            RoundButton button = new RoundButton(icon);
            
            button.addActionListener(this);
            
            button.setAlignmentX(Component.CENTER_ALIGNMENT);*/
            
             RoundButton button = (RoundButton) options.get(i);
             ActionDetail optionName = actions.get(i);            
            JLabel label = new JLabel(optionName.getActionLabel());
            
            label.setAlignmentX(Component.CENTER_ALIGNMENT);            
            options.add(button);           
            
            box.add(button);   
            box.add(label);
            box.add(Box.createVerticalStrut(15));

        }
         if(pagination.iterator().hasNext()){
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/megatim/tools/images/down.gif"));         
            downLabel = new JLabel(icon);
            //downLabel.addActionListener(this);            
            downLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            downLabel.addMouseListener(this);
            //options.add(downLabel);    
            box.add(downLabel);   
            box.add(Box.createVerticalStrut(15));
         }
    }
    
    private void fillPanel(){
        
        //this.remove(box);
        //box = Box.createVerticalBox();        
        //setLayout(new BorderLayout());        
        //add(box);
         box.removeAll();
        //pagination = new PaginationStep(actions.size()>=6 ? 6 : actions.size());
        //pagination.setSize(actions.size());
        //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^   :"+pagination.getOffset()+" ^^^^^^^^^^^^ : "+pagination.getStepSize()+" :::::: "+pagination.getSize());
         /*Affichage de la liste*/
         if(pagination.iterator().hasPrevious()){
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/megatim/tools/images/up.png"));         
            upLabel = new JLabel(icon);
            upLabel.addMouseListener(this);            
            upLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            //options.add(downLabel);    
            box.add(upLabel);   
            box.add(Box.createVerticalStrut(15));
         }
         long normalsize =(pagination.getOffset()+pagination.getStepSize());
         long size = (pagination.getSize()<normalsize) ? pagination.getStepSize()-(normalsize-pagination.getSize()):pagination.getStepSize();
         
         for(int i=pagination.getOffset();i<(pagination.getOffset()+size);i++){

             RoundButton button = (RoundButton) options.get(i);
             
             ActionDetail optionName = actions.get(i);    
             
            JLabel label = new JLabel(optionName.getActionLabel());
            
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            optionName = actions.get(i);
            
            //JLabel label = new JLabel(optionName.getActionLabel());
            
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            options.add(button);           
            
            box.add(button);   
            box.add(label);
            box.add(Box.createVerticalStrut(15));
            this.revalidate();
            this.repaint();
        }
         if(pagination.iterator().hasNext()){
            ImageIcon icon = new ImageIcon(getClass().getResource("/com/megatim/tools/images/down.gif"));         
            downLabel = new JLabel(icon);
            //downLabel.addActionListener(this);            
            downLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            downLabel.addMouseListener(this);
            //options.add(downLabel);    
            box.add(downLabel);   
            box.add(Box.createVerticalStrut(15));
         }       
          
         if(parentContainer!=null)
                   parentContainer.repaint();
         
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(246, 246, 246));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables

    public void actionPerformed(ActionEvent evt) {
        
        int buttonIndex = options.indexOf(evt.getSource());
        int selectIndex = cacheActions.get(buttonIndex).getActionIndex();
        //System.out.println("InternalOptionPanel.actionPerformed(ActionEvent evt)  ::::: "+PrincipalSceen.principalScreen);
        PrincipalFrame.principalScreen.accept(selectIndex);
    }
    
     /**
     * Applique les regles de securites
     */
    public void enableOptions(){
        
      /*if(options==null)
                  return ;
        
        if(ConnectedUser.getConnectedUser()==null)
                         return ;
        int index = 0 ;
        
        for(JButton bt : options){
            //System.out.println("InternalOptionPanel.enableOptions() :::::::::: "+ActionsName.getActionName(actionsIndex[index]));
            if(actionsIndex[index]!=12)bt.setEnabled(ConnectedUser.getUserHabilitation(ActionsName.getActionName(actionsIndex[index]))!=null&&!ConnectedUser.canNone(ActionsName.getActionName(actionsIndex[index])));
            else bt.setEnabled(ConnectedUser.getUserHabilitation(ActionsName.getActionName(11))!=null&&!ConnectedUser.canNone(ActionsName.getActionName(11)));
            index++;
        }*/
    }

    public void mouseClicked(MouseEvent e) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    public void mousePressed(MouseEvent e) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseReleased(MouseEvent e) {
        
        if(e.getSource().equals(downLabel)&&pagination.iterator().hasNext()){
            //System.out.println("**************************************************** : "+e.getSource());
            pagination = pagination.iterator().next();
        }else if(e.getSource().equals(upLabel)&&pagination.iterator().hasPrevious()){
            //System.out.println("שששששששששששששששששששששששששששששששששששששששששששששששששששש : "+e.getSource());
            pagination = pagination.iterator().previous();
        }
        fillPanel();
         //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseEntered(MouseEvent e) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseExited(MouseEvent e) {
        ; //To change body of generated methods, choose Tools | Templates.
    }

    public JPanel getParentContainer() {
        return parentContainer;
    }

    public void setParentContainer(JPanel parentContainer) {
        this.parentContainer = parentContainer;
    }
    
    
}
