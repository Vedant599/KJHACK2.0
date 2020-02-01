package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseAct extends AppCompatActivity {
    String TAG="Database";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Startups");
        final EditText name,worth,emp,proj,numberofpresentinvestors,launchdate,founderprofile;
        name=findViewById(R.id.name);
        worth=findViewById(R.id.networth);
        emp=findViewById(R.id.employee);
        proj=findViewById(R.id.project);
        numberofpresentinvestors = findViewById(R.id.NumberOfPresentInvestors);
        launchdate= findViewById(R.id.LaunchDate);
        founderprofile = findViewById(R.id.founderProfile);

        final StartupData startupData=new StartupData();

        Button btn=findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startupData.setCompanyName(name.getText().toString());
                startupData.setNetWorth(worth.getText().toString());
                startupData.setNoOfEmployees(emp.getText().toString());
                startupData.setProjectType(proj.getText().toString());
                startupData.setFounderProfile(founderprofile.getText().toString());
                startupData.setLaunchdate(launchdate.getText().toString());
                startupData.setNumberOfPresentInvestors(numberofpresentinvestors.getText().toString());
                databaseReference.child(name.getText().toString()).setValue(startupData);
                name.setText("");
                worth.setText("");
                emp.setText("");
                proj.setText("");
                founderprofile.setText("");
                launchdate.setText("");
                numberofpresentinvestors.setText("");



            }
        });/*
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StartupData name=dataSnapshot.getValue(StartupData.class);
                Log.d(TAG, "onDataChange: \nname:"+name.getCompanyName()+"net worth:"+name.getNetWorth());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
    }
}
