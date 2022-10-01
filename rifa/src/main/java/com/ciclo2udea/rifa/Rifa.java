package com.ciclo2udea.rifa;

import java.util.Scanner;

public class Rifa {
  
    public static Scanner input = new Scanner (System.in);  //Creamos un objeto llamado input que sirve para leer lo que el usuario escribe
    
    //Declaracion y definicion del metodo
    //public static String saludar(String nombre){ //Declaramos un metodo con un parametro de entrada
    //String Saludo="Hola " +nombre+", como estas?"; //Ejecutamos alguna accion con ese parametro de entrada
    //return Saludo; //Retornamos un string con el resultado
    //}
    
    public static void rifaJava(int b){ //limite lo establce el que ejecuta el metodo
           
   
    int intentos=0; //Variable contador (colaboradora)
    int n= (int) ((Math.random())*b)+1; //Genera un numero ganador random teniendo en cuenta el limite
    while(true){ //Ciclo obligatorio e infinito
        System.out.println("Por favor ingrese un numero: ");
        int numero=input.nextInt(); //Guardamos el numero ingresado por el usuario en la variable "numero"
        if (numero<=0 || numero>b){ // || significa ó ; && significa y
            System.out.println("¡Te saliste de intervalo!"); //No esta dentro de los numeros
        }
        else if (numero>n){ //Verificamos mayor que
            System.out.println("¡Ups! Te pasaste"); //Nos pasamos
            intentos +=1; //intentos=intentos+1;
        }
        else if (numero<n){  //Verificamos menor 
            System.out.println("¡Ups! Estás por debajo"); //Nos pasamos
            intentos=intentos+1; //Aumentamos 1 en el contador
        }
        else if (numero==n){ //Igual que
            intentos=intentos+1; //aumentamos uno al contador
            System.out.println("¡LO LOGRASTE! Usaste "+intentos+ " intentos.");
            break;
        }
    }
    
    
    } 
    
    //Prueba y ejecucion del metodo
    public static void main(String[] args) {
        
        System.out.println("Por favor ingresa el limite de numeros: "); //pedimos el limite al usuario
        int limite=input.nextInt();
        rifaJava(limite);         //llamamos al metodo con el limite que nos dio el usuario
       
    }
}
