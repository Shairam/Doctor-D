package com.example.acer.doctord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Appointment_List extends AppCompatActivity {

    ListView listViewAppo;
    DatabaseReference dbAppointments;
    Button signout;
    FirebaseAuth mAuth;

    List<Appointment> appointmentList;


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment__list);

        mAuth = FirebaseAuth.getInstance();

        signout =findViewById(R.id.signout);
        listViewAppo = findViewById(R.id.lvAppo);
        appointmentList = new ArrayList<>();
        dbAppointments = FirebaseDatabase.getInstance().getReference("Appointments");

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Appointment_List.this, Home.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        dbAppointments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                appointmentList.clear();

                for (DataSnapshot appoSnapshot : dataSnapshot.getChildren()){
                    Appointment appo = appoSnapshot.getValue(Appointment.class);
                    appointmentList.add(appo);
                }

                AppointmentList adapter = new AppointmentList(Appointment_List.this,appointmentList);
                listViewAppo.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
