package heartssystem;

import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MFR5107
 */
public class Trick {
    
    private LinkedList<Card> cardsPlayed;
    private int openingSuit;
    private int points;
    private int playerNumber;
    
    
    public Trick(){
        cardsPlayed = new LinkedList();
        points = 0;
        playerNumber = 0;
    }
    
    public LinkedList take(){
        LinkedList<Card> tempList = cardsPlayed;
        cardsPlayed.clear();
        return tempList;
    }
    
    public int getOpening(){
        return openingSuit;
    }
    
    public int getPoints(){
        return points;
    }
    
    public int getPlayerNumber()
    {
        return this.playerNumber;
    }
    
    public Card getCardPlayed(int i)
    {
        return this.cardsPlayed.get(i);
    }
    
    public boolean addCard(Card c, boolean brokenHearts){
        boolean flag = false;
        points += c.getPoints();
        
        if(this.getSize() == 0)
            this.openingSuit = c.getSuit();
        cardsPlayed.add(c);
        if(this.getSize() == 4)
            this.trickCalc();
        if(!brokenHearts && c.getSuit() != this.openingSuit && c.getSuit() == 1)    // Adjust for if someone broke hearts
            flag = true;
        return flag;
    }
    
    public int getSize(){
        return cardsPlayed.size();
    }
    
    private void trickCalc()
    {
        int highest = 0;
        int counter = 0;
        for(Card card : cardsPlayed)
        {
            if(card.getSuit() == this.openingSuit)
            {
                if(card.getFace() > highest)
                {
                    highest = card.getFace();
                    this.playerNumber = counter;
                }
            }
            counter++;
        }
    }
}
