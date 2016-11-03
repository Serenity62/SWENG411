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
    private LinkedList<Card> cardsContained;
    
    public Hand(){
        cardsContained = new LinkedList<Card>();
    }
    
    public void addCard(Card c){
        cardsContained.add(c);
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
            if (getCard(mid).getSuit() < suit) {
                hi = mid - 1;
            } else if (getCard(mid).getSuit() > suit) {
                low = mid + 1;
            } else {
                hasSuit = true;
                break;
            }
        }
        return true;
    }
    
    public Card getCard(int n) {
        return cardsContained.get(n-1);
    }
    
    public int getSize(){
        return cardsContained.size();
    }
    
    public LinkedList<Card> getSelectedCards(){
        LinkedList<Card> selected = new LinkedList();
        for (int i = 0; i < cardsContained.size(); i++) {
            if (cardsContained.get(i).getSelected()){
                selected.add(cardsContained.get(i));
                cardsContained.remove(cardsContained.get(i));
            }
        }
        
        return selected;
    }
    
    public Card getSelectedCard(){
        Card selected = new Card(0, -1);
        for (int i = 0; i < cardsContained.size(); i++){
            if (cardsContained.get(i).getSelected()){
                selected = cardsContained.get(i);
                cardsContained.remove(i);
            }
        }
        
    return selected;
    }
    
//    public void sortHand(){
//        int suitSorting = 1;
//        LinkedList tempList = new LinkedList();
//        
//        if (cardsContained.size() > 1){
//            while (suitSorting < 5){ // This handles which suit we're currently sorting
//                for (int i = 0; i < cardsContained.size(); i++)
//                {
//                    if (cardsContained.getIndexAt(i).getSuit() == suitSorting){ // Implement a sort here to sort by value. 
//                        tempList.add(cardsContained.getIndexAt(i));
//                    }
//                }
//            }
//        }
//    }
}
