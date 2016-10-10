package heartssystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MFR5107
 */

import java.util.*;

public class Player {
    private int points;
    private LinkedList<Card> cardsOwned;
    private Hand hand;
    private int playerID;
    
    public Player(int id){
        playerID = id;
        points = 0;
        cardsOwned = new LinkedList<Card>();
    }
    
    public Hand getHand(){
        return hand;
    }
    
    public LinkedList<Card> playCard(){
        // Add a card to the current trick. Do this by calling getTrick from the gameboard? GUI? Not sure. 
        return this.hand.getSelectedCards();
    }
    
    public int getPoint(){
        return points;
    }
    
    public void takeCards(LinkedList<Card> takenCards){ // getTrick again - need to get the current trick so you can take cards from it.
        takenCards.stream().forEach((card) -> {
            this.cardsOwned.add(card);
        });
    }
    
    public void buildHand(Card c){
        hand.addCard(c);
    }
    
    public void buildHand(LinkedList<Card> cards){
        for (int i = 0; i < cards.size(); i++){
            hand.addCard(cards.get(i));
        }
    }
    
    public LinkedList<Card> passCards(){
        return this.hand.getSelectedCards();
        
    }
    
    public void updateTotal(){
        for (int i = 0; i < cardsOwned.size(); i++)
        {
            points = points + cardsOwned.get(i).getPoints();
        }
    }
}
