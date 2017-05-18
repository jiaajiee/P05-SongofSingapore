package sg.edu.rp.c347.p05_songsofsingapore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        final ArrayList<Song> songs = dbh.getAllSong();

        adapter = new ShowAdaptor(this, R.layout.row, songs);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShowActivity.this,
                        ModifyActivity.class);
                i.putExtra("id", songs.get(position).getId());
                startActivity(i);
            }
        });
    }
}
