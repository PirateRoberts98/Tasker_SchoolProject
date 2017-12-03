package com.example.omarfedah.tasker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ShoppingListFragment extends Fragment {

    


    ListView groceryListView;
    ListView materialListView;
    GUI backendConnection ;
    ArrayList<testObject> groceryList ;
    ArrayList<testObject> materialList ;

    public ShoppingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        groceryListView = (ListView) view.findViewById(R.id.groceryList);
        materialListView = (ListView) view.findViewById(R.id.materialList);

        backendConnection = GUI.getInstance() ;
        buildDemoList() ;

        groceryListView.setAdapter(new objectListAdapter(getActivity(),groceryList));
        materialListView.setAdapter(new objectListAdapter(getActivity(),materialList));

        Button addGrocery ;
        Button addMaterial ;

        addGrocery = (Button) view.findViewById(R.id.groceryBtn);
        addMaterial = (Button) view.findViewById(R.id.materialBtn);


        addGrocery.setOnClickListener( new View.OnClickListener(){
            public void onClick(View view){
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_shoppinglist, null);

                TextView dialogTitle = mView.findViewById(R.id.dialogTitle);
                final EditText etObjectName = (EditText) mView.findViewById(R.id.shoppingObjectText);

                dialogTitle.setText("Add Grocery");
                final Button addBtn = (Button) mView.findViewById(R.id.addShoppingObject);
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        groceryList.add(
                               backendConnection.ad(etObjectName.getText().toString(), true, false)) ;
                    }
                });
                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        addMaterial.setOnClickListener( new View.OnClickListener(){
            public void onClick(View view){
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_shoppinglist, null);

                final EditText etObjectName = (EditText) mView.findViewById(R.id.shoppingObjectText);
                TextView dialogTitle = mView.findViewById(R.id.dialogTitle);

                dialogTitle.setText("Add Material");


                final Button addBtn = (Button) mView.findViewById(R.id.addShoppingObject);
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialList.add(
                                new testObject(etObjectName.getText().toString(), false, false)) ;

                    }
                });
                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        return view;}

    private void buildDemoList(){
         groceryList = new ArrayList<>() ;
         materialList = new ArrayList<>() ;
         groceryList.add(new testObject("Sauce", false , false)) ;
         materialList.add(new testObject("Camera", true ,true )) ;
    }
}
