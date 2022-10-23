package com.ciclo2udea.altosdelrosal;

import java.util.ArrayList;

public class solucion {
    
    public static int[] informe(ArrayList<apartamento> ventas){
    
        //Inicializamos variables buscadas
        int totalAptVendidos=0;
        int valorTotalVendido=0;
        int vendidosTipo1=0;
        int valorVendidosTipo1=0;
        int vendidosTipo2=0;
        int valorVendidosTipo2=0;
        int vendidosTipo3=0;
        int valorVendidosTipo3=0;
        
        for (int i=0; i<ventas.size(); i++){
            totalAptVendidos=totalAptVendidos+1;
            valorTotalVendido=valorTotalVendido+ventas.get(i).getPrecio();
            
            if("Tipo1".equals(ventas.get(i).getTipoApt())){
                vendidosTipo1=vendidosTipo1+1;
                valorVendidosTipo1=valorVendidosTipo1+ventas.get(i).getPrecio();
            }
            else if (ventas.get(i).getTipoApt()=="Tipo2"){
                vendidosTipo2=vendidosTipo2+1;
                valorVendidosTipo2=valorVendidosTipo2+ventas.get(i).getPrecio();
            }
            else{
                 vendidosTipo3=vendidosTipo3+1;
                valorVendidosTipo3=valorVendidosTipo3+ventas.get(i).getPrecio();
            }           
        }
        
        int [] respuesta = new int []{totalAptVendidos, valorTotalVendido, vendidosTipo1, valorVendidosTipo1, vendidosTipo2, valorVendidosTipo2, vendidosTipo3, valorVendidosTipo3};
    
        return respuesta;
    }
    
}
