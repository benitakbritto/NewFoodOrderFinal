package com.example.android.newfoodorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
    }

    public void goToStarters(View view)
    {
        Intent intent = new Intent(MenuList.this, Starters.class);
        startActivity(intent);
    }

    public void goToMainCourse(View view)
    {
        Intent intent = new Intent(MenuList.this, MainCourse.class);
        startActivity(intent);
    }

    public void goToDessert(View view)
    {
        Intent intent = new Intent(MenuList.this, Dessert.class);
        startActivity(intent);
    }

}
