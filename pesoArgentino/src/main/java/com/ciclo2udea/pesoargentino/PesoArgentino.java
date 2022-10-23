package com.ciclo2udea.pesoargentino;

import java.text.DecimalFormat;

public class PesoArgentino {

    public static double[] reporte(double[] valoresCierre) {

        //Inicializamos la variables necesarias
        double suma = 0;
        double menor = valoresCierre[0];
        double mayor = valoresCierre[0];

        for (int i = 0; i < valoresCierre.length; i++) {
            //Sumamos en la variable suma para despues sacar el promedio
            suma = suma + valoresCierre[i];

            //Verificamos cual valor es el menor
            if (valoresCierre[i] < menor) {
                menor = valoresCierre[i];
            }

            //Verficarmos cual es el valor mayor
            if (valoresCierre[i] > mayor) {
                mayor = valoresCierre[i];
            }
        }

        double promedio = suma / valoresCierre.length;

        //Si deseamos ajustar los decimales de una variable hacemos lo siguientes
        // DecimalFormat df = new DecimalFormat("#.0");
        double[] solucion = new double[3];
        solucion[0] = mayor;
        solucion[1] = menor;
        solucion[2] = promedio;

        return solucion;
    }

    //Esto lo hace el VPL, no debemos hacerlo
    public static void main(String[] args) {
        double[] cierres = {147.31, 147.06, 146.86, 146.56, 146.26, 145.44, 145.17, 144.89, 144.63, 144.35,
            143.51, 143.18, 142.87, 142.58, 142.27, 141.38, 141.12, 140.66, 140.56, 140.06, 139.15, 139.02, 138.74,
            138.34, 137.93, 138.34, 137.12, 135.91, 136.31, 135.91};
        
        double[] prueba = reporte(cierres);
        
        for (int i= 0; i<prueba.length; i++){
            System.out.println(prueba[i]);
        }
    }
}
