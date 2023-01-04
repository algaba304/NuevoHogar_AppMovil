package com.example.nuevohogar_appmovil.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuevohogar_appmovil.ui.usuarios.Homepage;
import com.example.nuevohogar_appmovil.ui.usuarios.RegistroUsuario;
import com.example.nuevohogar_appmovil.viewModel.InicioSesionViewModel;

import com.example.nuevohogar_appmovil.databinding.ActivityInicioSesionBinding;

import org.jetbrains.annotations.Nullable;

public class InicioSesion extends AppCompatActivity {

    private InicioSesionViewModel inicioSesionViewModel;
    private final ActivityInicioSesionBinding enlaceVista = ActivityInicioSesionBinding.inflate(getLayoutInflater());
    private final int VALIDO = 1;
    private final int CONTRASENIA_INCORRECTA = -2;
    private final EditText etUsuario = enlaceVista.txtUsuario;
    private final EditText etContrasenia = enlaceVista.psdContrasenia;
    private final Button btnInicioSesion = enlaceVista.btnInicioSesion;
    private final TextView tvEnlaceRegistro = enlaceVista.enlaceRegistro;
    private final String KEY_USUARIO = "usuario";
    private final String KEY_CONTRASENIA = "contrasenia";
    private final String txtUsuario = etUsuario.getText().toString();
    private final String txtContrasenia = etContrasenia.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(enlaceVista.getRoot());

        inicioSesionViewModel = new ViewModelProvider(this).get(InicioSesionViewModel.class);

        tvEnlaceRegistro.setMovementMethod(LinkMovementMethod.getInstance());
        Spannable spans = (Spannable) tvEnlaceRegistro.getText();
        ClickableSpan clickSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                mostrarVentanaRegistro();
            }
        };
        spans.setSpan(clickSpan, 22, spans.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        btnInicioSesion.setOnClickListener(v -> {

            int resultado = inicioSesionViewModel.inicioSesion(txtUsuario, txtContrasenia);

            if(resultado == CONTRASENIA_INCORRECTA){

                Toast.makeText(getApplicationContext(), "La contraseña registrada es incorrecta",
                        Toast.LENGTH_SHORT).show();

            }else if(resultado == VALIDO){

                mostrarVentanaHomepage();

            }else{

                Toast.makeText(getApplicationContext(), "ERROR: El servidor no esta disponible en este momento",
                        Toast.LENGTH_SHORT).show();
            }

        });

        inicioSesionViewModel.getEstadoFormulario().observe(this, new Observer<EstadoFormularioInicioSesion>() {
            @Override
            public void onChanged(@Nullable EstadoFormularioInicioSesion estadoFormularioInicioSesion) {
                if (estadoFormularioInicioSesion == null) {
                    return;
                }
                btnInicioSesion.setEnabled(estadoFormularioInicioSesion.esDatoValido());
                if (estadoFormularioInicioSesion.getErrorUsuario() != null) {
                    etUsuario.setError(getString(estadoFormularioInicioSesion.getErrorUsuario()));
                }
                if (estadoFormularioInicioSesion.getErrorContrasenia() != null) {
                    etContrasenia.setError(getString(estadoFormularioInicioSesion.getErrorContrasenia()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                inicioSesionViewModel.actaualizarCambiosInicioSesion(etUsuario.getText().toString(),
                        etContrasenia.getText().toString());
            }
        };

        etUsuario.addTextChangedListener(afterTextChangedListener);
        etContrasenia.addTextChangedListener(afterTextChangedListener);
        etContrasenia.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    inicioSesionViewModel.inicioSesion(etUsuario.getText().toString(),
                            etContrasenia.getText().toString());
                }

                return false;
            }
        });

    }

    private void mostrarVentanaRegistro() {
        Intent intent = new Intent(this, RegistroUsuario.class);
        startActivity(intent);
    }

    private void mostrarVentanaHomepage(){
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){

        super.onSaveInstanceState(outState);
        outState.putString(KEY_USUARIO, etUsuario.getText().toString());
        outState.putString(KEY_CONTRASENIA, etContrasenia.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

        super.onRestoreInstanceState(savedInstanceState);
        etUsuario.setText(savedInstanceState.getString(KEY_USUARIO));
        etContrasenia.setText(savedInstanceState.getString(KEY_CONTRASENIA));
    }
}