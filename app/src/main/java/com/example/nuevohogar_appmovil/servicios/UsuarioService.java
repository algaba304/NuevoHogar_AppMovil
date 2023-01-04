package com.example.nuevohogar_appmovil.servicios;

import com.example.nuevohogar_appmovil.modeloDTO.UsuarioDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioService {
    @POST
    Call<UsuarioDTO> crearPerfil(@Body UsuarioDTO usuario);
}
