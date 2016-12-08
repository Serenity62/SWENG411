package heartssystem;

public class HeartsEngine {
    private static HeartsEngine instance = null;
    private Deck deck = new Deck();
    private HeartsSession session;
    private Trick currentTrick;
    private CardSet tempHand = new CardSet();
    private CardSet[] buffers = new CardSet[4];
    private int round;
    private int trickNum;
    private Player[] players = new Player[4];
    private Player winner;
    private int activeID;
    private int passCount;
    private boolean brokenHearts;   // Boolean if anyone has broken hearts
    private boolean passing;
    private boolean gameEnd;
    
    private HeartsEngine()
    {
        session = new HeartsSession();
        for(int i = 0; i < 4; i++)
        {
            players[i] = new Player(i + 1);
            buffers[i] = new CardSet();
        }
        round = 1;
        trickNum = 0;
        passCount = 0;
        passing = false;
        gameEnd = false;
        startRound();
    }
    
    public static HeartsEngine getInstance() {
        if (instance == null) {
            instance = new HeartsEngine();
        }
        return instance;
    }
    
    public boolean getHeartsBroken() {
        return brokenHearts;
    }
    
    public Trick getCurrentTrick() {
        return currentTrick;
    }
    
    public int getTrickNum() {
        return trickNum;
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
        System.out.println("----------------------------");
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
            players[i].getHand().sort();
        }
        
    }
    
    public void dealCards()
    {
        // Shuffle the deck
        deck.shuffle();
        // deal out the hand
        for(int i = 0; i < 4; i++)
        {
            tempHand = deck.deal(i);
            players[i].buildHand(tempHand);
            players[i].getHand().sort();
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
    
    public HeartsSession getSession(){
        return session;
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
        if (players[currentTrick.getPlayerNumber()].getTakenPoints() == 26 && trickNum == 13){
            players[currentTrick.getPlayerNumber()].addScore(-26);
            session.addMoon(currentTrick.getPlayerNumber());
            switch(currentTrick.getPlayerNumber()){
                case 0:
                    players[1].addScore(26);
                    players[2].addScore(26);
                    players[3].addScore(26);
                    break;
                case 1:
                    players[0].addScore(26);
                    players[2].addScore(26);
                    players[3].addScore(26);
                    break;
                case 2:
                    players[0].addScore(26);
                    players[1].addScore(26);
                    players[3].addScore(26);
                    break;
                case 3:
                    players[0].addScore(26);
                    players[1].addScore(26);
                    players[2].addScore(26);
                    break;
            }
            players[currentTrick.getPlayerNumber()].clearTakenCards();
        }
    }
    
    public void startTrick(){
        this.currentTrick = new Trick();
    }
    
    public void endTrick(){
        System.out.println("----------------------------");
        this.assignPoints();
        System.out.printf("Points: %d", players[this.activeID].getPoints());
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
        while(!(players[activeID].getHand().getCard(0).getSuit() == 0 
                    && players[activeID].getHand().getCard(0).getFace() == 1)){ // Check this logic
            activeID++;
            if(activeID == 4){
                activeID--;
                break; // temp fix
            }
        }
        if(this.round % 4 != 0)
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
            ScoreGUI endR = new ScoreGUI(this);
            endR.setVisible(true);
            round++;
            startRound();
        } else {
            ScoreGUI endR = new ScoreGUI(this,winner.getID());
            endR.setVisible(true);
        }
    }
    
    public boolean checkVictory(){
        boolean vict = false;
        int winID = 0;
        for (int i = 0; i < 4; i++){
            if (players[i].getPoints() >= 100){
                vict = true;
                winner = players[0];
                for (int j = 0; j < 4; j++){
                    if (j != 3 && players[j].getPoints() > players[j+1].getPoints()){
                        winner = players[j+1];
                        winID = j+1;
                    }
                    else if (j == 3 && players[j].getPoints() > players[0].getPoints()){
                        winner = players[0];
                        winID = 0;
                    }
                }
                break; // Don't bother looping if we've found someone with > 100 points. 
            }
        }
        session.addWin(winID);
        return vict;
    }
    
    public void newGame(){
        round = 1;
        trickNum = 0;
        passCount = 0;
        passing = false;
        gameEnd = false;
        for (int i = 0; i < 4; i++){
            players[i].getTakenCards().clear();
            players[i].setPoints(0);
        }
        startRound();
    }
}