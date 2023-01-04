package com.example.nuevohogar_appmovil.Utils.validaciones;

import com.example.nuevohogar_appmovil.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionFormularioUsuario {

    public static boolean esCampoVacio(String campo){
        if (campo == null) {
            return true;
        }

        return campo.trim().isEmpty();
    }

    public static boolean validarLimiteUsuario(String usuario) {
        return usuario.length() >= 50;
    }

    public static boolean validarLimiteContrasenia(String contrasenia){
        return contrasenia.length() > 16 || contrasenia.length() < 8;
    }

    public static int esContraseniaInvalido(String contrasenia){
        String noHayEspaciosBlancos = "^(?=\\\\S+$)$";
        Pattern patron = Pattern.compile(noHayEspaciosBlancos);
        Matcher esDatoValido = patron.matcher(contrasenia);
        int resultado = 0;

        if(esDatoValido.matches()){

            resultado = R.string.contraseniaEspaciosBlancosInvalido;
            return resultado;

        }

        String contieneDigitos = "^(?=.*[0-9])$";
        patron = Pattern.compile(contieneDigitos);
        esDatoValido = patron.matcher(contrasenia);

        if(esDatoValido.matches()){

            resultado = R.string.contraseniaDigitoInvalido;
            return resultado;

        }

        String contieneMinusculas = "^(?=.*[a-z])$";
        patron = Pattern.compile(contieneMinusculas);
        esDatoValido = patron.matcher(contrasenia);

        if(esDatoValido.matches()){
            resultado = R.string.contraseniaMinusculaInvalida;
            return resultado;
        }

        String contieneMayusculas = "^(?=.*[A-Z])$";
        patron = Pattern.compile(contieneMayusculas);
        esDatoValido = patron.matcher(contrasenia);

        if(esDatoValido.matches()){
            resultado = R.string.contraseniaMayusculaInvalida;
            return resultado;
        }

        String contieneSimbolos = "^(?=.*[@#$%^&+=])$";
        patron = Pattern.compile(contieneSimbolos);
        esDatoValido = patron.matcher(contrasenia);

        if(esDatoValido.matches()){
            resultado = R.string.contraseniaSimboloInvalido;
            return resultado;
        }

        return resultado;

    }

    public static boolean validarLimiteTelefono(String telefono){
        return telefono.length() >= 20;
    }

    public static int esTelefonoInvalido(String telefono){

        String formato = "^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$";
        Pattern patron = Pattern.compile(formato);
        Matcher esDatoValido = patron.matcher(telefono);

        if(esDatoValido.matches()){
            return R.string.telefonoInvalido;
        }

        return 0;
    }

    public static boolean validarLimiteCorreo(String correo){
        return correo.length() >= 100;
    }

    public static int esCorreoInvalido(String correo){

        String formato = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern patron = Pattern.compile(formato);
        Matcher esDatoValido = patron.matcher(correo);

        if(esDatoValido.matches()){

            return R.string.correoInvalido;
        }

        return 0;
    }

    public static int esFechaInvalido(String fecha){

        String formato = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern patron = Pattern.compile(formato);
        Matcher esDatoValido = patron.matcher(fecha);

        if(esDatoValido.matches()){

            return R.string.telefonoInvalido;
        }

        return 0;
    }

    public static boolean validarNumeroCaracteresFecha(String fecha){
        return fecha.length() != 10;
    }

    public static boolean validarLimiteNombre(String nombre){
        return nombre.length() >= 100;
    }

    public static boolean validarLimiteDireccion(String direccion){
        return direccion.length() >= 100;
    }
}
