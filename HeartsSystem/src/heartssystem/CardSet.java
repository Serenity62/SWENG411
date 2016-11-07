/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartssystem;

import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author CUZ126
 */
public class CardSet {
    private LinkedList<Card> cards;
    
    public CardSet(){
        cards = new LinkedList<>();
    }
    
    public void addCards(LinkedList<Card> c){
        cards.addAll(c);
    }
    
    public void addCard(Card c){
        cards.add(c);
    }
    
    public LinkedList<Card> getCards(){
        return cards;
    }
    
    public Card getCard(int i){
        return cards.get(i);
    }
    
    public int getSize(){
        return cards.size();
    }
    
    public void shuffleCards(){
        Collections.shuffle(cards);
    }
    
    public void removeCard(Card c){
        cards.remove(c);
    }
}
