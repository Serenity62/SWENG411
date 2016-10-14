package heartssystem;

import java.util.LinkedList;


public class HeartsEngine {
    private Deck deck = new Deck();
    private LinkedList <Card> tempHand = new LinkedList();
    private LinkedList<Card>[] buffers = new LinkedList[4];
    private Trick currentTrick;
    private int round;
    private Player[] players = new Player[4];
    private Player activePlayer;
    private Player winner;
    private int activeID;
    private int i;
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
        currentTrick = new Trick();
        round = 0;
        passing = false;
        gameEnd = false;
        activeID = 0;
    }
    
    public Trick getCurrentTrick() {
        return currentTrick;
    }
    
    public boolean getSwapping() {
        return passing;
    }
    
    public void buildBuffers(){
        buffers[activeID] = this.activePlayer.passCards();
    }
    
    public void swapCards(){
        
        if (round == 1){
            players[0].buildHand(buffers[3]);
            players[1].buildHand(buffers[2]);
            players[2].buildHand(buffers[1]);
            players[3].buildHand(buffers[0]);
        }
        else if (round == 2){
            players[0].buildHand(buffers[1]);
            players[1].buildHand(buffers[2]);
            players[2].buildHand(buffers[3]);
            players[3].buildHand(buffers[0]);
        }
        else if (round == 3){
            players[0].buildHand(buffers[2]);
            players[1].buildHand(buffers[3]);
            players[2].buildHand(buffers[0]);
            players[3].buildHand(buffers[1]);
        }
    }
    
    public void DealCards()
    {
        // Shuffle the deck
        deck.Shuffle();
        // Deal out the hand
        for(i = 0; i < 4; i++)
        {
            tempHand = deck.Deal(i);
            for(int j = 0; j < tempHand.size(); j++)
            {
                players[i].buildHand(tempHand.pop());
            }
        }
        this.brokenHearts = false;
    }
    
    public void activateNextPlayer(){
        if (activeID < 3){
            activeID++;
            activePlayer = players[activeID];
        }
        else
        {
            activeID = 0;
            activePlayer = players[activeID];
        }
    }
    public Player getActivePlayer(){
        return activePlayer;
    }
    
    public Player getWinner(){
        return winner;
    }
    
    public void AddCardToTrick()
    {
        if(tempTrick.getPlays() < 4)
        {
            brokenHearts = tempTrick.addCard(this.activePlayer.playCard(), brokenHearts);
        }
    }
    
    public void AssignPoints()
    {
        players[tempTrick.getPlayerNumber()].takeCards(tempTrick.take());
        tempTrick = new Trick();
    }
    
    
    public void startRound(){
        
    }
    
    public void endRound(){
        if (!checkVictory()){
            round++;
            for (int i = 0; i < 4; i++){
                players[i].calcPoints();
            }
            deck.Clear();   // Reset the deck for the next round.
            deck.CreateDeck();
            deck.Shuffle();
            startRound();
        }
    }
    
    public boolean checkVictory(){
        boolean vict = false;
        for (int i = 0; i < 4; i++){
            if (players[i].getPoint() >= 100){
                vict = true;
                winner = players[i];
            }
        }
        return vict;
    }   
}
