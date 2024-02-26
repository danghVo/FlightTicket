/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectqtdl.user;
import java.awt.Dimension;
import projectqtdl.flight.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.JScrollPane;
import javax.swing.table.*;
import javax.swing.JPanel;
import projectqtdl.user.UserModel;

/**
 *
 * @author huynh
 */
public class userTicket extends javax.swing.JFrame {
    /**
     * Creates new form buyTicket
     */
    private int userId;
    
    public userTicket(int userId) {
        this.userId = userId;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        showFlightPanel(UserModel.getAllTicketOfUser(userId));
        this.usernameField.setText(UserModel.getUserById(this.userId));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelcontain = new javax.swing.JPanel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bảng Vé");
        setBounds(new java.awt.Rectangle(0, 0, 940, 700));
        setMinimumSize(new java.awt.Dimension(600, 600));
        setName("buyFrame"); // NOI18N
        setSize(new java.awt.Dimension(600, 600));

        jPanel1.setBackground(new java.awt.Color(19, 91, 160));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 600));

        backBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        backBtn.setText("Trở về");
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        usernameField.setEditable(false);
        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        usernameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        panelcontain.setLayout(new java.awt.GridLayout(0, 1, 0, 8));
        jScrollPane1.setViewportView(panelcontain);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 329, Short.MAX_VALUE)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void hideBtn(javax.swing.JButton button) {
        button.setSize(0, 0);
    }
    
    private void showBtn(javax.swing.JButton button, int width, int height) {
        button.setSize(width, height);
    }
    
    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        // TODO add your handling code here:
        new userFlight(userId).setVisible(true);
        dispose();
    }//GEN-LAST:event_backBtnMouseClicked

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backBtnActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

   
    public static void showFlightPanel(ResultSet tickets){
        try{
            jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            int sumHeight = 0;
            while(tickets.next()){
                String from = tickets.getString("from");
                String to = tickets.getString("to");
                String date = tickets.getString("date");
                String time = tickets.getString("boarding_time");
                String flight = tickets.getString("flight");
                String seat = tickets.getString("seat_name") + tickets.getString("seat_number");
                String gate = tickets.getString("gate");
                Boolean payed = tickets.getBoolean("payed");
                String payOption = tickets.getBoolean("pay_option")? "Thanh toán trực tiếp":"Thanh toán online";
                String cost = tickets.getString("cost");
                int id = tickets.getInt("id");
                ticketPanel panel = new ticketPanel(from, to ,date, time, seat, gate, flight, cost, payed, payOption);
                panel.setSize(new Dimension(600, 170));
                sumHeight += 170;
                panelcontain.add(panel);
            }   
                int scrollHeight = panelcontain.getHeight();
                if(sumHeight < scrollHeight) {
                    JPanel addSpace = new JPanel();
                    addSpace.setSize(600, scrollHeight - sumHeight - 200);
                    panelcontain.add(addSpace);   
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JPanel panelcontain;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}