package com.azhar.disabledate;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTanggal = findViewById(R.id.etTanggal);

        etTanggal.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int iYear = calendar.get(Calendar.YEAR);
            int iMonth = calendar.get(Calendar.MONTH);
            int iDay = calendar.get(Calendar.DAY_OF_MONTH);

            Calendar cDaysAgo = (Calendar) calendar.clone();
            cDaysAgo.add(Calendar.DATE, 0);
            Calendar cDaysLater = (Calendar) calendar.clone();

            //tanggal yang bisa dipilih hanya 20-23
            if (iDay == 22) {
                cDaysLater.add(Calendar.DATE, 2);
            } else if (iDay == 23) {
                cDaysLater.add(Calendar.DATE, 1);
            } else if (iDay == 24) {
                cDaysLater.add(Calendar.DATE, 0);
            } else {
                cDaysLater.add(Calendar.DATE, 3);
            }

            //untuk menampilkan dialog tanggal
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        Calendar cInput = Calendar.getInstance();
                        cInput.set(Calendar.YEAR, year);
                        cInput.set(Calendar.MONTH, monthOfYear);
                        cInput.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String strFormatDefault = "dd-MMMM-yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormatDefault,
                                Locale.getDefault());
                        etTanggal.setText(simpleDateFormat.format(cInput.getTime()));
                    }, iYear, iMonth, iDay);

            //untuk disable tanggal sebelum tanggal 20
            datePickerDialog.getDatePicker().setMinDate(cDaysAgo.getTimeInMillis());

            //untuk disable tanggal setelah tanggal 23
            datePickerDialog.getDatePicker().setMaxDate(cDaysLater.getTimeInMillis());
            datePickerDialog.show();
        });
    }

}