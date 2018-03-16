package barry.magicthegatheringlifecounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import static barry.magicthegatheringlifecounter.DummyInfo.*;

public class LeagueTable extends AppCompatActivity {
    //public Player[] players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.listView1);

        /*players = new Player[]{
                new Player("Barry",13, 10, 3, 30),
                new Player("Eoin",9, 7, 2, 21),
                new Player("Jack",12, 7, 5, 21),
                new Player("Kate",6, 3, 3, 9),
                new Player("Kevin",2, 2, 0, 6)
        };*/
        ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this,  android.R.layout.simple_list_item_1, Integer.parseInt(players[0].getPlayerName()));
        listView.setAdapter(adapter);
    }



    /*<include layout="@layout/content_league_table"     from activity_league_table.xml
    android:layout_height="559dp"
    tools:layout_editor_absoluteY="63dp"
    android:layout_width="386dp"
            />*/

}
