package com.satyaki.courierdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import models.SendRequestBody;
import models.SendResponseBody;
import services.Courier;
import services.SendService;


public class MainActivity extends AppCompatActivity {

    TextInputEditText editEmailLog,editPassLog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmailLog=findViewById(R.id.textEdit_Email_Login);
        editPassLog=findViewById(R.id.textEdit_Pass_Login);
        mAuth=FirebaseAuth.getInstance();
    }

    public void onNewAccount(View view){

        Intent intentLogin=new Intent(this,RegisterActivity.class);
        startActivity(intentLogin);
    }

    public void onClickLogin(View view){

        mAuth.signInWithEmailAndPassword(editEmailLog.getText().toString(),editPassLog.getText().toString()).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "User SignIn Successful", Toast.LENGTH_SHORT).show();
                             Intent intent_Home=new Intent(MainActivity.this,WelcomeActivity.class);
                             startActivity(intent_Home);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Error occurred while Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}

