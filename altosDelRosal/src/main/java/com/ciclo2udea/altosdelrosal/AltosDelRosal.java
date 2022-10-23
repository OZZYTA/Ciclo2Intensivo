package com.ciclo2udea.altosdelrosal;

import static com.ciclo2udea.altosdelrosal.solucion.informe;
import java.util.ArrayList;

public class AltosDelRosal {

    public static void main(String[] args) {
        
        ArrayList<apartamento> listaPrueba = new ArrayList<>();
        
        apartamento apt =new apartamento("101", "Tipo2", 110000000, "Natali Velasquez", "1231231231");
        listaPrueba.add(apt);
        
        listaPrueba.add(new apartamento("102", "Tipo1", 90000000, "Jorge Cuervo", "1231231231"));
        listaPrueba.add(new apartamento("202", "Tipo1", 90000000, "Lola Perez", "1231231231"));
        listaPrueba.add(new apartamento("301", "Tipo3", 135000000, "Luz Suarez", "1231231231"));
        listaPrueba.add(new apartamento("401", "Tipo2", 110000000, "Catalina la O", "1231231231"));
        listaPrueba.add(new apartamento("401", "Tipo1", 90000000,  "German Barrios", "1231231231"));
        listaPrueba.add(new apartamento("302", "Tipo1", 90000000, "Luis Cuervo", "1231231231"));
        listaPrueba.add(new apartamento("602", "Tipo3", 135000000, "Jairo Lopez", "1231231231"));
        
        int [] respuestaPrueba =informe(listaPrueba);
        System.out.println("Impreso por Natali Velásquez");
        
        System.out.println("Total Apt Vendidos; "+ respuestaPrueba[0]);
        System.out.println("Total valores vendidos: "+ respuestaPrueba[1]);
        System.out.println("Total apt Tipo 1 Vendidos: "+ respuestaPrueba[2]);
        System.out.println("Total valores Tipo 2 Vendidos: "+ respuestaPrueba[3]);
        
    }
}
