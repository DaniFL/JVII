package org.example.aplicacion;


import java.util.concurrent.TimeUnit;
import org.example.dominio.Tablero;
import java.io.IOException;
import java.lang.InterruptedException;

        public class Principal {
                public static void main(String[] args) throws InterruptedException {
                    Tablero tablero = new Tablero();
                    String archivo = "matriz.txt";

                    System.out.println("SIMULACIÓN CON TABLERO LEÍDO DE ARCHIVO:");
                    tablero.leerEstadoActual(archivo);
                    tablero.imprimirEstadoActual();
                    TimeUnit.SECONDS.sleep(1);
                    for (int i = 0; i < 10; i++) {
                        tablero.transitarAlEstadoSiguiente();
                        tablero.imprimirEstadoActual();
                        TimeUnit.SECONDS.sleep(1);
                    }

                    System.out.println("SIMULACIÓN CON TABLERO GENERADO POR MONTECARLO:");
                    tablero.generarEstadoActualPorMontecarlo();
                    tablero.imprimirEstadoActual();
                    TimeUnit.SECONDS.sleep(1);
                    for (int i = 0; i < 10; i++) {
                        tablero.transitarAlEstadoSiguiente();
                        tablero.imprimirEstadoActual();
                        TimeUnit.SECONDS.sleep(1);
                    }

            }
}
