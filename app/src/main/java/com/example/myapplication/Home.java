package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Prevalent.Prevalent;

import io.paperdb.Paper;

public class Home extends AppCompatActivity {
private Button Login,Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Login = (Button) findViewById(R.id.login);
        Register = (Button) findViewById(R.id.Home_Register);
        Paper.init(this);
        String UserPhoneKey=Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey=Paper.book().read(Prevalent.UserPasswordKey);
        String UserParentdbNameKey=Paper.book().read(Prevalent.UserParentdbNameKey);
        if(!(TextUtils.isEmpty(UserPhoneKey)&&TextUtils.isEmpty(UserPasswordKey))&&!TextUtils.isEmpty(UserParentdbNameKey))
        {
            startActivity(new Intent(Home.this,Main2Activity.class));
            finish();
        }
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,RegistrationActivity.class));
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,loginActivity.class));
            }
        });
    }
}
