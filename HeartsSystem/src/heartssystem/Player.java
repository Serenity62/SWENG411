package heartssystem;

import java.util.*;

public class Player {
    private int points;
    private CardSet cardsTaken;
    private Hand hand;
    private int playerID;
    
    public Player(int id){
        playerID = id;
        points = 0;
        cardsTaken = new CardSet();
        hand = new Hand();
    }
    
    public Hand getHand(){
        return hand;
    }
    
    public void setHand(Hand h){
        hand = h;
    }
    
    public Card playCard(){
        // Add a card to the current trick. Do this by calling getTrick from the gameboard? GUI? Not sure. 
        return this.hand.getSelectedCard();
    }
    
    public int getPoints(){ // Change to getPoints.
        return points;
    }
    
    public void takeCards(LinkedList<Card> takenCards){ // getTrick again - need to get the current trick so you can take cards from it.
        for (int i = 0; i < takenCards.size(); i++) {
            this.cardsTaken.addCard(takenCards.get(i));
            this.points += takenCards.get(i).getPoints();
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
}
