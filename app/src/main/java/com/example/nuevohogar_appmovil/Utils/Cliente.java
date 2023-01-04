package com.example.nuevohogar_appmovil.Utils;

import com.example.nuevohogar_appmovil.modeloDTO.UsuarioDTO;
import com.example.nuevohogar_appmovil.servicios.SesionService;
import com.example.nuevohogar_appmovil.servicios.UsuarioService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Cliente {

    private UsuarioDTO usuarioDTO;

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(MoshiConverterFactory.create()).build();

    private SesionService sesionService;
    private UsuarioService usuarioService;

    private static final Cliente instancia = new Cliente();

    public static Cliente getInstance(){
        return instancia;
    }

    private Cliente(){

    }

    public SesionService getSesionService() {
        if (sesionService == null) {
            sesionService = retrofit.create(SesionService.class);
        }
        return sesionService;
    }

    public UsuarioService getUsuarioService(){
        if(usuarioService == null){
            usuarioService = retrofit.create(UsuarioService.class);
        }
        return usuarioService;
    }
}
