package com.example.omarfedah.tasker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ShoppingListFragment extends Fragment {

    
    Button addGrocery ;
    Button addMaterial ;

    ListView groceryListView;
    ListView materialListView;

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
        buildDemoList() ;
        groceryListView.setAdapter(new objectListAdapter(getActivity(),groceryList));
        materialListView.setAdapter(new objectListAdapter(getActivity(),materialList));
        return view;
    }

    private void buildDemoList(){
         groceryList = new ArrayList<>() ;
         materialList = new ArrayList<>() ;
         groceryList.add(new testObject("Sauce", false , false)) ;
         materialList.add(new testObject("Camera", true ,true )) ;
    }
}
