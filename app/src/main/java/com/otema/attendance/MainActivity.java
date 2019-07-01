package com.otema.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignUp, btnLogin;
    private EditText uname, pwd;
    private DbHelper db;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DbHelper(this);
        session = new Session(this);

        // Get the reference of the buttons and text fields

        btnLogin = (Button)findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        uname = (EditText) findViewById(R.id.username);
        pwd = (EditText) findViewById(R.id.password);
        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        if(session.loggedin()){
            startActivity(new Intent(MainActivity.this,AppBase.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_button:
                login();
                break;
            case R.id.sign_up_button:
                Intent sign_up = new Intent(getApplicationContext(),Register.class);
                startActivity(sign_up);
                finish();
                break;
            default:

        }
    }
    private void login(){
        String email = uname.getText().toString();
        String pass = pwd.getText().toString();

        if(db.getUser(email,pass)){
            session.setLoggedin(true);
            startActivity(new Intent(MainActivity.this, AppBase.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong email/password",Toast.LENGTH_SHORT).show();
        }
   }
}
