package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;



public class CRMActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crm);
        String method=getIntent().getStringExtra("method");
        Fragment scrm= new CRM();
        Bundle bundle = new Bundle();
        bundle.putString("method",method);
        scrm.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.container,scrm).commit();
    }
}
