package com.example.portafgliomedico;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class OrderMediceFragment extends Fragment {

    TextView selectTime, selectDate;
    EditText nameMed,phoneNumber;
    Button prenotaBtn;
    int tvHour,tvMinute;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ordermed, container, false);

        nameMed = view.findViewById(R.id.nomeMedicale);
        String text = nameMed.getText().toString();


        // Initialize the view state, such as setting text on a TextView
        selectTime = view.findViewById(R.id.selezionaOra);
        selectTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                // Initialize hour and minute
                                tvHour = hourOfDay;
                                tvMinute = minute;
                                // Store hour and minute in string
                                String time = tvHour + ":" + tvMinute;
                                // Initializate 24 time format
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    // Inicialize 12 hours time format
                                    @SuppressLint("SimpleDateFormat") SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    // Set select time on via text view
                                    selectTime.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                // Set transparent backgroung
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                // Display preivous selectTime
                timePickerDialog.updateTime(tvHour,tvMinute);
                // Show Dialog
                timePickerDialog.show();
            }
        });


        selectDate = view.findViewById(R.id.selezionaData);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog.OnDateSetListener setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day+"/"+month+"/"+year;
                selectDate.setText(date);
            }
        };

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,
                        year,
                        month,
                        day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });


        prenotaBtn = view.findViewById(R.id.btnPrenotaMedicine);
        prenotaBtn.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View view) {
                // Navigate to the home page
                Fragment homeFragment = new Home1Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, homeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                // Show alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Medicina prenotata");
                builder.setMessage("La medicina è prenotata");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Close the dialog
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        phoneNumber = view.findViewById(R.id.telefonoFarmacia);
        String text1 = phoneNumber.getText().toString();

        return view;
    }

}

