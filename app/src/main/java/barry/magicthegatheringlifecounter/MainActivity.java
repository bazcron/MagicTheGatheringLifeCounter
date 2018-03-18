package barry.magicthegatheringlifecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private Button leagueTableButton;
    private Button setUpGameButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        leagueTableButton = (Button) findViewById(R.id.leagueTableButton);

        leagueTableButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v){

                leagueTable();
            }
        }));

        setUpGameButton = (Button) findViewById((R.id.setUpGameButton));

        setUpGameButton.setOnClickListener((new View.OnClickListener()  {
            public void onClick(View v){
                setUpGame();
            }
        }));
    }

    //code borrowed from this site : https://developer.android.com/guide/topics/ui/controls/radiobutton.html
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_light:
                if (checked)
                    //
                    break;
            case R.id.radio_dark:
                if (checked)
                    //
                    break;
        }
    }

    public void leagueTable(){
        Intent intent = new Intent(this, LeagueTable.class);
        startActivity(intent);
    }
    public void setUpGame(){
        Intent intent = new Intent(this, SetUpGame.class);
        startActivity(intent);
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
