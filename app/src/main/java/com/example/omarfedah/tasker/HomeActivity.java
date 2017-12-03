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
        Intent intent = new Intent(this, UserSelect.class);
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

        //DemoFillDatabase();
        //Toast.makeText(this, "DataBaseCreated", Toast.LENGTH_SHORT).show();
    }

    public void DemoFillDatabase() {
        try {
            //add Users
            User phillip = backendConnection.addUser("Phillip", "", "password");
            User robert = backendConnection.addUser("Robert", "", "password");
            User fedah = backendConnection.addUser("Fedah", "", "password");
            User cooper = backendConnection.addUser("Cooper", "", "password");
            //add PurchasableObjects
            backendConnection.addObject("dish soap", false, true);
            backendConnection.addObject("sponge", false, true);
            backendConnection.addObject("rake", false, true);
            backendConnection.addObject("laundry detergent", false, true);
            backendConnection.addObject("fabric softener", false, true);
            backendConnection.addObject("Bounce sheets", false, true);
            backendConnection.addObject("doggy bags", false, true);
            backendConnection.addObject("potato sack", false, true);
            backendConnection.addObject("gun", false, true);
            backendConnection.addObject("toilet bowl cleaner", false, true);
            backendConnection.addObject("bleach", false, true);
            backendConnection.addObject("more bleach", false, true);
            backendConnection.addObject("hemorrhoid cream", false, true);
            backendConnection.addObject("mop", false, true);
            backendConnection.addObject("mop bucket", false, true);
            backendConnection.addObject("all purpose cleaner", false, true);
            backendConnection.addObject("a sense of adventure", false, true);
            backendConnection.addObject("yogurt", true, false);
            backendConnection.addObject("milk", true, false);
            backendConnection.addObject("eggs", true, false);
            backendConnection.addObject("bread", true, false);
            backendConnection.addObject("2number9", true, false);
            backendConnection.addObject("number9large", true, false);
            //create task ObjectLists
            ObjectList task1 = new ObjectList();
            task1.add(new PurchasableObject("dish soap"));
            task1.add(new PurchasableObject("sponge"));
            ObjectList task2 = new ObjectList();
            task2.add(new PurchasableObject("rake"));
            ObjectList task3 = new ObjectList();
            task3.add(new PurchasableObject("laundry detergent"));
            task3.add(new PurchasableObject("fabric softener"));
            task3.add(new PurchasableObject("Bounce sheets"));
            ObjectList task4 = new ObjectList();
            task4.add(new PurchasableObject("doggy bags"));
            ObjectList task5 = new ObjectList();
            task5.add(new PurchasableObject("yogurt"));
            task5.add(new PurchasableObject("milk"));
            task5.add(new PurchasableObject("eggs"));
            task5.add(new PurchasableObject("bread"));
            task5.add(new PurchasableObject("2number9"));
            task5.add(new PurchasableObject("number9large"));
            ObjectList task6 = new ObjectList();
            task6.add(new PurchasableObject("potato sack"));
            task6.add(new PurchasableObject("gun"));
            ObjectList task7 = new ObjectList();
            task7.add(new PurchasableObject("toilet bowl cleaner"));
            task7.add(new PurchasableObject("bleach"));
            task7.add(new PurchasableObject("more bleach"));
            ObjectList task8 = new ObjectList();
            task8.add(new PurchasableObject("hemorrhoid cream"));
            ObjectList task9 = new ObjectList();
            task9.add(new PurchasableObject("a sense of adventure"));
            ObjectList task10 = new ObjectList();
            task10.add(new PurchasableObject("mop"));
            task10.add(new PurchasableObject("mop bucket"));
            task10.add(new PurchasableObject("all purpose cleaner"));
            //add tasks
            long date1 = 201712011900L;
            backendConnection.addTask("Do the dishes",201712011900L, false, "If we run out of soap, add it to the shopping list.", task1, robert, phillip);
            backendConnection.addTask("Rake the front lawn", 201712031530L, false, "Dont forget to put the rake back in the garage.", task2, phillip, cooper);
            backendConnection.addTask("Do the laundry", 201712050915L, false, "Dont tumble dry Phillips brown sweater.", task3, fedah, cooper);
            backendConnection.addTask("Walk the dog", 201712010745L, false, "Pet twice every 3 minutes.", task4, fedah, fedah);
            backendConnection.addTask("Shopping", 201712010745L, false, "gatta hit up that 2 4 1 deal at macys", task5, robert, cooper);
            backendConnection.addTask("Go to the bank", 201712031730L, false, "Open a new account to put newly aquired funds into.", task6, robert, robert);
            backendConnection.addTask("Wash the bathroom", 201712051200L, false, "Please actually wash the sink this time you lazy turd.", task7, phillip, fedah);
            backendConnection.addTask("Apply the dogs hemorrhoid cream", 201712090000L, false, "He will fight back. You must resist.", task8, phillip, robert);
            backendConnection.addTask("Get ice cream", 201712120730L, false, "The most important meal of the day.", task9, phillip, phillip);
            backendConnection.addTask("Wash the floors", 201712031230L, false, "Put the dog in the basement while the floors dry.", task10, cooper, phillip);
        } catch (UniqueIDException e) {}
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
