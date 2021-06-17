package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.to_dolist.Adapter.ToDoAdapter;
import com.example.to_dolist.Model.ToDoModel;
import com.example.to_dolist.Utils.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDialogCloseListner{
    private RecyclerView nrecycleView;
    private FloatingActionButton fab;
    private DataBaseHelper myDB;
    private List<ToDoModel> mList;
    private ToDoAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nrecycleView = findViewById(R.id.recycleview);
        fab = findViewById(R.id.fab);
        mList=new ArrayList<>();
        myDB = new DataBaseHelper(MainActivity.this);
        adapter = new ToDoAdapter(myDB , MainActivity.this);

        nrecycleView.setHasFixedSize(true);
        nrecycleView.setLayoutManager(new LinearLayoutManager(this));
        nrecycleView.setAdapter(adapter);

        mList=myDB.getallTask();
        Log.d("aaa",mList.toString());
        Collections.reverse(mList);
        adapter.setTasks(mList);


        myDB = new DataBaseHelper(MainActivity.this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);

            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(nrecycleView);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList=myDB.getallTask();
        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();

    }
}