package heartssystem;

public class Trick {
    private CardSet cardsPlayed;
    private int openingSuit;
    private int points;
    private int playerNumber;
    
    
    public Trick(){
        cardsPlayed = new CardSet();
        points = 0;
        playerNumber = 0;
    }
    
    public CardSet take(){
        CardSet tempList = new CardSet();
        tempList.addCards(cardsPlayed.getCards());
        cardsPlayed.clear();    // Also clearing templist???
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
        return this.cardsPlayed.getCard(i);
    }
    
    public boolean addCard(Card c, boolean brokenHearts){
        boolean flag = false;
        points += c.getPoints();
        
        if(this.getSize() == 0)
            this.openingSuit = c.getSuit();
        cardsPlayed.addCard(c);
        if(this.getSize() == 4)
            this.trickCalc();
        if(!brokenHearts && c.getSuit() != this.openingSuit && c.getSuit() == 3)    // Adjust for if someone broke hearts
            flag = true;
        return flag || brokenHearts;
    }
    
    public int getSize(){
        return cardsPlayed.getSize();
    }
    
    private void trickCalc()
    {
        int highest = 0;
        for(int i = 0; i < cardsPlayed.getSize(); i++)
        {
            if(cardsPlayed.getCard(i).getSuit() == this.openingSuit)
            {
                if(cardsPlayed.getCard(i).getFace() > highest)
                {
                    highest = cardsPlayed.getCard(i).getFace();
                    this.playerNumber = i;
                }
            }
        }
    }
}