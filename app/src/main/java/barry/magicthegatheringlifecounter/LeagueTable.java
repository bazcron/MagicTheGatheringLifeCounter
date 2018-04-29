package barry.magicthegatheringlifecounter;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static barry.magicthegatheringlifecounter.MainActivity.players;
import static barry.magicthegatheringlifecounter.MainActivity.playersList;

public class LeagueTable extends AppCompatActivity {
    private List<Players> allPlayers;
    private ArrayList<String> eachPlayer;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> playerListSort;
    private Boolean listIsSorted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //.........................
        //run through each object within players
        allPlayers = playersList.getPlayers();

        listView = (ListView) findViewById(R.id.listView1);


        int numOfPlayers = allPlayers.size();
        eachPlayer = new ArrayList<String>();

        if (!listIsSorted) {
            for (int i = 0; i < numOfPlayers; i++) {
                eachPlayer.add(allPlayers.get(i).getPlayerName() + "    Games Played: " + allPlayers.get(i).getPlayed() +
                        "    Won: " + allPlayers.get(i).getWon() +
                        "    Lost: " + allPlayers.get(i).getLost() + "    Points: " + allPlayers.get(i).getTotalScore());
            }
        }else{eachPlayer = (ArrayList<String>) playerListSort;        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, eachPlayer);
        listView.setAdapter(adapter);



    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_light:
                if (checked) {
               /* Toast.makeText(this, "RED",
                        Toast.LENGTH_LONG).show();*/
                    sortPlayerByName();
                    break;
                }
            case R.id.radio_dark:
                if (checked){
               /* Toast.makeText(this, "RED",
                        Toast.LENGTH_LONG).show();*/
                    sortPlayerByName();
                    break;
                }
        }
    }
    public void sortPlayerByName()
    {
        playerListSort= eachPlayer;
       /* Collections.sort(playerListSort, new Comparator() {
            public int compare(Object o1, Object o2) {
                Players player1 = (Players) o1;
                Players player2 = (Players) o2;
                String playerName1 = player1.getPlayerName();
                String playerName2 = player2.getPlayerName();

                return playerName1.compareToIgnoreCase(playerName2);
            }
        });*/
        //adapter.clear();
        adapter = (new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,playerListSort));
        listView.setAdapter(adapter);
        listIsSorted=true;
    }

    private void sortPlayerByPoints(){
   /* playerListSort= eachPlayer;
    Collections.sort(playerListSort, new Comparator<Object>() {
        public int compare(Object o1, Object o2) {
            Players p1 = (Players) o1;
            Players p2 = (Players) o2;
            int points1 = p1.getWon();
            int points2 = p2.getWon();

            // return Integer.valueOf(((Players) o1).getWon().compareTo(((Players) o2).getWon()));

            if(points1 > points2){
                return 1;
            }else if(points2 > points1){
                return -1;
            }else{
                return 0;
            }
        }
    });
    adapter = (new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,playerListSort));
    listView.setAdapter(adapter);
    listIsSorted=true;*/
    }


}
