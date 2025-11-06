package es.ieslavereda;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Pantalla {

    public static void mostrarMensaje(String texto) {
        System.out.println(texto);
    }

    public static void mostrarError(String mensajeError) {
        System.out.println(colorize("¡ERROR!", Attribute.TEXT_COLOR(255, 0, 0)));
        System.out.println(mensajeError);
    }

    public static void mostrarTablero(char[][] tableroJugador, char[][] tableroPC) {

        for (int fil = 0; fil < tableroJugador.length; fil++) {
            System.out.print((char)('A'+fil) + " ");
            for (int col = 0; col < tableroJugador[fil].length; col++)
                System.out.print(tableroJugador[fil][col] + " ");

            System.out.print((char)('A'+fil)+" "+"         "+(char)('A'+fil)+" ");

            for (int col = 0; col < 10; col++)
                System.out.print(tableroPC[fil][col] + " ");
            System.out.print((char)('A'+fil) + " ");
            System.out.println();


        }


    }

    public static void mostrarNumerosTablero(int[] numeros){
        System.out.print(" ");
        for(int i=0;i<numeros.length;i++){
            System.out.print(" " + numeros[i]);
        }
        System.out.print(" "+"           ");

        System.out.print(" ");
        for(int i=0;i<numeros.length;i++){
            System.out.print(" " + numeros[i]);
        }
    }

    public static void mostrarMenu(){
        System.out.println(
                "====================== \n" +
                        "   HUNDIR LA FLOTA \n" +
                        " V1.0 Héctor Cortell\n" +
                        "======================");
    }

    public static void mostrarTableros(int[] numeros, char[][] tableroJugador, char[][] tableroPC){
        System.out.println("      "+"Tu tablero"+" "+"                    "+"Tablero del PC");
        Pantalla.mostrarNumerosTablero(numeros);
        System.out.println();
        Pantalla.mostrarTablero(tableroJugador,tableroPC);
        Pantalla.mostrarNumerosTablero(numeros);
        System.out.println();
    }

    public static void mostrarTablerosDisparos(int[] numeros, char[][] tableroJugador, char[][] tableroPC){
        System.out.println("      "+"Tus disparos"+" "+"                  "+"Disparos del PC");
        Pantalla.mostrarNumerosTablero(numeros);
        System.out.println();
        Pantalla.mostrarTablero(tableroJugador,tableroPC);
        Pantalla.mostrarNumerosTablero(numeros);
        System.out.println();
    }

    public static void mostrarJunto(int[] numeros, char[][] tableroJugador, char[][] tableroPC, char[][] disparosJugador, char[][] disparosPC) {

        Juego.borrarPantalla();
        Pantalla.mostrarTableros(numeros, tableroJugador, tableroPC);
        System.out.println();
        Pantalla.mostrarTablerosDisparos(numeros, disparosJugador, disparosPC);
    }


}