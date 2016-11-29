package heartssystem;

public class HeartsSession {
    private int gamesWon[] = new int[4];
    private int moonsShot[] = new int[4];
    
    
    public HeartsSession(){
        for (int i = 0; i < 4; i++){
            gamesWon[i] = 0;
            moonsShot[i] = 0;
        }
    }
    
    public int getWins(int index){ // Index should be from 0 to 3; pass in the player ID?
        return gamesWon[index];
    }
    
    public int getMoons(int index){
        return moonsShot[index];
    }
    
    public void addWin(int index){
        gamesWon[index] = gamesWon[index] + 1;
    }
    
    public void addMoon(int index){
        moonsShot[index] = moonsShot[index] + 1;
    }
}
