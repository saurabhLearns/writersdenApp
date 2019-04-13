package com.example.writerssden;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitActivity extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progress;
    EditText submitHead;
    EditText submitContent;
    Button submit;
    FirebaseDatabase database;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    DatabaseReference myRef;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        toolbar = findViewById(R.id.submitToolbar);
        toolbar.setTitle(R.string.submit_title);

        submitHead=findViewById(R.id.submitHeading);
        submitContent=findViewById(R.id.submitContent);

        progress=findViewById(R.id.submitProgress);
        submit=findViewById(R.id.submitButton);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        database= FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("articles");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitarticle();
            }
        });
    }
    public void submitarticle(){
        String head=submitHead.getText().toString();
        String content=submitContent.getText().toString();

        if (!TextUtils.isEmpty(head) || !TextUtils.isEmpty(content)){
            String id= myRef.push().getKey();
            String user=firebaseUser.getEmail().toString();
            submitart submitar = new submitart(id, user, head, content);
            assert id != null;
            myRef.child(id).setValue(submitar);
            Toast.makeText(this, "done!", Toast.LENGTH_LONG).show();
        }

        else{
            Toast.makeText(this, "Missing parameter", Toast.LENGTH_LONG).show();

        }
    }

}
