package com.example.omarfedah.tasker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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

//heyyo

public class HomeActivity extends AppCompatActivity {

    private static final  String TAG = "HomeActivity";
    public DatePickerDialog.OnDateSetListener dateSetListener;
    public TimePickerDialog.OnTimeSetListener timeSetListener;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    public GUI BackendConnection ;
    protected ArrayList userList ;
    protected ChoresListAdapter adapter ;
    protected ArrayList completeTaskList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // BuildDatabase() ;
        createListView() ;

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button addTaskButton = (Button) findViewById(R.id.addTask);
        Button switchUser = (Button) findViewById(R.id.switchUser);

        switchUser.setOnClickListener(new View.OnClickListener(){
            //What happpen when clicked and creation message
            public void onClick(View view){
                final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(HomeActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_user_select, null);
                Button cancel = (Button) mView.findViewById(R.id.cancelBtn);
                alertBuilder.setView(mView);
                final AlertDialog dialog = alertBuilder.create();
                dialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //SET ACTIVE USER
                        adapter.changeList(userList) ;
                        adapter.notifyDataSetChanged();
                        dialog.cancel();
                    }
                });

            }
        });


        //Adding task methods
        addTaskButton.setOnClickListener(
                new View.OnClickListener(){
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
                startDate.setOnClickListener(
                        new View.OnClickListener(){
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
                endDate.setOnClickListener(
                        new View.OnClickListener(){
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
                        }else {
                            adapter.getActiveList().add("RANDOM NEW TASK" );
                            adapter.notifyDataSetChanged();
                            Toast.makeText(HomeActivity.this, "Not yet implemented", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        });
    }




    private void BuildDatabase(){
        String createTaskTable = "CREATE IF NOT EXISTS task(name TEXT PRIMARY KEY, enddatetime " +
                "INTEGER, iscompleted INTEGER, note TEXT, objectlist TEXT, creator TEXT," +
                " assignedto TEXT)";
        String createUserTable = "CREATE IF NOT EXISTS user(name TEXT PRIMARY KEY, icon TEXT, " +
                "password TEXT)";
        String createObjectTable = "CREATE IF NOT EXISTS object(name TEXT PRIMARY KEY, " +
                "isgrocery INTEGER, isowned INTEGER)";
        SQLiteDatabase conn = GUI.connect();
        conn.execSQL(createTaskTable);
        conn.execSQL(createUserTable);
        conn.execSQL(createObjectTable);
        Toast.makeText(this, "DataBaseCreated", Toast.LENGTH_SHORT).show();
    }

    private void createListView() {
        //Intializing the List View -R
        ListView listView = (ListView) findViewById(R.id.list);
        //Setting Array for List -R
        completeTaskList = new ArrayList();
        userList = new ArrayList();
        adapter = new ChoresListAdapter(this, completeTaskList);
        listView.setAdapter(adapter);


        //Testing Methods to initialize        listA.add("Task 1");
        completeTaskList.add("Task 2");
        completeTaskList.add("Task 3");
        completeTaskList.add("Task 4");
        completeTaskList.add("Task 4");
        completeTaskList.add("Task 4");
        completeTaskList.add("Task 4");
        completeTaskList.add("Task 4");
        completeTaskList.add("Task 4");

        //End of Test
        userList.add("SKRAAAAA");
        userList.add("AND A BOOM BOOM POW");
        userList.add("RAW SAUCE");
        userList.add("KETCHUP");


    }

    private void createDialog(){

    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
