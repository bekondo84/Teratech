
package com.megatim.security.clients;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.megatim.common.annotations.Search;
import com.megatim.common.services.IocContext;
import com.megatim.common.utilities.MessagesBundle;


/**
 * Panel de recherche ProfilCriteriaPanel

 * @since Sun Sep 18 21:53:38 CEST 2016
 * 
 */
public class ProfilCriteriaPanel
    extends JPanel
{

    private MessagesBundle bundle;
    private MessagesBundle resourcebundle;
    public final static String FRAME_NAME = ("com.megatim.security.clients.ProfilIFrame");
    private IocContext context = new IocContext();
    @Search(field = "nom", type = java.lang.String.class)
    private JTextField nom;
    private JLabel lbnom;
    private JLabel lbmnom;
    @Search(field = "description", type = java.lang.String.class)
    private JTextField description;
    private JLabel lbdescription;
    private JLabel lbmdescription;

    public ProfilCriteriaPanel() {
        initComponents() ; 
    }

    /**
     * Getter
     * 
     * @return
     *     javax.swing.JTextField
     */
    public JTextField getNom() {
        return nom;
    }

    /**
     * Getter
     * 
     * @return
     *     javax.swing.JTextField
     */
    public JTextField getDescription() {
        return description;
    }

    /**
     * Methode permettant d'initialiser les composants
     * 
     */
    private void initComponents() {
        nom = new JTextField() ;
        nom.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbnom = new JLabel();
        lbmnom = new JLabel();
         lbnom.setText(MessagesBundle.getMessage( "profil.nom"));
        description = new JTextField() ;
        description.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbdescription = new JLabel();
        lbmdescription = new JLabel();
         lbdescription.setText(MessagesBundle.getMessage( "profil.description"));
        GroupLayout layout = new GroupLayout((this));
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.ParallelGroup col1h = (layout.createParallelGroup() );
        GroupLayout.ParallelGroup col2h = (layout.createParallelGroup() );
        GroupLayout.ParallelGroup col3h = (layout.createParallelGroup() );
        GroupLayout.ParallelGroup col1v;
        GroupLayout.ParallelGroup col2v;
        GroupLayout.ParallelGroup col3v;
        GroupLayout.SequentialGroup hg = (layout.createSequentialGroup());
        GroupLayout.ParallelGroup hgp = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        hgp.addGroup(hg);
        layout.setHorizontalGroup(hgp);
        GroupLayout.ParallelGroup hv = (layout.createParallelGroup());
        GroupLayout.SequentialGroup sg = layout.createSequentialGroup();
        layout.setVerticalGroup(hv) ;
        hv.addGroup(sg);
        // Positionnement des elements 
        col1h.addComponent(lbnom, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmnom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colnomv = (layout.createParallelGroup() );
        colnomv.addComponent(lbnom, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colnomv.addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colnomv.addComponent( lbmnom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colnomv) ; 
        // Positionnement des elements 
        col1h.addComponent(lbdescription, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmdescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup coldescriptionv = (layout.createParallelGroup() );
        coldescriptionv.addComponent(lbdescription, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        coldescriptionv.addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        coldescriptionv.addComponent( lbmdescription, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(coldescriptionv) ; 
        hg.addGroup(col1h) ; 
        hg.addGroup(col2h) ; 
        hg.addGroup(col3h) ; 
    }

}
