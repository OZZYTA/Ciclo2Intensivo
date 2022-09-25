package com.mycompany.holamundo;

import java.util.Scanner;

public class Holamundo { //declaración de la clase

    public static void main(String[] args) {  //Objeto o metodo de la clase holamundo
        Scanner input = new Scanner(System.in); //Creamos un objeto de tipo Scanner llamado input que se le alimenta de lo que el usuario ingresa al sistema
        String nombre; //Declaramos una variable de tipo string llamada nombre
        System.out.println("Cual es tu nombre?: "); //Mostramos un mensaje en pantalla que pide el nombre al usuario
        nombre=input.nextLine(); //Usamos el metodo nextLine de la clase Scanner a través de su objeto input, y guardamos la información en la variable nombre.
        System.out.println("\nHola! "+nombre+" Cómo estás?"); //Imprimos un salud que incluye el nombre
    }
}
