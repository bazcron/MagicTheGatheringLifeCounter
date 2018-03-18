package barry.magicthegatheringlifecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import static barry.magicthegatheringlifecounter.DummyInfo.*;



import static barry.magicthegatheringlifecounter.DummyInfo.playersName;

public class trackLifeTotals extends AppCompatActivity {
    //declaring variables
    private Button looseLife;
    private Button AddLife;
    private static long playerSelectedId;
    private int numVal;
    static EditText numValue;
    static TextView playerText;


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


        numValue = (EditText) findViewById(R.id.lifeValueInput);
         playerText = (TextView) findViewById(R.id.playerSelected);

        final ListView listOfPlayers = (ListView) findViewById(R.id.gameListView);

        final ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this,  android.R.layout.simple_list_item_1, playersName);
        listOfPlayers.setAdapter(adapter);

        //makes listView clickable
        listOfPlayers.setClickable(true);

        //goes here when listView is clicked
        listOfPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(trackLifeTotals.this,((TextView)view).getText(), Toast.LENGTH_LONG).show();
                playerSelectedId = i;

                TextView textView = (TextView) view.findViewById(R.id.gameListView);
                //String selectedFromList = (String) listOfPlayers.getItemAtPosition(i);
                //Toast.makeText(trackLifeTotals.this, "Selected"+selectedFromList, Toast.LENGTH_LONG).show();
                TextView tv1 = (TextView)findViewById(R.id.playerSelected);

              //tv1.setText(selectedFromList);
                //String text = tv1.getText().toString();
                //setContentView(R.layout.tv1);
            }

        });



    }




    //takes life from player, and if players life is zero or less, removes from game by making unclickable
    public void looseLife(){
        //get text from editText box and convert to an int
        String numText = numValue.getText().toString();
        int lifeValue =Integer.parseInt(numText);

        //get the current value from player from textlist
        String prevValueText = numValue.getText().toString();
        int currentLife =Integer.parseInt(prevValueText);

        //get text from playerText to be able to see if a player has been chosen
        String playerName = playerText.getText().toString();

        //checks if there is a value to add/delete and if a player has been selected
        //if there is, take value from current life
        if (lifeValue > 0 && !playerName.equals("Selected Player")){
            int newValue = currentLife - lifeValue;
            if (newValue <=0){
                newValue=0;
            }
            //set num in textlist to new number...!!
            TextView tv1 = (TextView)findViewById(R.id.playerSelected);
            //tv1.setText(selectedFromList);

            if(newValue ==0){
                playerDied();
            }
        }
        //get id,
        // get value from findViewById(R.id.lifeValueInput)...
        // take life from that players Lifetotal
        //if lifetotal is less than or equal to Zero.. make it zero... and make its NOT clickable, drop numberOfPlayers down by 1...
        //if numberofplayers is less than 2... show winning player pop up.. add scores and data to leagueTable
       // EditText lifeValue = (EditText)findViewById(R.id.lifeValueInput);

        //refresh page

    }

    public void playerDied(){

    }

    //adds life to player
    public void addLife(){
        //get id,
        //get text from editText box and convert to an int
        String numText = numValue.getText().toString();
        int lifeValue =Integer.parseInt(numText);

        //get the current value from player from textlist
        String prevValueText = numValue.getText().toString();
        int currentLife =Integer.parseInt(prevValueText);

        //get text from playerText to be able to see if a player has been chosen
        String playerName = playerText.getText().toString();

        if (lifeValue > 0 && !playerName.equals("Selected Player")){
            int newValue = currentLife + lifeValue;

            //set num in textlist to new number...!!
        }
        // add life from that players Lifetotal
        //refresh page*/
    }


}
