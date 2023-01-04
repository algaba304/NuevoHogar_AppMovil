package com.example.nuevohogar_appmovil.ui.login;

import org.jetbrains.annotations.Nullable;

public class EstadoFormularioInicioSesion {
    @Nullable
    private Integer errorUsuario;
    @Nullable
    private Integer errorContrasenia;
    private boolean datoValido;

    public EstadoFormularioInicioSesion(@Nullable Integer errorUsuario, @Nullable Integer errorContrasenia) {
        this.errorUsuario = errorUsuario;
        this.errorContrasenia = errorContrasenia;
        this.datoValido = false;
    }

    public EstadoFormularioInicioSesion(boolean esDatoValido) {
        this.errorUsuario = null;
        this.errorContrasenia = null;
        this.datoValido = esDatoValido;
    }

    @Nullable
    public Integer getErrorUsuario() {
        return errorUsuario;
    }

    @Nullable
    public Integer getErrorContrasenia() {
        return errorContrasenia;
    }

    public boolean esDatoValido() {
        return datoValido;
    }
}
