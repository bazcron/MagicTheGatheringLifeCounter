package barry.magicthegatheringlifecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    static View theView;
    static String backgroundImage="";
    private Button leagueTableButton;
    private Button setUpGameButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // reworked code from this video https://www.youtube.com/watch?v=RrAxLCIMj6s
        theView = this.getWindow().getDecorView();
        switch (backgroundImage){
            case "red": theView.setBackgroundResource(R.drawable.red);
            break;
            case "white": theView.setBackgroundResource(R.drawable.white);
                break;
            case "black": theView.setBackgroundResource(R.drawable.black);
                break;
            case "blue": theView.setBackgroundResource(R.drawable.blue);
                break;
            case "green": theView.setBackgroundResource(R.drawable.green);
                break;
            default: theView.setBackgroundResource(R.drawable.magic_background);
                break;
        }


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
