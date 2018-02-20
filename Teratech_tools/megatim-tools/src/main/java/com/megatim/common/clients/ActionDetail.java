/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

/**
 *
 * @author DEV_4
 */
public class ActionDetail {
    
    private String viewClass ;
    
    private String actionName ;
    
    private String actionLabel;
    
    private int actionIndex ;
    
    private char mnemonic ;
    
    private String prefix = "";
    
    private Class<?> classType ;
    
    private boolean separator = false ;
    
    private boolean literal = false ;
    
    private boolean dialog = false ;
    

    /**
     * 
     * @param viewClass
     * @param actionName
     * @param actionLabel
     * @param prefix
     * @param actionIndex 
     */
    public ActionDetail(String viewClass, String actionName, String actionLabel,String prefix, int actionIndex) {
        this.viewClass = viewClass;
        this.actionName = actionName;
        this.actionLabel = actionLabel;
        this.actionIndex = actionIndex;
        this.prefix=prefix;
    }

    public ActionDetail(String viewClass, String actionName, String actionLabel,String prefix, int actionIndex, char mnemonic) {
        this.viewClass = viewClass;
        this.actionName = actionName;
        this.actionLabel = actionLabel;
        this.actionIndex = actionIndex;
        this.mnemonic = mnemonic;
        this.prefix=prefix;
    }

    
    /**
     * 
     */
    public ActionDetail() {
    }

    public boolean isDialog() {
        return dialog;
    }

    public void setDialog(boolean dialog) {
        this.dialog = dialog;
    }
    
    

    public String getViewClass() {
        return viewClass;
    }

    public void setViewClass(String viewClass) {
        this.viewClass = viewClass;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }

    public int getActionIndex() {
        return actionIndex;
    }

    public void setActionIndex(int actionIndex) {
        this.actionIndex = actionIndex;
    }

    public char getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(char mnemonic) {
        this.mnemonic = mnemonic;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public void setClassType(Class<?> classType) {
        this.classType = classType;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }    

    public boolean isSeparator() {
        return separator;
    }

    public void setSeparator(boolean separator) {
        this.separator = separator;
    }

    public boolean isLiteral() {
        return literal;
    }

    public void setLiteral(boolean literal) {
        this.literal = literal;
    }
    
    

    
    
    @Override
    public String toString() {
        return "ActionDetail{" + "viewClass=" + viewClass + ", actionName=" + actionName + ", actionLabel=" + actionLabel + ", actionIndex=" + actionIndex + '}';
    }
    
    
}
