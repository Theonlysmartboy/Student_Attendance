package com.otema.attendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

public class AppBase extends AppCompatActivity {

    ArrayList<String> basicFields;
    gridAdapter adapter;
    public static ArrayList<String> divisions ;
    GridView gridView;
    public static databaseHandler handler;
    public static Activity activity;
    private Session session;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mai_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        basicFields = new ArrayList<>();
        handler = new databaseHandler(this);
        activity = this;
        session = new Session(this);
        if(!session.loggedin()){
            logggedout();
        }
        getSupportActionBar().show();
        divisions = new ArrayList();
        divisions.add("UCI 101");
        divisions.add("UCI 102");
        divisions.add("UCI 103");
        divisions.add("UCI 104");
        divisions.add("UCI 201");
        divisions.add("UCI 202");
        divisions.add("UCI 203");
        divisions.add("UCI 204");
        divisions.add("UCI 301");
        divisions.add("UCI 302");
        divisions.add("UCI 303");
        divisions.add("UCI 304");
        divisions.add("UCI 401");
        divisions.add("UCI 402");
        divisions.add("UCI 403");
        divisions.add("UCI 404");
        gridView = (GridView)findViewById(R.id.grid);
        basicFields.add("ATTENDANCE");
        basicFields.add("SCHEDULER");
        basicFields.add("NOTES");
        basicFields.add("PROFILE");
        basicFields.add("RESULTS");
        adapter = new gridAdapter(this,basicFields);
        gridView.setAdapter(adapter);
    }

    public void loadSettings(MenuItem item) {
        Intent launchIntent = new Intent(this,SettingsActivity.class);
        startActivity(launchIntent);
    }

    public void loadAbout(MenuItem item) {
        Intent launchIntent = new Intent(this,About.class);
        startActivity(launchIntent);
    }
    public void logout(MenuItem item){
        logggedout();
    }
   public void logggedout(){
      session.setLoggedin(false);
       finish();
       startActivity(new Intent(AppBase.this,MainActivity.class));
    }
}
