package vista;

import controlador.ControladorVendedor;
import controlador.ControladorVentas;
import static controlador.ControladorVentas.listaVentas;
import modelos.Vendedor;
import modelos.Venta;

public class Vista {
    
    public static void menuPrincipal(){
        System.out.println("Tienda Nuestros Animalitos\n");
        System.out.println("Seleccione la tarea a realizar: (1 al 8):");
        System.out.println("1. Ingresar una nueva venta");
        System.out.println("2. Buscar una venta registrada");
        System.out.println("3. Modificar una venta registrada");
        System.out.println("4. Eliminar una venta registrada");
        System.out.println("5. Ver listado de ventas registradas");
        System.out.println("6. Ver area de vendedores");
        System.out.println("7. Exportar información de ventas en archivo plano");
        System.out.println("8. Salir.");
    }
    
    public static void verBuscado(Venta venta){
        System.out.println(venta.toString());
    }
    
    public static void verVentas(){
        ControladorVentas.Directorio();
    }
    
      public static void menuVendedores(){
        System.out.println("MENU AREA VENDEDORES\n");
        System.out.println("Seleccione la tarea a realizar: (1 al 7):");
        System.out.println("1. Ingresar un nuevo vendedor");
        System.out.println("2. Buscar un vendedor registrado");
        System.out.println("3. Modificar un vendedor registrado");
        System.out.println("4. Eliminar el registro de un vendedor");
        System.out.println("5. Ver listado de vendedores registrados");
        System.out.println("6. Ver ventas por vendedor");
        System.out.println("7. Ir atrás");

    }
    
      
    public static void verVendedorBuscado(Vendedor vendedor){
        System.out.println(vendedor.toString());
    } 
    
    public static void verVendedores(){
        ControladorVendedor.Directorio();
    }
}
