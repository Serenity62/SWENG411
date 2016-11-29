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

public class Hand {
    private CardSet cardsContained;
    
    public Hand(){
        cardsContained = new CardSet();
    }
    
    public void addCard(Card c){
        cardsContained.addCard(c);
//        this.sortHand();
    }
    
    public boolean hasSuit(int suit) {
        boolean hasSuit = false;
        //sortHand();
        int low = 0;
        int hi = this.getSize() - 1;
        int mid;
        while (low <= hi) {
            mid = low + (hi - low) / 2;
            if (getCard(mid+1).getSuit() < suit) {
                hi = mid - 1;
            } else if (getCard(mid+1).getSuit() > suit) {
                low = mid + 1;
            } else {
                hasSuit = true;
                break;
            }
        }
        return hasSuit;
    }
    
    public void sortHand(){
        this.cardsContained.sort();
    }
    
    public Card getCard(int n) {
        return cardsContained.getCard(n-1);
    }
    
    public int getSize(){
        return cardsContained.getSize();
    }
    
    public LinkedList<Card> getSelectedCards(){
        LinkedList<Card> selected = new LinkedList();
        for (int i = 0; i < cardsContained.getSize(); i++) {
            if (cardsContained.getCard(i).getSelected()){
                cardsContained.getCard(i).setSelected(false);
                selected.add(cardsContained.getCard(i));
                cardsContained.removeCard(cardsContained.getCard(i));
                i--;
            }
        }
        
        return selected;
    }
    
    public Card getSelectedCard(){
        Card selected = new Card(0, -1);
        for (int i = 0; i < cardsContained.getSize(); i++){
            if (cardsContained.getCard(i).getSelected()){
                cardsContained.getCard(i).setSelected(false);
                selected = cardsContained.getCard(i);
                cardsContained.removeCard(selected);
                i = cardsContained.getSize();
            }
        }
        
    return selected;
    }
}
