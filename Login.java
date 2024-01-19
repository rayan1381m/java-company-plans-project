
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 * @author 98990
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    MobileCompany mobileCompany = new MobileCompany();

    public Login() throws PlanException {
        test();
        initComponents();
    }

    public void login() {
        try {
            if (mobileCompany.validateAdmin(userIDTextField.getText(), jPasswordField1.getText())) {
                AdminUI adminUI = new AdminUI(this);
                this.setVisible(false);
                adminUI.setVisible(true);
            } else {
                User user = mobileCompany.validateUser(parseInt(userIDTextField.getText()), jPasswordField1.getText());
                jPasswordField1.setText("");
                if (user != null) {
                    ArrayList<String> cities = mobileCompany.populateDistinctCityNames();
                    UserUI userUI = new UserUI(user, this, cities);
                    userUI.setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Username or password wrong");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "hey. don't enter words. only number for user ID");
        } catch (PlanException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Can't access to the file.");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Can't find the path.");
        }

    }

    public void test() throws PlanException {
        //mobileCompany = new MobileCompany("HoomanCompany", "admin", "admin", 12);

        MobilePhone mobilePhone = new MobilePhone("Galaxy S10", MobileType.Android, 8, 500);
        MobilePhone mobilePhone1 = new MobilePhone("Iphone X", MobileType.IOS, 4, 500);
        MobilePhone mobilePhone2 = new MobilePhone("LG S50", MobileType.Windows, 16, 500);

        //Initialize plan objects
        MobilePlan plan0 = createPersonalPlan("OP123", 133, mobilePhone, 120, 22, new MyDate(2000, 5, 14), "Wollongong");
        MobilePlan plan1 = createPersonalPlan("Sara12", 345, mobilePhone, 30, 38, new MyDate(1999, 4, 34), "Sydney");
        MobilePlan plan2 = createPersonalPlan("john342", 435, mobilePhone1, 100, 20, new MyDate(2004, 3, 23), "Dubbo");
        MobilePlan plan3 = createBusinessPlan("Alex123", 679, mobilePhone1, 50, 80, new MyDate(2020, 6, 21), 20, 123568);
        MobilePlan plan4 = createBusinessPlan("Gh546", 356, mobilePhone2, 20, 30, new MyDate(2021, 7, 29), 10, 666555);
        MobilePlan plan5 = createBusinessPlan("S9845", 457, mobilePhone2, 200, 46, new MyDate(2024, 2, 17), 200, 222333);

        User user0 = new User(143543, "John Smith", new Address(12, "Princs Hwy", "Fairy Meadow", "Wollongong"), "password1");
        User user1 = new User(265756, "Sara Lawson", new Address(43, "Illawara Avenue", "Gwynneville", "Wollongong"), "password1");
        User user2 = new User(387899, "Robert London", new Address(22, "Edward st", "Coniston", "Wollongong"), "123");
        User user3 = new User(489123, "Alex Niton", new Address(330, "Smith st", "Liverpool", "Sydney"), "12345");
        User user4 = new User(565768, "Joe Tomson", new Address(20, "Rose st", "North Sydney", "Sydney"), "123");
        User user5 = new User(676767, "Allison Bird", new Address(41, "Grey st", "Monavale", "Melbourne"), "password123");

        mobileCompany = new MobileCompany("Rokham", "tra", "1234", 1);
        mobileCompany.addUser(user0);
        mobileCompany.addUser(user1);
        mobileCompany.addUser(user2);
        mobileCompany.addUser(user4);
        mobileCompany.addUser(user5);

        UITools.addPlan(user1, plan1);
        UITools.addPlan(user2, plan3);
        UITools.addPlan(user2, plan2);
        UITools.addPlan(user3, plan0);
        UITools.addPlan(user3, plan5);
        UITools.addPlan(user1, plan4);
        UITools.addPlan(user5, plan4);
        UITools.addPlan(user4, plan4);
        UITools.addPlan(user2, plan4);
        UITools.addPlan(user2, plan0);
        UITools.addPlan(user2, plan5);
        UITools.addPlan(user0, plan4);
    }

    public static PersonalPlan createPersonalPlan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit, MyDate expiryDate, String city) throws PlanException {
        PersonalPlan plan0;
        try {
            plan0 = new PersonalPlan(userName, id, handset, internetQuota, capLimit, expiryDate, city);
        } catch (PlanException e) {
            plan0 = new PersonalPlan(userName, e.getNewId(), handset, internetQuota, capLimit, expiryDate, city);
            System.out.println(e);
        }
        return plan0;
    }

    public static BusinessPlan createBusinessPlan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit, MyDate expiryDate, int numberOfEmployees, int ABN) throws PlanException {
        BusinessPlan plan;
        try {
            plan = new BusinessPlan(userName, id, handset, internetQuota, capLimit, expiryDate, numberOfEmployees, ABN);
        } catch (PlanException e) {
            plan = new BusinessPlan(userName, e.getNewId(), handset, internetQuota, capLimit, expiryDate, numberOfEmployees, ABN);
            System.out.println(e);
        }
        return plan;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userIDLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        userIDTextField = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        loadMenuBar = new javax.swing.JMenuItem();
        saveMenuBar = new javax.swing.JMenuItem();
        listOfUserBar = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Company Programm");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(500, 200));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        userIDLabel.setText("User ID");

        passwordLabel.setText("Password");

        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });

        loginButton.setForeground(new java.awt.Color(255, 51, 51));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        loginButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginButtonKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                loginButtonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                loginButtonKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel1.setText("you can enter by pressing \"Enter\" key");

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        loadMenuBar.setText("Load");
        loadMenuBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuBarActionPerformed(evt);
            }
        });
        jMenu1.add(loadMenuBar);

        saveMenuBar.setText("Save");
        saveMenuBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuBarActionPerformed(evt);
            }
        });
        jMenu1.add(saveMenuBar);

        jMenuBar1.add(jMenu1);

        listOfUserBar.setText("List of User");
        listOfUserBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listOfUserBarMouseClicked(evt);
            }
        });
        listOfUserBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listOfUserBarActionPerformed(evt);
            }
        });
        jMenuBar1.add(listOfUserBar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(213, Short.MAX_VALUE)
                .addComponent(loginButton)
                .addGap(133, 133, 133))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userIDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jPasswordField1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userIDLabel)
                    .addComponent(userIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void listOfUserBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listOfUserBarActionPerformed
        // TODO add your handling code here:
        String outPut = "";
        for (User user : mobileCompany.getUsers().values()) {
            outPut += "userID: " + user.getID() + "  Password: " + user.getPassword() + "\n";
        }
        JOptionPane.showMessageDialog(this, outPut);
    }//GEN-LAST:event_listOfUserBarActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void saveMenuBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuBarActionPerformed
        // TODO add your handling code here:
        try {
            if (mobileCompany.saveTextFile("company.txt")) {
                   JOptionPane.showMessageDialog(this, "file save succesfull.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Can't save. try again");
        }
    }//GEN-LAST:event_saveMenuBarActionPerformed

    private void listOfUserBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listOfUserBarMouseClicked
        // TODO add your handling code here:
        String outPut = "";
        for (User user : mobileCompany.getUsers().values()) {
            outPut += "userID: " + user.getID() + "  Password: " + user.getPassword() + "\n";
        }
        JOptionPane.showMessageDialog(this, outPut);
    }//GEN-LAST:event_listOfUserBarMouseClicked

    private void loadMenuBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuBarActionPerformed
        // TODO add your handling code here:
        try {
            try {
                if (mobileCompany.loadTextFile("company.txt")) {

                } else {

                }
            } catch (PlanException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Can't load. try again");
        }
    }//GEN-LAST:event_loadMenuBarActionPerformed

    private void loginButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonKeyPressed

    private void loginButtonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonKeyTyped

    private void loginButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonKeyReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Login().setVisible(true);
                } catch (PlanException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JMenu listOfUserBar;
    private javax.swing.JMenuItem loadMenuBar;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JMenuItem saveMenuBar;
    private javax.swing.JLabel userIDLabel;
    private javax.swing.JTextField userIDTextField;
    // End of variables declaration//GEN-END:variables
}
