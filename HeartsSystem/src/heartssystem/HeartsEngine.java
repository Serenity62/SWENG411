package heartssystem;

import java.util.LinkedList;


public class HeartsEngine {
    private Deck deck = new Deck();
    private LinkedList <Card> tempHand = new LinkedList();
    private LinkedList<Card>[] buffers = new LinkedList[4];
    private Trick currentTrick;
    private int round;
    private int trickNum;
    private Player[] players = new Player[4];
    private Player winner;
    private int activeID;
    private int i;
    private int passCount;
    private boolean brokenHearts;   // Boolean if anyone has broken hearts
    private boolean passing;
    private boolean gameEnd;
    
    public HeartsEngine()
    {
        for(i = 0; i < 4; i++)
        {
            players[i] = new Player(i + 1);
            buffers[i] = new LinkedList();
        }
        round = 1;
        trickNum = 0;
        passCount = 0;
        passing = false;
        gameEnd = false;
        startRound();
    }
    
    public boolean getHeartsBroken() {
        return brokenHearts;
    }
    
    public Trick getCurrentTrick() {
        return currentTrick;
    }
    
    public boolean getSwapping() {
        return passing;
    }
    
    public void buildBuffers(){
        buffers[activeID] = players[activeID].passCards();   
        if (passCount == 3){
            this.endPassing();
            this.swapCards();
            passCount = 0;
        }
        else
        {
            passCount++; 
        }
        this.activateNextPlayer();
    }
    
    public void swapCards(){
        
        switch (round%4) {
            case 1:
                players[0].buildHand(buffers[3]);
                players[1].buildHand(buffers[2]);
                players[2].buildHand(buffers[1]);
                players[3].buildHand(buffers[0]);
                break;
            case 2:
                players[0].buildHand(buffers[1]);
                players[1].buildHand(buffers[2]);
                players[2].buildHand(buffers[3]);
                players[3].buildHand(buffers[0]);
                break;
            case 3:
                players[0].buildHand(buffers[2]);
                players[1].buildHand(buffers[3]);
                players[2].buildHand(buffers[0]);
                players[3].buildHand(buffers[1]);
                break;
            default:
                break;
        }
        for (int i = 0; i < 4; i++){
            players[i].getHand().sortHand();
        }
        
    }
    
    public void dealCards()
    {
        // Shuffle the deck
        deck.Shuffle();
        // Deal out the hand
        for(i = 0; i < 4; i++)
        {
            tempHand = deck.Deal(i);
            players[i].buildHand(tempHand);
            players[i].getHand().sortHand();
        }
    }
    
    public void activateNextPlayer(){
        if (activeID < 3){
            activeID++;
        }
        else
        {
            activeID = 0;
        }
    }
    public Player getActivePlayer(){
        return players[activeID];
    }
    
    public Player getPlayer(int p){
        return players[p];
    }
    
    public int getPlayerID() {
        return activeID;
    }
    
    public Player getWinner(){
        return winner;
    }
    
    public void addCardToTrick()
    {
        brokenHearts = currentTrick.addCard(players[activeID].playCard(), brokenHearts);
        if (currentTrick.getSize() > 3){
            this.endTrick();
        }
        this.activateNextPlayer();
    }
    
    public void assignPoints()
    {
        players[currentTrick.getPlayerNumber()].takeCards(currentTrick.take());
    }
    
    public void startTrick(){
        this.currentTrick = new Trick();
    }
    
    public void endTrick(){
        this.assignPoints();
        if (trickNum < 12){
            this.startTrick();
            trickNum++;
        }
        else{
            this.endRound();
        }
        
    }
    
    public void startRound(){
        activeID = 0;
        this.dealCards();
        while(!(players[activeID].getHand().getCard(1).getSuit() == 0 
                    && players[activeID].getHand().getCard(1).getFace() == 2)){
            activeID++;
            if(activeID == 4){
                activeID--;
                break; // temp fix
            }
        }
        this.startPassing();
        this.startTrick();
        this.brokenHearts = false;
        trickNum = 0;
    }
    
    public void startPassing(){
        this.passing = true;
    }
    
    public void endPassing(){
        this.passing = false;
    }
    
    public void endRound(){
        if (!checkVictory()){
            round++;
            for (int i = 0; i < 4; i++){
                players[i].calcPoints();
            }
            startRound();
        }
    }
    
    public boolean checkVictory(){
        boolean vict = false;
        for (int i = 0; i < 4; i++){
            if (players[i].getPoints() >= 100){
                vict = true;
                winner = players[i];
            }
        }
        return vict;
    }   
}
