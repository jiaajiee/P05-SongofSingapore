package sg.edu.rp.c347.p05_songsofsingapore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * Created by 15017608 on 18/5/2017.
 */

public class ModifyActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etSinger;
    EditText etYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rg;
    int data, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        etTitle = (EditText)findViewById(R.id.editTextTitle);
        etSinger = (EditText)findViewById(R.id.editTextSingers);
        etYear = (EditText)findViewById(R.id.editTextYear);
        btnCancel = (Button)findViewById(R.id.buttonCancel);
        btnDelete = (Button)findViewById(R.id.buttonDelete);
        btnUpdate = (Button)findViewById(R.id.buttonUpdate);
        rg = (RadioGroup)findViewById(R.id.radiogroup);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ModifyActivity.this, ShowActivity.class);
                i.putExtra("data", id);
                //startActivity(i);
                startActivityForResult(i, 9);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteSong(data.getId());
                dbh.close();
                //test bffw
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            btnUpdate.performClick();
        }
    }


}