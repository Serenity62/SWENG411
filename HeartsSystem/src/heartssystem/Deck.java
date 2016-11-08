/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartssystem;

import java.util.LinkedList;
import java.util.Collections;

/**
 *
 * @author CUZ126
 */
public class Deck {
    //private static LinkedList<Card> deck =  new LinkedList<Card>();
    private CardSet deck = new CardSet();
    
    public Deck()
    {
        this.CreateDeck();
    }
    
    private final void CreateDeck()
    {
        
        //System.out.println("Created card: ");
        for(int i = 1; i < 14; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                Card card = new Card(i, j);
                if(j == 3)
                    card.setPoint(1);
                else if(i == 12 && j == 2)
                    card.setPoint(13);
                else
                    card.setPoint(0);
                deck.addCard(card);
                //System.out.printf("%d %d\n", card.getFace(), card.getSuit());
            }
        }
        
    }
    
    public void Shuffle()
    {
        deck.shuffleCards();
//        System.out.println("Deck List:");
//        for(int i = 0; i < 52; i++)
//        {
//            System.out.printf("%d %d, ", deck.get(i).getFace(), deck.get(i).getSuit());
//        }
//        System.out.println();
            
    }
    
    public void Clear(){
        deck = new CardSet();
    }
    
    public LinkedList<Card> Deal(int playerOffset)  // player is which player, this for an offset for dealing the cards
    {
        LinkedList<Card> hand = new LinkedList<>(); // hand to be printed out
        
        for(int i = 0; i < 13; i++)
        {
            hand.add(deck.getCard(playerOffset));
            playerOffset += 4;
        }
            
        return hand;
    }
}
