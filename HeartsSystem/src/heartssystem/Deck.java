/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartssystem;

import java.util.LinkedList;
import java.util.Collections;

public class Deck extends CardSet {
    public Deck()
    {
        super();
        createDeck();
    }
    
    private void createDeck()
    {
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
                addCard(card);
                //System.out.printf("%d %d\n", card.getFace(), card.getSuit());
            }
        }   
    }
    
    public LinkedList<Card> deal(int playerOffset)  // player is which player, this for an offset for dealing the cards
    {
        LinkedList<Card> hand = new LinkedList<>(); // hand to be printed out
        
        for(int i = 0; i < 13; i++)
        {
            hand.add(getCard(playerOffset));
            playerOffset += 4;
        }
            
        return hand;
    }
}
