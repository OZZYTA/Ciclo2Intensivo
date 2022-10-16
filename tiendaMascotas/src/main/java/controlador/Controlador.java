package controlador;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
    public static final String URL = "jdbc:mysql://localhost:3306/tiendaMascotas";
    public static final String USER = "root";
    public static final String CLAVE = "";

    public Controlador() {
        try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS ventas(id INT NOT NULL AUTO_INCREMENT, "
                    + "fecha DATE NOT NULL, numVenta INT(5) NOT NULL, "
                    + "cliente VARCHAR(100) NOT NULL, "
                    + "producto VARCHAR(100) NOT NULL, "
                    + "precio FLOAT(11) NOT NULL, "
                    + "cantidad INT(3) NOT NULL, "
                    + "vendedor VARCHAR(100) NOT NULL, PRIMARY KEY (id));";
            stmt.executeUpdate(sql);
            System.out.println("Conectados a la base, pudimos encontrar o crear la base");
        } catch (SQLException e) {
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
                    System.out.println("\n Por favor ingrese el ID de la venta: ");
                    int numeroID = input.nextInt();
                    Venta buscado = buscar(numeroID);
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
                case 6:
                    getByVendedor();
                    break;
                case 7:
                    exportarPLANO();
                    break;
                default:
                    System.out.println("Hasta luego, gracias por usar nuestro app");
                    break OUTER;
            }
        }
    }

    public static int capturarOpcion() {
        int opcion = 0;
        while (opcion < 1 || opcion > 8) { //Descartamos que el usuario nos de numeros fuera del rango
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
        try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "INSERT INTO ventas (id, fecha, numVenta, cliente, "
                    + "producto, precio, cantidad, vendedor) "
                    + "VALUES(" + nuevaVenta.getId() + ",'" + fecha + "',"
                    + "" + numVenta + ",'" + cliente + "','" + producto + "',"
                    + precio + "," + cantidad + ",'" + vendedor + "');";

            stmt.executeUpdate(sql);
            System.out.println("Venta registrada correctamente");
        } catch (SQLException e) {
            System.out.println("No se pudo registrar esta venta");
            e.printStackTrace();
        }

    }

    public static Venta buscar(int id) {
        Venta resultado = null; //Por defecto, garantizo el return
        try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "SELECT * FROM ventas WHERE id=" + id + ";";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Date fecha = rs.getDate("fecha");
                int numVenta = rs.getInt("numVenta");
                String cliente = rs.getString("cliente");
                String producto = rs.getString("producto");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String vendedor = rs.getString("vendedor");
                resultado = new Venta(fecha, numVenta, cliente, producto, precio, cantidad, vendedor);
            }

        } catch (SQLException e) {
            System.out.println("No pudimos conectarnos y buscar en la base de datos.");
        }
        return resultado;
    }

    public void Modificar() {
        System.out.println("Ingrese el ID de la venta que desea modificar: ");
        int numeroID = input.nextInt();
        input.nextLine();
        Venta aModificar = buscar(numeroID);
        if (aModificar != null) {
            System.out.println("Por favor ingrese el numero de venta");
            int numVenta = input.nextInt();
            input.nextLine();

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

            try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
                //Consulta SQL para actualizar un objeto o registro
                String sql = "UPDATE ventas SET numVenta=" + numVenta + ", "
                        + "cliente='" + cliente + "', producto='" + producto + "', "
                        + "precio=" + precio + ", cantidad=" + cantidad + ", "
                        + "vendedor='" + vendedor + "' WHERE id=" + numeroID + ";";
                stmt.executeUpdate(sql);
                System.out.println("Venta actualizada correctamente");
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("No pudimos interactuar con la BD");
            }
        } else {
            System.out.println("Esa venta no esta registrada.\n");
        }

    }

    public void Eliminar() {
        System.out.println("Por favor ingrese el numero del ID la venta que desea eliminar: ");
        int numeroID = input.nextInt();
        Venta aEliminar = buscar(numeroID);
        if (aEliminar != null) { //Verificamos que el objeto no este vacio
            try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
                String sql = "DELETE FROM ventas WHERE id=" + numeroID + ";";
                stmt.executeUpdate(sql);
                System.out.println("Venta eliminada correctamente");
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("No se logró la interacción con la BD");
            }

        } else {
            System.out.println("Esta venta no esta registrada. \n");
        }

    }

    public static String Directorio() {
        String resultado = "";
        try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "SELECT * FROM ventas";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Date fecha = rs.getDate("fecha");
                int numVenta = rs.getInt("numVenta");
                String cliente = rs.getString("cliente");
                String producto = rs.getString("producto");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String vendedor = rs.getString("vendedor");
                resultado = "\nINFORMACION DE LA VENTA\nFecha: "
                        + fecha + "\nNumero de Venta: " + numVenta + "\nCliente: "
                        + cliente + "\nProducto Vendido: "
                        + producto + "\nPrecio individual del producto: "
                        + precio + "\nCantidad Comprada: "
                        + cantidad + "\nVendedor: "
                        + vendedor + "\nTotal de la venta: $" + cantidad * precio + "\n";
                System.out.println(resultado);
            }

        } catch (SQLException e) {
        }
        return resultado;
    }

    //Filtro buscador sencillo
    public static String getByVendedor() {
        System.out.println("Por favor ingrese el nombre del vendedor que desea consultar: ");
        String vendedor = input.nextLine();
        String resultado = "";
        try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "SELECT * FROM ventas WHERE vendedor='" + vendedor + "';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Date fecha = rs.getDate("fecha");
                int numVenta = rs.getInt("numVenta");
                String cliente = rs.getString("cliente");
                String producto = rs.getString("producto");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                resultado = "\nINFORMACION DE LA VENTA\nFecha: "
                        + fecha + "\nNumero de Venta: " + numVenta + "\nCliente: "
                        + cliente + "\nProducto Vendido: "
                        + producto + "\nPrecio individual del producto: "
                        + precio + "\nCantidad Comprada: "
                        + cantidad + "\nVendedor: "
                        + vendedor + "\nTotal de la venta: $" + cantidad * precio + "\n";
                System.out.println(resultado);
            }

        } catch (SQLException e) {
        }
        return resultado;
    }

    public void exportarPLANO() {
        String pathArchivo = "infoventas.csv";

        try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "SELECT * FROM ventas";
            ResultSet rs = stmt.executeQuery(sql);
            BufferedWriter escritorArchivo = new BufferedWriter(new FileWriter(pathArchivo));

            //Escribo la linea que contiene los header y delimitadores de columnas
            escritorArchivo.write("ID,Fecha,No. Venta, Cliente, Producto, Precio, Cantidad, Vendedor, Venta Total");

            while (rs.next()) {
                int id=rs.getInt("id");
                Date fecha = rs.getDate("fecha");
                int numVenta = rs.getInt("numVenta");
                String cliente = rs.getString("cliente");
                String producto = rs.getString("producto");
                Double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String vendedor= rs.getString("vendedor");
                Double ventaTotal=precio*cantidad;
                
               //Escribamos en el documento
               String linea=id+","+fecha+","+numVenta+","+cliente+","+producto+","+precio+","+cantidad+","+vendedor+","+ventaTotal;
               escritorArchivo.newLine();
               escritorArchivo.write(linea);
            }
            System.out.println("Archivo externo creado y/o actualizado");
            escritorArchivo.close();
            stmt.close();
            conn.close();
            rs.close();
        }catch(SQLException e){
            System.out.println("No se pudo interactuar con la DB");
          }catch(IOException e){
              System.out.println("Hay problemas con el archivo externe (creación y/o consulta)");
          }
        }

        /*
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
            this.listaVentas = (ArrayList) gafas.readObject();
            gafas.close();
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("No encontramos el archivo, no se pudo cargar memoria previa");
        } catch (IOException e) {
            System.out.println("No se puede leer archivo, no se pudo cargar memoria previa");
        } catch (ClassNotFoundException e) {
            System.out.println("La informaciòn encontrada en el archivo no corresponde a ventas.");
        }
    }*/
    }
