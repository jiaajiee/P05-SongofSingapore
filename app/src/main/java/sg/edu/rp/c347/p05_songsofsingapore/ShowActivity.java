package sg.edu.rp.c347.p05_songsofsingapore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView lv;
    ShowAdaptor adapter;
    ArrayList<Song> songs;
    Button btn5star;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        btn5star = (Button)findViewById(R.id.btn5Star);
        lv = (ListView)this.findViewById(R.id.lvShow);
        final DBHelper dbh = new DBHelper(this);
        songs = dbh.getAllSong();

        adapter = new ShowAdaptor(this, R.layout.row, songs);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShowActivity.this,
                        ModifyActivity.class);
                i.putExtra("id", songs.get(position).getId());
                i.putExtra("title", songs.get(position).getTitle());
                i.putExtra("singer", songs.get(position).getSingers());
                i.putExtra("year", songs.get(position).getYear());
                i.putExtra("stars", songs.get(position).getStars());
                startActivityForResult(i, 9);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            lv.performClick();
        }

    }
}
