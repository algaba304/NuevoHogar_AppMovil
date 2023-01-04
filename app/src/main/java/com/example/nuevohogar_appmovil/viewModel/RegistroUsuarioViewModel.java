package com.example.nuevohogar_appmovil.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nuevohogar_appmovil.R;
import com.example.nuevohogar_appmovil.Utils.validaciones.ValidacionFormularioUsuario;
import com.example.nuevohogar_appmovil.repository.UsuarioRepository;
import com.example.nuevohogar_appmovil.ui.usuarios.EstadoFormularioRegistroUsuario;

import java.util.List;

public class RegistroUsuarioViewModel extends ViewModel {

    private final MutableLiveData<EstadoFormularioRegistroUsuario> estadoFormulario = new MutableLiveData<>();
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public LiveData<EstadoFormularioRegistroUsuario> getEstadoFormulario() {
        return estadoFormulario;
    }

    public int crearPerfil(String usuario, String contrasenia, String correo, String fecha, String nombre, String direccion,
                           String telefono, String idRol) {
        return usuarioRepository.crearPerfil(usuario, contrasenia, correo, fecha, nombre, direccion, telefono, idRol);
    }

    public void actualizarCambiosRegistroUsuario(String usuario, String contrasenia, String direccion,
                                              String nombre, String telefono, String fecha,
                                              String correo){

        if(ValidacionFormularioUsuario.esCampoVacio(usuario)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(R.string.campoVacio, null,
                    null, null, null, null, null));

        }else if(ValidacionFormularioUsuario.validarLimiteUsuario(usuario)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(R.string.limiteAlcanzado50, null,
                    null, null, null, null, null));

        }else if(ValidacionFormularioUsuario.esCampoVacio(contrasenia)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, R.string.campoVacio,
                    null, null, null, null, null));

        }else if(ValidacionFormularioUsuario.validarLimiteContrasenia(contrasenia)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, R.string.limiteContrasenia,
                    null, null, null, null, null));
        }
        else if(ValidacionFormularioUsuario.esCampoVacio(direccion)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, null, null,
                    R.string.campoVacio, null, null, null));

        }else if(ValidacionFormularioUsuario.validarLimiteDireccion(direccion)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, null, null,
                    R.string.limiteAlcanzado100, null, null, null));

        }else if(ValidacionFormularioUsuario.esCampoVacio(nombre)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, null,
                    R.string.campoVacio, null, null, null, null));

        }else if(ValidacionFormularioUsuario.validarLimiteNombre(nombre)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, null,
                    R.string.limiteAlcanzado100, null, null, null, null));

        }else if(ValidacionFormularioUsuario.esCampoVacio(telefono)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, null,
                    null, null, R.string.campoVacio, null, null));

        }else if(ValidacionFormularioUsuario.validarLimiteTelefono(telefono)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, null,
                    null, null, R.string.telefonoLimite, null, null));

        }else if(ValidacionFormularioUsuario.esCampoVacio(fecha)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, null,
                    null, null, null, R.string.campoVacio, null));

        }else if(ValidacionFormularioUsuario.validarNumeroCaracteresFecha(fecha)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, null,
                    null, null, null, R.string.fechaLimite, null));

        }else if(ValidacionFormularioUsuario.esCampoVacio(correo)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, null,
                    null, null, null, null, R.string.campoVacio));

        }else if(ValidacionFormularioUsuario.validarLimiteCorreo(correo)){

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(null, null,
                    null, null, null, null, R.string.limiteAlcanzado100));

        }else{

            estadoFormulario.setValue(new EstadoFormularioRegistroUsuario(true));
        }
    }
}
