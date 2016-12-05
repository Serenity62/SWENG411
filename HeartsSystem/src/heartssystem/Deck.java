package heartssystem;

public class Deck extends CardSet {
    public Deck()
    {
        this.createDeck();
    }
    
    private void createDeck()
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
                addCard(card);
                //System.out.printf("%d %d\n", card.getFace(), card.getSuit());
            }
        }
    }
    
    public CardSet deal(int playerOffset)  // player is which player, this for an offset for dealing the cards
    {
        CardSet hand = new CardSet(); // hand to be printed out
        
        for(int i = 0; i < 13; i++)
        {
            hand.addCard(getCard(playerOffset));
            playerOffset += 4;
        }
            
        return hand;
    }
}
