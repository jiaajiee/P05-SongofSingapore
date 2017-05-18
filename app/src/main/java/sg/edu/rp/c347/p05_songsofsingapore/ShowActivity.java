package sg.edu.rp.c347.p05_songsofsingapore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView lv;
    ShowAdaptor adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lv = (ListView)this.findViewById(R.id.lvShow);
        DBHelper dbh = new DBHelper(this);
        ArrayList<Song> songs = dbh.getAllSong();

        adapter = new ShowAdaptor(this, R.layout.row, songs);
        lv.setAdapter(adapter);

    }
}
