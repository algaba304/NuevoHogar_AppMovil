package com.example.nuevohogar_appmovil.repository;

import android.util.Log;
import android.widget.Toast;

import com.example.nuevohogar_appmovil.Utils.Cliente;
import com.example.nuevohogar_appmovil.modeloDTO.RolDTO;
import com.example.nuevohogar_appmovil.modeloDTO.UsuarioDTO;
import com.example.nuevohogar_appmovil.servicios.SesionService;
import com.example.nuevohogar_appmovil.servicios.UsuarioService;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository {

    private static volatile UsuarioRepository instancia;
    private final String ID_ANIMALISTA = "AN_123_R";
    private final String ID_REFUGIO = "RF_123_R";

    public UsuarioRepository() {

    }

    public static UsuarioRepository getInstance(){
        if(instancia == null){
            instancia = new UsuarioRepository();
        }
        return instancia;
    }

    public int iniciarSesion(String nombreUsuario, String contrasenia){

        final int[] resultado = {0};
        SesionService sesionService = Cliente.getInstance().getSesionService();

        sesionService.iniciarSesion(nombreUsuario, contrasenia).enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {

                if(response.code() == 404){

                    resultado[0] = -2;

                }else if(response.code() == 204){

                    resultado[0] = -1;

                }else if(response.isSuccessful()) {

                    Cliente.getInstance().setUsuarioDTO(response.body());
                    resultado[0] = 1;

                }else{
                    resultado[0] = -1;
                }
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                resultado[0] = -1;
            }
        });

        return resultado[0];
    }

    public int crearPerfil(String usuario, String contrasenia, String correo, String fecha, String nombre, String direccion,
                           String telefono, String idRol){
        final int[] resultado = {0};
        UsuarioService usuarioService = Cliente.getInstance().getUsuarioService();
        String estadoUsuario;
        RolDTO rol =  new RolDTO();
        rol.setIdRol(idRol);

        if(idRol.equals(ID_ANIMALISTA)){
            estadoUsuario = "Aceptado";
        }else{
            estadoUsuario = "En espera";
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO(UUID.randomUUID().toString(), nombre, usuario, contrasenia, estadoUsuario,
                direccion, 0, "", telefono, correo, "", fecha, rol);

        usuarioService.crearPerfil(usuarioDTO).enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {

                if(response.code() == 409){
                    resultado[0] = -2;
                }
                if(response.isSuccessful()){

                    Cliente.getInstance().setUsuarioDTO(usuarioDTO);
                    resultado[0] = 1;

                }else{

                    resultado[0] = -1;
                }
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                resultado[0] = -1;
            }
        });

        return resultado[0];
    }
}
