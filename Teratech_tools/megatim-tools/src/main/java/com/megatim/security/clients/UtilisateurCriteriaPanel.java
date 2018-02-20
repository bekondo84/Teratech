
package com.megatim.security.clients;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.annotations.Search;
import com.megatim.common.clients.CustomComboBox;
import com.megatim.common.services.IocContext;
import com.megatim.common.utilities.MessagesBundle;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Panel de recherche UtilisateurCriteriaPanel

 * @since Sun Sep 18 21:53:37 CEST 2016
 * 
 */
public class UtilisateurCriteriaPanel
    extends JPanel
{

    private MessagesBundle bundle;
    private MessagesBundle resourcebundle;
    public final static String FRAME_NAME = ("com.megatim.security.clients.UtilisateurIFrame");
    private IocContext context = new IocContext();
    @Search(field = "login", type = java.lang.String.class)
    private JTextField login;
    private JLabel lblogin;
    private JLabel lbmlogin;
    @Search(field = "nom", type = java.lang.String.class)
    private JTextField nom;
    private JLabel lbnom;
    private JLabel lbmnom;
    @Search(field = "prenom", type = java.lang.String.class)
    private JTextField prenom;
    private JLabel lbprenom;
    private JLabel lbmprenom;
    @Search(field = "password", type = java.lang.String.class)
    private JTextField password;
    private JLabel lbpassword;
    private JLabel lbmpassword;
    @Search(field = "profil", type = com.megatim.security.model.Profil.class)
    private CustomComboBox profil;
    private JLabel lbprofil;
    private JLabel lbmprofil;

    public UtilisateurCriteriaPanel() {
        initComponents() ; 
    }

    /**
     * Getter
     * 
     * @return
     *     javax.swing.JTextField
     */
    public JTextField getLogin() {
        return login;
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
    public JTextField getPrenom() {
        return prenom;
    }

    /**
     * Getter
     * 
     * @return
     *     javax.swing.JTextField
     */
    public JTextField getPassword() {
        return password;
    }

    /**
     * Getter
     * 
     * @return
     *     com.megatim.common.clients.CustomComboBox
     */
    public CustomComboBox getProfil() {
        return profil;
    }

    /**
     * Methode permettant d'initialiser les composants
     * 
     */
    private void initComponents() {
        login = new JTextField() ;
        login.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lblogin = new JLabel();
        lbmlogin = new JLabel();
         lblogin.setText(MessagesBundle.getMessage( "utilisateur.login"));
        nom = new JTextField() ;
        nom.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbnom = new JLabel();
        lbmnom = new JLabel();
         lbnom.setText(MessagesBundle.getMessage( "utilisateur.nom"));
        prenom = new JTextField() ;
        prenom.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbprenom = new JLabel();
        lbmprenom = new JLabel();
         lbprenom.setText(MessagesBundle.getMessage( "utilisateur.prenom"));
        password = new JTextField() ;
        password.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lbpassword = new JLabel();
        lbmpassword = new JLabel();
         lbpassword.setText(MessagesBundle.getMessage( "utilisateur.password"));
        profil = new CustomComboBox() ;
        profil.setManager(getProfilManager());
        profil.loadData() ;
        lbprofil = new JLabel();
        lbmprofil = new JLabel();
         lbprofil.setText(MessagesBundle.getMessage( "utilisateur.profil"));
         GroupLayout layout = new GroupLayout((this));
         layout.setAutoCreateGaps(true);
         layout.setAutoCreateContainerGaps(true);
         this.setLayout(layout);
         layout.setHorizontalGroup(
                 layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                 .addGroup(layout.createSequentialGroup()
 	                .addComponent(lbnom, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
 	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
 	                .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)	)
 	                .addGap(0, 2, 10)
                 .addGroup(layout.createSequentialGroup()
                     .addComponent(lbprofil  ,javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                     .addComponent(profil, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                       
                     ) 
                
             );
             layout.setVerticalGroup(
                 layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                 .addGroup(layout.createSequentialGroup()
                 		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                 .addComponent(lbnom)
                                 .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE) 
                             .addGap(0, 10, Short.MAX_VALUE)	
                             )
                     .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     	.addComponent(lbprofil)
                     	 .addComponent(profil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     		)
                   )
                     
             );
    }

    /**
     * Methode permettant de renvoyer un manager
     * 
     * @return
     *     com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager
     */
    private GenericManager getProfilManager() {
        try {
             return (GenericManager)context.lookup("com.megatim.security.core.impl.Profil.ProfilManagerImpl");
        } catch (Exception _x) {
            return null;
        }
    }

}
