package com.example.nuevohogar_appmovil.viewModel;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nuevohogar_appmovil.R;
import com.example.nuevohogar_appmovil.Utils.validaciones.ValidacionFormularioUsuario;
import com.example.nuevohogar_appmovil.repository.UsuarioRepository;
import com.example.nuevohogar_appmovil.ui.login.EstadoFormularioInicioSesion;

public class InicioSesionViewModel extends ViewModel {

    private final MutableLiveData<EstadoFormularioInicioSesion> estadoFormulario = new MutableLiveData<>();
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public LiveData<EstadoFormularioInicioSesion> getEstadoFormulario() {
        return estadoFormulario;
    }

    public int inicioSesion(String usuario, String contrasenia){
        return usuarioRepository.iniciarSesion(usuario, contrasenia);
    }

    public void actaualizarCambiosInicioSesion(String usuario, String contrasenia) {

        if(ValidacionFormularioUsuario.esCampoVacio(usuario)){

            estadoFormulario.setValue(new EstadoFormularioInicioSesion(R.string.campoVacio, null));

        }else if(ValidacionFormularioUsuario.validarLimiteUsuario(usuario)){

            estadoFormulario.setValue(new EstadoFormularioInicioSesion(R.string.limiteAlcanzado50, null));

        }else if(ValidacionFormularioUsuario.esCampoVacio(contrasenia)){

            estadoFormulario.setValue(new EstadoFormularioInicioSesion(null, R.string.campoVacio));

        }else if(ValidacionFormularioUsuario.validarLimiteContrasenia(contrasenia)){

            estadoFormulario.setValue(new EstadoFormularioInicioSesion(null, R.string.limiteContrasenia));

        }else{

            estadoFormulario.setValue(new EstadoFormularioInicioSesion(true));
        }
    }
}
