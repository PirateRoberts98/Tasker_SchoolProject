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
import android.widget.Toast;

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
        String dueDate =  Long.toString( listTasks.get(position).getEndDateTime()) ;
        String formatedDueDate = dueDate.substring(0,4)+"/"+dueDate.substring(4,6)+'/'+dueDate.substring(6,8)+"   Time: "+
                dueDate.substring(8,10)+":"+dueDate.substring(10,12) ;
        choreDescriptionTextField.setText("Assigned to: "+ listTasks.get(position).getAssignedTo().getUserName() + "\n" + "Due Date:" + formatedDueDate  +
                "\n" + "Description:"+listTasks.get(position).getNote());
     //fixme
        /*  if( position == 1){
            Toast.makeText(getContext(),Long.toString( listTasks.get(position).getEndDateTime() ), Toast.LENGTH_SHORT).show() ;
        }
*/

      /*TODO Implement Icons in ListView

        Icon a =       Icon.createWithFilePath("E:\\School\\Courses\\SEG2105\\Final_Project\\app\\src\\main\\res\\usericonsprofessor.png");
        choreImage.setImageIcon(a) ;

        */
        return rowView;
    }





}
