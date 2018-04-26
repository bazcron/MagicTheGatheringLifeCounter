package barry.magicthegatheringlifecounter;

/**
 * Created by Conor on 02/04/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayersList {

    @SerializedName("players")
    @Expose
    private List<Players> players = null;

    public List<Players> getPlayers() {
        return players;
    }

    public void setPlayers(List<Players> players) {
        this.players = players;
    }

}

