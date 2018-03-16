package barry.magicthegatheringlifecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SetUpGame extends AppCompatActivity {
    private Button trackLifeTotalsButton;
    private Player[] players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        trackLifeTotalsButton = (Button) findViewById(R.id.trackLifeTotalsButton);

        trackLifeTotalsButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v){
                trackLifeTotals();
            }
        }));


    }

    public void trackLifeTotals(){
        Intent intent = new Intent(this, trackLifeTotals.class);
        startActivity(intent);
    }

}
