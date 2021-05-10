package com.demo.note;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {

    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        edit = findViewById(R.id.edit);

        findViewById(R.id.btn_save).setOnClickListener(v -> {

            String note = edit.getText().toString().trim();
            if (note.length()==0){
                Toast.makeText(CreateActivity.this,"Please enter your note",Toast.LENGTH_SHORT).show();
                return;
            }

            Database.getInstance(this).create(new Note(note));

            Toast.makeText(CreateActivity.this,"saved success",Toast.LENGTH_SHORT).show();
        });
    }
}