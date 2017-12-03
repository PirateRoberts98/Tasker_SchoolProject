package com.example.omarfedah.tasker;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class ChoresListAdapter extends ArrayAdapter {

    private final Context context;
    private ArrayList<Task> listTasks;


    ChoresListAdapter(Context context, ArrayList list){
        super(context, R.layout.chores_list_activity, list);

        this.listTasks = list;
        this.context = context;
    }

    public ArrayList getList(){
        return listTasks ;
    }


    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chores_list_activity, parent, false);

        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.TaskName);
        TextView choreDescriptionTextField = (TextView) rowView.findViewById(R.id.taskDescription);
       // ImageView choreImage = (ImageView) rowView.findViewById(R.id.icon);

        choreNameTextField.setText(listTasks.get(position).getTaskName());

        choreDescriptionTextField.setText("Assigned to: " + "\n" + "Due Date:" +
                         Long.toString( listTasks.get(position).getEndDateTime() ) +
                "\n" + "Description:");


      /*TODO IMPLEMENTED LATER

        Icon a =       Icon.createWithFilePath("E:\\School\\Courses\\SEG2105\\Final_Project\\app\\src\\main\\res\\usericonsprofessor.png");
        choreImage.setImageIcon(a) ;

        */
        return rowView;
    }





}
