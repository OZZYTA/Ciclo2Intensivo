package com.ciclo2udea.tiendamascotas;

import controlador.Controlador;

public class TiendaMascotas {

    public static void main(String[] args) {
        Controlador control= new Controlador();
        control.recuperarArchivoVentas();
        control.trabajar();
    }
}
