/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartssystem;

import java.util.LinkedList;

/**
 *
 * @author Serenity
 */
public class HeartBackground {
    private Deck deck = new Deck();
    private LinkedList <Card> tempHand = new LinkedList<Card>();
    private Trick tempTrick;
    private Player[] players = new Player[4];
    private int i;
    
    public HeartBackground()
    {
        for(i = 0; i < 4; i++)
        {
            players[i] = new Player(i + 1);
        }
    }
    
    public void Round()
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
        // Play 13 Tricks
        for(i = 0; i < 13; i++)
        {
            tempTrick = new Trick();
            
        }
    }
        
}
