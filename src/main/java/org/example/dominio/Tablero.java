package org.example.dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Clase Tablero.
 */
public class Tablero {
    private static final int DIMENSION = 30;
    private int[][] estadoActual;
    private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];

    /**
     * Instancia un nuevo Tablero.
     */
    public Tablero() {
        estadoActual = new int[DIMENSION][DIMENSION];
    }

    /**
     * Leer estado actual.
     *
     * @param archivo lee archivo-(Matriz.txt)
     */
    public void leerEstadoActual(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int fila = 0;
            while ((linea = br.readLine()) != null && fila < DIMENSION) {
                for (int columna = 0; columna < DIMENSION; columna++) {
                    estadoActual[fila][columna] = Character.getNumericValue(linea.charAt(columna));
                }
                fila++;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Generar estado actual por montecarlo.
     */
    public void generarEstadoActualPorMontecarlo() {
        Random rand = new Random();
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int columna = 0; columna < DIMENSION; columna++) {
                estadoActual[fila][columna] = rand.nextDouble() < 0.5 ? 1 : 0;
            }
        }
    }

    /**
     * Transitar al estado siguiente.
     */
    public void transitarAlEstadoSiguiente() {
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int columna = 0; columna < DIMENSION; columna++) {
                int vecinasVivas = contarVecinasVivas(fila, columna);
                if (estadoActual[fila][columna] == 1 && (vecinasVivas == 2 || vecinasVivas == 3)) {
                    estadoSiguiente[fila][columna] = 1;
                } else if (estadoActual[fila][columna] == 0 && vecinasVivas == 3) {
                    estadoSiguiente[fila][columna] = 1;
                } else {
                    estadoSiguiente[fila][columna] = 0;
                }
            }
        }
        estadoActual = estadoSiguiente.clone();
        estadoSiguiente = new int[DIMENSION][DIMENSION];
    }

    /**
     * Cuenta las vecinas vivas.
     */
    private int contarVecinasVivas(int fila, int columna) {
        int vecinasVivas = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                if (i >= 0 && j >= 0 && i < DIMENSION && j < DIMENSION && (i != fila || j != columna)) {
                    vecinasVivas += estadoActual[i][j];
                }
            }
        }
        return vecinasVivas;
    }

    /**
     * Representación en String del estado actual.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int fila = 0; fila < DIMENSION; fila++) {
            for (int columna = 0; columna < DIMENSION; columna++) {
                sb.append(estadoActual[fila][columna]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Imprimir estado actual.
     */
    public void imprimirEstadoActual() {
        System.out.print("\033[H\033[2J");
        System.out.println("\n");
        System.out.flush();
        System.out.println(this);
    }
}
