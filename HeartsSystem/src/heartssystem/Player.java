package heartssystem;

import java.util.*;

public class Player {
    private int points;
    private CardSet cardsTaken;
    private Hand hand;
    private int playerID;
    private String name;
    
    public Player(int id){
        playerID = id;
        points = 0;
        cardsTaken = new CardSet();
        hand = new Hand();
        name = "Player " + Integer.toString(id); // incase they don't enter a name, so it has a default value
    }
    
    public int getID() {
        return playerID;
    }
    
    public Hand getHand(){
        return hand;
    }
    
    public void setHand(Hand h){
        hand = h;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public Card playCard(){
        // Add a card to the current trick. Do this by calling getTrick from the gameboard? GUI? Not sure. 
        return this.hand.getSelectedCard();
    }
    
    public int getPoints(){ // Change to getPoints.
        return points;
    }
    
    public void takeCards(CardSet takenCards){ // getTrick again - need to get the current trick so you can take cards from it.
        for (int i = 0; i < takenCards.getSize(); i++) {
            this.cardsTaken.addCard(takenCards.getCard(i));
            this.points += takenCards.getCard(i).getPoints();
        }
    }
    
    public void buildHand(Card c){
        hand.addCard(c);
    }
    
    public void buildHand(CardSet cards){
        for (int i = 0; i < cards.getSize(); i++){
            hand.addCard(cards.getCard(i));
        }
    }
    
    public CardSet passCards(){
        return this.hand.getSelectedCards();
        
    }
    
    public int getTakenPoints(){
        int tempPoints = 0;
        for (int i = 0; i < cardsTaken.getSize(); i++){
            tempPoints = tempPoints + cardsTaken.getCard(i).getPoints();
        }
        
        return tempPoints;
    }
    
    public void addScore(int sc){
        points = points + sc;
    }
    
    public CardSet getTakenCards(){
        return cardsTaken;
    }
    
    public void setPoints(int p){
        points = p;
    }
    
    public void clearTakenCards(){
        this.cardsTaken.clear();
    }
}