package com.example.omarfedah.tasker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

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
    //LIST OF USER
    protected ArrayList userList ;
    protected ChoresListAdapter adapter ;
    protected ArrayList completeTaskList ;

    private ArrayList<Task> allTasks;
    private ArrayList<Task> userTasks;
    private ArrayList<Task> completedTasks;


    private boolean MADRID ; //TODO

    //constructor
    public TaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_task, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        backendConnection = GUI.getInstance() ;
        createTaskLists();
        //createListView() ;

        //Intializing the List View -R
        MADRID = true ;
        //Setting Array for List -R//completeTaskList
      //  ArrayList<Task> aList = new TaskList().getList() ;
        adapter = new ChoresListAdapter(getContext(),  backendConnection.getAllTasks().getList());
        listView.setAdapter(adapter);

        Button addTaskButton = (Button) view.findViewById(R.id.addTask);
        Button switchUser = (Button) view.findViewById(R.id.switchUser);

        TextView addTaskText = (TextView) view.findViewById(R.id.textView4);
        //TEST Used For Quick Additions
        Button myTask = (Button) view.findViewById(R.id.myTask);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Button testButton = (Button) view.findViewById(R.id.adapterButton);
                testButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        View mView = getLayoutInflater().inflate(R.layout.dialog_edit_task, null);

                        Button editButton = (Button) mView.findViewById(R.id.editTaskButton);
                        Button deleteButton = (Button) mView.findViewById(R.id.deleteButton);
                        Button cancleButton = (Button) mView.findViewById(R.id.cancelBtn);

                        final EditText etTaskName = (EditText) mView.findViewById(R.id.etTaskName);
                        final EditText etTaskDesc = (EditText) mView.findViewById(R.id.etTaskDesc);
                        final EditText etPersonTo = (EditText) mView.findViewById(R.id.etPersonTo);

                        //final TextView startDate = (TextView) mView.findViewById(R.id.etStartDate);
                        final TextView endDate = (TextView) mView.findViewById(R.id.etEndDate);
                        final TextView time = (TextView) mView.findViewById(R.id.etTime);
                    }
                });
            }
        });

        myTask.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        swapUserList();

                    }
                });

        addTaskText.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (MADRID) {
                    adapter = (ChoresListAdapter) listView.getAdapter();
                    ObjectList soccer = new ObjectList();
                    soccer.add(new PurchasableObject("Soccer Ball"));
                    try {
                        adapter.getList().add(backendConnection.addTask("HALA MADRID", 201712031200L, true
                                , "this is a note ", soccer , backendConnection.getActiveUser(), backendConnection.getActiveUser()));
                        adapter.notifyDataSetChanged();
                    } catch (UniqueIDException e) {
                    }
                }
                MADRID = false ;
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
                                                String formattedMonth = Integer.toString(month);
                                                if(formattedMonth.length() == 1) {formattedMonth = "0" + formattedMonth;}
                                                String formattedDay = Integer.toString(dayOfMonth);
                                                if(formattedDay.length() == 1) {formattedDay = "0" + formattedDay;}
                                                Log.d(TAG, "onDateSet: mm/dd/yy" + formattedMonth + "/" + formattedDay + "/" + year);
                                                String displayDate = year + "/" + formattedMonth + "/" + formattedDay;
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
                                        String formattedHour = Integer.toString(hour);
                                        if(formattedHour.length() == 1) {formattedHour = "0" + formattedHour;}
                                        String formattedMinute = Integer.toString(minute);
                                        if(formattedMinute.length() == 1) {formattedMinute = "0" + formattedMinute;}
                                        Log.d(TAG, "onTimeSet: hh:mm" + formattedHour + ":" + formattedMinute);
                                        String displayTime = formattedHour + ":" + formattedMinute;
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
                                    if (!etTaskDesc.getText().toString().isEmpty() || !etTaskName.getText().toString().isEmpty()
                                            || !etPersonTo.getText().toString().isEmpty() || !endDate.getText().toString().equals("Choose End Date")
                                            || !time.getText().toString().equals("Choose time")) {
                                        try {

                                            ((ChoresListAdapter) listView.getAdapter()).getList().add(
                                                    backendConnection.addTask(etTaskName.getText().toString(), Long.parseLong(endDate.getText().toString().replace("/", "") +
                                                                    time.getText().toString().replace(":","")),
                                                            true, etTaskDesc.getText().toString(), new ObjectList(),
                                                            backendConnection.activeUser, new User(etPersonTo.getText().toString())));
                                            adapter.notifyDataSetChanged();
                                            dialog.cancel();

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

        adapter = new ChoresListAdapter(getActivity(), completeTaskList);
        listView.setAdapter(adapter);
    }

    private void swapUserList(){
        if(countTracker % 2 == 0) {
            adapter = new ChoresListAdapter(getActivity(),backendConnection.getUserTasks(backendConnection.getActiveUser().getUserName()).getList());
            countTracker = 1 ;
        } else {
            adapter= new ChoresListAdapter(getActivity(),  backendConnection.getAllTasks().getList());
            countTracker = 0 ;
        }

        listView.setAdapter(adapter);
        //Toast.makeText(getContext(), "SKRAAAAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
    }

    /**
     * Initializes the required task lists.
     */
    private void createTaskLists() {
        allTasks = backendConnection.getAllTasks().getList();
        userTasks = backendConnection.getUserTasks(backendConnection.getActiveUser().getUserName()).getList();
        completedTasks = backendConnection.getCompletedTasks().getList();

        if (completedTasks == null){
            Toast.makeText(getContext(),"BOI",Toast.LENGTH_SHORT).show() ;
        }

        if (userTasks == null){
            Toast.makeText(getContext(),"YOU'RE FACE",Toast.LENGTH_SHORT).show() ;
        }

        if (allTasks == null){
            Toast.makeText(getContext(),"YOU ARE FACE ",Toast.LENGTH_SHORT).show() ;
        }
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
    }
}
