package com.example.omarfedah.tasker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UserSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_select);
    }

        public void SetTeamIcon(View view) {
            Intent returnIntent = new Intent();
            ImageView selectedImage = (ImageView) view;
            returnIntent.putExtra("imageID", selectedImage.getId());
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }

