package com.example.acer.doctord;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ACER on 12/22/2017.
 */

public class AppointmentList extends ArrayAdapter<Appointment> {

    private Activity context;
    private List<Appointment> appoList;

    public AppointmentList(Activity context,List<Appointment> appoList){
        super(context,R.layout.list_layout,appoList);
        this.context = context;
        this.appoList =appoList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView txtName = listViewItem.findViewById(R.id.tvName);
        TextView txtDate = listViewItem.findViewById(R.id.tvDate);
        TextView txtPhone = listViewItem.findViewById(R.id.tvPhone);

        Appointment appointment = appoList.get(position);

        txtName.setText("Name:- "+appointment.getName());
        txtDate.setText("Date of Appointment:- "+ appointment.getDate());
        txtPhone.setText("Patient's Phone Number:- "+appointment.getPhoneNum());

        return listViewItem;
    }
}
