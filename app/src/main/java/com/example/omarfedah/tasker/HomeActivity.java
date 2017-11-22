package com.example.omarfedah.tasker;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ListView listView = (ListView) findViewById(R.id.list);

        //List that tracks all the tasks (needs better name)
        ArrayList listA = new ArrayList();
        listA.add("Thing"); //Adding element to list
        ChoresListAdapter adapter = new ChoresListAdapter(this,listA);
        listView.setAdapter(adapter);

        //Adding task methods
        Button addTaskButton = (Button) findViewById(R.id.addTask);
        addTaskButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //creates dialog
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);

                //Functionalities inside dialog, cancle
                Button cancel = (Button) mView.findViewById(R.id.cancelBtn);
                Button create = (Button) mView.findViewById(R.id.createBtn);

                final EditText etTaskName = (EditText) mView.findViewById(R.id.etTaskName);
                final EditText etTaskDesc = (EditText) mView.findViewById(R.id.etTaskDesc);
                final EditText etPersonTo = (EditText) mView.findViewById(R.id.etPersonTo);

                //Displays dialog
                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                //Buttons' function inside dialog
                cancel.setOnClickListener(new View.OnClickListener(){ //cancel button
                    public void onClick(View view){
                        dialog.cancel();
                    }
                });

                create.setOnClickListener(new View.OnClickListener(){ //create function
                    public void onClick(View view){
                        if(etTaskDesc.getText().toString().isEmpty() || etTaskName.getText().toString().isEmpty()
                                || etPersonTo.getText().toString().isEmpty()){
                            Toast.makeText(HomeActivity.this, "Please fill in all the information", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(HomeActivity.this, "Not yet implemented", Toast.LENGTH_SHORT).show();

                    }
                });
            }


        });
    }



}
