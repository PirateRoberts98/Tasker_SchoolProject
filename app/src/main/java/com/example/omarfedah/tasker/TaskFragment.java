package com.example.omarfedah.tasker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Calendar;


/**

 */
public class TaskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final  String TAG = "HomeActivity";
    public DatePickerDialog.OnDateSetListener dateSetListener;
    public TimePickerDialog.OnTimeSetListener timeSetListener;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ListView listView ;
    public GUI backendConnection ;
    protected ArrayList userList ;
    protected ChoresListAdapter adapter ;
    protected ArrayList completeTaskList ;
    static int countTracker;




    //constructor
    public TaskFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_task, container, false);

        //createListView() ;
        //BuildDatabase() ;
        //backendConnection = GUI.getInstance()  ;
        Button addTaskButton = (Button) view.findViewById(R.id.addTask);
        Button switchUser = (Button) view.findViewById(R.id.switchUser);
        TextView addTaskText = (TextView) view.findViewById(R.id.textView4);

        addTaskText.setOnClickListener(new View.OnClickListener(){
            //What happpen when clicked and creation message
            public void onClick(View view){
                ChoresListAdapter a =  (ChoresListAdapter)  listView.getAdapter() ;
                a.getList().add( new testTASK("HALA MADRID", 10 ,true ,"this is a note ", null , null , null) );
                adapter.notifyDataSetChanged() ;

            }
        });



        countTracker = 1;
        switchUser.setOnClickListener(new View.OnClickListener(){
            //What happen when clicked and creation message
            public void onClick(View view){
                final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.activity_user_select, null);
                Button cancel = (Button) mView.findViewById(R.id.cancelBtn);
                alertBuilder.setView(mView);
                final AlertDialog dialog = alertBuilder.create();
                dialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //SET ACTIVE USER
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
                        //PUT ADDED VALUES HERE

                        //creates dialog
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        View mView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);

                        //Functionality inside dialog, cancel
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

                                        DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.Theme_AppCompat_Light_Dialog_MinWidth, dateSetListener,year,month,day);
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

                                        DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.Theme_AppCompat_Light_Dialog_MinWidth, dateSetListener,year,month,day);
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
                                TimePickerDialog dialog = new TimePickerDialog(getContext(), R.style.Theme_AppCompat_Light_Dialog_MinWidth, timeSetListener, hour, minute, false);
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

                        int startDateValue ;
                        int endDateValue   ;
                        String nameValue   ;
                        //USED FOR LEARNING HOW WORKS
                        etTaskName.getText().toString() ;
                        etTaskDesc.getText()  ;
                        etPersonTo.getText() ;



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
                                    Toast.makeText(getContext(), "Please fill in all the information", Toast.LENGTH_SHORT).show();
                                }else {
                                    ((ChoresListAdapter) listView.getAdapter()).getList().add(
                                            new testTASK(etTaskName.getText().toString(), 420 ,
                                                    true ,"this is a note ", null ,
                                                    null , null)) ;
                                    ((ChoresListAdapter) listView.getAdapter()).notifyDataSetChanged();
                                    Toast.makeText(getContext(), "Not yet implemented",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }


                });

        return view;}
    private void createListView() {

        //Intializing the List View -R
        listView = (ListView) getView().findViewById(R.id.list);
        //Setting Array for List -R
        completeTaskList = new ArrayList<testTASK>();
        userList = new ArrayList<testTASK>();

        Button myTask = (Button) getView().findViewById(R.id.myTask);
        myTask.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        swapUser();

                    }
                });

        adapter = new ChoresListAdapter(getContext(), completeTaskList);
        listView.setAdapter(adapter);


        //Testing Methods to initialize
        // completeTaskList.add( new Task("name",7, 7, "note", new ObjectList(), GUI.activeUser ,GUI.activeUser )) ;
       /* Commented out to test type swap
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
    */

        completeTaskList.add( new testTASK("exampleName", 15 ,true ,"this is a note ", null , null , null)) ;
        userList.add( new testTASK("SKRAAAA", 420 ,true ,"this is a note ", null , null , null)) ;
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
        Toast.makeText(getContext(), "DataBaseCreated", Toast.LENGTH_SHORT).show();
    }

    private void swapUser(){
        if(countTracker % 2 == 0) {
            adapter = new ChoresListAdapter(getContext(), userList);
            countTracker++;
        }
        else {
            adapter= new ChoresListAdapter(getContext(), completeTaskList);
            countTracker++;
        }
        listView.setAdapter(adapter);
        Toast.makeText(getContext(), "SKRAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
    }


}
