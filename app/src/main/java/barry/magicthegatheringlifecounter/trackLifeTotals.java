package barry.magicthegatheringlifecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static barry.magicthegatheringlifecounter.DummyInfo.players;

public class trackLifeTotals extends AppCompatActivity {
    private Button looseLife;
    private Button AddLife;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_life_totals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        looseLife = (Button) findViewById(R.id.looseLifeButton);
        looseLife.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                looseLife();
            }
        }));

        AddLife = (Button) findViewById(R.id.addLifeButton);
        AddLife.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                addLife();
            }
        }));

        ListView LinearLayout = (ListView) findViewById(R.id.gameListView);

        ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this,  android.R.layout.simple_list_item_1, players);
        LinearLayout.setAdapter(adapter);

    }
    public void looseLife(){
        //add code here
        //if array of players is less than 2... show winning player pop up.. add scores and data to leagueTable
    }
    public void addLife(){
        //add code here
    }


}
