package com.example.omarfedah.tasker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    private static final  String TAG = "HomeActivity";
    public DatePickerDialog.OnDateSetListener dateSetListener;
    public TimePickerDialog.OnTimeSetListener timeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ListView listView = (ListView) findViewById(R.id.list);

        //com.example.omarfedah.tasker.List that tracks all the tasks (needs better name)
        //
        ArrayList listA = new ArrayList();
        listA.add("Task 1"); //Adding element to list
        listA.add("Task 2");
        listA.add("Task 3");
        listA.add("Task 4");

        ChoresListAdapter adapter = new ChoresListAdapter(this,listA);
        listView.setAdapter(adapter);


        Button addTaskButton = (Button) findViewById(R.id.addTask);
        Button switchUser = (Button) findViewById(R.id.switchUser);



        switchUser.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_user_select, null);
                Button cancel = (Button) mView.findViewById(R.id.cancelBtn);

                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

            }
        });


        //Adding task methods
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

                final TextView startDate = (TextView) mView.findViewById(R.id.etStartDate);
                final TextView endDate = (TextView) mView.findViewById(R.id.etEndDate);
                final TextView time = (TextView) mView.findViewById(R.id.etTime);




                //START DAY SETTER
                startDate.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(HomeActivity.this, R.style.Theme_AppCompat_Light_Dialog_MinWidth, dateSetListener,year,month,day);
                        dialog.show();

                        dateSetListener = new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                month++;
                                Log.d(TAG, "onDateSet: mm/dd/yy" + month + "/" + dayOfMonth + "/" + year);
                                String displayDate = month + "/" + dayOfMonth + "/" + year;
                                startDate.setText(displayDate);
                            }
                        };
                    }

                });

                //END DATE SETTER
                endDate.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(HomeActivity.this, R.style.Theme_AppCompat_Light_Dialog_MinWidth, dateSetListener,year,month,day);
                        dialog.show();

                        dateSetListener = new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                month++;
                                Log.d(TAG, "onDateSet: mm/dd/yy" + month + "/" + dayOfMonth + "/" + year);
                                String displayDate = month + "/" + dayOfMonth + "/" + year;
                                endDate.setText(displayDate);
                            }
                        };
                    }

                });


                //TIME SETTER
                time.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        Calendar cTime = Calendar.getInstance();
                        int hour = cTime.get(Calendar.HOUR_OF_DAY);
                        int minute = cTime.get(Calendar.MINUTE);
                        TimePickerDialog dialog = new TimePickerDialog(HomeActivity.this, R.style.Theme_AppCompat_Light_Dialog_MinWidth, timeSetListener, hour, minute, false);
                        dialog.show();

                        timeSetListener = new TimePickerDialog.OnTimeSetListener(){
                            public void onTimeSet(TimePicker timePicker, int hour, int minute){
                                Log.d(TAG, "onTimeSet: hh:mm" + hour + ":" +minute);
                                String displayTime = hour + ":" + minute;
                                time.setText(displayTime);
                            }
                        };
                    }

                });

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
