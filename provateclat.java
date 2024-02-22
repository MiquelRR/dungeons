import java.io.Console;

public class provateclat {
    public static void main(String[] args) {
        Console console = System.console();

        if (console == null) {
            System.out.println("No se puede obtener la consola. Asegúrate de estar ejecutando el programa en un entorno de consola.");
            System.exit(1);
        }

        // Lee la entrada sin mostrarla en la pantalla
        char[] passwordArray = console.readPassword("Ingresa tu contraseña: ");

        // Convierte el array de caracteres a String si es necesario
        String password = new String(passwordArray);

        // Puedes imprimir la contraseña para verificar que se capturó correctamente (no se recomienda en un entorno de producción)
        System.out.println("Contraseña ingresada: " + password);
    }
}