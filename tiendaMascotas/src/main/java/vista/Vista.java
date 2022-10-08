package vista;

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
        System.out.println("6. Salir.");
    }
    
    public static void verBuscado(Venta venta){
        System.out.println(venta.toString());
    }
    
    public static void verVentas(){
    
        for (int i=0; i<listaVentas.size();i++){
            System.out.println("\nINFORMACION DE LA VENTA: ");
            System.out.println("Fecha de la venta: "+listaVentas.get(i).getFecha());
            System.out.println("Numero de la venta: "+listaVentas.get(i).getNumVenta());
            System.out.println("Nombre del cliente: "+listaVentas.get(i).getCliente());
            System.out.println("Producto vendido: "+listaVentas.get(i).getProducto());
            System.out.println("Precio del producto "+listaVentas.get(i).getPrecio());
            System.out.println("Cantidad comprada: "+listaVentas.get(i).getCantidad());
            System.out.println("Responsable de la venta: "+listaVentas.get(i).getVendedor());
            System.out.println("Precio de la compra: "+listaVentas.get(i).getPrecio()*listaVentas.get(i).getCantidad()+"\n");
        }
    }
    
}
