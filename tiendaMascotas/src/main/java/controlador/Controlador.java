package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelos.Venta;
import vista.Vista;
import static vista.Vista.menuPrincipal;

public class Controlador {

    public static ArrayList<Venta> listaVentas = new ArrayList<>(); //Parametro principal
    public static Scanner input = new Scanner(System.in); //Parametro principal

    public void trabajar() {
        OUTER:
        while (true) {
            menuPrincipal();
            int opcion = capturarOpcion();
            switch (opcion) {
                case 1:
                    System.out.println("Ingresar una venta.");
                    ingresar();
                    break;

                case 2:
                    System.out.println("Buscar una venta");
                    System.out.println("\n Por favor ingrese el numero de la venta: ");
                    int numVenta = input.nextInt();
                    Venta buscado = buscar(numVenta);
                    if (buscado == null) {
                        System.out.println("Esa venta no esta registrada");
                    } else {
                        Vista.verBuscado(buscado);
                    }
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    Vista.verVentas();
                    break;
                default:
                    System.out.println("Hasta luego, gracias por usar nuestro app");
                    break OUTER;
            }
        }
    }

    public static int capturarOpcion() {
        int opcion = 0;
        while (opcion < 1 || opcion > 6) { //Descartamos que el usuario nos de numeros fuera del rango
            System.out.println("Por favor ingrese Opción: \n");
            try { //Descarta que el usuario se equivoque en tipos de variables
                opcion = input.nextInt();
                input.nextLine(); //Evitar error de escane despues de recibir numeros
            } catch (InputMismatchException exception) {
                System.out.println("Opcion invalida. \n");
                input.nextLine();
            }
        }
        return opcion;
    }

    public void ingresar() {
        long milisegundos = System.currentTimeMillis();
        Date fecha = new Date(milisegundos);

        System.out.println("Por favor ingrese el numero de la venta a registrar: \n");
        int numVenta = input.nextInt();
        input.nextLine();

        System.out.println("Por favor ingrese el nombre del cliente: \n");
        String cliente = input.nextLine();

        System.out.println("Que producto esta comprando?: \n");
        String producto = input.nextLine();

        System.out.println("Que precio tiene este producto?: \n");
        Double precio = input.nextDouble();

        System.out.println("Que cantidad lleva de este producto?: \n");
        int cantidad = input.nextInt();
        input.nextLine();

        System.out.println("Quien es el responsable de esta venta?:\n");
        String vendedor = input.nextLine();

        Venta nuevaVenta = new Venta(fecha, numVenta, cliente, producto, precio, cantidad, vendedor);
        this.listaVentas.add(nuevaVenta);
        System.out.println("Venta registrada correctamente.\n");

    }

    public static Venta buscar(int numeroVenta) {
        Venta resultado = null; //Por defecto, garantizo el return
        for (int i = 0; i < listaVentas.size(); i++) {
            if (listaVentas.get(i).getNumVenta() == numeroVenta) {
                System.out.println("Venta encontrada");
                resultado = listaVentas.get(i);
            }
        }
        return resultado;
    }

}
