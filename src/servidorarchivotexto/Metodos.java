
package servidorarchivotexto;


public class Metodos {
    
    public static boolean Numerico(String cadena) {

        boolean resultado;
        try {
            
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
    
    
    
}
