package com.demo.note;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

public class AllListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MultiTypeAdapter mAdapter;
    private List<Note> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mItems = new ArrayList<>();

        mAdapter = new MultiTypeAdapter();
        mAdapter.register(Note.class, new NoteViewBinder(note -> UpdateActivity.enter(AllListActivity.this, note)));
        mAdapter.setItems(mItems);

        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();

        mItems.clear();
        mItems.addAll(Database.getInstance(this).getAll());
        mAdapter.notifyDataSetChanged();
    }
}