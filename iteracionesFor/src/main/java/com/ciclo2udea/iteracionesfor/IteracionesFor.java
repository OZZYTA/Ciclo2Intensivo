/*Doña Juana, tiene 7 hijos, y ha querido junto con ellos aprender java, uno 
de los ejercicios propuestos para esta practica es, meter en un arreglo las 
edades de todos los miembros de su familia. Quiere crear un proyecto que, 
recibiendo dicho arreglo, de retorne un arreglo que tenga en su primera posición
la suma de todas las edades de los miembros de su hogar, en la segunda posición 
la edad del miembro mas joven de su hogar y por ultimo la edad del miembro con 
mas edad en su hogar.*/

package com.ciclo2udea.iteracionesfor;

public class IteracionesFor {
    
    public static int[] familia(int[] edades){    
    
        //La edad del mas joven
        int joven=edades[0]; //Guardo la edad en la primera posicion como si fuera la menor
        
        for(int i=0 ; i<edades.length ; i=i+1){ //For (desde donde; hasta donde; intervalo)
            if(edades[i]<joven){ //verificamos que, el que esta en esa posicion sea menor al que ya tengo registrado como mas joven
                joven=edades[i]; //Obtenemos la variable que nos interesa, la verdadera edad del mas joven
            }  
        }      
        
        //La edad del mayor
        int mayor=edades[0];
        for (int i=0; i<edades.length ; i++){
            if(edades[i]>mayor){
                mayor=edades[i]; //obtenemos la edad del mayor
            }
        }
                
        //La suma de las edades
        int suma=0;
        for(int i=0 ; i<edades.length ; i++){
            suma=suma+edades[i]; //obtenemos la sumatoria de todas la edades
        }
        
        int[] respuesta= new int[3];
        respuesta[0]=suma;
        respuesta[1]=joven;
        respuesta[2]=mayor;
        
        return respuesta;
    }
    
   
    public static void main(String[] args) {
        
        int [] misHijos ={23, 22, 5, 18, 24, 25};
        
        //Imprimir posicion por posicion si sabemos el tamaño del arreglo
        //System.out.println(familia(misHijos)[0]);
        //System.out.println(familia(misHijos)[1]);
        //System.out.println(familia(misHijos)[2]);
        
        
        //Imprimir posicion por posicion de un arreglo usando un for
        int [] respuesta= familia(misHijos);  //llamamos al metodo alimentadolo de "misHijos" y guardamos su retorn en un arreglo nuevo
        
        for (int i=0; i<respuesta.length; i++){  //recorremos el arreglo
            System.out.println(respuesta[i]); //imprimimos lo que encontramos en cada posicion
        }
        
    }
}
