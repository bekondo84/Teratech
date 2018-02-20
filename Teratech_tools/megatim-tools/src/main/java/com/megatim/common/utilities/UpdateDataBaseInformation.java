/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

import com.megatim.common.clients.BannierePanel;
import com.megatim.common.clients.CommonsUtilities;
import com.megatim.common.clients.Messages;
import com.megatim.common.clients.NotificationType;
import com.megatim.common.enumeration.JavaTypeConnection;
import com.megatim.security.ifaces.client.ClientInterfaceSecurity;
import com.megatim.security.manager.connection.SecurityCenter;
import java.awt.BorderLayout;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class UpdateDataBaseInformation extends javax.swing.JFrame {
    
    //Variables d'instance
    private Map dataBaseInfo;
    private String dataBaseFileName;
    private String titreBanniere;
    private String titreFenetre;
    private Image imageBanniere;
    private Image logoFenetre;
    private ClientInterfaceSecurity clientInterfaceSecurity;
    
    /**
     * Creates new form UpdateDataBaseInformation
     */
    public UpdateDataBaseInformation(Map dataBaseInfo, String dataBaseFileName, String titreBanniere, String titreFenetre, Image imageBanniere, Image logoFenetre) {
        
        //Variables
        this.dataBaseFileName = dataBaseFileName;
        this.dataBaseInfo = dataBaseInfo;
        this.titreBanniere = titreBanniere;
        this.titreFenetre = titreFenetre;
        this.imageBanniere = imageBanniere;
        this.logoFenetre = logoFenetre;
        
        //Appel de methodes
        initComponents();
        getBanniere(titreBanniere, titreFenetre, imageBanniere, logoFenetre);
        publishDataBaseInfosToFields();
    }
    
    /**
     * Creates new form UpdateDataBaseInformation
     */
    public UpdateDataBaseInformation(Map dataBaseInfo, String dataBaseFileName, String titreBanniere, String titreFenetre, Image imageBanniere, Image logoFenetre, ClientInterfaceSecurity clientInterfaceSecurity) {
        
        //Variables
        this.dataBaseFileName = dataBaseFileName;
        this.dataBaseInfo = dataBaseInfo;
        this.titreBanniere = titreBanniere;
        this.titreFenetre = titreFenetre;
        this.imageBanniere = imageBanniere;
        this.logoFenetre = logoFenetre;
        this.clientInterfaceSecurity = clientInterfaceSecurity;
        
        //Appel de methodes
        initComponents();
        getBanniere(titreBanniere, titreFenetre, imageBanniere, logoFenetre);
        publishDataBaseInfosToFields();
    }
    
    /**
     * Hydrater la fenetre avec les donnees
     */
    private void publishDataBaseInfosToFields(){
        
        //Type de BD
        getTypeBD(cbTypeDataBase, (JavaTypeConnection)dataBaseInfo.get("javaTypeConnection"));
        
        //Adresse ip du serveur
        txtHost.setText((String)dataBaseInfo.get("host"));
        
        //Port du serveur
        txtPort.setText((String)dataBaseInfo.get("port"));
        
        //Nom de la base des donnees
        txtBdName.setText((String)dataBaseInfo.get("name"));
        
        //Nom utilisateur
        txtUser.setText((String)dataBaseInfo.get("username"));
        
        //Mot de passe
        txtPassword.setText((String)dataBaseInfo.get("password"));
    }
    
    /**
     * Cette methode permet de recuperer le type de BD
     * @param cb
     * @param type 
     */
    private void getTypeBD(JComboBox cb, JavaTypeConnection type){
        
        if(type.equals(JavaTypeConnection.ORACLE)){
            cb.setSelectedIndex(0);
        }else if(type.equals(JavaTypeConnection.MYSQL)){
            cb.setSelectedIndex(1);
        }else if(type.equals(JavaTypeConnection.SQL_SERVER)){
            cb.setSelectedIndex(2);
        }
    }
    
    /**
     * Cette methode nous permet à partir du combox et de l'index de l'item
     * actuelllement selectionner, de recuperer le type de BD 
     * @param cb
     * @return 
     */
    private JavaTypeConnection getTypeBD(JComboBox cb){
        
        switch(cb.getSelectedIndex()){
            case 0 : 
                return JavaTypeConnection.ORACLE;
            case 1 :
                return JavaTypeConnection.MYSQL;
            case 2 :
                return JavaTypeConnection.SQL_SERVER;
            case 3 :
                return JavaTypeConnection.POSGRES_SQL;
            default :
                return null;
        }
    }
    
    /**
     * Afficher la banniere
     */
    public void getBanniere(String titreBanniere, String titreFenetre, Image imageBanniere, Image logoFenetre) {
        
        //Definition des labels
        JLabel lblIcon = new javax.swing.JLabel();
        JLabel lblTitle = new javax.swing.JLabel();
        
        //Intitialisation du titre
        this.setTitle(titreFenetre);
        
        //traitement du panel Header
        this.jPanelBanniere.setLayout(new BorderLayout());
        
        //Set du logo de la fenetre
        this.setIconImage(logoFenetre);

        JPanel entetePanel = new BannierePanel(imageBanniere);
        lblIcon.setIcon(getImage());
        entetePanel.add(lblIcon);
        lblTitle.setFont(CommonsUtilities.getFontTitreFrame());
        lblTitle.setForeground(CommonsUtilities.COULEUR_TITRE_FRAME);
        lblTitle.setText(titreBanniere);
        lblTitle.setName("lblTitle"); // NOI18N
        entetePanel.add(lblTitle);
        this.jPanelBanniere.add(entetePanel, BorderLayout.CENTER);
    }
    
    /**
     * Methode permettant de retourner l'icone de la fenetre
     *
     * @return javax.swing.ImageIcon
     */
    public ImageIcon getImage() {
        try {
            ImageIcon icon = null;
            icon = CommonsUtilities.getImageLogin();
            return icon;
        } catch (Exception ex) {;
        }
        return null;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBanniere = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        txtBdName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbTypeDataBase = new javax.swing.JComboBox();
        txtPassword = new javax.swing.JPasswordField();
        btnTesterConnexion = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnAnnuler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout jPanelBanniereLayout = new javax.swing.GroupLayout(jPanelBanniere);
        jPanelBanniere.setLayout(jPanelBanniereLayout);
        jPanelBanniereLayout.setHorizontalGroup(
            jPanelBanniereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelBanniereLayout.setVerticalGroup(
            jPanelBanniereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuration Base des données"));

        jLabel1.setText("Base des donnees");

        jLabel2.setText("Adresse Serveur BD");

        jLabel3.setText("Numero du Port");

        jLabel4.setText("Nom de la Base des donnees");

        jLabel5.setText("Nom Utilisateur");

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });

        jLabel6.setText("Mot de passe");

        cbTypeDataBase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ORACLE", "MYSQL", "SQL_SERVER", "POSGRE_SQL" }));

        btnTesterConnexion.setText("Tester la connexion");
        btnTesterConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTesterConnexionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTesterConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUser)
                            .addComponent(txtBdName)
                            .addComponent(txtPort)
                            .addComponent(txtHost)
                            .addComponent(cbTypeDataBase, 0, 208, Short.MAX_VALUE)
                            .addComponent(txtPassword))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbTypeDataBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBdName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btnTesterConnexion))
        );

        btnSave.setText("Enregistrer");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnAnnuler.setText("Annuler");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBanniere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnuler))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelBanniere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnAnnuler))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void btnTesterConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTesterConnexionActionPerformed
        
        //On teste la connexion
        if(ifTestPassed()){
            Messages.Messages(this, true, NotificationType.SUCCES, "OPERATION REUSSIE", "Connexion effectuee avec succes !","");
        }else{
            Messages.Messages(this, true, NotificationType.ERROR, "ECHEC LORS DE LA CONNEXION A LA BASE DES DONNEES", "Des erreurs sont survenues"
                    + " lors de la connexion à la base des donnees : \n 1) Verifer vos parametres de connexion "
                    + "\n 2) Verifer l'accessibilite a votre serveur de base de donnees","");
        }
    }//GEN-LAST:event_btnTesterConnexionActionPerformed

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        
        //On save la configuration
        saveConfiguration();
        
        //On lance relance l'application
        launchApp();
    }//GEN-LAST:event_btnSaveActionPerformed
    
    /**
     * Cette methode permet de lancer une autre fenetre
     */
    private void launchApp(){
        
        if(clientInterfaceSecurity != null){
            clientInterfaceSecurity.launchClientFrame();
        }
    }
    
    /**
     * Cette methode permet d'enregistrer les parametres de la BD
     */
    private void saveConfiguration(){
        
        //La map qui contient les parametres
        Map map = collectDataBaseInfos();
        
        //On declare et initialise les variables
        final Properties prop = new Properties();

        //set the properties value
        prop.setProperty("javax.persistence.jdbc.user", SecurityCenter.encrypte((String)map.get("username")));
        prop.setProperty("javax.persistence.jdbc.password", SecurityCenter.encrypte((String)map.get("password")));

        //Determination de la base de donnees
        switch ((Integer)cbTypeDataBase.getSelectedIndex()) {
            case 0:
                prop.setProperty("javax.persistence.jdbc.url","jdbc:oracle:thin:@"+(String)map.get("host")+":"+(String)map.get("port")+":"+(String)map.get("name"));
                prop.setProperty("javax.persistence.jdbc.driver", "oracle.jdbc.OracleDriver");
                prop.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
                break;
            case 1:
                prop.setProperty("javax.persistence.jdbc.url","jdbc:mysql://"+(String)map.get("host")+":"+(String)map.get("port")+"/"+(String)map.get("name"));
                prop.setProperty("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
                prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                break;
            case 2:
                prop.setProperty("javax.persistence.jdbc.url","jdbc:sqlserver://"+(String)map.get("host")+":"+(String)map.get("port")+";databaseName="+(String)map.get("name"));
                prop.setProperty("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                prop.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
                break;
            case 3:
                prop.setProperty("javax.persistence.jdbc.url","jdbc:postgresql://"+(String)map.get("host")+":"+(String)map.get("port")+"/"+(String)map.get("name"));
                prop.setProperty("javax.persistence.jdbc.driver", "org.postgresql.Driver");
                prop.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                break;
        }

        //On ecrit dans le fichier de proprietes
        FileHelper.writeToFileProperties(dataBaseFileName, prop);
        
        //On detruit la fenetre
        this.dispose();
    }
    
    /**
     * Cette methode permet de tester la connexion
     * @return 
     */
    private boolean ifTestPassed(){
        
        if(checkConnexionForDataBase(collectDataBaseInfos())){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Methode pour tester la conexion
     *
     * @param filePropertiesPath
     * @return
     */
    public static boolean checkConnexionForDataBase(Map infoBd) {
        
        //Initialisation
        String host = null;
        String name = null;
        String port = null;
        String username = null;
        String password = null;
        String typeBd = null;
        String urlInfo []= null;
        JavaTypeConnection javaTypeConnection = null;
        
        //Affectation
        host = (String)infoBd.get("host");
        name = (String)infoBd.get("name");
        port = (String)infoBd.get("port");
        username = (String)infoBd.get("username");
        password = (String)infoBd.get("password");
        typeBd = (String)infoBd.get("typeBd");
        javaTypeConnection = (JavaTypeConnection)infoBd.get("javaTypeConnection");
        
        return CheckConnectionDataBase.check(host, name,
                username, password, port, javaTypeConnection);
    }
    
    private Map collectDataBaseInfos(){
        
        //Declaration
        Map map = null;
        
        //Affectation
        map = new HashMap();
        
        //Type de BD
        map.put("javaTypeConnection", getTypeBD(cbTypeDataBase));
        
        //Adresse ip du serveur
        map.put("host", txtHost.getText());
        
        //Port du serveur
        map.put("port", txtPort.getText());
        
        //Nom de la base des donnees
        map.put("name", txtBdName.getText());
        
        //Nom utilisateur
        map.put("username", txtUser.getText());
        
        //Mot de passe
        map.put("password", txtPassword.getText());
        
        //On retourne la map
        return map;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTesterConnexion;
    private javax.swing.JComboBox cbTypeDataBase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBanniere;
    private javax.swing.JTextField txtBdName;
    private javax.swing.JTextField txtHost;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
