/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.security.enumerations;

import com.megatim.common.utilities.MessagesBundle;


/**
 * Etat d'une autorisation
 * @author DEV_4
 */
public enum EnumStatutAutorisation {
    
//    NONE(MessagesBundle.getMessage("enum.autorisation.none")),
//    READ(MessagesBundle.getMessage("enum.autorisation.read")),
//    WRITE(MessagesBundle.getMessage("enum.autorisation.write")),
//    DELETE(MessagesBundle.getMessage("enum.autorisation.delete"));


    NONE(MessagesBundle.getMessage("Aucun")),
    READ(MessagesBundle.getMessage("lecture")),
    WRITE(MessagesBundle.getMessage("ecriture")),
    DELETE(MessagesBundle.getMessage("supprimer"));
    
    private String name ;
    
    private EnumStatutAutorisation(String name){this.name = name;}

    @Override
    public String toString() {
        return name ;
    }
    
    
}
