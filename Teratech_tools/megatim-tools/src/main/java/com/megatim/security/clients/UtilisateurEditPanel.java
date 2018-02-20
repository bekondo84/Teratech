
package com.megatim.security.clients;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.annotations.Champ;
import com.megatim.common.clients.CustomComboBox;
import com.megatim.common.services.IocContext;
import com.megatim.common.utilities.MessagesBundle;
import javax.swing.JPasswordField;


/**
 * Panel d'edition UtilisateurEditPanel

 * @since Sun Sep 18 21:53:37 CEST 2016
 * 
 */
public class UtilisateurEditPanel
    extends JPanel
{

    private MessagesBundle bundle;
    private MessagesBundle resourcebundle;
    public final static String FRAME_NAME = ("com.megatim.security.clients.UtilisateurIFrame");
    private IocContext context = new IocContext();
    @Champ(mappedBy = "login", type = java.lang.String.class, nullable = false, update = false, errorMessageField = "lbmlogin", errorMessage = "utilisateur.login.error")
    private JTextField login;
    private JLabel lblogin;
    private JLabel lbmlogin;
    @Champ(mappedBy = "nom", type = java.lang.String.class, errorMessageField = "lbmnom", errorMessage = "utilisateur.nom.error")
    private JTextField nom;
    private JLabel lbnom;
    private JLabel lbmnom;
    @Champ(mappedBy = "prenom", type = java.lang.String.class, errorMessageField = "lbmprenom", errorMessage = "utilisateur.prenom.error")
    private JTextField prenom;
    private JLabel lbprenom;
    private JLabel lbmprenom;
    @Champ(mappedBy = "password", type = java.lang.String.class, errorMessageField = "lbmpassword", errorMessage = "utilisateur.password.error")
    private JPasswordField password;
    private JLabel lbpassword;
    private JLabel lbmpassword;
    @Champ(mappedBy = "profil", type = com.megatim.security.model.Profil.class, errorMessageField = "lbmprofil", errorMessage = "utilisateur.profil.error")
    private CustomComboBox profil;
    private JLabel lbprofil;
    private JLabel lbmprofil;

    public UtilisateurEditPanel() {
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
    public JPasswordField getPassword() {
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

    public JLabel getLbpassword() {
        return lbpassword;
    }

    public JLabel getLbmpassword() {
        return lbmpassword;
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
        password = new JPasswordField() ;
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
        col1h.addComponent(lblogin, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmlogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colloginv = (layout.createParallelGroup() );
        colloginv.addComponent(lblogin, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colloginv.addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colloginv.addComponent( lbmlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colloginv) ; 
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
        col1h.addComponent(lbprenom, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmprenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colprenomv = (layout.createParallelGroup() );
        colprenomv.addComponent(lbprenom, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colprenomv.addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colprenomv.addComponent( lbmprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colprenomv) ; 
        // Positionnement des elements 
        col1h.addComponent(lbpassword, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmpassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colpasswordv = (layout.createParallelGroup() );
        colpasswordv.addComponent(lbpassword, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colpasswordv.addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colpasswordv.addComponent( lbmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colpasswordv) ; 
        // Positionnement des elements 
        col1h.addComponent(lbprofil, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(profil, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmprofil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colprofilv = (layout.createParallelGroup() );
        colprofilv.addComponent(lbprofil, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colprofilv.addComponent(profil, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colprofilv.addComponent( lbmprofil, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colprofilv) ; 
        hg.addGroup(col1h) ; 
        hg.addGroup(col2h) ; 
        hg.addGroup(col3h) ; 
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
