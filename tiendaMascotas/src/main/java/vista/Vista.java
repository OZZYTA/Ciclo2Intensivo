package vista;

import controlador.Controlador;
import static controlador.Controlador.listaVentas;
import modelos.Venta;

public class Vista {
    
    public static void menuPrincipal(){
        System.out.println("Tienda Nuestros Animalitos\n");
        System.out.println("Seleccione la tarea a realizar: (1 al 6):");
        System.out.println("1. Ingresar una nueva venta");
        System.out.println("2. Buscar una venta registrada");
        System.out.println("3. Modificar una venta registrada");
        System.out.println("4. Eliminar una venta registrada");
        System.out.println("5. Ver listado de ventas registradas");
        System.out.println("6. Ver ventas por vendedor");
        System.out.println("7. Exportar información de ventas en archivo plano");
        System.out.println("8. Salir.");
    }
    
    public static void verBuscado(Venta venta){
        System.out.println(venta.toString());
    }
    
    public static void verVentas(){
        Controlador.Directorio();
    }
    
}
