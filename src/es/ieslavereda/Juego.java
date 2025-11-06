package es.ieslavereda;

import java.sql.SQLOutput;

public class Juego {
    public static void main(String[] args) {

        //System.out.println(Entrada.obtenerFila());
        //System.out.println(Entrada.obtenerColumna());

        Pantalla.mostrarMenu();
        String nombre = Entrada.obtenerTexto("Nombre:");

        borrarPantalla();

        //arrays
        char[][] tableroJugador = new char[10][10];
        char[][] tableroPC = new char[10][10];
        char[][] disparosJugador = new char[10][10];
        char[][] disparosPC = new char[10][10];
        int[] numeros = new int[10];

        //coordenadas
        int fila;
        int columna;

        rellenarNums(numeros);
        inicializarTablero(tableroJugador);
        inicializarTablero(tableroPC);

        Pantalla.mostrarTableros(numeros,tableroJugador,tableroPC);

        colocarBarcosJugador(tableroJugador, numeros, tableroPC);
        colocarBarcosPC(tableroJugador, numeros, tableroPC);

        inicializarTablero(disparosJugador);
        inicializarTablero(disparosPC);

        iniciarJuego(numeros, tableroJugador, tableroPC, disparosJugador, disparosPC);
    }

    public static void iniciarJuego(int[] numeros, char[][] tableroJugador, char[][] tableroPC, char[][] disparosJugador, char[][] disparosPC) {

        borrarPantalla();
        Pantalla.mostrarTableros(numeros, tableroJugador, tableroPC);
        System.out.println();
        Pantalla.mostrarTablerosDisparos(numeros, disparosJugador, disparosPC);




    }

    // Métodos a implementar
// Metodo que implementa el disparo del jugador

    //public static boolean disparoJugador (char[][] disparosJugador, char[][] tableroPC){}


// Metodo que implementa el disparo del PC

    //public static boolean disparoPC(char[][] tableroDisparosPC, char[][] tableroJugador){}


// Este metodo inicializa cada tablero de la siguiente manera:

    public static void rellenarNums(int[] numeros){
        for(int i=0;i<numeros.length;i++){
            numeros[i]=i;
        }
    }

    public static void inicializarTablero(char[][] tablero){

        for (int col = 0; col < 10; col++)
            for (int fila = 0; fila < 10; fila++)
                tablero[fila][col] = '~';
    }

//Este metodo visualiza el tablero por pantalla

    public static void visualizarTablero(char[][] tablero,char[][] tablero2) {
        for (int col = 0; col < 10; col++) {
            for (int fila = 0; fila < 10; fila++)
                System.out.print(tablero[fila][col]);
        }
    }

//Este metodo borra la terminal

    public static void borrarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

//Este metodo coloca los barcos pasados como vector dentro del tablero del Jugador

    public static void colocarBarcosJugador(char[][] tableroJugador, int[] numeros, char[][] tableroPC){
        String coordenada;
        int orientacion;
        boolean validado=false;
        int longitudBarco = 4;

        char fila;
        char columna;
        int filaInt;
        int columnaInt;
        boolean cabe;
        boolean colision=false;
        boolean doubleBarco3=false;

        do{
            System.out.println();
            coordenada = Entrada.obtenerTexto("Vamos a colocar el barco de " + (longitudBarco) + " celdas [A-J][0-9] ");

            if(coordenada.length() < 2){
                Pantalla.mostrarError("La coordenada debe comprenderse de dos caracteres [LETRA][número]");

            }else{
                fila=coordenada.charAt(0);
                columna=coordenada.charAt(1);
                validado=Entrada.validarCoordenada('A','J',fila, columna, coordenada);

                if (validado) {
                    orientacion = Entrada.obtenerEnteroBetween(0,1,"Orientación [0-Horizontal  |  1-Vertical]");
                    System.out.println("________________________________");
                    filaInt=convertirFilaInt(fila);
                    columnaInt=convertirColumnaInt(columna);
                    cabe = cabeBarco(tableroJugador, longitudBarco, filaInt, columnaInt, orientacion);

                    if(cabe)
                        colision = hayColision(tableroJugador,longitudBarco, filaInt, columnaInt, orientacion);

                    if (cabe && orientacion==0 && !colision){

                        for (int i=0;i<longitudBarco;i++){
                            tableroJugador[filaInt][columnaInt+i]= 'B';
                        }
                        borrarPantalla();
                        Pantalla.mostrarTableros(numeros,tableroJugador,tableroPC);

                        cabe=false;

                        //Repetir el barco de 3 celdas
                        if (longitudBarco==3 && !doubleBarco3){
                            doubleBarco3 = true;
                        }else if (longitudBarco<=3 && doubleBarco3){
                            longitudBarco--;
                        }else if (longitudBarco==4){
                            longitudBarco--;
                        }

                    }else if (cabe && orientacion==1 && !colision){

                        for (int i=0;i<longitudBarco;i++){
                            tableroJugador[filaInt+i][columnaInt]= 'B';
                        }
                        borrarPantalla();
                        Pantalla.mostrarTableros(numeros,tableroJugador,tableroPC);

                        cabe=false;

                        //Repetir el barco de 3 celdas
                        if (longitudBarco==3 && !doubleBarco3){
                            doubleBarco3 = true;
                        }else if (longitudBarco<=3 && doubleBarco3){
                            longitudBarco--;
                        }else if (longitudBarco==4){
                            longitudBarco--;
                        }

                    }

                }
            }
        }while(longitudBarco>0);
    }

    //Este metodo devuelve la fila/columna en int

    public static int convertirFilaInt(char fila){
        switch (fila){
            case 'A':return 0;
            case 'B':return 1;
            case 'C':return 2;
            case 'D':return 3;
            case 'E':return 4;
            case 'F':return 5;
            case 'G':return 6;
            case 'H':return 7;
            case 'I':return 8;
            case 'J':return 9;
            default:return 0;
        }

    }

    public static int convertirColumnaInt(char columna){
        switch (columna){
            case '0':return 0;
            case '1':return 1;
            case '2':return 2;
            case '3':return 3;
            case '4':return 4;
            case '5':return 5;
            case '6':return 6;
            case '7':return 7;
            case '8':return 8;
            case '9':return 9;
            default:return 0;
        }
    }


//Este metodo comprueba si hay algun barco en la zona del barco a colocar

    public static boolean hayColision(char[][] tablero, int longitudBarco, int filaInt, int columnaInt, int orientacion){

        if (orientacion == 0) {
            for (int i=0;i<longitudBarco;i++){
                if (tablero[filaInt][columnaInt+i]=='B'){
                    Pantalla.mostrarError("El barco colisiona con otro");
                    return true;
                }
            }
        }

        if (orientacion == 1) {
            for (int i=0;i<longitudBarco;i++){
                if (tablero[filaInt+i][columnaInt]=='B'){
                    Pantalla.mostrarError("El barco colisiona con otro");
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean hayColisionPC(char[][] tablero, int longitudBarco, int filaInt, int columnaInt, int orientacion){

        if (orientacion == 0) {
            for (int i=0;i<longitudBarco;i++){
                if (tablero[filaInt][columnaInt+i]=='B'){
                    return true;
                }
            }
        }

        if (orientacion == 1) {
            for (int i=0;i<longitudBarco;i++){
                if (tablero[filaInt+i][columnaInt]=='B'){
                    return true;
                }
            }
        }

        return false;
    }


//Este metodo comprueba si el barco está en los límites del tablero

    public static boolean cabeBarco(char[][] tablero, int longitudBarco, int filaInt, int columnaInt, int orientacion){
        if (orientacion == 0) {
            if (columnaInt+longitudBarco-1 >= tablero[0].length){
                Pantalla.mostrarError("El barco NO cabe");
                return false;
            }
        }
        if (orientacion == 1) {
            if (filaInt+longitudBarco-1 >= tablero.length){
                Pantalla.mostrarError("El barco NO cabe");
                return false;
            }
        }

        return true;
    }

    public static boolean cabeBarcoPC(char[][] tablero, int longitudBarco, int filaInt, int columnaInt, int orientacion){
        if (orientacion == 0) {
            if (columnaInt+longitudBarco-1 >= tablero[0].length){
                return false;
            }
        }
        if (orientacion == 1) {
            if (filaInt+longitudBarco-1 >= tablero.length){
                return false;
            }
        }

        return true;
    }


    //Este metodo coloca los barcos pasados como vector dentro del tablero del PC
    public static void colocarBarcosPC(char[][] tableroJugador, int[] numeros, char[][] tableroPC) {
        String coordenada;
        int orientacion;
        boolean validado = true;
        int longitudBarco = 4;

        boolean cabe;
        boolean colision = false;
        boolean doubleBarco3 = false;

        do {

            int filaInt = (int)((Math.random() * 1000) / 100f);
            int columnaInt = (int)((Math.random() * 1000) / 100f);
            int random = (int)((Math.random() * 1000) / 100f);

            if (random < 5)
                orientacion = 0;
            else
                orientacion = 1;

            if (validado) {
                cabe = cabeBarcoPC(tableroPC, longitudBarco, filaInt, columnaInt, orientacion);

                if (cabe)
                    colision = hayColisionPC(tableroPC, longitudBarco, filaInt, columnaInt, orientacion);

                if (cabe && orientacion == 0 && !colision) {

                    for (int i = 0; i < longitudBarco; i++) {
                        tableroPC[filaInt][columnaInt + i] = 'B';
                    }

                    cabe = false;

                    //Repetir el barco de 3 celdas
                    if (longitudBarco == 3 && !doubleBarco3) {
                        doubleBarco3 = true;
                    } else if (longitudBarco <= 3 && doubleBarco3) {
                        longitudBarco--;
                    } else if (longitudBarco == 4) {
                        longitudBarco--;
                    }

                } else if (cabe && orientacion == 1 && !colision) {

                    for (int i = 0; i < longitudBarco; i++) {
                        tableroPC[filaInt + i][columnaInt] = 'B';
                    }

                    cabe = false;

                    //Repetir el barco de 3 celdas
                    if (longitudBarco == 3 && !doubleBarco3) {
                        doubleBarco3 = true;
                    } else if (longitudBarco <= 3 && doubleBarco3) {
                        longitudBarco--;
                    } else if (longitudBarco == 4) {
                        longitudBarco--;
                    }

                }

            }

        } while (longitudBarco > 0);
        borrarPantalla();
        Pantalla.mostrarTableros(numeros, tableroJugador, tableroPC);
    }

}