package barry.magicthegatheringlifecounter;

/**
 * Created by Conor on 13/03/2018.
 */

public class Player {

    // code aquired from this site http://ezzylearning.com/tutorial/binding-android-listview-with-custom-objects-using-arrayadapter

    private String playerName;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int totalPoints;
    private int playerLife;


    //different constructors
    public Player(String name, int played, int won, int lost, int points) {
        super();
        this.playerName = name;
        this.gamesPlayed = played;
        this.gamesWon = won;
        this.gamesLost = lost;
        this.totalPoints = points;
    }

    public Player( String name){
        this.playerName = name;
    }
    public Player( int life){
        this.playerLife = life;
    }

    //getters and setters for variables

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerLife() {
        return playerLife;
    }

    public void setPlayerLife(int playerLife) {
        this.playerLife = playerLife;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return this.playerName + "     Life: "+ this.playerLife ;
    }




}
