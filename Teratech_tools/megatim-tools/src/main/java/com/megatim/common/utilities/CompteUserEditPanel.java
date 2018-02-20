
package com.megatim.common.utilities;

import javax.swing.GroupLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.megatim.common.annotations.Champ;
import com.megatim.common.services.IocContext;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class CompteUserEditPanel
    extends JPanel
{

     public final static String FRAME_NAME = ("com.megatim.views.test.CompteUserIFrame");
    private IocContext context = new IocContext();
    @Champ(mappedBy = "login", type = java.lang.String.class, nullable = false)
    private JTextField login;
    private JLabel lblogin;
    private JLabel lbmlogin;
    @Champ(mappedBy = "password", type = java.lang.String.class, nullable = false, length = 12, update = true)
    private JTextField password;
    private JLabel lbpassword;
    private JLabel lbmpassword;
    @Champ(mappedBy = "age", type = double.class)
    private JFormattedTextField age;
    private JLabel lbage;
    private JLabel lbmage;
    @Champ(mappedBy = "user", type = com.megatim.model.test.CompteUser.class)
    private JComboBox user;
    private JLabel lbuser;
    private JLabel lbmuser;
    @Champ(mappedBy = "desactiver", type = java.lang.Boolean.class)
    private JCheckBox desactiver;
    private JLabel lbdesactiver;
    private JLabel lbmdesactiver;
    @Champ(mappedBy = "values", type = java.util.List.class)
    private AbstractTablePanel values;

    public CompteUserEditPanel() {
        initComponents() ; 
    }

    public JTextField getLogin() {
        return login;
    }

    public JTextField getPassword() {
        return password;
    }

    public JFormattedTextField getAge() {
        return age;
    }

    public JComboBox getUser() {
        return user;
    }

    public JCheckBox getDesactiver() {
        return desactiver;
    }

    

    private void initComponents() {
        login = new JTextField() ;
        lblogin = new JLabel();
        lbmlogin = new JLabel();
         lblogin.setText(MessagesBundle.getMessage( "compteuser.login"));
        login.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        password = new JTextField() ;
        lbpassword = new JLabel();
        lbmpassword = new JLabel();
         lbpassword.setText(MessagesBundle.getMessage( "compteuser.password"));
        password.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        age = new JFormattedTextField() ;
        lbage = new JLabel();
        lbmage = new JLabel();
         lbage.setText(MessagesBundle.getMessage( "compteuser.age"));
        age.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        user = new JComboBox() ;
        lbuser = new JLabel();
        lbmuser = new JLabel();
         lbuser.setText(MessagesBundle.getMessage( "compteuser.user"));
        user.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        desactiver = new JCheckBox() ;
        lbdesactiver = new JLabel();
        lbmdesactiver = new JLabel();
         lbdesactiver.setText(MessagesBundle.getMessage( "compteuser.desactiver"));
        desactiver.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        values = new CompteUserTablePanel();
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
        col1h.addComponent(lbpassword, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmpassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colpasswordv = (layout.createParallelGroup() );
        colpasswordv.addComponent(lbpassword, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colpasswordv.addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colpasswordv.addComponent( lbmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colpasswordv) ; 
        // Positionnement des elements 
        col1h.addComponent(lbage, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup colagev = (layout.createParallelGroup() );
        colagev.addComponent(lbage, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        colagev.addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        colagev.addComponent( lbmage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(colagev) ; 
        // Positionnement des elements 
        col1h.addComponent(lbuser, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmuser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup coluserv = (layout.createParallelGroup() );
        coluserv.addComponent(lbuser, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        coluserv.addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        coluserv.addComponent( lbmuser, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(coluserv) ; 
        // Positionnement des elements 
        col1h.addComponent(lbdesactiver, javax.swing.GroupLayout.DEFAULT_SIZE , 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        col2h.addComponent(desactiver, javax.swing.GroupLayout.PREFERRED_SIZE , 200 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        col3h.addComponent( lbmdesactiver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) ;
        GroupLayout.ParallelGroup coldesactiverv = (layout.createParallelGroup() );
        coldesactiverv.addComponent(lbdesactiver, javax.swing.GroupLayout.DEFAULT_SIZE, 23, javax.swing.GroupLayout.DEFAULT_SIZE) ;
        coldesactiverv.addComponent(desactiver, javax.swing.GroupLayout.PREFERRED_SIZE , 23 , javax.swing.GroupLayout.PREFERRED_SIZE) ;
        coldesactiverv.addComponent( lbmdesactiver, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE) ;
        sg.addGroup(coldesactiverv) ; 
        // Positionnement des elements 
        hgp.addComponent(values, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        sg.addComponent(values, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
        hg.addGroup(col1h) ; 
        hg.addGroup(col2h) ; 
        hg.addGroup(col3h) ; 
    }


}
