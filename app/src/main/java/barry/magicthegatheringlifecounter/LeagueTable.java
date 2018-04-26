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
    List<Players> allPlayers;
    ArrayList<String> eachPlayer;
    ListView listView;
    ArrayAdapter<String> adapter;
    static List<String> playerListSort;
    static Boolean listIsSorted = false;
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
                    sortData();
                    break;
                }
            case R.id.radio_dark:
                if (checked){
                   /* Toast.makeText(this, "RED",
                            Toast.LENGTH_LONG).show();*/
                    break;
                }
        }
    }
    private void sortData()
    {
        playerListSort= eachPlayer;

        Collections.sort(playerListSort, new Comparator() {
            public int compare(Object o1, Object o2) {
                String name1 = (String) o1;
                String name2 = (String) o2;
                return name1.compareToIgnoreCase(name2);
            }
        });

        //adapter.clear();
        adapter = (new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,playerListSort));
        listView.setAdapter(adapter);
        listIsSorted=true;
    }


}
