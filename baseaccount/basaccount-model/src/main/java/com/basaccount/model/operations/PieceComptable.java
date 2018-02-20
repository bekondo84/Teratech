/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.operations;

import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.JournalComptable;
import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import com.megatim.common.annotations.TableFooter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_PIECECMLE")
public class PieceComptable extends BaseElement implements Serializable,Comparable<PieceComptable>{
    @ManyToOne
    @JoinColumn(name = "JRN_ID")
    @Predicate(label = "JOURNAL COMPTABLE",type = JournalComptable.class,target = "many-to-one",optional = false,updatable = false,search = true)
    private JournalComptable journal;
    
    @Temporal(TemporalType.DATE)
    @Predicate(label = "DATE PIECE",type = Date.class,target = "date",optional = false,search = true)
    private Date datePiece ;
   
    @Predicate(label = "N° DE PIECE" ,optional = false,updatable = false,search = true,unique = true)
    @Column(unique = true)
    private String code ;
    
    @Predicate(label = "LIBELLE",search = true)
    private String libelle ;
    
    @Predicate(label = "DEBIT",type = BigDecimal.class,updatable = false,search = true,editable = false,hide = true)
    private BigDecimal debit = BigDecimal.ZERO;
    
    @Predicate(label = "CREDIT",type = BigDecimal.class,updatable = false , search = true,editable = false,hide = true)
    private BigDecimal credit = BigDecimal.ZERO;
    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "PIEC_ID")
    @Predicate(label="Ecritures",group = true,groupLabel = "ECRITURES",groupName = "group1",type = EcritureComptable.class,target = "one-to-many",customfooter = true)
    @TableFooter(value = "<tr style='border:none;'> <td></td><td></td><td></td><td></td><td>Total Debit</td><td></td> <td>debit</td> </tr> <tr style='border:none;'> <td></td><td></td><td></td><td></td><td>Total Credit</td><td></td> <td>credit</td> </tr> <tr style='border:none;'> <td></td><td></td><td></td><td></td><td>Solde</td><td></td><td>debit,-,credit</td> </tr>")
    private List<EcritureComptable> ecritures = new ArrayList<EcritureComptable>();
    
    @ManyToOne
    @JoinColumn(name = "EXER_ID")
    private ExerciceComptable exercice;

    /**
     * 
     * @param libelle
     * @param journal 
     */
    public PieceComptable(String libelle, JournalComptable journal) {
        this.libelle = libelle;
        this.journal = journal;
    }

    /**
     * 
     * @param libelle
     * @param journal
     * @param id
     * @param designation
     * @param moduleName 
     */
    public PieceComptable(String libelle, JournalComptable journal, long id, String designation, String moduleName) {
        super(id, designation, moduleName);
        this.libelle = libelle;
        this.journal = journal;
    }

    public PieceComptable(PieceComptable piece) {
        super(piece.id, piece.designation, piece.moduleName);
        this.code = piece.code;
        this.libelle = piece.libelle;
        this.journal = piece.journal;
        this.datePiece = piece.datePiece;
        this.debit = piece.debit;
        this.credit = piece.credit;
        this.exercice = piece.exercice;
    }
    /**
     * 
     */
    public PieceComptable() {
    }

    
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public JournalComptable getJournal() {
        return journal;
    }

    public void setJournal(JournalComptable journal) {
        this.journal = journal;
    }

    public List<EcritureComptable> getEcritures() {
        return ecritures;
    }

    public void setEcritures(List<EcritureComptable> ecritures) {
        this.ecritures = ecritures;
    }

    public Date getDatePiece() {
        return datePiece;
    }

    public void setDatePiece(Date datePiece) {
        this.datePiece = datePiece;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    @Override
    public String getDesignation() {
        return libelle; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "PIECES COMPTABLES"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "PIECE COMPTABLE"; //To change body of generated methods, choose Tools | Templates.
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ExerciceComptable getExercice() {
        return exercice;
    }

    public void setExercice(ExerciceComptable exercice) {
        this.exercice = exercice;
    }

    @Override
    public boolean isActivatefollower() {
         //To change body of generated methods, choose Tools | Templates.
        return true;
    }

    @Override
    public boolean isActivefilelien() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return "piececomptable01234"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(PieceComptable o) {
         //To change body of generated methods, choose Tools | Templates.
        return journal.compareTo(o.journal);
    }
    
}
