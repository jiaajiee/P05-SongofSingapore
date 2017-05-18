package sg.edu.rp.c347.p05_songsofsingapore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShow;
    EditText etTitle, etSinger, etYear;
    RadioGroup rg;
    ArrayList<String> al;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnShow = (Button) findViewById(R.id.btnShow);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etSinger = (EditText) findViewById(R.id.etSinger);
        etYear = (EditText) findViewById(R.id.etYear);

        al = new ArrayList<>();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);
                String strAmountStars = rb.getText() + "";
                int amountStars = Integer.parseInt(strAmountStars);


                DBHelper dbh = new DBHelper(MainActivity.this);
                long row_affected = dbh.insertSong(year, title, singer, amountStars);
                dbh.close();

                if (row_affected != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this,
                        ShowActivity.class);
                // Start the new activity
                startActivity(i);
            }
        });


    }
}
