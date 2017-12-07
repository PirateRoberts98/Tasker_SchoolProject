package com.example.omarfedah.tasker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Class to display tasks.
 */
public class ChoresListAdapter extends ArrayAdapter {

    private final Context context; //Context associated to the class
    private ArrayList<Task> listTasks; //List of tasks to display

    /**
     * Constructor to initialize the class.
     * @param context Context to be associated with the class.
     * @param list List of tasks to display.
     */
    ChoresListAdapter(Context context, ArrayList list){
        super(context, R.layout.chores_list_activity, list);

        this.listTasks = list;
        this.context = context;
    }

    /**
     * Gets the list of tasks in the class.
     * @return ArrayList Tasks loaded in the class.
     */
    public ArrayList getList(){
        return listTasks;
    }

    /**
     * Gets the specified task to display its info.
     * @param position Index of the task in the ArrayList.
     * @param convertView
     * @param parent The parent of the View.
     * @return View A view containing the information of the specified task.
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chores_list_activity, parent, false);

        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.TaskName);
        TextView choreDescriptionTextField = (TextView) rowView.findViewById(R.id.taskDescription);
       // ImageView choreImage = (ImageView) rowView.findViewById(R.id.icon);

        choreNameTextField.setText(listTasks.get(position).getTaskName());
        String dueDate =  Long.toString( listTasks.get(position).getEndDateTime());
        String formatedDueDate = dueDate.substring(0,4)+"/"+dueDate.substring(4,6)+'/'+dueDate.substring(6,8)+"   Time: "+
                dueDate.substring(8,10)+":"+dueDate.substring(10,12);
        choreDescriptionTextField.setText("Assigned to: "+ listTasks.get(position).getAssignedTo().getUserName() + "\n" + "Due Date:" + formatedDueDate  +
                "\n" + "Description:"+listTasks.get(position).getNote());

        return rowView;
    }
}
