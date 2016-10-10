package heartssystem;

import java.util.LinkedList;


public class HeartsEngine {
    private Deck deck = new Deck();
    private LinkedList <Card> tempHand = new LinkedList<Card>();
    private LinkedList<Card> p1Buffer = new LinkedList<Card>();
    private LinkedList<Card> p2Buffer = new LinkedList<Card>();
    private LinkedList<Card> p3Buffer = new LinkedList<Card>();
    private LinkedList<Card> p4Buffer = new LinkedList<Card>();
    private Trick tempTrick;
    private Player[] players = new Player[4];
    private int i;
    private boolean brokenHearts;   // Boolean if anyone has broken hearts
    
    public HeartsEngine()
    {
        for(i = 0; i < 4; i++)
        {
            players[i] = new Player(i + 1);
        }
        tempTrick = new Trick();
    }
    
    public void swapCards(int round){
        if (round < 4){
            p1Buffer = this.players[0].passCards();
            p2Buffer = this.players[1].passCards();
            p3Buffer = this.players[2].passCards();
            p4Buffer = this.players[3].passCards();
        }
        
        if (round == 1){
            players[0].buildHand(p4Buffer);
            players[1].buildHand(p1Buffer);
            players[2].buildHand(p2Buffer);
            players[3].buildHand(p3Buffer);
        }
        else if (round == 2){
            players[0].buildHand(p2Buffer);
            players[1].buildHand(p3Buffer);
            players[2].buildHand(p4Buffer);
            players[3].buildHand(p1Buffer);
        }
        else if (round == 3){
            players[0].buildHand(p3Buffer);
            players[1].buildHand(p4Buffer);
            players[2].buildHand(p1Buffer);
            players[3].buildHand(p2Buffer);
        }
    }
    
    public void DealCards()
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
        this.brokenHearts = false;
    }
    
    public void AddCardToTrick(int playerNumber, int cardIndex)
    {
        if(tempTrick.getPlays() < 4)
        {
            brokenHearts = tempTrick.addCard(this.players[playerNumber].playCard(cardIndex), brokenHearts);
        }
    }
    
    public void AssignPoints()
    {
        players[tempTrick.getPlayerNumber()].takeCards(tempTrick.take());
        tempTrick = new Trick();
    }
        
}
