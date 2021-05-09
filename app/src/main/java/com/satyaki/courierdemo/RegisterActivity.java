package com.satyaki.courierdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText editNameReg,editEmailReg,editPhnReg,editPassReg,editConPassReg;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editNameReg=findViewById(R.id.textEdit_Name_Register);
        editEmailReg=findViewById(R.id.textEdit_Email_Register);
        editPhnReg=findViewById(R.id.textEdit_Phn_Register);
        editPassReg=findViewById(R.id.textEdit_Pass_Register);
        editConPassReg=findViewById(R.id.textEdit_Con_Pass_Register);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public void onAddData(String email, String pass, String phoneNum, String name, FirebaseUser firebaseUser) {

        String ranking="Bronze",medal="V";

        User user=new User(name,email,pass,phoneNum,ranking,medal);
        db.collection("Users").document(firebaseUser.getUid()).set(user).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Error Occurred while Adding Data", Toast.LENGTH_SHORT).show();
                            Log.i("Er",task.getException().getMessage());
                        }

                    }
                });

    }


    public void onClickRegister(View view){

        if (editPassReg.getText().toString().equals(editConPassReg.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(editEmailReg.getText().toString(),editPassReg.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        onAddData(editEmailReg.getText().toString(),editPassReg.getText().toString(),editPhnReg.getText().toString()
                                ,editNameReg.getText().toString(),user);
                        Log.i("Success", "Create User");


                    } else {
                        Toast.makeText(RegisterActivity.this, "Error occurred while Registering", Toast.LENGTH_SHORT).show();
                        Log.i("Error", "Create User");
                        Log.i("er", task.getException().getMessage());
                    }
                }
            });
        }
    }


}