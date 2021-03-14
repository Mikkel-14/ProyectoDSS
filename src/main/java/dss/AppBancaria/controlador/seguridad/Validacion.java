package dss.AppBancaria.controlador.seguridad;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {
    public static boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                    int verificador = Integer.parseInt(cedula.substring(9,10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    }
                    else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {

            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {

        }
        return cedulaCorrecta;
    }

    public static boolean validadorStrings(String cadena) {
        boolean resultado=false;
        Pattern p = Pattern.compile("[a-zA-ZñÑáéíóúÁÉÍÓÚ]{5,20}");
        Matcher validar= p.matcher(cadena);
        if(validar.matches()){
            resultado=true;
        }
        return resultado;

    }

    public static boolean validadorTelefono(String cadena) {
        boolean resultado=false;
        Pattern p = Pattern.compile("[0-9]{7,10}");
        Matcher validar= p.matcher(cadena);
        if(validar.matches()){
            resultado=true;
        }
        return resultado;

    }

    public static boolean validadorSaldo(String cadena) {
        boolean resultado=false;
        Pattern p = Pattern.compile("[1-9]{1}[0-9]*[.][0-9]{2}");
        Matcher validar= p.matcher(cadena);
        if(validar.matches()){
            resultado=true;
        }
        return resultado;
    }
    public static boolean validadorCuenta(String cadena) {
        boolean resultado=false;
        Pattern p = Pattern.compile("[0-9]{6,8}");
        Matcher validar= p.matcher(cadena);
        if(validar.matches()){
            resultado=true;
        }
        return resultado;
    }


}
