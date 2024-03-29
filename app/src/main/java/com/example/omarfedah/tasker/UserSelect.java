package com.example.omarfedah.tasker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserSelect extends AppCompatActivity {

    public GUI backendConnection ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select);
        backendConnection = GUI.getInstance() ;
        final ImageView user1 = (ImageView) findViewById(R.id.user1);
        ImageView user2 = (ImageView) findViewById(R.id.user2);
        ImageView user3 = (ImageView) findViewById(R.id.user3);
        ImageView user4 = (ImageView) findViewById(R.id.user4);

        final TextView userName1 = (TextView) findViewById(R.id.userName1);
        final TextView userName2 = (TextView) findViewById(R.id.userName2);
        final TextView userName3 = (TextView) findViewById(R.id.userName3);
        final TextView userName4 = (TextView) findViewById(R.id.userName4);

        user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendConnection.setActiveUser(new User(userName1.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });

        user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendConnection.setActiveUser(new User(userName2.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });

        user3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendConnection.setActiveUser(new User(userName3.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });

        user4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendConnection.setActiveUser(new User(userName4.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivityForResult(intent, 0);
                finish();
            }
        } );


    }

    public void SetTeamIcon(View view) {
        Intent returnIntent = new Intent();
        ImageView selectedImage = (ImageView) view;
        returnIntent.putExtra("imageID", selectedImage.getId());
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}

