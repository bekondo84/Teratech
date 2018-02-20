/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.stock.model.base;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_ENTR")
public class Entrepot extends BaseElement implements Serializable,Comparable<Entrepot>{
    
    @Predicate(label = "Code dépôt",optional = false,unique = true)
    private String code ;
    
    @Predicate(label = "Magasin principal?" ,type = Boolean.class)
    private Boolean principal = Boolean.FALSE;
    
    @Predicate(label = "Intitulé")
    private String intitule ;
    
    @Predicate(label = "Adresse",group = true,groupName = "group1",groupLabel = "Complement")
    private String adresse1 ;
    
    @Predicate(label = "Complement",group = true,groupName = "group1",groupLabel = "Complement")
    private String adresse2;
    
    @Predicate(label = "Boîte postale",group = true,groupName = "group1",groupLabel = "Complement")
    private String cp ;
    
    @Predicate(label = "Ville",group = true,groupName = "group1",groupLabel = "Complement")
    private String ville ;
    
    @ManyToOne
    @JoinColumn(name = "REGI_ID")
    @Predicate(label = "Région",type = Region.class,target = "many-to-one",group = true,groupName = "group1",groupLabel = "Complement")
    private Region region ;
    
    @ManyToOne
    @JoinColumn(name = "PAYS_ID")
    @Predicate(label = "Pays",type = Pays.class,target = "many-to-one",group = true,groupName = "group1",groupLabel = "Complement")
    private Pays pays ;
    
    @ManyToOne
    @JoinColumn(name = "TIER_ID")
    @Predicate(label = "Responsable",type = Tier.class,target = "many-to-one",group = true,groupName = "group1",groupLabel = "Complement")
    private Tier responsable ;   
    
    
    @Predicate(label = "Téléphone",target = "tel",group = true,groupName = "group1",groupLabel = "Complement")
    private String tel;
    
     @Predicate(label = "Mobile",target = "tel",group = true,groupName = "group1",groupLabel = "Complement")
    private String mobile;
    
    @Predicate(label = "Courriel",target = "email",group = true,groupName = "group1",groupLabel = "Complement")
    private String email;
    
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "CONT_ID")
    @Predicate(label = "contact",type = Contact.class,target = "one-to-many",group = true,groupName = "group2",groupLabel = "Contacts")
    private List<Contact> contacts = new ArrayList<Contact>();
    
    @Predicate(label = "c",target = "textarea",group = true,groupName = "group3",groupLabel = "Commentaire")
    private String commentaire ;

    /**
     * 
     * @param code
     * @param intitule
     * @param adresse1
     * @param adresse2
     * @param cp
     * @param ville
     * @param region
     * @param pays
     * @param responsable
     * @param tel
     * @param mobile
     * @param email
     * @param commentaire 
     */
    public Entrepot(String code, String intitule, String adresse1, String adresse2, String cp, String ville, Region region, Pays pays, Tier responsable, String tel, String mobile, String email, String commentaire) {
        this.code = code;
        this.intitule = intitule;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.cp = cp;
        this.ville = ville;
        this.region = region;
        this.pays = pays;
        this.responsable = responsable;
        this.tel = tel;
        this.mobile = mobile;
        this.email = email;
        this.commentaire = commentaire;
    }

    /**
     * 
     * @param code
     * @param intitule
     * @param adresse1
     * @param adresse2
     * @param cp
     * @param ville
     * @param region
     * @param pays
     * @param responsable
     * @param tel
     * @param mobile
     * @param email
     * @param commentaire
     * @param id
     * @param designation
     * @param moduleName 
     */
    public Entrepot(String code, String intitule, String adresse1, String adresse2, String cp, String ville, Region region, Pays pays, Tier responsable, String tel, String mobile, String email, String commentaire, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.code = code;
        this.intitule = intitule;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.cp = cp;
        this.ville = ville;
        this.region = region;
        this.pays = pays;
        this.responsable = responsable;
        this.tel = tel;
        this.mobile = mobile;
        this.email = email;
        this.commentaire = commentaire;
    }

    public Entrepot() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Tier getResponsable() {
        return responsable;
    }

    public void setResponsable(Tier responsable) {
        this.responsable = responsable;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String getDesignation() {
        return code+" - "+intitule; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechstock"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "ENTREPÔTS"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "ENTREPÔT"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
   
    @Override
    public int compareTo(Entrepot o) {
         //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
