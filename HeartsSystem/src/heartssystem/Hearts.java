package heartssystem;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Hearts extends BasicGUI {
    private final ImageIcon[][] cardFiles = new ImageIcon[4][13];
    private ImageIcon cardbackFile[] = new ImageIcon[2];
    private final String[] suits = {"Clubs","Diamonds","Spades","Hearts"};
    private final JLabel[] cardsInHand = new JLabel[13];
    private final JLabel[][] numPlayersCards = new JLabel[3][13];
    private final JLabel[] cardsInTrick = new JLabel[4];
    private int numSelectedCards = 0;
    private int defaultY = 0;
    
    private class cardClicked extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int selectedMax = 1;
            if (getEngine().getSwapping()) {
                selectedMax+=2;
            }
            if (numSelectedCards <= selectedMax) {
                JLabel thisCardLabel = (JLabel)e.getSource();
                if(thisCardLabel.getIcon() != null){
                    int num = Integer.parseInt(thisCardLabel.getText()) + 1;
                    Trick thisTrick = getEngine().getCurrentTrick();
                    Hand thisHand = getEngine().getActivePlayer().getHand();
                    Card thisCard = thisHand.getCard(num);
                    boolean inSuit = thisTrick.getOpening() == thisCard.getSuit();
                    if (e.getClickCount() >= 2 && !getEngine().getSwapping() && 
                            ((((thisTrick.getSize() < 1) && (inSuit || !thisHand.hasSuit(thisTrick.getOpening())) 
                                || (thisCard.getSuit() != 3 || getEngine().getHeartsBroken() 
                                || (!thisHand.hasSuit(0) && !thisHand.hasSuit(1) && !thisHand.hasSuit(2)))))
                            || (thisTrick.getSize() >= 1 && (inSuit || !thisHand.hasSuit(thisTrick.getOpening()))))) // trying to play a card / Check this logic
                    {
                        if(!thisCard.getSelected() && numSelectedCards >= 1){
                            for (int i = 0; i < thisHand.getSize(); i++) {
                                if (thisHand.getCard(i + 1).getSelected()) {
                                    thisHand.getCard(i + 1).setSelected(false);
                                    //cardsInHand[i].setLocation(thisCardLabel.getX(), thisCardLabel.getY()+20);    // could be grabbing the wrong one
                                    numSelectedCards--;
                                }
                            }
                            
                        }
                        thisCard.setSelected(true);
                        //thisCardLabel.setLocation(thisCardLabel.getX(), thisCardLabel.getY()-20);
                        
                        //thisTrick.addCard(engine.getActivePlayer().playCard(), engine.getHeartsBroken());
                        getEngine().addCardToTrick();
                        //numSelectedCards--;
                        
                        System.out.println("Trip in playing card by the double tap");
                        System.out.printf("click count: %d\n", e.getClickCount());
                        resetTrick();
                        updateCards();
                        reset();
                        if (getEngine().getTrickNum() >= 12) {
                            EndRoundGUI endR = new EndRoundGUI();
                            endR.setVisible(true);
                        }
                        swapPlayers(); // preps for shift to next player
                    } else {
                        if (getEngine().getActivePlayer().getHand().getCard(num).getSelected()) { // card is selected
                            getEngine().getActivePlayer().getHand().getCard(num).setSelected(false);
                            thisCardLabel.setLocation(thisCardLabel.getX(), thisCardLabel.getY()+20);
                            numSelectedCards--;
                        } else if(numSelectedCards < selectedMax){
                            // card is not selected
                            getEngine().getActivePlayer().getHand().getCard(num).setSelected(true);
                            thisCardLabel.setLocation(thisCardLabel.getX(), thisCardLabel.getY()-20);
                            numSelectedCards++;
                        }
                    }
                    //System.out.printf("Selected Max: %d\nNum Selected: %d\n", selectedMax, numSelectedCards);   // Debug purposes.
                }
            }
        }
    }
    
    public Hearts() {
        super();
        initComponents();
        playBttn.setVisible(false);
        for (int suit = 0; suit < 4; suit++) {
            for (int face = 1; face < 14; face++) {
                cardFiles[suit][face-1] = new ImageIcon(new ImageIcon(
                    "src/cardImages/"+suits[suit]+Integer.toString(face+1)+".png")
                    .getImage().getScaledInstance(63, 91, Image.SCALE_DEFAULT));
                /*Image imgTemp = cardFiles[suit][face].getImage();
                BufferedImage buTemp = new BufferedImage(imgTemp.getWidth(null), imgTemp.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                Graphics g = buTemp.createGraphics();
                g.drawImage(imgTemp, 0, 0, 63, 91, null);
                cardFiles[suit][face] = new ImageIcon(buTemp);*/
            }
        }
        cardbackFile[0] = new ImageIcon(new ImageIcon("src/cardImages/Card_back.png").getImage().getScaledInstance(63, 91, Image.SCALE_DEFAULT));
        cardbackFile[1] = new ImageIcon(new ImageIcon("src/cardImages/Card_back_side.png").getImage().getScaledInstance(91, 63, Image.SCALE_DEFAULT));
        cardsInTrick[0] = trickCardLeft;
        cardsInTrick[1] = trickCardTop;
        cardsInTrick[2] = trickCardRight;
        cardsInTrick[3] = trickCardBottom;
        cardClicked mHand = new cardClicked();
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
        defaultY = cardsInHand[0].getY();
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
        
        numPlayersCards[0][0] = player1card1;
        numPlayersCards[0][1] = player1card2;
        numPlayersCards[0][2] = player1card3;
        numPlayersCards[0][3] = player1card4;
        numPlayersCards[0][4] = player1card5;
        numPlayersCards[0][5] = player1card6;
        numPlayersCards[0][6] = player1card7;
        numPlayersCards[0][7] = player1card8;
        numPlayersCards[0][8] = player1card9;
        numPlayersCards[0][9] = player1card10;
        numPlayersCards[0][10] = player1card11;
        numPlayersCards[0][11] = player1card12;
        numPlayersCards[0][12] = player1card13;
        numPlayersCards[1][0] = player2card1;
        numPlayersCards[1][1] = player2card2;
        numPlayersCards[1][2] = player2card3;
        numPlayersCards[1][3] = player2card4;
        numPlayersCards[1][4] = player2card5;
        numPlayersCards[1][5] = player2card6;
        numPlayersCards[1][6] = player2card7;
        numPlayersCards[1][7] = player2card8;
        numPlayersCards[1][8] = player2card9;
        numPlayersCards[1][9] = player2card10;
        numPlayersCards[1][10] = player2card11;
        numPlayersCards[1][11] = player2card12;
        numPlayersCards[1][12] = player2card13;
        numPlayersCards[2][0] = player3card1;
        numPlayersCards[2][1] = player3card2;
        numPlayersCards[2][2] = player3card3;
        numPlayersCards[2][3] = player3card4;
        numPlayersCards[2][4] = player3card5;
        numPlayersCards[2][5] = player3card6;
        numPlayersCards[2][6] = player3card7;
        numPlayersCards[2][7] = player3card8;
        numPlayersCards[2][8] = player3card9;
        numPlayersCards[2][9] = player3card10;
        numPlayersCards[2][10] = player3card11;
        numPlayersCards[2][11] = player3card12;
        numPlayersCards[2][12] = player3card13;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 13; j++) {
                if (i == 0 || i == 2) {
                    numPlayersCards[i][j].setIcon(cardbackFile[1]);
                } else {
                    numPlayersCards[i][j].setIcon(cardbackFile[0]);
                }
            }
        }
        updateCards();
        showSwapButton();
    }
    
    private void updateCards() {
        Card thisC;
        Player thisPlayer = getEngine().getActivePlayer();
        System.out.printf("\nPlayer : %s\n", getEngine().getPlayerID());
        Hand thisHand = thisPlayer.getHand();
        int j = 13-thisHand.getSize();
        for (int i =thisHand.getSize() - 1; i >= 0; i--)
        {
            thisC = thisHand.getCard(i+1);
            System.out.printf("%d. %d %d %s %d\n", i, thisC.getSuit(),thisC.getFace(), thisC.getSelected(), thisC.getPoints());
            cardsInHand[i].setIcon(cardFiles[thisC.getSuit()][thisC.getFace() - 1]);
        }
        for(int i = thisHand.getSize(); i < 13; i++)
        {
            cardsInHand[i].setIcon(null);
            cardsInHand[i].setVisible(false);
        }
        Trick thisTrick = getEngine().getCurrentTrick();
        for (int i = 0; i < thisTrick.getSize(); i++)
        {
            thisC = thisTrick.getCardPlayed(i);
            cardsInTrick[i].setIcon(cardFiles[thisC.getSuit()][thisC.getFace() - 1]);
        }
    }
    
    private void updateHandSizes() {
        int id = getEngine().getPlayerID();
        for (int i = 0; i < 3; i++) {
            id++;
            if (id == 4) {
                id = 0;
            }
            int numCards = getEngine().getPlayer(id).getHand().getSize();
            for (int j = 0; j < numCards; j++) {
                numPlayersCards[i][j].setVisible(true);
            }
            for (int j = numCards; j < 13; j++) {
                numPlayersCards[i][j].setVisible(false);
            }
        }
    }
    
    private void reset() {
        for (int i = 0; i < 13; i++) {
            if (cardsInHand[i].getY() < defaultY) {
                cardsInHand[i].setLocation(cardsInHand[i].getX(), cardsInHand[i].getY()+20);
                System.out.printf("\nDeselct happend. Default y: %d\nIndex : %d", defaultY, i);
            }
        }
    }
    
    private void swapPlayers() {
        for (int i = 0; i < 13; i++) {
            cardsInHand[i].setVisible(false);
        }
        playBttn.setText("Start Turn");
        playBttn.setVisible(true);
    }
    
    private void startNextPlayer() {
        for (int i = 0; i < 13; i++) {
            if(cardsInHand[i].getIcon()!= null){
                cardsInHand[i].setVisible(true);
            }
        }
        playBttn.setText("Swap");
        //playBttn.setVisible(false);
        showSwapButton();
        //playerScore.setText(String.format("%d", engine.getActivePlayer().getPoints()));
        //System.out.printf("\nPoints: %d\n", engine.getActivePlayer().getPoints());
    }
    
    private void resetTrick() {
        for (int i = 0; i < 4; i++) {
            cardsInTrick[i].setIcon(null);
        }
    }
    
    private void hideSwapButton() {
        playBttn.setVisible(false);
    }
    
    private void showSwapButton() {
        playBttn.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playBttn = new javax.swing.JButton();
        trickCardTop = new javax.swing.JLabel();
        trickCardRight = new javax.swing.JLabel();
        trickCardLeft = new javax.swing.JLabel();
        trickCardBottom = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        card13 = new javax.swing.JLabel();
        card12 = new javax.swing.JLabel();
        card11 = new javax.swing.JLabel();
        card10 = new javax.swing.JLabel();
        card9 = new javax.swing.JLabel();
        card8 = new javax.swing.JLabel();
        card7 = new javax.swing.JLabel();
        card6 = new javax.swing.JLabel();
        card5 = new javax.swing.JLabel();
        card4 = new javax.swing.JLabel();
        card3 = new javax.swing.JLabel();
        card2 = new javax.swing.JLabel();
        card1 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        player2card13 = new javax.swing.JLabel();
        player2card12 = new javax.swing.JLabel();
        player2card11 = new javax.swing.JLabel();
        player2card10 = new javax.swing.JLabel();
        player2card9 = new javax.swing.JLabel();
        player2card8 = new javax.swing.JLabel();
        player2card7 = new javax.swing.JLabel();
        player2card6 = new javax.swing.JLabel();
        player2card5 = new javax.swing.JLabel();
        player2card4 = new javax.swing.JLabel();
        player2card3 = new javax.swing.JLabel();
        player2card2 = new javax.swing.JLabel();
        player2card1 = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        player1card13 = new javax.swing.JLabel();
        player1card12 = new javax.swing.JLabel();
        player1card11 = new javax.swing.JLabel();
        player1card10 = new javax.swing.JLabel();
        player1card9 = new javax.swing.JLabel();
        player1card8 = new javax.swing.JLabel();
        player1card7 = new javax.swing.JLabel();
        player1card6 = new javax.swing.JLabel();
        player1card5 = new javax.swing.JLabel();
        player1card4 = new javax.swing.JLabel();
        player1card3 = new javax.swing.JLabel();
        player1card2 = new javax.swing.JLabel();
        player1card1 = new javax.swing.JLabel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        player3card13 = new javax.swing.JLabel();
        player3card12 = new javax.swing.JLabel();
        player3card11 = new javax.swing.JLabel();
        player3card10 = new javax.swing.JLabel();
        player3card9 = new javax.swing.JLabel();
        player3card8 = new javax.swing.JLabel();
        player3card7 = new javax.swing.JLabel();
        player3card6 = new javax.swing.JLabel();
        player3card5 = new javax.swing.JLabel();
        player3card4 = new javax.swing.JLabel();
        player3card3 = new javax.swing.JLabel();
        player3card2 = new javax.swing.JLabel();
        player3card1 = new javax.swing.JLabel();
        newGameMenuButton = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        statsMenuButton = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        playBttn.setText("Swap");
        playBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnPressed(evt);
            }
        });

        trickCardTop.setBackground(new java.awt.Color(0, 0, 0));
        trickCardTop.setMaximumSize(new java.awt.Dimension(63, 91));
        trickCardTop.setMinimumSize(new java.awt.Dimension(63, 91));
        trickCardTop.setPreferredSize(new java.awt.Dimension(63, 91));

        trickCardRight.setBackground(new java.awt.Color(0, 0, 0));
        trickCardRight.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        trickCardRight.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        trickCardRight.setMaximumSize(new java.awt.Dimension(63, 91));
        trickCardRight.setMinimumSize(new java.awt.Dimension(63, 91));
        trickCardRight.setPreferredSize(new java.awt.Dimension(63, 91));

        trickCardLeft.setBackground(new java.awt.Color(0, 0, 0));
        trickCardLeft.setMaximumSize(new java.awt.Dimension(63, 91));
        trickCardLeft.setMinimumSize(new java.awt.Dimension(63, 91));
        trickCardLeft.setPreferredSize(new java.awt.Dimension(63, 91));

        trickCardBottom.setBackground(new java.awt.Color(0, 0, 0));
        trickCardBottom.setMaximumSize(new java.awt.Dimension(63, 91));
        trickCardBottom.setMinimumSize(new java.awt.Dimension(63, 91));
        trickCardBottom.setPreferredSize(new java.awt.Dimension(63, 91));

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(514, 91));

        card13.setBackground(new java.awt.Color(0, 0, 0));
        card13.setText("12");
        card13.setAlignmentX(0.2F);
        card13.setMaximumSize(new java.awt.Dimension(63, 91));
        card13.setMinimumSize(new java.awt.Dimension(63, 91));
        card13.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card13);
        card13.setBounds(240, 30, 63, 91);

        card12.setBackground(new java.awt.Color(0, 0, 0));
        card12.setText("11");
        card12.setMaximumSize(new java.awt.Dimension(63, 91));
        card12.setMinimumSize(new java.awt.Dimension(63, 91));
        card12.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card12);
        card12.setBounds(220, 30, 63, 91);

        card11.setBackground(new java.awt.Color(0, 0, 0));
        card11.setText("10");
        card11.setAlignmentX(2.6F);
        card11.setMaximumSize(new java.awt.Dimension(63, 91));
        card11.setMinimumSize(new java.awt.Dimension(63, 91));
        card11.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card11);
        card11.setBounds(200, 30, 63, 91);

        card10.setBackground(new java.awt.Color(0, 0, 0));
        card10.setText("9");
        card10.setAlignmentX(2.2F);
        card10.setMaximumSize(new java.awt.Dimension(63, 91));
        card10.setMinimumSize(new java.awt.Dimension(63, 91));
        card10.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card10);
        card10.setBounds(180, 30, 63, 91);

        card9.setBackground(new java.awt.Color(0, 0, 0));
        card9.setText("8");
        card9.setAlignmentX(2.0F);
        card9.setMaximumSize(new java.awt.Dimension(63, 91));
        card9.setMinimumSize(new java.awt.Dimension(63, 91));
        card9.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card9);
        card9.setBounds(160, 30, 63, 91);

        card8.setBackground(new java.awt.Color(0, 0, 0));
        card8.setText("7");
        card8.setAlignmentX(1.8F);
        card8.setMaximumSize(new java.awt.Dimension(63, 91));
        card8.setMinimumSize(new java.awt.Dimension(63, 91));
        card8.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card8);
        card8.setBounds(140, 30, 63, 91);

        card7.setBackground(new java.awt.Color(0, 0, 0));
        card7.setText("6");
        card7.setAlignmentX(1.6F);
        card7.setMaximumSize(new java.awt.Dimension(63, 91));
        card7.setMinimumSize(new java.awt.Dimension(63, 91));
        card7.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card7);
        card7.setBounds(120, 30, 63, 91);

        card6.setBackground(new java.awt.Color(0, 0, 0));
        card6.setText("5");
        card6.setAlignmentX(1.4F);
        card6.setMaximumSize(new java.awt.Dimension(63, 91));
        card6.setMinimumSize(new java.awt.Dimension(63, 91));
        card6.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card6);
        card6.setBounds(100, 30, 63, 91);

        card5.setBackground(new java.awt.Color(0, 0, 0));
        card5.setText("4");
        card5.setAlignmentX(1.2F);
        card5.setMaximumSize(new java.awt.Dimension(63, 91));
        card5.setMinimumSize(new java.awt.Dimension(63, 91));
        card5.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card5);
        card5.setBounds(80, 30, 63, 91);

        card4.setBackground(new java.awt.Color(0, 0, 0));
        card4.setText("3");
        card4.setAlignmentX(1.0F);
        card4.setMaximumSize(new java.awt.Dimension(63, 91));
        card4.setMinimumSize(new java.awt.Dimension(63, 91));
        card4.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card4);
        card4.setBounds(60, 30, 63, 91);

        card3.setBackground(new java.awt.Color(0, 0, 0));
        card3.setText("2");
        card3.setAlignmentX(0.8F);
        card3.setMaximumSize(new java.awt.Dimension(63, 91));
        card3.setMinimumSize(new java.awt.Dimension(63, 91));
        card3.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card3);
        card3.setBounds(40, 30, 63, 91);

        card2.setBackground(new java.awt.Color(0, 0, 0));
        card2.setText("1");
        card2.setAlignmentX(0.6F);
        card2.setMaximumSize(new java.awt.Dimension(63, 91));
        card2.setMinimumSize(new java.awt.Dimension(63, 91));
        card2.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card2);
        card2.setBounds(20, 30, 63, 91);

        card1.setBackground(new java.awt.Color(0, 0, 0));
        card1.setText("0");
        card1.setAlignmentX(0.4F);
        card1.setMaximumSize(new java.awt.Dimension(63, 91));
        card1.setMinimumSize(new java.awt.Dimension(63, 91));
        card1.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane1.add(card1);
        card1.setBounds(0, 30, 63, 91);

        jLayeredPane2.setPreferredSize(new java.awt.Dimension(514, 91));

        player2card13.setBackground(new java.awt.Color(0, 0, 0));
        player2card13.setAlignmentX(0.2F);
        player2card13.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card13.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card13.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card13);
        player2card13.setBounds(240, 0, 63, 91);

        player2card12.setBackground(new java.awt.Color(0, 0, 0));
        player2card12.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card12.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card12.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card12);
        player2card12.setBounds(220, 0, 63, 91);

        player2card11.setBackground(new java.awt.Color(0, 0, 0));
        player2card11.setAlignmentX(2.6F);
        player2card11.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card11.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card11.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card11);
        player2card11.setBounds(200, 0, 63, 91);

        player2card10.setBackground(new java.awt.Color(0, 0, 0));
        player2card10.setAlignmentX(2.2F);
        player2card10.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card10.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card10.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card10);
        player2card10.setBounds(180, 0, 63, 91);

        player2card9.setBackground(new java.awt.Color(0, 0, 0));
        player2card9.setAlignmentX(2.0F);
        player2card9.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card9.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card9.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card9);
        player2card9.setBounds(160, 0, 63, 91);

        player2card8.setBackground(new java.awt.Color(0, 0, 0));
        player2card8.setAlignmentX(1.8F);
        player2card8.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card8.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card8.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card8);
        player2card8.setBounds(140, 0, 63, 91);

        player2card7.setBackground(new java.awt.Color(0, 0, 0));
        player2card7.setAlignmentX(1.6F);
        player2card7.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card7.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card7.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card7);
        player2card7.setBounds(120, 0, 63, 91);

        player2card6.setBackground(new java.awt.Color(0, 0, 0));
        player2card6.setAlignmentX(1.4F);
        player2card6.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card6.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card6.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card6);
        player2card6.setBounds(100, 0, 63, 91);

        player2card5.setBackground(new java.awt.Color(0, 0, 0));
        player2card5.setAlignmentX(1.2F);
        player2card5.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card5.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card5.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card5);
        player2card5.setBounds(80, 0, 63, 91);

        player2card4.setBackground(new java.awt.Color(0, 0, 0));
        player2card4.setAlignmentX(1.0F);
        player2card4.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card4.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card4.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card4);
        player2card4.setBounds(60, 0, 63, 91);

        player2card3.setBackground(new java.awt.Color(0, 0, 0));
        player2card3.setAlignmentX(0.8F);
        player2card3.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card3.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card3.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card3);
        player2card3.setBounds(40, 0, 63, 91);

        player2card2.setBackground(new java.awt.Color(0, 0, 0));
        player2card2.setAlignmentX(0.6F);
        player2card2.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card2.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card2.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card2);
        player2card2.setBounds(20, 0, 63, 91);

        player2card1.setBackground(new java.awt.Color(0, 0, 0));
        player2card1.setAlignmentX(0.4F);
        player2card1.setMaximumSize(new java.awt.Dimension(63, 91));
        player2card1.setMinimumSize(new java.awt.Dimension(63, 91));
        player2card1.setPreferredSize(new java.awt.Dimension(63, 91));
        jLayeredPane2.add(player2card1);
        player2card1.setBounds(0, 0, 63, 91);

        jLayeredPane3.setPreferredSize(new java.awt.Dimension(514, 91));

        player1card13.setBackground(new java.awt.Color(0, 0, 0));
        player1card13.setAlignmentX(0.4F);
        player1card13.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card13.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card13.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card13);
        player1card13.setBounds(0, 0, 91, 63);

        player1card12.setBackground(new java.awt.Color(0, 0, 0));
        player1card12.setAlignmentX(0.4F);
        player1card12.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card12.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card12.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card12);
        player1card12.setBounds(0, 20, 91, 63);

        player1card11.setBackground(new java.awt.Color(0, 0, 0));
        player1card11.setAlignmentX(0.4F);
        player1card11.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card11.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card11.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card11);
        player1card11.setBounds(0, 40, 91, 63);

        player1card10.setBackground(new java.awt.Color(0, 0, 0));
        player1card10.setAlignmentX(0.4F);
        player1card10.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card10.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card10.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card10);
        player1card10.setBounds(0, 60, 91, 63);

        player1card9.setBackground(new java.awt.Color(0, 0, 0));
        player1card9.setAlignmentX(0.4F);
        player1card9.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card9.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card9.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card9);
        player1card9.setBounds(0, 80, 91, 63);

        player1card8.setBackground(new java.awt.Color(0, 0, 0));
        player1card8.setAlignmentX(0.4F);
        player1card8.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card8.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card8.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card8);
        player1card8.setBounds(0, 100, 91, 63);

        player1card7.setBackground(new java.awt.Color(0, 0, 0));
        player1card7.setAlignmentX(0.4F);
        player1card7.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card7.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card7.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card7);
        player1card7.setBounds(0, 120, 91, 63);

        player1card6.setBackground(new java.awt.Color(0, 0, 0));
        player1card6.setAlignmentX(0.4F);
        player1card6.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card6.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card6.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card6);
        player1card6.setBounds(0, 140, 91, 63);

        player1card5.setBackground(new java.awt.Color(0, 0, 0));
        player1card5.setAlignmentX(0.4F);
        player1card5.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card5.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card5.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card5);
        player1card5.setBounds(0, 160, 91, 63);

        player1card4.setBackground(new java.awt.Color(0, 0, 0));
        player1card4.setAlignmentX(0.4F);
        player1card4.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card4.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card4.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card4);
        player1card4.setBounds(0, 180, 91, 63);

        player1card3.setBackground(new java.awt.Color(0, 0, 0));
        player1card3.setAlignmentX(0.4F);
        player1card3.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card3.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card3.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card3);
        player1card3.setBounds(0, 200, 91, 63);

        player1card2.setBackground(new java.awt.Color(0, 0, 0));
        player1card2.setAlignmentX(0.4F);
        player1card2.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card2.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card2.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card2);
        player1card2.setBounds(0, 220, 91, 63);

        player1card1.setBackground(new java.awt.Color(0, 0, 0));
        player1card1.setAlignmentX(0.4F);
        player1card1.setMaximumSize(new java.awt.Dimension(91, 63));
        player1card1.setMinimumSize(new java.awt.Dimension(91, 63));
        player1card1.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane3.add(player1card1);
        player1card1.setBounds(0, 240, 91, 63);

        jLayeredPane4.setPreferredSize(new java.awt.Dimension(514, 91));

        player3card13.setBackground(new java.awt.Color(0, 0, 0));
        player3card13.setAlignmentX(0.4F);
        player3card13.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card13.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card13.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card13);
        player3card13.setBounds(0, 240, 91, 63);

        player3card12.setBackground(new java.awt.Color(0, 0, 0));
        player3card12.setAlignmentX(0.4F);
        player3card12.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card12.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card12.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card12);
        player3card12.setBounds(0, 220, 91, 63);

        player3card11.setBackground(new java.awt.Color(0, 0, 0));
        player3card11.setAlignmentX(0.4F);
        player3card11.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card11.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card11.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card11);
        player3card11.setBounds(0, 200, 91, 63);

        player3card10.setBackground(new java.awt.Color(0, 0, 0));
        player3card10.setAlignmentX(0.4F);
        player3card10.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card10.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card10.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card10);
        player3card10.setBounds(0, 180, 91, 63);

        player3card9.setBackground(new java.awt.Color(0, 0, 0));
        player3card9.setAlignmentX(0.4F);
        player3card9.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card9.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card9.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card9);
        player3card9.setBounds(0, 160, 91, 63);

        player3card8.setBackground(new java.awt.Color(0, 0, 0));
        player3card8.setAlignmentX(0.4F);
        player3card8.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card8.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card8.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card8);
        player3card8.setBounds(0, 140, 91, 63);

        player3card7.setBackground(new java.awt.Color(0, 0, 0));
        player3card7.setAlignmentX(0.4F);
        player3card7.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card7.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card7.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card7);
        player3card7.setBounds(0, 120, 91, 63);

        player3card6.setBackground(new java.awt.Color(0, 0, 0));
        player3card6.setAlignmentX(0.4F);
        player3card6.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card6.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card6.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card6);
        player3card6.setBounds(0, 100, 91, 63);

        player3card5.setBackground(new java.awt.Color(0, 0, 0));
        player3card5.setAlignmentX(0.4F);
        player3card5.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card5.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card5.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card5);
        player3card5.setBounds(0, 80, 91, 63);

        player3card4.setBackground(new java.awt.Color(0, 0, 0));
        player3card4.setAlignmentX(0.4F);
        player3card4.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card4.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card4.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card4);
        player3card4.setBounds(0, 60, 91, 63);

        player3card3.setBackground(new java.awt.Color(0, 0, 0));
        player3card3.setAlignmentX(0.4F);
        player3card3.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card3.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card3.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card3);
        player3card3.setBounds(0, 40, 91, 63);

        player3card2.setBackground(new java.awt.Color(0, 0, 0));
        player3card2.setAlignmentX(0.4F);
        player3card2.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card2.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card2.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card2);
        player3card2.setBounds(0, 20, 91, 63);

        player3card1.setBackground(new java.awt.Color(0, 0, 0));
        player3card1.setAlignmentX(0.4F);
        player3card1.setMaximumSize(new java.awt.Dimension(91, 63));
        player3card1.setMinimumSize(new java.awt.Dimension(91, 63));
        player3card1.setPreferredSize(new java.awt.Dimension(91, 63));
        jLayeredPane4.add(player3card1);
        player3card1.setBounds(0, 0, 91, 63);

        jMenu1.setText("New Game");
        newGameMenuButton.add(jMenu1);

        statsMenuButton.setText("View Stats");
        statsMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statsMenuButtonMouseClicked(evt);
            }
        });
        newGameMenuButton.add(statsMenuButton);

        setJMenuBar(newGameMenuButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(trickCardLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(trickCardBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trickCardTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(trickCardRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 112, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(playBttn)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(trickCardTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(trickCardBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(trickCardLeft, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(trickCardRight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(106, 106, 106)))
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLayeredPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(playBttn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttnPressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnPressed
        if (playBttn.getText().equals("Swap") && numSelectedCards == 3) {
            numSelectedCards = 0;
            getEngine().buildBuffers();
            updateCards();
            reset();
            swapPlayers();
        } else if (playBttn.getText().equals("Start Turn")) {
            startNextPlayer();
            if(!getEngine().getSwapping()){
                hideSwapButton();
            }
            updateHandSizes();
            numSelectedCards = 0;
            
        }
    }//GEN-LAST:event_bttnPressed

    private void statsMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statsMenuButtonMouseClicked
        StatsGUI stats = new StatsGUI();
        stats.setVisible(true);
    }//GEN-LAST:event_statsMenuButtonMouseClicked

    
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
                Hearts h = new Hearts();
                h.setTitle("Hearts");
                h.setVisible(true);
                //new Hearts().setVisible(true);
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
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar newGameMenuButton;
    private javax.swing.JButton playBttn;
    private javax.swing.JLabel player1card1;
    private javax.swing.JLabel player1card10;
    private javax.swing.JLabel player1card11;
    private javax.swing.JLabel player1card12;
    private javax.swing.JLabel player1card13;
    private javax.swing.JLabel player1card2;
    private javax.swing.JLabel player1card3;
    private javax.swing.JLabel player1card4;
    private javax.swing.JLabel player1card5;
    private javax.swing.JLabel player1card6;
    private javax.swing.JLabel player1card7;
    private javax.swing.JLabel player1card8;
    private javax.swing.JLabel player1card9;
    private javax.swing.JLabel player2card1;
    private javax.swing.JLabel player2card10;
    private javax.swing.JLabel player2card11;
    private javax.swing.JLabel player2card12;
    private javax.swing.JLabel player2card13;
    private javax.swing.JLabel player2card2;
    private javax.swing.JLabel player2card3;
    private javax.swing.JLabel player2card4;
    private javax.swing.JLabel player2card5;
    private javax.swing.JLabel player2card6;
    private javax.swing.JLabel player2card7;
    private javax.swing.JLabel player2card8;
    private javax.swing.JLabel player2card9;
    private javax.swing.JLabel player3card1;
    private javax.swing.JLabel player3card10;
    private javax.swing.JLabel player3card11;
    private javax.swing.JLabel player3card12;
    private javax.swing.JLabel player3card13;
    private javax.swing.JLabel player3card2;
    private javax.swing.JLabel player3card3;
    private javax.swing.JLabel player3card4;
    private javax.swing.JLabel player3card5;
    private javax.swing.JLabel player3card6;
    private javax.swing.JLabel player3card7;
    private javax.swing.JLabel player3card8;
    private javax.swing.JLabel player3card9;
    private javax.swing.JMenu statsMenuButton;
    private javax.swing.JLabel trickCardBottom;
    private javax.swing.JLabel trickCardLeft;
    private javax.swing.JLabel trickCardRight;
    private javax.swing.JLabel trickCardTop;
    // End of variables declaration//GEN-END:variables
}
