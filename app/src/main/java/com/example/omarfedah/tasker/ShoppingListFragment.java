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


public class ShoppingListFragment extends Fragment {

    ListView groceryListView;
    ListView materialListView;

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


        return view;
    }


}
