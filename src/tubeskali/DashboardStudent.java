package tubeskali;

import javax.swing.JOptionPane;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardStudent extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost/tubespbo";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    private String user;
    private DefaultListModel<String> poolCourse;
    private DefaultListModel<String> poolQuiz;
    
    public DashboardStudent(String name) {
        initComponents();
        jLabel1.setText("Welcome, Student "+name);
        this.user = name;
        poolCourse = new DefaultListModel();
        jList1.setModel(poolCourse);
        compileCourse(name);
        jList1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                if(!e.getValueIsAdjusting()){
                    String name = jList1.getSelectedValue();
                    if(name != null){
                        detailCourse(name);
                    }
                }
            }
        });
    }
    
    private void compileCourse(String name){
        try {
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement ps = con.prepareStatement
            ("SELECT * FROM classes JOIN student ON student.student_id=classes.student_id WHERE student.name=?");
            ps.setString(1, name);
            ResultSet res = ps.executeQuery();
            
            poolCourse = new DefaultListModel<>();
            
            while(res.next()){
                boolean unique = true;
                String nk = res.getString("classes.name");
                int ek = poolCourse.getSize();
                int i = 0;
                while(i < ek && unique){
                    if(poolCourse.getElementAt(i).equals(nk)){
                        unique = false;
                    }
                    i++;
                }
                if(unique){
                    poolCourse.addElement(nk);           
                }
            }
            
            jList1.setModel(poolCourse);
            res.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void detailCourse(String name){
        try {
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement ps = con.prepareStatement
            ("SELECT * FROM classes JOIN courses ON classes.class_id=courses.class_id JOIN student ON student.student_id=classes.student_id JOIN quizzes ON courses.quiz_id = quizzes.quiz_id WHERE classes.name=?");
            ps.setString(1, name);
            
            ResultSet res = ps.executeQuery();
            
            poolQuiz = new DefaultListModel<>();
            
            while(res.next()){
                boolean unique = true;
                String soal = res.getString("quizzes.name");
                int ek = poolQuiz.getSize();
                int i = 0;
                while(i < ek && unique){
                    if(poolQuiz.getElementAt(i).equals(soal)){
                        unique = false;
                    }
                    i++;
                }
                if(unique){
                    poolQuiz.addElement(soal);           
                }
            }
            
            jList2.setModel(poolQuiz);
            res.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(".");
        jLabel1.setToolTipText("");

        jButton1.setText("Logout");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setText("Available Course:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setText("Available Quiz:");

        jList1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jList2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jScrollPane2.setViewportView(jList2);

        jButton2.setText("Take Quiz");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        dispose();
        Login l = new Login();
        l.setTitle("Login Page");
        l.setLocationRelativeTo(null); // center a form.
        l.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        String course = jList1.getSelectedValue();
        String quiz = jList2.getSelectedValue();
        
        if(course != null && quiz != null){
            dispose();
            TakeQuiz tq = new TakeQuiz(course, quiz, user);
            tq.setTitle("Take Quiz");
            tq.setLocationRelativeTo(null); // center a form.
            tq.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Pilih Quiz Dan Kursus Terlebih Dahulu! ", "Error", JOptionPane.ERROR_MESSAGE);  
        }
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DashboardStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DashboardStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DashboardStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DashboardStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DashboardStudent().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
