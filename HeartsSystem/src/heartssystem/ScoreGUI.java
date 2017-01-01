package heartssystem;

public class ScoreGUI extends BasicGUI {
    private static ScoreGUI instance = null;
    HeartsGUI heartsGUI;
    
    public ScoreGUI(HeartsEngine e) {
        super(e);
        initComponents();
        setScores();
        winnerLabel.setVisible(false);
        startBttn.setVisible(false);
    }
    
    public ScoreGUI(HeartsEngine e, int p, HeartsGUI gui){
        super(e);
        initComponents();
        setScores();
        winnerLabel.setText(getEngine().getPlayer(p).getName()+" won!");
        winnerLabel.setVisible(true);
        startBttn.setVisible(true);
        heartsGUI = gui;
    }
    
    private void setScores() {
        player1Label.setText(getEngine().getPlayer(0).getName());
        Player1Score.setText(Integer.toString(getEngine().getPlayer(0).getPoints()));
        player2Label.setText(getEngine().getPlayer(1).getName());
        Player2Score.setText(Integer.toString(getEngine().getPlayer(1).getPoints()));
        player3Label.setText(getEngine().getPlayer(2).getName());
        Player3Score.setText(Integer.toString(getEngine().getPlayer(2).getPoints()));
        player4Label.setText(getEngine().getPlayer(3).getName());
        Player4Score.setText(Integer.toString(getEngine().getPlayer(3).getPoints()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        player1Label = new javax.swing.JLabel();
        Player1Score = new javax.swing.JLabel();
        player2Label = new javax.swing.JLabel();
        player3Label = new javax.swing.JLabel();
        Player2Score = new javax.swing.JLabel();
        player4Label = new javax.swing.JLabel();
        Player3Score = new javax.swing.JLabel();
        Player4Score = new javax.swing.JLabel();
        winnerLabel = new javax.swing.JLabel();
        startBttn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setText("Score");

        player1Label.setText("Player 1:");

        Player1Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1Score.setText("0");

        player2Label.setText("Player 2:");

        player3Label.setText("Player 3:");

        Player2Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2Score.setText("0");

        player4Label.setText("Player 4:");

        Player3Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player3Score.setText("0");

        Player4Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player4Score.setText("0");

        winnerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winnerLabel.setText("Player 0 won!");

        startBttn.setText("Start New Game");
        startBttn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startBttnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(player3Label)
                                    .addComponent(player4Label)
                                    .addComponent(player2Label)
                                    .addComponent(player1Label))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Player1Score, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Player2Score, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Player3Score, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Player4Score, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(startBttn)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(winnerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(player1Label)
                    .addComponent(Player1Score))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(player2Label)
                    .addComponent(Player2Score))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(player3Label)
                    .addComponent(Player3Score))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(player4Label)
                    .addComponent(Player4Score))
                .addGap(18, 18, 18)
                .addComponent(winnerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(startBttn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startBttnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startBttnMouseClicked
        heartsGUI.resetGame();
        this.dispose();
    }//GEN-LAST:event_startBttnMouseClicked

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
            java.util.logging.Logger.getLogger(ScoreGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScoreGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScoreGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScoreGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Player1Score;
    private javax.swing.JLabel Player2Score;
    private javax.swing.JLabel Player3Score;
    private javax.swing.JLabel Player4Score;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel player1Label;
    private javax.swing.JLabel player2Label;
    private javax.swing.JLabel player3Label;
    private javax.swing.JLabel player4Label;
    private javax.swing.JButton startBttn;
    private javax.swing.JLabel winnerLabel;
    // End of variables declaration//GEN-END:variables
}
