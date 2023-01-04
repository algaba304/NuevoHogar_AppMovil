package com.example.nuevohogar_appmovil.Utils.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;

    public static DatePickerFragment newIntance(DatePickerDialog.OnDateSetListener listener){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener){
        this.listener = listener;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int anio = calendario.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), listener, anio - 18, mes, dia);
        calendario.set(Calendar.YEAR, anio - 10);
        datePickerDialog.getDatePicker().setMaxDate(calendario.getTimeInMillis());
        calendario.set(Calendar.YEAR, anio - 50);
        datePickerDialog.getDatePicker().setMinDate(calendario.getTimeInMillis());
        return new DatePickerDialog(getActivity(), listener, dia, mes, anio);
    }
}
