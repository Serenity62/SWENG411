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
    
    public void sort(){
        LinkedList<Card> clubs = new LinkedList<>();
        LinkedList<Card> diamonds = new LinkedList<>();
        LinkedList<Card> spades = new LinkedList<>();
        LinkedList<Card> hearts = new LinkedList<>();
        
        for (int i = 0; i < cards.size(); i++){
            switch(cards.get(i).getSuit()){
                case 0:
                    clubs.add(cards.get(i));
                    break;
                case 1:
                    diamonds.add(cards.get(i));
                    break;
                case 2:
                    spades.add(cards.get(i));
                    break;
                case 3:
                    hearts.add(cards.get(i));
                    break;
                default:
                    System.out.println("Invalid suit! How did you get here?");
                    break;
            }
        }
        
        cards.clear();
        Collections.sort(clubs, (c1, c2) -> c1.getFace() - c2.getFace());
        cards.addAll(clubs);
        Collections.sort(diamonds, (d1, d2) -> d1.getFace() - d2.getFace());
        cards.addAll(diamonds);
        Collections.sort(spades, (s1, s2) -> s1.getFace() - s2.getFace());
        cards.addAll(spades);
        Collections.sort(hearts, (h1, h2) -> h1.getFace() - h2.getFace());
        cards.addAll(hearts);
    }
    
    public void shuffleCards(){
        Collections.shuffle(cards);
    }
    
    public void removeCard(Card c){
        cards.remove(c);
    }
}
