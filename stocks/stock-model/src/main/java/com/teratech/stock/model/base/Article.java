/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.stock.model.base;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_ART")
public class Article extends BaseElement implements Serializable,Comparable<Article>{

    @Predicate(label = "Photo",target = "image")
    private String image ;
    
    @Predicate(label = "Reference interne",optional = false,unique = true,search = true)
    private String code ;
    
    @Predicate(label = "Intitulé",search = true)
    private String intitule ;
    
    @Predicate(label = "Article vendu?",type = Boolean.class,search = true)
    private Boolean vendu = Boolean.FALSE;
    
     @Predicate(label = "Article acheté?",type = Boolean.class,search = true)
    private Boolean achete = Boolean.FALSE;
    
    @Predicate(label = "Type d'article",target = "combobox",values = "Article stockable;Utilisation directe;Service;Immobilisation",group =true,groupName = "group1",groupLabel = "Informations générales")
    private String type = "0";    
    
    @Predicate(label = "Actif",type = Boolean.class,group =true,groupName = "group1",groupLabel = "Informations générales")
    private Boolean actif = Boolean.TRUE;    
    
    @ManyToOne
    @JoinColumn(name = "FAAR_ID")
    @Predicate(label = "Famille articles",type = FamilleArticle.class,target = "many-to-one",group =true,groupName = "group1",groupLabel = "Informations générales")
    private FamilleArticle famille ;
    
    @Predicate(label = "Code barre EAN13",group =true,groupName = "group1",groupLabel = "Informations générales")
    private String codebarre ;
    
    @Predicate(label = "Prix d'achat (HT)",type = Double.class,group =true,groupName = "group1",groupLabel = "Informations générales")
    private Double puachat ;
    
    @ManyToOne
    @JoinColumn(name = "UNAC_ID")
    @Predicate(label = "Unité d'achat",type = UniteAchat.class,target = "many-to-one",search = true)
    private UniteAchat uniteachat ;
    
    @Predicate(label = "Prix de vente (HT)",type = Double.class,group =true,groupName = "group1",groupLabel = "Informations générales")
    private Double puvente ;
    
    @ManyToOne
    @JoinColumn(name = "UNGE_ID")
    @Predicate(label = "Unité de vente",type = UniteAchat.class,target = "many-to-one",search = true)
    private UniteGestion unitevente ;
    
   @Predicate(label = "Réference du fabriquant",group =true,groupName = "group1",groupLabel = "Informations générales")
    private String reference ;  
    
    
    

    /**
     * 
     */
    public Article() {
    }

    public Article(String image, String code, String intitule, FamilleArticle famille, String codebarre, Double puachat, UniteAchat uniteachat, Double puvente, UniteGestion unitevente, String reference) {
        this.image = image;
        this.code = code;
        this.intitule = intitule;
        this.famille = famille;
        this.codebarre = codebarre;
        this.puachat = puachat;
        this.uniteachat = uniteachat;
        this.puvente = puvente;
        this.unitevente = unitevente;
        this.reference = reference;
    }

    public Article(String image, String code, String intitule, FamilleArticle famille, String codebarre, Double puachat, UniteAchat uniteachat, Double puvente, UniteGestion unitevente, String reference, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.image = image;
        this.code = code;
        this.intitule = intitule;
        this.famille = famille;
        this.codebarre = codebarre;
        this.puachat = puachat;
        this.uniteachat = uniteachat;
        this.puvente = puvente;
        this.unitevente = unitevente;
        this.reference = reference;
    }
    
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getVendu() {
        return vendu;
    }

    public void setVendu(Boolean vendu) {
        this.vendu = vendu;
    }

    public Boolean getAchete() {
        return achete;
    }

    public void setAchete(Boolean achete) {
        this.achete = achete;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public FamilleArticle getFamille() {
        return famille;
    }

    public void setFamille(FamilleArticle famille) {
        this.famille = famille;
    }

    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }

    public Double getPuachat() {
        return puachat;
    }

    public void setPuachat(Double puachat) {
        this.puachat = puachat;
    }

    public UniteAchat getUniteachat() {
        return uniteachat;
    }

    public void setUniteachat(UniteAchat uniteachat) {
        this.uniteachat = uniteachat;
    }

    public Double getPuvente() {
        return puvente;
    }

    public void setPuvente(Double puvente) {
        this.puvente = puvente;
    }

    public UniteGestion getUnitevente() {
        return unitevente;
    }

    public void setUnitevente(UniteGestion unitevente) {
        this.unitevente = unitevente;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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
        return "ARTICLES"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "ARTICLE"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public int compareTo(Article o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
