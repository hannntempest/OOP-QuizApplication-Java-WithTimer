package tubeskali;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TakeQuiz extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost/tubespbo";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    private String name;
    private String course;
    private String quiz;
    private ArrayList<Question> listQ;
    private ArrayList<String> listAns;
    int finalScore = 0;
    int incrementScore = 0;
    int numSoal = 0;
    int curSoal = 1;
    Timer t;
    
    public TakeQuiz(String course, String quiz, String name) {
        initComponents();
        this.name = name;
        this.course = course;
        this.quiz = quiz;
        listQ = new ArrayList<>();
        listAns = new ArrayList<>(numSoal);
        listAns.add(0, " ");
        listAns.add(1, " ");
        listAns.add(2, " ");
        listAns.add(3, " ");
        jLabel1.setText(course + " , " + quiz);
        saveQ();
        showQ();
        countdown();
        nextPrevSubmitSet();
    }
    
    private void saveQ(){
        try {
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement ps = con.prepareStatement
            ("SELECT * FROM courses CROSS JOIN quizzes JOIN questions ON questions.question_id=quizzes.question_id JOIN classes ON classes.class_id=courses.class_id  WHERE classes.name=? AND quizzes.name=? AND courses.course_id=1");
            ps.setString(1, course);
            ps.setString(2, quiz);
            
            ResultSet res = ps.executeQuery();
                                  
            while(res.next()){
                String soal = res.getString("question");
                String o1 = res.getString("option1");
                String o2 = res.getString("option2");
                String o3 = res.getString("option3");
                String o4 = res.getString("option4");
                String ans = res.getString("answer");
                Question q = new Question(soal,o1,o2,o3,o4,ans);
                listQ.add(q);
                numSoal++;
            }
            
            incrementScore = 100 / numSoal;
            
            res.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void showQ(){
        nextPrevSubmitSet();
        jLabel5.setText(Integer.toString(curSoal));
        jLabel6.setText(listQ.get(curSoal-1).getSoal());
        jRadioButton1.setText(listQ.get(curSoal-1).getO1());
        jRadioButton2.setText(listQ.get(curSoal-1).getO2());
        jRadioButton3.setText(listQ.get(curSoal-1).getO3());
        jRadioButton4.setText(listQ.get(curSoal-1).getO4());
    }
    
    private void countdown(){
        t = new Timer(1000, new ActionListener() {
            int time = 120;
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                
                int minute = time/60;
                int sec = time%60;
                
                if(sec < 10){
                    jLabel4.setText("0"+Integer.toString(time%60));
                }else{
                    jLabel4.setText(""+Integer.toString(time%60));
                }
                
                if(minute < 10){
                    jLabel2.setText("0"+Integer.toString(time/60));
                }else{
                    jLabel2.setText(""+Integer.toString(time/60));
                }
                
                if (time == 0) {
                    t.stop();
                    JOptionPane.showMessageDialog(null,"Time Is Up, Mate!","Error",JOptionPane.ERROR_MESSAGE);
                    showScore();
                }
            }
        });
        t.start();
    }
    
    private void nextPrevSubmitSet(){
        if (curSoal == 1){
            jButton1.setVisible(false);
            jButton3.setVisible(false);
        }else{
            jButton1.setVisible(true);
        }
        
        if(curSoal == numSoal){
            jButton2.setVisible(false);
            jButton3.setVisible(true);
        }else{
            jButton2.setVisible(true);
            jButton3.setVisible(false);
        }
    }

    public void showScore(){
        for(int j = 0; j < listQ.size(); j++){
            if(listQ.get(j).getAns().equals(listAns.get(j))){
                finalScore += incrementScore;
            }
        }
        JOptionPane.showMessageDialog(this, "Nilai Kuis Anda adalah: " + finalScore);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(".");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(".");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(":");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(".");

        jButton1.setText("Previous");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Next");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("Submit All");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("No");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel6.setText("Soal");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Opt1");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Opt2");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Opt3");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Opt4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
            .addGroup(layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton3)))
                        .addGap(156, 156, 156))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton4))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        if(jRadioButton1.isSelected()){
            listAns.set(curSoal-1, jRadioButton1.getText());
        }else if(jRadioButton2.isSelected()){
            listAns.set(curSoal-1, jRadioButton2.getText());
        }else if(jRadioButton3.isSelected()){
            listAns.set(curSoal-1, jRadioButton3.getText());
        }else if(jRadioButton4.isSelected()){
            listAns.set(curSoal-1, jRadioButton4.getText());
        }
        
        showScore();
        
        dispose();
        DashboardStudent ds = new DashboardStudent(name);
        ds.setTitle("Dashboard Student");
        ds.setLocationRelativeTo(null);
        ds.setVisible(true);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if(jRadioButton1.isSelected()){
            listAns.set(curSoal-1, jRadioButton1.getText());
        }else if(jRadioButton2.isSelected()){
            listAns.set(curSoal-1, jRadioButton2.getText());
        }else if(jRadioButton3.isSelected()){
            listAns.set(curSoal-1, jRadioButton3.getText());
        }else if(jRadioButton4.isSelected()){
            listAns.set(curSoal-1, jRadioButton4.getText());
        }
        curSoal++;
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        buttonGroup1.clearSelection();
        showQ();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if(jRadioButton1.isSelected()){
            listAns.set(curSoal-1, jRadioButton1.getText());
        }else if(jRadioButton2.isSelected()){
            listAns.set(curSoal-1, jRadioButton2.getText());
        }else if(jRadioButton3.isSelected()){
            listAns.set(curSoal-1, jRadioButton3.getText());
        }else if(jRadioButton4.isSelected()){
            listAns.set(curSoal-1, jRadioButton4.getText());
        }
        curSoal--;
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        buttonGroup1.clearSelection();
        showQ();
    }//GEN-LAST:event_jButton1MouseClicked

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
//            java.util.logging.Logger.getLogger(TakeQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TakeQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TakeQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TakeQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TakeQuiz().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration//GEN-END:variables
}
