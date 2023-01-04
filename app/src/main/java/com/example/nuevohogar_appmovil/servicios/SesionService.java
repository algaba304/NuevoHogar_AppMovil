package com.example.nuevohogar_appmovil.servicios;

import com.example.nuevohogar_appmovil.modeloDTO.UsuarioDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SesionService {
    @GET("api/sesion/")
    Call<UsuarioDTO> iniciarSesion(@Query("nombreUsuario") String nombreUsuario, @Query("contrasenia") String contrasenia);
}
