package com.example.nuevohogar_appmovil.ui.usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nuevohogar_appmovil.databinding.ActivityHomepageBinding;

public class Homepage extends AppCompatActivity {

    private final ActivityHomepageBinding enlaceVista = ActivityHomepageBinding.inflate(getLayoutInflater());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(enlaceVista.getRoot());
    }
}