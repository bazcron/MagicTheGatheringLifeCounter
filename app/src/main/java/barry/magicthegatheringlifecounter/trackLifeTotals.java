package barry.magicthegatheringlifecounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import static barry.magicthegatheringlifecounter.MainActivity.playersList;
import static barry.magicthegatheringlifecounter.MainActivity.playersPlaying;

public class trackLifeTotals extends AppCompatActivity {
    //declaring variables
    private static final String TAG = trackLifeTotals.class.getName();
    private Button looseLife;
    private Button AddLife;
    public static int playerSelectedId;
    private int numVal;
    static EditText numValue;
    static TextView playerText;
    static String playerPicked="";
    static int newLife;
    EditText thisNum;
    static List<Players> allPlayers;
    static List<Players> leaguePlayers;

    static ListView listView;
    static View view;
    static ArrayAdapter<String> adapter;
    static ArrayList<String> eachPlayer;
    public Gson gsonInstance;
   //public static PlayersList playersPlaying;

    //*** set int numberOfPlayers by getting number of players in list................!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_life_totals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //set up button and click listener for life Lost
        looseLife = (Button) findViewById(R.id.looseLifeButton);
        looseLife.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                looseLife();
            }
        }));

        //set up button and click listener for life Gained
        AddLife = (Button) findViewById(R.id.addLifeButton);
        AddLife.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                addLife();
            }
        }));


        //numValue = (EditText) findViewById(R.id.lifeValueInput);
        playerText = (TextView) findViewById(R.id.playerSelected);
        thisNum = (EditText) findViewById(R.id.thisNum);

        //.........................
        gsonInstance = new Gson();

        //run through each object within players
        allPlayers = playersPlaying.getPlayers();
        leaguePlayers = playersList.getPlayers();

        listView = (ListView) findViewById(R.id.gameListView);

        int numOfPlayers = allPlayers.size();
        eachPlayer = new ArrayList<String>();
        for(int i=0; i<numOfPlayers; i++) {
            eachPlayer.add(allPlayers.get(i).getPlayerName()+"    LIFE: "+allPlayers.get(i).getLifeTotal());
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, eachPlayer);
        listView.setAdapter(adapter);

        //goes here when listView is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                playerSelectedId = i;

                playerPicked = allPlayers.get(playerSelectedId).getPlayerName();

                playerText.setText(playerPicked);
            }
        });
    }


    //takes life from player, and if players life is zero or less, removes from game by making unclickable
    public void looseLife(){
        //get the current value from player from textlist, then gets text from editText box and convert to an int
        int currentLife = allPlayers.get(playerSelectedId).getLifeTotal();

        //put value from number field into variable to be used in if statement
        String thisNumber = thisNum.getText().toString();
        int valueFromInput = Integer.parseInt(thisNumber);

        //checks if there is a value to add/delete and if a player has been selected
        //if there is, take value from current life
        if (valueFromInput > 0 && !playerText.getText().equals("Selected Player")) {  //check if value in number field is greater than zero and player has been picked
            newLife = currentLife - valueFromInput;
            if (newLife <= 0) {
                newLife = 0;
            }
            allPlayers.get(playerSelectedId).setLifeTotal(newLife);

            if (newLife == 0) {
                playerDied();
            }else {
                updateItemAtPosition();
            }
        }
        }//end of if statement.................

    public void playerDied(){
        //https://stackoverflow.com/questions/2558591/remove-listview-items-in-android
                AlertDialog.Builder adb=new AlertDialog.Builder(trackLifeTotals.this);
                adb.setTitle("It's The End For You!!");
                adb.setMessage(allPlayers.get(playerSelectedId).getPlayerName()+" Has Died! ");
                final int positionToRemove = playerSelectedId;
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                eachPlayer.remove(positionToRemove);
                adapter.notifyDataSetChanged();
            }});
        adb.show();
        int all = allPlayers.size();
        all = all -1;

        int league = leaguePlayers.size();
        for (int i =0; i<leaguePlayers.size()-1; i++){
            Log.i(TAG,"league size........................................................... "+league);
            if (allPlayers.get(playerSelectedId).getPlayerName() == leaguePlayers.get(i).getPlayerName()){
                int gamesPlayed = leaguePlayers.get(i).getPlayed();
                int gamesLost = leaguePlayers.get(i).getLost();
                leaguePlayers.get(i).setPlayed(gamesPlayed+1);
                leaguePlayers.get(i).setLost(gamesLost+1);
            }
        }


              if(all==1){
                  AlertDialog.Builder adb2=new AlertDialog.Builder(trackLifeTotals.this);
                  adb2.setTitle("The Winner IS");
                  adb2.setMessage(allPlayers.get(0).getPlayerName());
                    adb2.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }});
                    adb2.show();
                  for (int i =0; i<leaguePlayers.size()-1; i++){
                  if (allPlayers.get(playerSelectedId).getPlayerName() == leaguePlayers.get(i).getPlayerName()) {
                      int gamesPlayed = leaguePlayers.get(i).getPlayed();
                      int gamesWon = leaguePlayers.get(i).getWon();
                      int total = leaguePlayers.get(i).getTotalScore();
                      leaguePlayers.get(i).setPlayed(gamesPlayed + 1);
                      leaguePlayers.get(i).setWon(gamesWon + 1);
                      leaguePlayers.get(i).setTotalScore(total+3);
                  }
                  }
                  looseLife.setEnabled(false);
                  AddLife.setEnabled(false);
                }
        allPlayers.remove(playerSelectedId);
    }




    //adds life to player
    public void addLife(){
        int currentLife = allPlayers.get(playerSelectedId).getLifeTotal();

        //put value from number field into variable to be used in if statement
        String thisNumber = thisNum.getText().toString();
        int valueFromInput = Integer.parseInt(thisNumber);


        //get text from playerText to be able to see if a player has been chosen
        String playerName = playerText.getText().toString();

        if (valueFromInput > 0 && !playerName.equals("Selected Player")){
            int newValue = currentLife + valueFromInput;

            //set num in textlist to new number...!!
            allPlayers.get(playerSelectedId).setLifeTotal(newValue);
            updateItemAtPosition();
        }
        // add life from that players Lifetotal
        //refresh page*/
    }

    private void updateItemAtPosition() {
       // Toast.makeText(trackLifeTotals.this,"position:"+playerSelectedId, Toast.LENGTH_LONG).show();
        adapter.clear();
        for(int i=0; i<allPlayers.size(); i++) {
            eachPlayer.add(allPlayers.get(i).getPlayerName()+"    LIFE: "+allPlayers.get(i).getLifeTotal());
        }
        adapter.notifyDataSetChanged();
        //int visiblePosition = listView.getFirstVisiblePosition();
       // Toast.makeText(trackLifeTotals.this,"Visibleposition:"+visiblePosition, Toast.LENGTH_LONG).show();

       // View view = listView.getChildAt(playerSelectedId );

        //listView.getAdapter().getView(playerSelectedId, view, listView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_leagueTable : startActivity (new Intent(this, LeagueTable.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
