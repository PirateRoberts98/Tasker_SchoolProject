package com.example.omarfedah.tasker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by omarfedah on 2017-11-11.
 */

public class ChoresListAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList listTasks;

    //YET TO IMPLEMENT LIST AND NOT ARRAY
    ChoresListAdapter(Context context, ArrayList list){
        super(context, R.layout.chores_list_activity, list);

        this.listTasks = list;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chores_list_activity, parent, false);

        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.TaskName);
        TextView choreDescriptionTextField = (TextView) rowView.findViewById(R.id.taskDescription);
        ImageView choreImage = (ImageView) rowView.findViewById(R.id.icon);

        choreNameTextField.setText(listTasks.get(position).toString());
        choreDescriptionTextField.setText(listTasks.get(position) + " is a chore!");

        return rowView;
    }


}
