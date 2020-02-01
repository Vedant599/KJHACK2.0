package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Model.Users;
import com.example.myapplication.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class loginActivity extends AppCompatActivity {

    private Button login;
    private EditText email, password,phone;
    private ProgressDialog progressDialog;
    private String ParentdbName="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        Paper.init(this);
        progressDialog = new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessToAccount();
            }
        });

    }

    private void accessToAccount() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String Phone = phone.getText().toString().trim();
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Please give Phone Number", Toast.LENGTH_SHORT).show();
            email.requestFocus();
        } else if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please give Password", Toast.LENGTH_SHORT).show();
            phone.requestFocus();
        }else if (TextUtils.isEmpty(Phone)) {
            Toast.makeText(this, "Please give Password", Toast.LENGTH_SHORT).show();
            password.requestFocus();
        } else {
            progressDialog.setTitle("Login Account");
            progressDialog.setMessage("Please wait while we are checking your Credentials");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            LoginAccount(Email,Phone,Password);
        }
    }

    private void LoginAccount(final String Email,final String phone1, final String password1) {

        final DatabaseReference Rootref;
        Rootref= FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").child(phone1).exists())
                {
                    Users userdata = dataSnapshot.child(ParentdbName).child(phone1).getValue(Users.class);
                    if(userdata.getEmail().equals(Email)) {
                        if(userdata.getPhone().equals(phone1))
                        if (userdata.getPassword().equals(password1)) {

                            if(ParentdbName.equals("Users"))
                            {
                                Toast.makeText(loginActivity.this, "Successfull User Login", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                 {
                                    Paper.book().write(Prevalent.UserNameKey,userdata.getName());
                                    Paper.book().write(Prevalent.UserPhoneKey,phone1);
                                    Paper.book().write(Prevalent.UserPasswordKey,password1);
                                    Paper.book().write(Prevalent.UserParentdbNameKey,ParentdbName);
                                }
                                startActivity(new Intent(loginActivity.this, MainActivity.class));
                                Toast.makeText(loginActivity.this, "Hi " + userdata.getName(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        else
                        {
                            Toast.makeText(loginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                }
                else
                {
                    Toast.makeText(loginActivity.this, "Please Register First", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    startActivity(new Intent(loginActivity.this,RegistrationActivity.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
