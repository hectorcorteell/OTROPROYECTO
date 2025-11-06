package es.ieslavereda;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Entrada {

    public static String obtenerTexto(String texto){

        Scanner sc = new Scanner(System.in);

        Pantalla.mostrarMensaje(texto);
        return sc.nextLine();

    }

    private static int obtenerEntero(String texto) {
        Scanner sc = new Scanner(System.in);
        Pantalla.mostrarMensaje(texto);
        do{
            if(!sc.hasNextInt()) {
                Pantalla.mostrarError("¡Debes introducir un número entre 0-1!");
                sc.nextLine();
                Pantalla.mostrarMensaje(texto);
            }
        }while(!sc.hasNextInt());

        return sc.nextInt();

    }

    public static int obtenerEnteroBetween(int min, int max, String texto) {
        int numero;

        do {
            numero = obtenerEntero(texto);
            if (numero < min)
                Pantalla.mostrarError("El número debe ser entre 0-1");
            if (numero > max)
                Pantalla.mostrarError("El número debe ser entre 0-1");
        }while (numero<min || numero>max);

        return numero;
    }

    private static char obtenerChar(String texto){
        Scanner sc = new Scanner(System.in);
        Pantalla.mostrarMensaje(texto);
        String entrada;

        do {
            entrada = sc.nextLine();
            if (entrada.length() > 1)
                Pantalla.mostrarError("Has introducido demasiados caracteres");
        }while(entrada.length()!=1);

        return entrada.charAt(0);
    }

    public static char obtenerChar(char minChar, char maxChar, String texto){
        char letra;
        letra = obtenerChar(texto);

        do {
            if (letra < minChar || letra > maxChar)
                Pantalla.mostrarError("Debes introducir una letra entre " + minChar + " y " + maxChar);
        }while(letra < minChar || letra > maxChar);

        return letra;
    }

    public static boolean validarCoordenada(char minChar, char maxChar, char fila, char columna, String coordenada){

        if(coordenada.length() != 2){
            Pantalla.mostrarError("La coordenada debe comprenderse de una letra y un número");
            return false;
        } else if(coordenada.length() < 2){
            Pantalla.mostrarError("La coordenada debe comprenderse de una letra y un número");
            return false;
        }

        if (fila < minChar || fila > maxChar){
            Pantalla.mostrarError("Debes introducir una letra MAYÚSCULA entre " + minChar + " y " + maxChar);
            return false;
        }

        if (columna < '0'){
            Pantalla.mostrarError("El número debe ser mayor o igual que 0.");
            return false;
        }
        else if (columna > '9'){
            Pantalla.mostrarError("El número debe ser menor o igual que 9.");
            return false;
        } else {
            System.out.println(colorize("Coordenada válida", Attribute.TEXT_COLOR(0, 255, 0)));
            return true;
        }

    }

    public static int obtenerFila(){
        return obtenerChar('A','J', "Introduce la fila [A-J]");
    }

    public static int obtenerColumna(){
        return obtenerEnteroBetween(0,9,"Introduce la columna [0-9]");
    }

}