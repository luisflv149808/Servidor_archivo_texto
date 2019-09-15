package servidorarchivotexto;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ServidorArchivoTexto {

    //Validar solo lectura de archivos
    public static void main(String[] args) {

        ServerSocket socketServidor = null;
        Socket socket = null;
        String entrada = "";
        Scanner scanner = new Scanner(System.in);

        BufferedReader lector = null;
        String salida;
        PrintWriter escritor = null;

        if (args.length == 1) {
            if (Metodos.Numerico(args[0])) {

                while (true) {

                    try {
                        socketServidor = new ServerSocket(Integer.parseInt(args[0]));
                    } catch (IOException e) {
                        System.out.println("Error al crear el socket " + e);
                        System.exit(1);
                    }

                    try {
                        socket = socketServidor.accept();
                    } catch (IOException e) {
                        System.out.println("Error con el servidor " + e);
                        System.exit(2);
                    }

                    try {
                        lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException e) {
                        System.out.println("Error al intentar leer " + e);
                        System.exit(3);
                    }

                    try {
                        escritor = new PrintWriter(socket.getOutputStream(), true);
                    } catch (IOException e) {
                        System.out.println("Error al crear el escritor " + e);
                        System.exit(4);
                    }

                    try {
                        entrada = lector.readLine();
                    } catch (IOException e) {
                        System.out.println("Error al leer una linea " + e);
                    }

                    BufferedReader lectorarchivo;
                    try {
                        File file = new File(entrada);
                        if (file.canRead()) {
                            escritor.println(file.getName());
                            lectorarchivo = new BufferedReader(new FileReader(file));
                            String line = lectorarchivo.readLine();
                            while (line != null) {
                                escritor.println(line);
                                line = lectorarchivo.readLine();
                            }
                            lectorarchivo.close();
                            socket.close();
                            socketServidor.close();
                        } else {
                            System.out.println("El archivo no tiene permisos");
                        }

                    } catch (IOException e) {
                        System.out.println("No se encontro el archivo " + e);
                    }
                }

            } else {
                System.out.println("!!Error!!. El puerto debe ser númerico (Argumento 1)");
            }

        } else {
            System.out.println("!!Error!!. Ingresa solamente un argumento (Puerto)");

        }

    }

}
