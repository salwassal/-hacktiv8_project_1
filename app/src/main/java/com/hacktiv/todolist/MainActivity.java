package com.hacktiv.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private SQLiteDatabaseHandler db;
    private ArrayList<Task> tasksList;
    private Button btnAdd;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.TaskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new SQLiteDatabaseHandler(this);
        tasksList = (ArrayList<Task>) db.getAllTask();
        taskAdapter = new TaskAdapter(tasksList, db);
        recyclerView.setAdapter(taskAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.layout_add_task, null, false);
                popupWindow = new PopupWindow(view, 1000, 500, true);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                final EditText TaskName = view.findViewById(R.id.TaskName);
                Button btnSave = view.findViewById(R.id.btnSave);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.addTask(new Task(TaskName.getText().toString()));

                        if (taskAdapter == null){
                            taskAdapter = new TaskAdapter(tasksList, db);
                            recyclerView.setAdapter(taskAdapter);
                        }
                        taskAdapter.tasksList = (ArrayList<Task>) db.getAllTask();
                        recyclerView.getAdapter().notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }
}