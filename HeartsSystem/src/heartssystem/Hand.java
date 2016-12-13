package heartssystem;

public class Hand extends CardSet {
    public Hand(){
        super();
    }
    
    public boolean hasSuit(int suit) {
        boolean hasSuit = false;
        //sortHand();
        for(int i = 0; i < this.getSize() && !hasSuit; i++){
            if(this.getCard(i).getSuit() == suit)
                hasSuit = true;
        }
        return hasSuit;
    }
}
