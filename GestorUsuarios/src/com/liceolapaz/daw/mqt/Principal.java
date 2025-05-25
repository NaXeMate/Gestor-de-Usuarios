package com.liceolapaz.daw.mqt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Principal {

    private static final HashMap<Integer, Usuario> usuarios = new HashMap<>();

    public static void main(String[] args) {
        do {
            escribirMenu();
            int opcion = leerNumero();
            switch (opcion) {
                case 0 -> {
                    System.out.println("¡Hasta la próxima!");
                    System.exit(0);
                }

                case 1 -> {
                    importarUsuarios();
                    System.out.println(usuarios);
                }

                case 2 -> {
                    addUsuario();
                    System.out.println(usuarios);
                }

                case 3 -> {
                    modificarUsuario();
                    System.out.println(usuarios);
                }

                case 4 -> {
                    eliminarUsuario();
                    System.out.println(usuarios);
                }

                case 5 -> exportarUsuarios();

                default -> System.out.println("Opción no válida. Pruebe de nuevo.");
            }
        } while (true);

    }

    private static void exportarUsuarios() {

    }

    private static void eliminarUsuario() {
        System.out.print("Escribe el número del departamento: ");
        int id = leerNumero();

        if (!usuarios.containsKey(id)) {
            System.out.println("El usuario no está registrado, no se puede eliminar. Volviendo al menú principal...");
            return;
        }

        usuarios.remove(id);
    }

    private static void modificarUsuario() {
        System.out.print("Escribe el ID del usuario que se va a modificar: ");
        int idOG = leerNumero();

        if (!usuarios.containsKey(idOG)) {
            System.out.println("El usuario que estás buscando no está registrado. Volviendo al menú principal...");
        }

        System.out.print("Escribe el ID que tendrá el usuario tras la modificación: ");
        int id = leerNumero();

        if (id <= 0) {
            System.out.println("No se permiten IDs de usuario iguales o inferiores a 0.");
            return;
        }

        System.out.print("Escribe el email que tendrá el usuario tras la modificación: ");
        String email = leerTexto();

        if (email.isEmpty()) {
            System.out.println("El email del usuario no puede estar vacío.");
            return;
        }

        if (!validarEmail(email)) {
            System.out.println("El email introducido no es válido. Ejemplo: example@example.com.");
            return;
        }

        System.out.print("Introduce el nombre que tendrá el usuario tras la modificación: ");
        String firstName = leerTexto();

        if (firstName.isEmpty()) {
            System.out.println("El nombre del usuario no puede estar vacío.");
        }

        System.out.print("Escribe el apellido que tendrá el usuario tras la modificación: ");
        String lastName = leerTexto();

        if (lastName.isEmpty()) {
            System.out.println("El apellido del usuario no puede estar vacío.");
            return;
        }

        System.out.print("Escribe la URL que contenga el avatar que tendrá el usuario tras la modificación: ");
        String avatar = leerTexto();

        if (avatar.isEmpty()) {
            System.out.println("El avatar del usuario no puede estar vacío.");
            return;
        }

        if (!validarAvatar(avatar)) {
            System.out.println("El avatar introducido no es válido. Ejemplo: https://...");
            return;
        }

        Usuario usuario = new Usuario(id, email, firstName, lastName, avatar);
        usuarios.replace(idOG, usuario);
    }

    private static void addUsuario() {
        System.out.print("Escribe el ID del nuevo usuario: ");
        int id = leerNumero();

        if (usuarios.containsKey(id)) {
            System.out.println("El usuario ya ha sido añadido anteriormente. Volviendo al menú principal...");
            return;
        }

        System.out.print("Escribe el email del nuevo usuario: ");
        String email = leerTexto();

        if (email.isEmpty()) {
            System.out.println("El email del usuario no puede estar vacío.");
            return;
        }

        if (!validarEmail(email)) {
            System.out.println("El email introducido no es válido. Ejemplo: example@example.com.");
            return;
        }

        System.out.print("Escribe el nombre del nuevo usuario: ");
        String firstName = leerTexto();

        if (firstName.isEmpty()) {
            System.out.println("El nombre del usuario no puede estar vacío.");
            return;
        }

        System.out.print("Escribe el apellido del nuevo usuario: ");
        String lastName = leerTexto();

        if (lastName.isEmpty()) {
            System.out.println("El apellido del usuario no puede estar vacío.");
            return;
        }

        System.out.print("Escribe la URL que contenga el avatar del nuevo usuario: ");
        String avatar = leerTexto();

        if (avatar.isEmpty()) {
            System.out.println("El avatar del usuario no puede estar vacío.");
            return;
        }

        if (!validarAvatar(avatar)) {
            System.out.println("El avatar introducido no es válido. Ejemplo: https://...");
            return;
        }

        Usuario usuario = new Usuario(id, email, firstName, lastName, avatar);
        usuarios.put(id, usuario);
    }

    private static void importarUsuarios() {
        System.out.println("Se va a importar una lista de usuarios desde un fichero.");
        System.out.print("Introduce la ruta del archivo: ");

        String rutafichero = leerTexto();
        File fichero = new File(rutafichero);

        try {
            HashMap<Integer, Usuario> usuariosFichero = new HashMap<>();
            Scanner escaner = new Scanner(fichero);
            String linea = escaner.nextLine();

            if (linea.equals("[")) {
                linea = escaner.nextLine();
            }

            while (linea.equals("  {")) {
                linea = escaner.nextLine();
                String[] campos = linea.split(": ");

                int id;
                try {
                    id = Integer.parseInt(campos[1].substring(0,campos[1].length() - 1));
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }

                linea = escaner.nextLine();
                campos = linea.split(": ");

                String email = campos[1].substring(1,campos[1].length() - 2); // 1 = "; - 2 = ",
                if (!validarEmail(email)) {
                    System.out.println("El email introducido no es válido. Ejemplo: example@example.com.");
                    return;
                }

                linea = escaner.nextLine();
                campos = linea.split(": ");

                String firstName = campos[1].substring(1,campos[1].length() - 2);

                linea = escaner.nextLine();
                campos = linea.split(": ");

                String lastName = campos[1].substring(1,campos[1].length() - 2);

                linea = escaner.nextLine();
                campos = linea.split(": ");

                String avatar = campos[1].substring(1,campos[1].length() - 1);
                if (!validarAvatar(avatar)) {
                    System.out.println("El avatar introducido no es válido. Ejemplo: https://...");
                    return;
                }

                escaner.nextLine();
                linea = escaner.nextLine();

                Usuario usuario = new Usuario(id, email, firstName, lastName, avatar);
                usuariosFichero.put(id, usuario);

                usuarios.putAll(usuariosFichero);
            }
        } catch (FileNotFoundException e) {
            System.out.println("la ruta indicada no apunta a un fichero existente.");
        }
    }

    public static boolean validarAvatar(String avatar) {
/*
        if (avatar.startsWith("https://")) {
            return true;
        } else {
            return false;
        }
*/

        return avatar.startsWith("https://");
    }

    public static boolean validarEmail (String email) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static String leerTexto() {
        Scanner sc_text = new Scanner(System.in);
        return sc_text.nextLine();
    }

    private static int leerNumero() {
        Scanner sc_num = new Scanner(System.in);
        return sc_num.nextInt();
    }

    private static void escribirMenu() {
        System.out.print("""
                GESTOR USUARIOS
                1. Importar usuarios
                2. Añadir usuario
                3. Modificar usuario
                4. Eliminar usuario
                5. Exportar usuarios
                0. Salir
                Escoja una opción:\s""");
    }
}
