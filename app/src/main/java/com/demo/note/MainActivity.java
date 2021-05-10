package com.demo.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_create).setOnClickListener(v -> startActivity(new Intent(MainActivity.this,CreateActivity.class)));

        findViewById(R.id.btn_home).setOnClickListener(v -> startActivity(new Intent(MainActivity.this,AllListActivity.class)));

    }
}