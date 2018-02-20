/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import com.megatim.common.jaxb.entities.Principalscreen;
import com.megatim.common.jaxb.entities.Submodule;

/**
 * Classe utilitaire pour le client
 *
 * @author DEV_4
 */
public class CommonsUtilities {

    public static final int PAGINATION_STEP_SIZE = 14;
    
    public static final String SOCIETE_CODE ="EPA";
    
    public static final String OPERATION_MACHINE_STATE_CODE="EDITDATA";
    
    public static final String EDITDATA_SEQUENCE_CODE="EDITDATA";
    public static final String  POLICE_APPLICATION="Lucida Grande";
    public static final Color COULEUR_TITRE_FRAME=new java.awt.Color(33, 104, 98);
    
    public static Font getFontTitreFrame(){
    	return new Font(POLICE_APPLICATION, 1,15);
    }
    
    private static ImageIcon application_icon=null;
    /**
     * Retourne la pied de toute les pages
     *
     * @return
     */
    public static JPanel footerPanel() {

        return new FooterPanel();
    }

    /**
     *
     * @return
     */
    public static JPanel headerPanel() {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/BtitreNew.png"));
        return new BannierePanel(icon.getImage());
    }

    public static JPanel titreHeaderPanel() {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/BtitreNew.png"));
        return new BannierePanel(icon.getImage());
    }

    public static Image midlePanel() {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/home_transparent.png"));
        return icon.getImage();
    }
    
    // image connexion
    public static ImageIcon getImageLogin(){
    	return new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/banner_login.png")) ;
    }
    
    // image user
    public static ImageIcon getImageUser(){
    	return new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/banner_user.png")) ;
    }
    
    // image user
    public static ImageIcon getIcone(){
    	return new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/icone.png")) ;
    }
    
    public static ImageIcon getIconPrevious(){
    	return new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/previous.png")) ;
    }
    
    public static ImageIcon getIconFirst(){
    	return new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/first.png")) ;
    }
    
    public static ImageIcon getIconNext(){
    	return new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/next.png")) ;
    }
    
    public static ImageIcon getIconLast(){
    	return new ImageIcon(ClassLoader.getSystemResource("com/megatim/tools/images/last.png")) ;
    }
    
    
    public static Image getIcon(){
        
        if(application_icon==null)
              application_icon = new ImageIcon(ClassLoader.getSystemResource("/com/megatim/tools/images/icone.png"));
       return application_icon.getImage();
    }

    public static void setIcon(ImageIcon application_icon) {
        CommonsUtilities.application_icon = application_icon;
    }

    public static Color desableFieldColor() {
        return new Color(223 , 242 ,255);
    }
    
    public static Color desableFieldSimpleColor(){
        return new Color(223 , 242 ,255);
    }
    
    public static Font getFont(){
    	return new Font("Lucida Grande", Font.BOLD,12);
    }
    public static Font getFontSimple(){
    	return new Font("Lucida Grande", Font.PLAIN,12);
    }
    
    public static Font getFontBorderGroupBox(){
    	return new Font(CommonsUtilities.POLICE_APPLICATION, com.lowagie.text.Font.BOLDITALIC, 13);
    }
    
    public static Font getFontHederTable(){
    	return new Font(CommonsUtilities.POLICE_APPLICATION, Font.BOLD, 12);
    }
    
    
    
    
    public static JTextComponent buildJTextFielddisable(JTextComponent textField){
    	if(textField instanceof JFormattedTextField){
    		buildFieldAmountDisable((JFormattedTextField) textField);
    	}else{
    		textField.setFont(getFontSimple());
        	textField.setBackground(desableFieldColor());
        	textField.setEditable(false);
        	textField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    	}
    
    	return textField;
    }
    
    public static JTextField buildFieldAmountDisable(JFormattedTextField textField){
    	//textField.setText("0");
    	textField.setHorizontalAlignment(JTextField.RIGHT);
    	textField.setFont(getFont());
    	textField.setBackground(desableFieldColor());
    	textField.setEditable(false);
    	textField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    	return textField;
    }
    
    public static JTextField buildFieldAmount(JTextField textField){
    	Color color = new Color(251, 242, 183);
    	textField.setText("0");
    	textField.setBackground(color);
    	textField.setHorizontalAlignment(JTextField.RIGHT);
    	textField.setFont(getFont());
    	textField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    	return textField;
    }
    
    public static JTextField buildJTextFieldBorder(JTextField textField){
    	textField.setFont(getFontSimple());
    	textField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
    	return textField;
    }
    
    public static JComboBox buildJComboboxdisable(JComboBox combobox){
    	combobox.setEnabled(false);
    	ComboBoxEditor editor = combobox.getEditor() ;
    	JTextField etf = (JTextField)editor.getEditorComponent();
    	etf.setDisabledTextColor(Color.BLACK);//UIManager.getColor("ComboBox.foreground"));
    	etf.setFont(getFont());
    	etf.setBackground(desableFieldSimpleColor());//UIManager.getColor("ComboBox.background"));
    	// editor.setItem(format(obj));
    	etf.setEnabled(false);
    	return combobox;
    }
    
    public static JTextField buildTranformeJComboboxdisable(JComboBox combobox){
    	
    	combobox.setEnabled(false);
    	ComboBoxEditor editor = combobox.getEditor() ;
    	JTextField etf = (JTextField)editor.getEditorComponent();
    	etf.setDisabledTextColor(Color.BLACK);//UIManager.getColor("ComboBox.foreground"));
    	etf.setFont(getFontSimple());
    	etf.setBackground(desableFieldSimpleColor());//UIManager.getColor("ComboBox.background"));
    	// editor.setItem(format(obj));
    	etf.setEnabled(false);
    	return etf;
    }
    
    public static JDateChooser buildJDatedisable(JDateChooser date){
    	
    	JTextFieldDateEditor textDate = ((JTextFieldDateEditor)date.getDateEditor());
    	textDate.setDisabledTextColor(Color.BLACK);
    	textDate.setBackground(desableFieldColor());
    	textDate.setFont(getFont());
    	date.setEnabled(false);
    	return date;
    }
    
    
    public static List<ActionGroup> convert(Principalscreen screen){
        List<ActionGroup> grouplist = new ArrayList<ActionGroup>();

        if (screen == null) {
            return grouplist;
        }
        if (screen.getModule() == null || screen.getModule().isEmpty()) {
            return grouplist;
        }
        for (com.megatim.common.jaxb.entities.Module module : screen.getModule()) {

            ActionGroup group = new ActionGroup(module.getName());
            group.setLabel(module.getLabel());
            group.setVmenu(module.isVmenu());
            for (com.megatim.common.jaxb.entities.Action action : module.getAction()) {
                ActionDetail act = new ActionDetail(action.getClassname(), action.getActionname(), action.getLabel(), action.getPrefix(), action.getActionindex(), action.getMnemonic().charAt(0));
                act.setSeparator(action.isSeparator());
                act.setLiteral(action.isLiteral());
                act.setDialog(action.isDialog());
                group.getActions().add(act);

            }
            for (Submodule submodule : module.getSubmodule()) {
                ActionGroup subgroup = new ActionGroup(submodule.getName());
                subgroup.setLabel(submodule.getLabel());
                subgroup.setIndependent(submodule.isIndependent());
                for (com.megatim.common.jaxb.entities.Action action : submodule.getAction()) {
                    ActionDetail act = new ActionDetail(action.getClassname(), action.getActionname(), action.getLabel(), action.getPrefix(), action.getActionindex(), action.getMnemonic().charAt(0));
                    act.setSeparator(action.isSeparator());
                    act.setLiteral(action.isLiteral());
                    act.setDialog(action.isDialog());
                    subgroup.getActions().add(act);

                }
                group.getGroupes().add(subgroup);
            }
            //cas des sous groupe
            grouplist.add(group);

        }
        return grouplist;
    }
}
