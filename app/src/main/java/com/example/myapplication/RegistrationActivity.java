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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {
    private Button create_account;
    private EditText name,phone,password,email;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        create_account=(Button)findViewById(R.id.Register);
        name=(EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);
        progressDialog= new ProgressDialog(this);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        String Name=name.getText().toString().trim();
        String Phone=phone.getText().toString().trim();
        String Password=password.getText().toString().trim();
        String Email=email.getText().toString().trim();
        if(TextUtils.isEmpty(Name))
        {
            Toast.makeText(this, "Please fill Name", Toast.LENGTH_SHORT).show();
            name.requestFocus();
        }
        else if(TextUtils.isEmpty(Phone)||Phone.length()!=10)
        {
            Toast.makeText(this, "Please give valid Phone Number", Toast.LENGTH_SHORT).show();
            phone.requestFocus();
        }else if(TextUtils.isEmpty(Password)||!(Email.contains("@")))
        {
            Toast.makeText(this, "Please give Password", Toast.LENGTH_SHORT).show();
            password.requestFocus();
        }
        else if(TextUtils.isEmpty(Email)||!(Email.contains("@")))
        {
            Toast.makeText(this, "Please give valid Email", Toast.LENGTH_SHORT).show();
            password.requestFocus();
        }
        else
        {
            progressDialog.setTitle("Create Account");
            progressDialog.setMessage("Please wait while we are checking your Credentials");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            validatePhone(Name,Phone,Password,Email);
        }
    }

    private void validatePhone(final String Name, final String Phone1, final String Password,final String Email) {
        final DatabaseReference Rootref;
        Rootref= FirebaseDatabase.getInstance().getReference();
        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.child("Users").child(Phone1).exists())
                {
                    HashMap<String,Object> userDataMap=new HashMap<String,Object>();
                    userDataMap.put("Phone",Phone1);
                    userDataMap.put("Password",Password);
                    userDataMap.put("Name",Name);
                    userDataMap.put("Email",Email);
                    Rootref.child("Users").child(Phone1).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegistrationActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                //startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, "Some error occured Try again", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                finish();

                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(RegistrationActivity.this, "This Phone number already exists", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Toast.makeText(RegistrationActivity.this, "Please Try with Another Phone number", Toast.LENGTH_SHORT).show();
                    phone.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }
}
