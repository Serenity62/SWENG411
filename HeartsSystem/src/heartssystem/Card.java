/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heartssystem;

/**
 *
 * @author CUZ126
 */
public class Card {
    private int face;   // Number/value of the card like Jack or King or 2
    private int suit;   // 0 = Clubs, 1 = Diamonds, 2 = Spades, 3 = Hearts, -1 = null
    private int point;
    private boolean selected;
    
    public Card(int num, int suit)
    {
        this.face = num;
        this.suit = suit;
        this.point = 0;
    }
    
    public int getFace()
    {
        return face;
    }
    
    public void setFace(int num)
    {
        this.face = num;
    }
    
    public int getSuit()
    {
        return suit;
    }
    
    public void setSuit(int suit)
    {
        this.suit = suit;
    }
    
    public void setPoint(int pt)
    {
        this.point = pt;
    }
    
    public int getPoints()
    {
        return point;
    }
    
    public void setSelected(boolean s)
    {
        this.selected = s;
    }
    
    public boolean getSelected()
    {
        return this.selected;
    }
}
