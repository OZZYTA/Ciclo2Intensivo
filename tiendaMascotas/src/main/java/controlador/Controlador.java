package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.SQLException;
import modelos.Venta;
import vista.Vista;
import static vista.Vista.menuPrincipal;

public class Controlador {
    public static ArrayList<Venta> listaVentas; //Parametro principal
    public static Scanner input = new Scanner(System.in); //Parametro principal
    public static final String URL="jdbc:mysql://localhost:3306/tiendaMascotas";
    public static final String USER="root";
    public static final String CLAVE="";

    public Controlador() {
        try (Connection conn = DriverManager.getConnection(URL,USER,CLAVE);
                Statement stmt = conn.createStatement();){
                String sql="CREATE TABLE IF NOT EXISTS ventas(id INT NOT NULL AUTO_INCREMENT, "
                        + "fecha DATE NOT NULL, numVenta INT(5) NOT NULL, "
                        + "cliente VARCHAR(100) NOT NULL, "
                        + "producto VARCHAR(100) NOT NULL, "
                        + "precio FLOAT(11) NOT NULL, "
                        + "cantidad INT(3) NOT NULL, "
                        + "vendedor VARCHAR(100) NOT NULL, PRIMARY KEY (id));";
                stmt.executeUpdate(sql);
                System.out.println("Conectados a la base, pudimos encontrar o crear la base");
                }catch (SQLException e){
                System.out.println("No nos pudimos conectar a la base de datos");
                e.printStackTrace();
                }
    }

    
    
    
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
                    Modificar();
                    break;

                case 4:
                    Eliminar();
                    break;

                case 5:
                    Vista.verVentas();
                    break;
                default:
                    guardarArchivoVentas();
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
        
        //Ahora lo mandamos a base de datos
        try (Connection conn = DriverManager.getConnection(URL,USER,CLAVE);
                Statement stmt = conn.createStatement();){
                String sql="INSERT INTO ventas (id, fecha, numVenta, cliente, "
                        + "producto, precio, cantidad, vendedor) "
                        + "VALUES("+nuevaVenta.getId()+",'"+fecha+"',"
                        + ""+numVenta+",'"+cliente+"','"+producto+"',"
                        +precio+","+cantidad+",'"+vendedor+"');";
         
                stmt.executeUpdate(sql);
                System.out.println("Venta registrada correctamente");
                }catch (SQLException e){
                System.out.println("No se pudo registrar esta venta");
                e.printStackTrace();
                }
       

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
    

    public void Modificar() {
        System.out.println("Ingrese el numero de la venta que desea modificar: ");
        int numero = input.nextInt();
        input.nextLine();
        Venta aModificar = buscar(numero);
        if (aModificar != null) {
            System.out.println("Ingrese el nombre del cliente: \n");
            String cliente = input.nextLine();

            System.out.println("Ingrese el nombre del producto vendido: \n");
            String producto = input.nextLine();

            System.out.println("Ingrese por favor el precio del producto: \n");
            Double precio = input.nextDouble();

            System.out.println("Ingrese la cantidad que lleva de dicho producto: \n");
            int cantidad = input.nextInt();
            input.nextLine();

            System.out.println("Ingrese el nombre del responsable de la venta: \n");
            String vendedor = input.nextLine();

            for (int i = 0; i < this.listaVentas.size(); i++) {
                if (this.listaVentas.get(i).getNumVenta() == numero) {
                    this.listaVentas.get(i).setCliente(cliente);
                    this.listaVentas.get(i).setProducto(producto);
                    this.listaVentas.get(i).setPrecio(precio);
                    this.listaVentas.get(i).setCantidad(cantidad);
                    this.listaVentas.get(i).setVendedor(vendedor);
                }
            }
            System.out.println("Venta actualizada correctamente.\n");
        } else {
            System.out.println("Esa venta no esta registrada.\n");
        }

    }

    public void Eliminar() {
        System.out.println("Por favor ingrese el id de la venta que desea eliminar: ");
        int numero = input.nextInt();
        ///ELIMINAR EN LA BASE DE DATOS
        try (Connection conn = DriverManager.getConnection(URL,USER,CLAVE);
                Statement stmt = conn.createStatement();){
                String sql="DELETE FROM ventas WHERE id="+numero+";";
         
                stmt.executeUpdate(sql);
                System.out.println("Venta Eliminada correctamente");
                }catch (SQLException e){
                System.out.println("Hubo un error eliminando, tal vez esa venta no existe");
                e.printStackTrace();
                }

    }

    public void guardarArchivoVentas() {
        try {
            FileOutputStream archivo = new FileOutputStream("ventas.dat");
            ObjectOutputStream lapiz = new ObjectOutputStream(archivo);
            lapiz.writeObject(this.listaVentas);
            lapiz.close();
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ruta de archivo no valida.");
        } catch (IOException e) {
            System.out.println("No puedo escribir en el archivo.");
            e.printStackTrace();
        }
    }

    public void recuperarArchivoVentas() {
        try {
            FileInputStream archivo = new FileInputStream("ventas.dat");
            ObjectInputStream gafas = new ObjectInputStream(archivo);
            this.listaVentas=(ArrayList)gafas.readObject();
            gafas.close();
            archivo.close();
        }catch (FileNotFoundException e){
            System.out.println("No encontramos el archivo, no se pudo cargar memoria previa");
        }catch (IOException e){
            System.out.println("No se puede leer archivo, no se pudo cargar memoria previa");
        }catch (ClassNotFoundException e){
            System.out.println("La informaciòn encontrada en el archivo no corresponde a ventas.");
        }
    }

}
