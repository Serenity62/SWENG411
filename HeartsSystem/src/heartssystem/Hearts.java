package heartssystem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author J.Appallonia
 */
public class Hearts extends javax.swing.JFrame {
    private HeartsEngine engine;
    private final ImageIcon[][] cardFiles = new ImageIcon[4][13];
    private final String[] suits = {"Clubs","Diamonds","Spades","Hearts"};
    private final JLabel[] cardsInHand = new JLabel[13];
    private final JLabel[] cardsInTrick = new JLabel[4];
    private int numSelectedCards = 0;
    
    private class mouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int selectedMax = 1;
            if (engine.getSwapping()) {
                selectedMax+=2;
            }
            if (numSelectedCards < selectedMax) {
                int num = Integer.parseInt(e.getSource().toString().substring(4));
                JLabel thisCard = (JLabel)e.getSource();
                if (e.getClickCount() >= 2)
                {
                    engine.getActivePlayer().getHand().getCard(num).setSelected(true);
                    thisCard.setLocation(thisCard.getX(), thisCard.getY()-20);
                    engine.getActivePlayer().playCard();
                    numSelectedCards--;
                    update();
                } else {
                    if (engine.getActivePlayer().getHand().getCard(num).getSelected()) { // card is selected
                        engine.getActivePlayer().getHand().getCard(num).setSelected(false);
                        thisCard.setLocation(thisCard.getX(), thisCard.getY()+20);
                        numSelectedCards++;
                    } else {
                        // card is not selected
                        engine.getActivePlayer().getHand().getCard(num).setSelected(true);
                        thisCard.setLocation(thisCard.getX(), thisCard.getY()-20);
                        numSelectedCards--;
                    }
                }
                if (numSelectedCards == 3) {
                    showSwapButton();
                } else {
                    hideSwapButton();
                }
            }
        }
    }
    
    public Hearts() {
        swapBttn.setVisible(false);
        for (int suit = 0; suit < 4; suit++) {
            for (int face = 0; face < 13; face++) {
                cardFiles[suit][face] = new ImageIcon(getClass().
                        getResource("/cardImages/"+suits[suit]+Integer.toString(face+1)+".png"));
            }
        }
        cardsInTrick[0] = trickCardLeft;
        cardsInTrick[1] = trickCardTop;
        cardsInTrick[2] = trickCardRight;
        cardsInTrick[3] = trickCardBottom;
        mouseHandler mHand = new mouseHandler();
        cardsInHand[0] = card1;
        cardsInHand[1] = card2;
        cardsInHand[2] = card3;
        cardsInHand[3] = card4;
        cardsInHand[4] = card5;
        cardsInHand[5] = card6;
        cardsInHand[6] = card7;
        cardsInHand[7] = card8;
        cardsInHand[8] = card9;
        cardsInHand[9] = card10;
        cardsInHand[10] = card11;
        cardsInHand[11] = card12;
        cardsInHand[12] = card13;
        card1.addMouseListener(mHand);
        card2.addMouseListener(mHand);
        card3.addMouseListener(mHand);
        card4.addMouseListener(mHand);
        card5.addMouseListener(mHand);
        card6.addMouseListener(mHand);
        card7.addMouseListener(mHand);
        card8.addMouseListener(mHand);
        card9.addMouseListener(mHand);
        card10.addMouseListener(mHand);
        card11.addMouseListener(mHand);
        card12.addMouseListener(mHand);
        card13.addMouseListener(mHand);
        initComponents();
    }
    
    public void update() {
        Card thisC;
        for (int i = 0; i < 13; i++)
        {
            thisC = engine.getActivePlayer().getHand().getCard(i+1);
            if (thisC.getSuit() != -1) {
                cardsInHand[i].setIcon(cardFiles[thisC.getSuit()][thisC.getFace()]);
                engine.getActivePlayer().getHand().getCard(1).setSelected(true);
                cardsInHand[0].setLocation(cardsInHand[0].getX(), cardsInHand[0].getY()-20);
            }
        }
        for (int i = 0; i < 4; i++)
        {
            thisC = engine.getCurrentTrick().getCardPlayed(i);
            if (thisC.getSuit() != -1) {
                cardsInTrick[i].setIcon(cardFiles[thisC.getSuit()][thisC.getFace()]);
            }
        }
    }
    
    private void hideSwapButton() {
        swapBttn.setVisible(false);
    }
    
    private void showSwapButton() {
        swapBttn.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList();
        swapBttn = new javax.swing.JButton();
        trickCardTop = new javax.swing.JLabel();
        trickCardRight = new javax.swing.JLabel();
        trickCardLeft = new javax.swing.JLabel();
        trickCardBottom = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        card1 = new javax.swing.JLabel();
        card2 = new javax.swing.JLabel();
        card3 = new javax.swing.JLabel();
        card4 = new javax.swing.JLabel();
        card5 = new javax.swing.JLabel();
        card6 = new javax.swing.JLabel();
        card7 = new javax.swing.JLabel();
        card8 = new javax.swing.JLabel();
        card9 = new javax.swing.JLabel();
        card10 = new javax.swing.JLabel();
        card11 = new javax.swing.JLabel();
        card12 = new javax.swing.JLabel();
        card13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jList3.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList3);

        jList4.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList4);

        swapBttn.setText("Swap");
        swapBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwapCards(evt);
            }
        });

        trickCardTop.setBackground(new java.awt.Color(0, 0, 0));
        trickCardTop.setText("jLabel1");
        trickCardTop.setMaximumSize(new java.awt.Dimension(63, 91));
        trickCardTop.setMinimumSize(new java.awt.Dimension(63, 91));
        trickCardTop.setPreferredSize(new java.awt.Dimension(63, 91));

        trickCardRight.setBackground(new java.awt.Color(0, 0, 0));
        trickCardRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cardImages/Clubs1.png"))); // NOI18N
        trickCardRight.setText("jLabel1");
        trickCardRight.setMaximumSize(new java.awt.Dimension(63, 91));
        trickCardRight.setMinimumSize(new java.awt.Dimension(63, 91));
        trickCardRight.setPreferredSize(new java.awt.Dimension(63, 91));

        trickCardLeft.setBackground(new java.awt.Color(0, 0, 0));
        trickCardLeft.setText("jLabel1");
        trickCardLeft.setMaximumSize(new java.awt.Dimension(63, 91));
        trickCardLeft.setMinimumSize(new java.awt.Dimension(63, 91));
        trickCardLeft.setPreferredSize(new java.awt.Dimension(63, 91));

        trickCardBottom.setBackground(new java.awt.Color(0, 0, 0));
        trickCardBottom.setText("jLabel1");
        trickCardBottom.setMaximumSize(new java.awt.Dimension(63, 91));
        trickCardBottom.setMinimumSize(new java.awt.Dimension(63, 91));
        trickCardBottom.setPreferredSize(new java.awt.Dimension(63, 91));

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(514, 91));

        card1.setBackground(new java.awt.Color(0, 0, 0));
        card1.setText("jLabel1");
        card1.setAlignmentX(0.4F);
        card1.setMaximumSize(new java.awt.Dimension(63, 91));
        card1.setMinimumSize(new java.awt.Dimension(63, 91));
        card1.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card1);
        card1.setBounds(0, 0, 63, 91);

        card2.setBackground(new java.awt.Color(0, 0, 0));
        card2.setText("jLabel1");
        card2.setAlignmentX(0.6F);
        card2.setMaximumSize(new java.awt.Dimension(63, 91));
        card2.setMinimumSize(new java.awt.Dimension(63, 91));
        card2.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card2);
        card2.setBounds(20, 0, 63, 91);

        card3.setBackground(new java.awt.Color(0, 0, 0));
        card3.setText("jLabel1");
        card3.setAlignmentX(0.8F);
        card3.setMaximumSize(new java.awt.Dimension(63, 91));
        card3.setMinimumSize(new java.awt.Dimension(63, 91));
        card3.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card3);
        card3.setBounds(40, 0, 63, 91);

        card4.setBackground(new java.awt.Color(0, 0, 0));
        card4.setText("jLabel1");
        card4.setAlignmentX(1.0F);
        card4.setMaximumSize(new java.awt.Dimension(63, 91));
        card4.setMinimumSize(new java.awt.Dimension(63, 91));
        card4.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card4);
        card4.setBounds(60, 0, 63, 91);

        card5.setBackground(new java.awt.Color(0, 0, 0));
        card5.setText("jLabel1");
        card5.setAlignmentX(1.2F);
        card5.setMaximumSize(new java.awt.Dimension(63, 91));
        card5.setMinimumSize(new java.awt.Dimension(63, 91));
        card5.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card5);
        card5.setBounds(80, 0, 63, 91);

        card6.setBackground(new java.awt.Color(0, 0, 0));
        card6.setText("jLabel1");
        card6.setAlignmentX(1.4F);
        card6.setMaximumSize(new java.awt.Dimension(63, 91));
        card6.setMinimumSize(new java.awt.Dimension(63, 91));
        card6.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card6);
        card6.setBounds(100, 0, 63, 91);

        card7.setBackground(new java.awt.Color(0, 0, 0));
        card7.setText("jLabel1");
        card7.setAlignmentX(1.6F);
        card7.setMaximumSize(new java.awt.Dimension(63, 91));
        card7.setMinimumSize(new java.awt.Dimension(63, 91));
        card7.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card7);
        card7.setBounds(120, 0, 63, 91);

        card8.setBackground(new java.awt.Color(0, 0, 0));
        card8.setText("jLabel1");
        card8.setAlignmentX(1.8F);
        card8.setMaximumSize(new java.awt.Dimension(63, 91));
        card8.setMinimumSize(new java.awt.Dimension(63, 91));
        card8.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card8);
        card8.setBounds(140, 0, 63, 91);

        card9.setBackground(new java.awt.Color(0, 0, 0));
        card9.setText("jLabel1");
        card9.setAlignmentX(2.0F);
        card9.setMaximumSize(new java.awt.Dimension(63, 91));
        card9.setMinimumSize(new java.awt.Dimension(63, 91));
        card9.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card9);
        card9.setBounds(160, 0, 63, 91);

        card10.setBackground(new java.awt.Color(0, 0, 0));
        card10.setText("jLabel1");
        card10.setAlignmentX(2.2F);
        card10.setMaximumSize(new java.awt.Dimension(63, 91));
        card10.setMinimumSize(new java.awt.Dimension(63, 91));
        card10.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card10);
        card10.setBounds(180, 0, 63, 91);

        card11.setBackground(new java.awt.Color(0, 0, 0));
        card11.setText("jLabel1");
        card11.setAlignmentX(2.6F);
        card11.setMaximumSize(new java.awt.Dimension(63, 91));
        card11.setMinimumSize(new java.awt.Dimension(63, 91));
        card11.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card11);
        card11.setBounds(200, 0, 63, 91);

        card12.setBackground(new java.awt.Color(0, 0, 0));
        card12.setText("jLabel1");
        card12.setMaximumSize(new java.awt.Dimension(63, 91));
        card12.setMinimumSize(new java.awt.Dimension(63, 91));
        card12.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card12);
        card12.setBounds(220, 0, 63, 91);

        card13.setBackground(new java.awt.Color(0, 0, 0));
        card13.setText("jLabel1");
        card13.setAlignmentX(0.2F);
        card13.setMaximumSize(new java.awt.Dimension(63, 91));
        card13.setMinimumSize(new java.awt.Dimension(63, 91));
        card13.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card13);
        card13.setBounds(240, 0, 63, 91);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(swapBttn)
                        .addGap(87, 87, 87))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(trickCardLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(trickCardBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trickCardTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(trickCardRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 34, Short.MAX_VALUE)))
                        .addGap(151, 151, 151)
                        .addComponent(swapBttn)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(trickCardTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(trickCardBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(trickCardRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trickCardLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SwapCards(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwapCards
        //curPlayer.passCard();
    }//GEN-LAST:event_SwapCards

    
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
            java.util.logging.Logger.getLogger(Hearts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hearts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hearts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hearts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
           
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hearts().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel card1;
    private javax.swing.JLabel card10;
    private javax.swing.JLabel card11;
    private javax.swing.JLabel card12;
    private javax.swing.JLabel card13;
    private javax.swing.JLabel card2;
    private javax.swing.JLabel card3;
    private javax.swing.JLabel card4;
    private javax.swing.JLabel card5;
    private javax.swing.JLabel card6;
    private javax.swing.JLabel card7;
    private javax.swing.JLabel card8;
    private javax.swing.JLabel card9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JList jList4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton swapBttn;
    private javax.swing.JLabel trickCardBottom;
    private javax.swing.JLabel trickCardLeft;
    private javax.swing.JLabel trickCardRight;
    private javax.swing.JLabel trickCardTop;
    // End of variables declaration//GEN-END:variables
}
