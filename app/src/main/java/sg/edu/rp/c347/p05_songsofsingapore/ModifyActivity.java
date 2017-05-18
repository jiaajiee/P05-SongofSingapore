package sg.edu.rp.c347.p05_songsofsingapore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



/**
 * Created by 15017608 on 18/5/2017.
 */

public class ModifyActivity extends AppCompatActivity {

    TextView tvID;
    EditText etTitle;
    EditText etSinger;
    EditText etYear;
    Button btnUpdate, btnDelete, btnCancel;
    int id, year, stars;
    String title, singer;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        tvID = (TextView)findViewById(R.id.textViewID);
        etTitle = (EditText)findViewById(R.id.editTextTitle);
        etSinger = (EditText)findViewById(R.id.editTextSingers);
        etYear = (EditText)findViewById(R.id.editTextYear);
        btnCancel = (Button)findViewById(R.id.buttonCancel);
        btnDelete = (Button)findViewById(R.id.buttonDelete);
        btnUpdate = (Button)findViewById(R.id.buttonUpdate);
        rg = (RadioGroup) findViewById(R.id.radiogroup);

        Intent i = getIntent();
        id = i.getIntExtra("id", 0);
        title = i.getStringExtra("title");
        singer = i.getStringExtra("singer");
        year = i.getIntExtra("year", 0);
        stars = i.getIntExtra("stars", 0);

        tvID.setText(id + "");
        etTitle.setText(title);
        etSinger.setText(singer);
        etYear.setText("" + year);
        if(stars >= 5){
            rg.check(R.id.radioButton5);
        }
        else if(stars == 4){
            rg.check(R.id.radioButton4);
        }
        else if(stars == 3){
            rg.check(R.id.radioButton3);
        }
        else if(stars == 2){
            rg.check(R.id.radioButton2);
        }
        else if(stars == 1){
            rg.check(R.id.radioButton);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                String newTitle = etTitle.getText().toString();
                String newSinger = etSinger.getText().toString();
                String newYearStr = etYear.getText().toString();
                int newYear = Integer.parseInt(newYearStr);
                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);
                String newStarsStr = rb.getText().toString();
                int newStars = Integer.parseInt(newStarsStr);
                Song data = new Song(id, newTitle, newSinger, newYear, newStars);
                int update = dbh.updateSong(data);
                if (update < 1){
                    Toast.makeText(ModifyActivity.this, "Update Fail",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ModifyActivity.this, "Update Successful",
                            Toast.LENGTH_SHORT).show();
                }
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                int delete = dbh.deleteSong(id);
                if (delete < 1){
                    Toast.makeText(ModifyActivity.this, "Delete Fail",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ModifyActivity.this, "Delete Successful",
                            Toast.LENGTH_SHORT).show();
                }
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}