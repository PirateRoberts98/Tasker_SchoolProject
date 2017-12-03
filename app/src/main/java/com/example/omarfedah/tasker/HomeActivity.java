package com.example.omarfedah.tasker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;


public class HomeActivity extends AppCompatActivity {

    private static final  String TAG = "HomeActivity";


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    public static GUI backendConnection ;

    static Context context;
    //protected ArrayList completeTaskList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = getApplicationContext();
        Intent intent = new Intent(this, UserLoginActivity.class);
        startActivity(intent);


        backendConnection = GUI.getInstance()  ;
        BuildDatabase() ;


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView drawer = (NavigationView) findViewById(R.id.navigationView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawerContent(drawer);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


    private void BuildDatabase(){

        SQLiteDatabase conn = backendConnection.connect();

        //drops any existing table, for testing
        String clearTaskTable = "DROP TABLE task";
        String clearUserTable = "DROP TABLE user";
        String clearObjectTable = "DROP TABLE object";
        //comment these out to keep values stored across simulations
        conn.execSQL(clearTaskTable);
        conn.execSQL(clearUserTable);
        conn.execSQL(clearObjectTable);

        String createTaskTable = "CREATE TABLE IF NOT EXISTS task(name TEXT PRIMARY KEY, enddatetime " +
                "INTEGER, iscompleted INTEGER, note TEXT, objectlist TEXT, creator TEXT," +
                " assignedto TEXT)";
        String createUserTable = "CREATE TABLE IF NOT EXISTS user(name TEXT PRIMARY KEY, icon TEXT, " +
                "password TEXT)";
        String createObjectTable = "CREATE TABLE IF NOT EXISTS object(name TEXT PRIMARY KEY, " +
                "isgrocery INTEGER, isowned INTEGER)";
        conn.execSQL(createTaskTable);
        conn.execSQL(createUserTable);
        conn.execSQL(createObjectTable);



        conn.close();
        //Toast.makeText(this, "DataBaseCreated", Toast.LENGTH_SHORT).show();
    }


    public void selectItemDrawer(MenuItem menuItem){
        Fragment fragment = null;
        Class fragmentClass;

        switch (menuItem.getItemId()){
            case R.id.taskListsMenu:
                fragmentClass = TaskFragment.class;
                break;
            case R.id.shoppingListMenu:
                fragmentClass = ShoppingListFragment.class;
                break;

            default:
                fragmentClass = TaskFragment.class;
        }
        try{
            fragment = (Fragment) fragmentClass.newInstance();
        } catch(Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentSwitch, fragment).commit();
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();


    }


    private void setDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;

            }
        });
    }


    public static Context getAppContext() {
        return context;
    }

}
