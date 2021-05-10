package com.demo.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    private EditText edit;
    private Note mNote;
    private static final String KEY_BEAN = "KEY_BEAN";
    public static void enter(Context context,Note note){

        Intent intent = new Intent(context,UpdateActivity.class);
        intent.putExtra(KEY_BEAN,note);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edit = findViewById(R.id.edit);


        mNote = (Note) getIntent().getSerializableExtra(KEY_BEAN);
        edit.setText(mNote.getContent());

        findViewById(R.id.btn_update).setOnClickListener(v -> {

            String note = edit.getText().toString().trim();
            if (note.length()==0){
                Toast.makeText(UpdateActivity.this,"Please enter your note",Toast.LENGTH_SHORT).show();
                return;
            }

            mNote.setContent(note);

            Database.getInstance(this).update(mNote);

            Toast.makeText(UpdateActivity.this,"update success",Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btn_delete).setOnClickListener(v -> {

            String note = edit.getText().toString().trim();
            if (note.length()==0){
                Toast.makeText(UpdateActivity.this,"Please enter your note",Toast.LENGTH_SHORT).show();
                return;
            }

            Database.getInstance(this).deleteEntry(mNote);

            Toast.makeText(UpdateActivity.this,"delete success",Toast.LENGTH_SHORT).show();
        });
    }
}