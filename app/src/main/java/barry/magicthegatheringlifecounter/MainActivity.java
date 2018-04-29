package barry.magicthegatheringlifecounter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    static View theView;
    static String backgroundImage = "";
    private Button leagueTableButton;
    private Button setUpGameButton;
    private Button addPlayerButton;
    private ListView list_table;
    public Gson gsonInstance;
    public static PlayersList playersList;
    public static PlayersList playersPlaying;
    public static Players players = new Players();
    private List<Players> allPlayers;
    Context mContext;

    //important variables....
    static Boolean isGameBeingPlayed = false;   //all functions on pickPlayer screen are disabled if this is true
    static Boolean moreThanOnePlayerPicked = false; //button playGame is disabled until this is true


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView thisListView = (ListView) findViewById(R.id.list_table);

        //code for json adapted from https://www.youtube.com/watch?v=97rvAwlUnvA
        String jsonString = readFromFile();
        Log.i(TAG,"............................................"+jsonString);

        //create an instance of Gson and Players Class
        gsonInstance = new Gson();
        playersList = new PlayersList();
        playersPlaying = new PlayersList();
        // List<Players> allPlayers = playersList.getPlayers();

        //creates an object of all the information
        playersList = gsonInstance.fromJson(jsonString, playersList.getClass());
        playersPlaying = gsonInstance.fromJson(jsonString, playersList.getClass());

        // Log.i(TAG, playersList.getPlayers().get(1).getPlayerName());

        //run through each object within players
        allPlayers = playersList.getPlayers();
        int numOfPlayers = allPlayers.size();
        // allPlayers.add(numOfPlayers-1,players);
        ArrayList<String> eachPlayer = new ArrayList<String>();
        for (int i = 0; i < numOfPlayers; i++) {
            eachPlayer.add(allPlayers.get(i).getPlayerName());
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, eachPlayer);
        thisListView.setAdapter(adapter);

        leagueTableButton = (Button) findViewById(R.id.leagueTableButton);
        leagueTableButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                leagueTable();
            }
        }));

        addPlayerButton = (Button) findViewById((R.id.addPlayer));
        addPlayerButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                addPlayer();
            }
        }));

        setUpGameButton = (Button) findViewById((R.id.setUpGameButton));

        setUpGameButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                //...........................
                //https://stackoverflow.com/questions/4590856/how-to-get-selected-items-from-multi-select-list-view
                //........................... see if this code works
                int len = thisListView.getCount();
                SparseBooleanArray checked = thisListView.getCheckedItemPositions();
                for (int i = 0; i < len; i++)
                    if (checked.get(i)) {
                        String item = adapter.getItem(i);
                    /* do whatever you want with the checked item */
                    }
                //.......................
                playGame();
            }
        }));

        // reworked code from this video https://www.youtube.com/watch?v=RrAxLCIMj6s
        theView = this.getWindow().getDecorView();
        switch (backgroundImage) {
            case "red":
                theView.setBackgroundResource(R.drawable.red);
                break;
            case "white":
                theView.setBackgroundResource(R.drawable.white);
                break;
            case "black":
                theView.setBackgroundResource(R.drawable.black);
                break;
            case "blue":
                theView.setBackgroundResource(R.drawable.blue);
                break;
            case "green":
                theView.setBackgroundResource(R.drawable.green);
                break;
            default:
                theView.setBackgroundResource(R.drawable.magic_background);
                break;
        }

    }// end of OnCreate...................................



    //code borrowed from this site : https://developer.android.com/guide/topics/ui/controls/radiobutton.html
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_light:
                if (checked) {
               /* Toast.makeText(this, "RED",
                        Toast.LENGTH_LONG).show();*/

                    CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.main);
                    layout.setBackgroundResource(R.drawable.white);
                    break;
                }
            case R.id.radio_dark:
                if (checked){
               /* Toast.makeText(this, "RED",
                        Toast.LENGTH_LONG).show();*/

                    CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.main);
                    layout.setBackgroundResource(R.drawable.black);
                    break;
                }
        }
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



    public void leagueTable(){
        Intent intent = new Intent(this, LeagueTable.class);
        startActivity(intent);
    }
    public void playGame(){
        Intent intent = new Intent(this, trackLifeTotals.class);
        startActivity(intent);
    }

    public void writeJsonFile(String json) {
        //String yourFile = "player_json.txt";
        //String fileContents = "Hello world!";
        //FileOutputStream outputStream;

       // File directory = context.getFilesDir();
       // File file = new File(directory, filename);
        //File file = new File(getFilesDir(), "player_json.txt");
        try {
            File file = new File("player_json.txt");
            file.createNewFile(); // if file already exists will do nothing
            FileOutputStream fileout=openFileOutput("player_json.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(json);
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String readFromFile(){
      /* String ret = "";

        try {
          //  File yourFile = new File("player_json.txt");
          //  yourFile.createNewFile(); // if file already exists will do nothing
            InputStream FileInputStream  = openFileInput("player_json.txt");
            Toast.makeText(getBaseContext(), "inside try",
                    Toast.LENGTH_SHORT).show();
            if ( FileInputStream != null ) {
                Toast.makeText(getBaseContext(), "inside not null",
                        Toast.LENGTH_SHORT).show();
                InputStreamReader inputStreamReader = new InputStreamReader(FileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                FileInputStream.close();
                Toast.makeText(getBaseContext(), "ret"+ret,
                        Toast.LENGTH_SHORT).show();
                ret = stringBuilder.toString();
            }else{
                Toast.makeText(getBaseContext(), "inside else",
                        Toast.LENGTH_SHORT).show();
            }

        }
        catch (FileNotFoundException e) {
            Log.e("login activity", ".....................................File not found:....................... " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", ".....................................Can not read file:.................... " + e.toString());
        }

        return ret;*/


        StringBuffer sbJsonString = new StringBuffer();
        InputStream is = getResources().openRawResource(R.raw.player_jsonhardcoded);
        int character;
        try {
            while ((character = is.read())!=-1)
            {
                sbJsonString.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        String readstring="";
        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();

            //Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
       return sbJsonString.toString();

    }
    public void addPlayer() {

        players.setPlayerName("jim");
        players.setLifeTotal(20);
        players.setLost(20);
        players.setPlayed(0);
        players.setTotalScore(0);
        players.setWon(0);
        allPlayers.add(players);
        playersList.setPlayers(allPlayers);
        gsonInstance.toJson(allPlayers);
        String listThis = allPlayers.toString();

        //Toast.makeText(this, listThis,
        //        Toast.LENGTH_LONG).show();

        //String strFileJson = getStringFromFile(fileJson.toString());
        writeJsonFile(listThis);
    }


}
