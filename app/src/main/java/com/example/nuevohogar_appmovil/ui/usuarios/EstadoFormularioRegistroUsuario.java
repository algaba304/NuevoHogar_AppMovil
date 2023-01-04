package com.example.nuevohogar_appmovil.ui.usuarios;


import org.jetbrains.annotations.Nullable;

public class EstadoFormularioRegistroUsuario {

    @Nullable
    private Integer errorUsuario;
    @Nullable
    private Integer errorContrasenia;
    @Nullable
    private Integer errorNombre;
    @Nullable
    private Integer errorDireccion;
    @Nullable
    private Integer errorTelefono;
    @Nullable
    private Integer errorFecha;
    @Nullable
    private Integer errorCorreo;
    private boolean datoValido;

    public EstadoFormularioRegistroUsuario(@Nullable Integer errorUsuario,
                                           @Nullable Integer errorContrasenia,
                                           @Nullable Integer errorNombre,
                                           @Nullable Integer errorDireccion,
                                           @Nullable Integer errorTelefono,
                                           @Nullable Integer errorFecha,
                                           @Nullable Integer errorCorreo) {
        this.errorUsuario = errorUsuario;
        this.errorContrasenia = errorContrasenia;
        this.errorNombre = errorNombre;
        this.errorDireccion = errorDireccion;
        this.errorTelefono = errorTelefono;
        this.errorFecha = errorFecha;
        this.errorCorreo = errorCorreo;
        this.datoValido = false;
    }

    public EstadoFormularioRegistroUsuario(boolean datoValido){
        this.errorUsuario = null;
        this.errorContrasenia = null;
        this.errorNombre = null;
        this.errorDireccion = null;
        this.errorTelefono = null;
        this.errorFecha = null;
        this.errorCorreo = null;
        this.datoValido = datoValido;
    }

    @Nullable
    public Integer getErrorUsuario() {
        return errorUsuario;
    }

    @Nullable
    public Integer getErrorContrasenia() {
        return errorContrasenia;
    }

    @Nullable
    public Integer getErrorNombre() {
        return errorNombre;
    }

    @Nullable
    public Integer getErrorDireccion() {
        return errorDireccion;
    }

    @Nullable
    public Integer getErrorTelefono() {
        return errorTelefono;
    }

    @Nullable
    public Integer getErrorFecha() {
        return errorFecha;
    }

    @Nullable
    public Integer getErrorCorreo() {
        return errorCorreo;
    }

    public boolean esDatoValido() {
        return datoValido;
    }
}
