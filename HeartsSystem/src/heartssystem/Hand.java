package heartssystem;

public class Hand extends CardSet {
    public Hand(){
        super();
    }
    
    public boolean hasSuit(int suit) {
        boolean hasSuit = false;
        //sortHand();
        int low = 0;
        int hi = this.getSize() - 1;
        int mid;
        while (low <= hi) {
            mid = low + (hi - low) / 2;
            if (getCard(mid).getSuit() < suit) {
                hi = mid - 1;
            } else if (getCard(mid).getSuit() > suit) {
                low = mid + 1;
            } else {
                hasSuit = true;
                break;
            }
        }
        return hasSuit;
    }
}
