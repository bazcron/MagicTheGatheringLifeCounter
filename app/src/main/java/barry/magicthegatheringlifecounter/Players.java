package barry.magicthegatheringlifecounter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Players {
    private String playerName;
    private Integer totalScore;
    private Integer played;
    private Integer won;
    private Integer lost;
    private Integer lifeTotal;

   /* public Players(String name){
        this.setPlayerName(name);
    }*/

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getPlayed() {
        return played;
    }

    public void setPlayed(Integer played) {
        this.played = played;
    }

    public Integer getWon() {
        return won;
    }

    public void setWon(Integer won) {
        this.won = won;
    }

    public Integer getLost() {
        return lost;
    }

    public void setLost(Integer lost) {
        this.lost = lost;
    }

    public Integer getLifeTotal() {
        return lifeTotal;
    }

    public void setLifeTotal(Integer lifeTotal) {
        this.lifeTotal = lifeTotal;
    }

}




