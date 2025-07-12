package org.proyectosemestral;

/** Clase Validador que contiene métodos para validar correos electrónicos y números de teléfono al ser ingresados.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */

public class Validador {
    public static boolean esCorreoValido(String correo) {
        return correo != null && correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
    public static boolean esTelefonoValido(String telefono) {
        return telefono != null && telefono.matches("^(\\+?56)?9\\d{8}$");
    }
}