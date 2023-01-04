package com.example.nuevohogar_appmovil.ui.usuarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuevohogar_appmovil.R;
import com.example.nuevohogar_appmovil.Utils.Cliente;
import com.example.nuevohogar_appmovil.Utils.Fragment.DatePickerFragment;
import com.example.nuevohogar_appmovil.Utils.validaciones.ValidacionFormularioUsuario;
import com.example.nuevohogar_appmovil.databinding.ActivityRegistroUsuarioBinding;
import com.example.nuevohogar_appmovil.viewModel.RegistroUsuarioViewModel;

import java.util.Objects;

public class RegistroUsuario extends AppCompatActivity {

    private RegistroUsuarioViewModel registroUsuarioViewModel;
    private final ActivityRegistroUsuarioBinding enlaceVista = ActivityRegistroUsuarioBinding.inflate(getLayoutInflater());
    private final int DATOS_REPETIDOS = -2;
    private final String KEY_USUARIO = "usuario";
    private final String KEY_CONTRASENIA = "contrasenia";
    private final String KEY_TELEFONO = "telefono";
    private final String KEY_NOMBRE = "nombre";
    private final String KEY_DIRECCION = "direccion";
    private final String KEY_FECHA = "fecha";
    private final String KEY_CORREO = "correo";
    private final EditText etUsuario = enlaceVista.txtUsuario;
    private final EditText etContrasenia = enlaceVista.psdContrasenia;
    private final EditText etFecha = enlaceVista.dpFecha;
    private final EditText etNombre = enlaceVista.txtNombre;
    private final EditText etTelefono = enlaceVista.txtTelefono;
    private final EditText etDireccion = enlaceVista.txtDireccion;
    private final EditText etCorreo = enlaceVista.txtCorreo;
    private final RadioButton rdAnimalista = enlaceVista.rdAnimalista;
    private final RadioButton rdRefugio = enlaceVista.rdRefugio;
    private final Button btnRegistrar = enlaceVista.btnRegistrar;
    private final Button btnCancelar = enlaceVista.btnCancelar;
    private final String ID_REFUGIO = "RF_123_R";
    private final String ID_ANIMALISTA = "AN_123_R";
    private String rolSeleccionado;
    private final String txtUsuario = etUsuario.getText().toString();
    private final String txtContrasenia = etContrasenia.getText().toString();
    private final String txtCorreo = etCorreo.getText().toString();
    private final String txtTelefono = etTelefono.getText().toString();
    private final String txtNombre = etNombre.getText().toString();
    private final String txtFecha = etFecha.getText().toString();
    private final String txtDireccion = etDireccion.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(enlaceVista.getRoot());
        etFecha.setOnClickListener(view -> mostrarDatePickerDialog(etFecha));
        registroUsuarioViewModel = new ViewModelProvider(this).get(RegistroUsuarioViewModel.class);

        registroUsuarioViewModel.getEstadoFormulario().observe(this, new Observer<EstadoFormularioRegistroUsuario>() {
            @Override
            public void onChanged(EstadoFormularioRegistroUsuario estadoFormularioRegistroUsuario) {

                if (estadoFormularioRegistroUsuario == null) {
                    return;
                }

                btnRegistrar.setEnabled(estadoFormularioRegistroUsuario.esDatoValido());

                if (estadoFormularioRegistroUsuario.getErrorUsuario() != null) {
                    etUsuario.setError(getString(estadoFormularioRegistroUsuario.getErrorUsuario()));
                }

                if (estadoFormularioRegistroUsuario.getErrorContrasenia() != null) {
                    etContrasenia.setError(getString(estadoFormularioRegistroUsuario.getErrorContrasenia()));
                }

                if(estadoFormularioRegistroUsuario.getErrorCorreo() != null){
                    etCorreo.setError(getString(estadoFormularioRegistroUsuario.getErrorCorreo()));
                }

                if(estadoFormularioRegistroUsuario.getErrorDireccion() != null){
                    etDireccion.setError(getString(estadoFormularioRegistroUsuario.getErrorDireccion()));
                }

                if(estadoFormularioRegistroUsuario.getErrorFecha() != null){
                    etFecha.setError(getString(estadoFormularioRegistroUsuario.getErrorFecha()));
                }

                if(estadoFormularioRegistroUsuario.getErrorNombre() != null){
                    etNombre.setError(getString(estadoFormularioRegistroUsuario.getErrorNombre()));
                }

                if(estadoFormularioRegistroUsuario.getErrorTelefono() != null){
                    etTelefono.setError(getString(estadoFormularioRegistroUsuario.getErrorTelefono()));
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
                registroUsuarioViewModel.actualizarCambiosRegistroUsuario(txtUsuario, txtContrasenia, txtDireccion, txtNombre,
                        txtTelefono, txtFecha, txtCorreo);
            }
        };

        etUsuario.addTextChangedListener(afterTextChangedListener);
        etCorreo.addTextChangedListener(afterTextChangedListener);
        etDireccion.addTextChangedListener(afterTextChangedListener);
        etFecha.addTextChangedListener(afterTextChangedListener);
        etNombre.addTextChangedListener(afterTextChangedListener);
        etTelefono.addTextChangedListener(afterTextChangedListener);
        etContrasenia.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){

                if(actionId == EditorInfo.IME_ACTION_DONE){
                    registroUsuarioViewModel.actualizarCambiosRegistroUsuario(txtUsuario, txtContrasenia, txtDireccion,
                            txtNombre, txtTelefono, txtFecha, txtCorreo);
                }

                return false;
            }
        });

        rdAnimalista.setOnClickListener(view -> {
            boolean checked = rdAnimalista.isChecked();

            if(checked){
                rolSeleccionado = ID_ANIMALISTA;
            }

        });

        rdRefugio.setOnClickListener(view -> {
            boolean checked = rdRefugio.isChecked();

            if(checked){
                rolSeleccionado = ID_REFUGIO;
            }
        });

        btnRegistrar.setOnClickListener(view -> {

            boolean esValido = true;

            if(!rolSeleccionado.equals(ID_ANIMALISTA) && !rolSeleccionado.equals(ID_REFUGIO)) {
                esValido = false;
                Toast.makeText(getApplicationContext(), "Rol no seleccionado", Toast.LENGTH_SHORT).show();
            }

            int resultado = ValidacionFormularioUsuario.esCorreoInvalido(txtCorreo);

            if(resultado > 0){
                esValido = false;
                etCorreo.setError(getString(resultado));
            }

            resultado = ValidacionFormularioUsuario.esContraseniaInvalido(txtContrasenia);

            if(resultado > 0){
                esValido = false;
                etContrasenia.setError(getString(resultado));
            }

            resultado = ValidacionFormularioUsuario.esFechaInvalido(etFecha.getText().toString());

            if(resultado > 0){
                esValido = false;
                etFecha.setError(getString(resultado));
            }

            resultado = ValidacionFormularioUsuario.esTelefonoInvalido(txtTelefono);

            if(resultado > 0){
                esValido = false;
                etTelefono.setError(getString(resultado));
            }

            if(esValido){

                resultado = registroUsuarioViewModel.crearPerfil(txtUsuario, txtContrasenia, txtCorreo, txtFecha, txtNombre,
                        txtDireccion, txtTelefono, rolSeleccionado);

                if(resultado > 0){

                    if(Cliente.getInstance().getUsuarioDTO().getIdUsuario().equals(ID_REFUGIO)){

                        finish();

                    }else if(Cliente.getInstance().getUsuarioDTO().getIdUsuario().equals(ID_ANIMALISTA)){

                        mostrarHomepage();

                    }else{

                        Toast.makeText(getApplicationContext(), "ERROR: No es posible guardar este usuario",
                                Toast.LENGTH_SHORT).show();
                    }

                }else if(resultado == DATOS_REPETIDOS){

                    Toast.makeText(getApplicationContext(), "El usuario y/o contraseña esta repetido", Toast.LENGTH_SHORT)
                            .show();

                }else{

                    Toast.makeText(getApplicationContext(), "ERROR: No es posible guardar el usuario", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });

        btnCancelar.setOnClickListener(view -> {

            finish();

        });

    }

    private void mostrarDatePickerDialog(EditText etFecha){
        DatePickerFragment newFragment = DatePickerFragment.newIntance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                final String fechaSeleccionada = dia + "/" + (mes + 1) + "/" + anio;
                etFecha.setText(fechaSeleccionada);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void mostrarHomepage(){
        Intent intent = new Intent(this, Homepage.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){

        super.onSaveInstanceState(outState);
        outState.putString(KEY_USUARIO, txtUsuario);
        outState.putString(KEY_CONTRASENIA, txtContrasenia);
        outState.putString(KEY_CORREO, txtCorreo);
        outState.putString(KEY_DIRECCION, txtDireccion);
        outState.putString(KEY_FECHA, txtFecha);
        outState.putString(KEY_NOMBRE, txtNombre);
        outState.putString(KEY_TELEFONO, txtTelefono);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

        super.onRestoreInstanceState(savedInstanceState);
        etUsuario.setText(savedInstanceState.getString(KEY_USUARIO));
        etContrasenia.setText(savedInstanceState.getString(KEY_CONTRASENIA));
        etCorreo.setText(savedInstanceState.getString(KEY_CORREO));
        etDireccion.setText(savedInstanceState.getString(KEY_DIRECCION));
        etFecha.setText(savedInstanceState.getString(KEY_FECHA));
        etNombre.setText(savedInstanceState.getString(KEY_NOMBRE));
        etTelefono.setText(savedInstanceState.getString(KEY_TELEFONO));
    }
}