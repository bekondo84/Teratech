
package com.megatim.security.clients;

import javax.swing.GroupLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.megatim.common.annotations.Champ;
import com.megatim.common.services.IocContext;
import com.megatim.common.utilities.MessagesBundle;


/**
 * Panel d'edition AutorisationEditPanel

 * @since Sun Sep 18 21:53:38 CEST 2016
 * 
 */
public class AutorisationEditPanel
    extends JPanel
{

    private MessagesBundle bundle;
    private MessagesBundle resourcebundle;
    public final static String FRAME_NAME = ("com.megatim.security.clients.AutorisationIFrame");
    private IocContext context = new IocContext();
    @Champ(mappedBy = "id", type = java.lang.Long.class, nullable = false, update = false, errorMessageField = "lbmid", errorMessage = "autorisation.id.error")
    private JFormattedTextField id;
    private JLabel lbid;
    private JLabel lbmid;
    @Champ(mappedBy = "nomAutorisation", type = java.lang.String.class, errorMessageField = "lbmnomautorisation", errorMessage = "autorisation.nomautorisation.error")
    private JTextField nomautorisation;
    private JLabel lbnomautorisation;
    private JLabel lbmnomautorisation;
    @Champ(mappedBy = "description", type = java.lang.String.class, errorMessageField = "lbmdescription", errorMessage = "autorisation.description.error")
    private JTextField description;
    private JLabel lbdescription;
    private JLabel lbmdescription;
    @Champ(mappedBy = "statut", type = java.lang.String.class, errorMessageField = "lbmstatut", errorMessage = "autorisation.statut.error")
    private JTextField statut;
    private JLabel lbstatut;
    private JLabel lbmstatut;

    public AutorisationEditPanel() {
        initComponents() ; 
    }

    /**
     * Getter
     * 
     * @return
     *     javax.swing.JTextField
     */
    public JTextField getId() {
        return id;
    }

    /**
     * Getter
     * 
     * @return
     *     javax.swing.JTextField
     */
    public JTextField getNomAutorisation() {
        return nomautorisation;
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
     * Getter
     * 
     * @return
     *     javax.swing.JTextField
     */
    public JTextField getStatut() {
        return statut;
    }

    /**
     * Methode permettant d'initialiser les composants
     * 
     */
    private void initComponents() {
        id = new JFormattedTextField() ;
        id.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbid = new JLabel();
        lbmid = new JLabel();
         lbid.setText(MessagesBundle.getMessage( "autorisation.id"));
        nomautorisation = new JTextField() ;
        nomautorisation.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbnomautorisation = new JLabel();
        lbmnomautorisation = new JLabel();
         lbnomautorisation.setText(MessagesBundle.getMessage( "autorisation.nomautorisation"));
        description = new JTextField() ;
        description.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbdescription = new JLabel();
        lbmdescription = new JLabel();
         lbdescription.setText(MessagesBundle.getMessage( "autorisation.description"));
        statut = new JTextField() ;
        statut.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbstatut = new JLabel();
        lbmstatut = new JLabel();
         lbstatut.setText(MessagesBundle.getMessage( "autorisation.statut"));
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
        col1h.addComponent(lbid, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colidv = (layout.createParallelGroup() );
        colidv.addComponent(lbid, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colidv.addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colidv.addComponent( lbmid, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colidv) ; 
        // Positionnement des elements 
        col1h.addComponent(lbnomautorisation, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(nomautorisation, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmnomautorisation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colnomautorisationv = (layout.createParallelGroup() );
        colnomautorisationv.addComponent(lbnomautorisation, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colnomautorisationv.addComponent(nomautorisation, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colnomautorisationv.addComponent( lbmnomautorisation, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colnomautorisationv) ; 
        // Positionnement des elements 
        col1h.addComponent(lbdescription, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmdescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup coldescriptionv = (layout.createParallelGroup() );
        coldescriptionv.addComponent(lbdescription, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        coldescriptionv.addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        coldescriptionv.addComponent( lbmdescription, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(coldescriptionv) ; 
        // Positionnement des elements 
        col1h.addComponent(lbstatut, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(statut, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmstatut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colstatutv = (layout.createParallelGroup() );
        colstatutv.addComponent(lbstatut, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colstatutv.addComponent(statut, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colstatutv.addComponent( lbmstatut, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colstatutv) ; 
        hg.addGroup(col1h) ; 
        hg.addGroup(col2h) ; 
        hg.addGroup(col3h) ; 
    }

}
