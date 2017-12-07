package com.example.omarfedah.tasker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLoginActivity extends AppCompatActivity {

    private GUI backendConnection ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        backendConnection = GUI.getInstance() ;
        Button loginBtn = findViewById(R.id.loginBtn);
        //todo set the name of the icon to sleected user
        final EditText typedPassword = (EditText) findViewById(R.id.editTaskPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (backendConnection.authenticateUser(backendConnection.getActiveUser(), typedPassword.getText().toString()) == true) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}