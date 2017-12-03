package com.example.omarfedah.tasker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
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
    // TODO: Rename parameter arguments
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final  String TAG = "HomeActivity";
    public DatePickerDialog.OnDateSetListener dateSetListener;
    public TimePickerDialog.OnTimeSetListener timeSetListener;

    private int getDate ;
    public GUI backendConnection ;
    static int countTracker;
    private View view;
    private ListView listView ;
    protected ArrayList userList ;
    protected ChoresListAdapter adapter ;
    protected ArrayList completeTaskList ;

    private ArrayList<Task> allTasks;
    private ArrayList<Task> userTasks;
    private ArrayList<Task> completedTasks;

    //constructor
    public TaskFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_task, container, false);

        createListView() ;
        backendConnection = GUI.getInstance() ;
        Button addTaskButton = (Button) view.findViewById(R.id.addTask);
        Button switchUser = (Button) view.findViewById(R.id.switchUser);

        TextView addTaskText = (TextView) view.findViewById(R.id.textView4);
        //TEST Used For Quick Additions
        addTaskText.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                ChoresListAdapter a =  (ChoresListAdapter)  listView.getAdapter() ;
                ObjectList gun = new ObjectList() ;
                gun.add(new PurchasableObject("Gun")) ;
                try {
                    a.getList().add(backendConnection.addTask("HALA MADRID", 10, true
                            , "this is a note ", gun, backendConnection.getActiveUser(),backendConnection.getActiveUser()));
                    adapter.notifyDataSetChanged();
                } catch (UniqueIDException e) {
                }
            }
        }) ;



        countTracker = 1;
        switchUser.setOnClickListener(new View.OnClickListener(){
            //What happen when clicked and creation message
            public void onClick(View view){
                Intent intent = new Intent(getContext(), UserSelect.class);
                startActivity(intent);

            }


        });

        //Adding task methods
        addTaskButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {


                        //PUT ADDED VALUES HERE
                        int year ;
                        //creates dialog
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        View mView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);

                        //Functionality inside dialog, cancel
                        Button cancel = (Button) mView.findViewById(R.id.cancelBtn);
                        Button create = (Button) mView.findViewById(R.id.createBtn);

                        final EditText etTaskName = (EditText) mView.findViewById(R.id.etTaskName);
                        final EditText etTaskDesc = (EditText) mView.findViewById(R.id.etTaskDesc);
                        final EditText etPersonTo = (EditText) mView.findViewById(R.id.etPersonTo);

                        //final TextView startDate = (TextView) mView.findViewById(R.id.etStartDate);
                        final TextView endDate = (TextView) mView.findViewById(R.id.etEndDate);
                        final TextView time = (TextView) mView.findViewById(R.id.etTime);

                        // String startDateValue = startDate.getText().toString() ;
                         String endDateValue = endDate.getText().toString() ;
                         String timeValue = time.getText().toString() ;


                        //START DAY SETTER
                        /*startDate.setOnClickListener(
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

                                });*/



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





                        //Buttons' function inside dialog
                        cancel.setOnClickListener(new View.OnClickListener(){ //cancel button
                            public void onClick(View view){
                                dialog.cancel();
                            }
                        });
                        create.setOnClickListener(new View.OnClickListener(){ //create function
                            public void onClick(View view){
                                if (verifyUser(etPersonTo.getText().toString())) {
                                    if (etTaskDesc.getText().toString().isEmpty() || etTaskName.getText().toString().isEmpty()
                                            || etPersonTo.getText().toString().isEmpty() || endDate.getText().toString().equals("Choose End Date")
                                            || time.getText().toString().equals("Choose time")) {
                                        try {
                                            ((ChoresListAdapter) listView.getAdapter()).getList().add(
                                                    backendConnection.addTask(etTaskName.getText().toString(), Integer.parseInt(endDate.getText().toString().replace("/", "")),
                                                            true, "this is a note ", new ObjectList(),
                                                            backendConnection.activeUser, backendConnection.activeUser));
                                            ((ChoresListAdapter) listView.getAdapter()).notifyDataSetChanged();

                                        }catch(Exception e){
                                            Toast.makeText(getContext(), "Please pick a unique name", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        Toast.makeText(getContext(), "Please fill in all the information", Toast.LENGTH_SHORT).show();
                                        }
                                 } else {
                                    Toast.makeText(getContext(), "Not a Correct User ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
//END OF ONCLICK

                });

        return view;}

    private void createListView() {

        //Intializing the List View -R
        listView = (ListView) view.findViewById(R.id.list);
        //Setting Array for List -R
        completeTaskList = new ArrayList<testTASK>();
        userList = new ArrayList<testTASK>();

        Button myTask = (Button) view.findViewById(R.id.myTask);
        myTask.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        swapUser();

                    }
                });
        adapter = new ChoresListAdapter(getActivity(), completeTaskList);
        listView.setAdapter(adapter);


    }


    private void swapUser(){
        if(countTracker % 2 == 0) {
            adapter = new ChoresListAdapter(getActivity(), userList);
            countTracker = 1 ;
        }
        else {
            adapter= new ChoresListAdapter(getActivity(), completeTaskList);
            countTracker = 0 ;
        }
        listView.setAdapter(adapter);
        Toast.makeText(getContext(), "SKRAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
    }

    /**
     * Initializes the required task lists.
     */
    private void createTaskLists() {
        allTasks = backendConnection.getAllTasks().getList();
        userTasks = backendConnection.getUserTasks(backendConnection.getActiveUser().getUserName()).getList();
        completedTasks = backendConnection.getCompletedTasks().getList();
    }

    private boolean verifyUser(String user ){
        UserList users = backendConnection.getAllUser() ;
        ArrayList<User> userList = users.getList() ;
        for (int i = 0 ; i< userList.size(); i++) {
            if (user.equals(userList.get(i).getUserName())) {
                return true;
            }
        }
        return true  ;

        //return false ;
    }
}
