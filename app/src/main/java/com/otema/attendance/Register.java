package com.otema.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private Button btnsignup;
    private EditText uname,pwd,rpwd;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DbHelper(this);
        btnsignup = (Button) findViewById(R.id.buttonCreateAccount);
        uname = (EditText) findViewById(R.id.editTextUserName);
        pwd = (EditText) findViewById(R.id.editTextPassword);
        rpwd = (EditText) findViewById(R.id.editTextConfirmPassword);
        btnsignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
switch (view.getId()){
    case R.id.buttonCreateAccount:
        register();
        break;
    default:
}
    }
    private void register(){
        String email = uname.getText().toString();
        String pass = pwd.getText().toString();
        String cpwd = rpwd.getText().toString();
        if(email.isEmpty() && pass.isEmpty()){
            displayToast("Username/password field empty");
        }else {
            if (!cpwd.equals(pass)) {
                displayToast("Passwords don't match");
                finish();
            } else {
                db.addUser(email, pass);
                displayToast("User registered");
                finish();
            }
        }
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Intent login = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(login);
        finish();
    }
}
