package com.example.acer.doctord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AppoPage extends AppCompatActivity {

    private EditText etname;
    private  EditText etdate;
    private EditText etphone;
    private Button addAppo;

    DatabaseReference dbAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appo_page);

        dbAppointments = FirebaseDatabase.getInstance().getReference("Appointments");

        etname = findViewById(R.id.name);
        etdate = findViewById(R.id.date);
        etphone = findViewById(R.id.phone);
        addAppo = findViewById(R.id.conApp);

        addAppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAppointment();
            }
        });



    }

    private void addAppointment(){
        String name = etname.getText().toString().trim();
        String date = etdate.getText().toString().trim();
        String phone = etphone.getText().toString().trim();

        if (name.isEmpty()){
            etname.setError("Name is required");
            etname.requestFocus();
            return;
        }

        if (date.isEmpty()){
            etdate.setError("Date is required");
            etdate.requestFocus();
            return;
        }

        if (phone.isEmpty()){
            etphone.setError("Phone number is required");
            etphone.requestFocus();
            return;
        }

      String id =   dbAppointments.push().getKey();
        Appointment appo = new Appointment(id,name,date,phone);

        dbAppointments.child(id).setValue(appo);

        Toast.makeText(this,"Appointment is made successfully",Toast.LENGTH_LONG).show();;
    }
}
