package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
private Button Pie,Bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Pie=findViewById(R.id.ButtonPie);
        Bar=findViewById(R.id.ButtonBar);
        Pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Entered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main2Activity.this, CRMActivity.class);
                intent.putExtra("method","bars");
                startActivity(intent);
            }
        });
        Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, CRMActivity.class);
                intent.putExtra("method","pie");
                startActivity(intent);
            }
        });
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,DatabaseAct.class));
            }
        });
    }
}
