/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oxClient;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Силов
 */
public class OXGameFrame extends javax.swing.JFrame {
    private OXGame game;
    private JPanel GameField;
    JButton[][] fieldButtons;
    /**
     * Creates new form OXGameFrame
     */    
    public OXGameFrame(OXGame game) throws IOException {
        this.game=game;
        initComponents();
        generateField();
        initFrame();
        this.game.addFrame(this);
    }
    public void gameStart(char gamer){
        this.setTitle("Вы играете за"+gamer);
        if(gamer=='X')
            statusLebel.setText("Статус: Ваш ход");
        if(gamer=='O')
            statusLebel.setText("Статус: Ход противника");
        this.startButton.setEnabled(false);
        this.gameComboBox.setEnabled(false);
        this.userStatus.setEnabled(false);
        this.leaveButton.setEnabled(true);
        this.chatTextField.setEnabled(true);
        this.sendButton.setEnabled(true);
    }
    public void endGame(String text){
        this.statusLebel.setText(text);
    }
    public void updateChat(String text){
        chatTextArea.setText(chatTextArea.getText()+text+"\n");
    }
    public void updateField(String str){
        String[] parts=str.split(" ");
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(parts[3*i+j].compareTo("-1")==0)
                    fieldButtons[i][j].setText("");
                else if(parts[3*i+j].compareTo("0")==0)
                    fieldButtons[i][j].setText("X");
                else if(parts[3*i+j].compareTo("1")==0)
                    fieldButtons[i][j].setText("O");
            }
        }
        this.statusLebel.setText("Статус: Ход игрока "+parts[9]);
    }
    public void updateFrame(){
        
    }
    private void generateField(){
        GameField=new JPanel();
        this.add(GameField);
        this.GameField.setBounds(10, 10, 210, 210);
        this.GameField.setSize(210, 210);
        this.GameField.setLayout(new GridLayout(3,3));
        fieldButtons=new JButton[3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
            {
                final int I=i,J=j;
                fieldButtons[i][j]=new JButton("");
                fieldButtons[i][j].setSize(60,60);
                fieldButtons[i][j].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        game.click(I,J);
                    }
                });
                this.GameField.add(fieldButtons[i][j]);
            }
        this.GameField.setVisible(true);
        this.GameField.setSize(210,210);
    }
    private void initFrame(){
        this.gameComboBox.setEnabled(false);
        this.chatTextArea.setEnabled(false);
        this.chatTextField.setEnabled(false);
        this.sendButton.setEnabled(false);
        this.leaveButton.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusLebel = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        userStatus = new javax.swing.JComboBox<>();
        gameComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        leaveButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        chatTextField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        statusLebel.setText("Статус: ");

        startButton.setText("Начать игру");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        userStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Игрок", "Зритель" }));

        jLabel2.setText("Список игр");

        leaveButton.setText("Сдаться");

        exitButton.setText("Выйти");

        chatTextField.setToolTipText("Введите сообщение");

        sendButton.setText("Отпр.");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        chatTextArea.setColumns(20);
        chatTextArea.setRows(5);
        jScrollPane1.setViewportView(chatTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusLebel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(userStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(gameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(exitButton)))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(startButton)
                                    .addComponent(leaveButton))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(chatTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendButton)
                        .addGap(0, 43, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(startButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leaveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sendButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(statusLebel)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(exitButton)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        this.setTitle("Ожидание начала игры");
        try {
            game.startGame(-1);
        } catch (IOException ex) {
            Logger.getLogger(OXGameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        game.sendChat(this.chatTextField.getText());
        this.chatTextField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_sendButtonActionPerformed

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
//            java.util.logging.Logger.getLogger(OXGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(OXGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(OXGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(OXGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    new OXGameFrame().setVisible(true);
//                } catch (IOException ex) {
//                    Logger.getLogger(OXGameFrame.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatTextArea;
    private javax.swing.JTextField chatTextField;
    private javax.swing.JButton exitButton;
    private javax.swing.JComboBox<String> gameComboBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leaveButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel statusLebel;
    private javax.swing.JComboBox<String> userStatus;
    // End of variables declaration//GEN-END:variables
}
