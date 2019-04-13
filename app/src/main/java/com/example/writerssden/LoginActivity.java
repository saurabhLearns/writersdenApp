package com.example.writerssden;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText loginname;
    EditText loginpassword;
    Button loginbutton;
    TextView loginlink;
    Toolbar toolbar;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginname = (EditText) findViewById(R.id.LoginUname);
        loginpassword = (EditText) findViewById(R.id.LoginUpassword);
        loginbutton = (Button) findViewById(R.id.LoginButton);
        loginlink = (TextView) findViewById(R.id.LoginLink);
        toolbar = findViewById(R.id.loginToolbar);
        progressBar= findViewById(R.id.loginProgress);

        toolbar.setTitle(R.string.login_title);
        mAuth = FirebaseAuth.getInstance();
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(loginname.getText().toString(), loginpassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_LONG).show();
                            Intent profileintent = new Intent(LoginActivity.this, ProfActivity.class);
                            LoginActivity.this.startActivity(profileintent);

                        }
                        else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginlinkintent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(loginlinkintent);
            }
        });


    }
   // public void onBackPressed() {
// empty so nothing happens
   // }
}
