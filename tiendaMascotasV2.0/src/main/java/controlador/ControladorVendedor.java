package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelos.Vendedor;
import modelos.Venta;
import vista.Vista;

public class ControladorVendedor {
    public static Scanner input = new Scanner(System.in); //Parametro principal
    public static final String URL = "jdbc:mysql://localhost:3306/tiendaMascotas";
    public static final String USER = "root";
    public static final String CLAVE = "";

    public ControladorVendedor() {
        
          try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS vendedor(id INT NOT NULL AUTO_INCREMENT, "
                    + "cedula VARCHAR(12) NOT NULL UNIQUE, nombre VARCHAR(50) NOT NULL, "
                    + "correo VARCHAR(22) NOT NULL, "
                    + "telefono VARCHAR(10) NOT NULL, PRIMARY KEY (id));";
            stmt.executeUpdate(sql);
            System.out.println("Conectados al area de vendedores, pudimos encontrar o crear la base");
        } catch (SQLException e) {
            System.out.println("No nos pudimos conectar a la base de datos");
            e.printStackTrace();
        }
        
    }
    
    
    public static void trabajarVendedor() {
        OUTER:
        while (true) {
            Vista.menuVendedores();
            int opcion = capturarOpcion();
            switch (opcion) {
                case 1:
                    System.out.println("Ingresar un nuevo Vendedor.");
                    ingresarVendedor();
                    break;
                case 2:
                    System.out.println("Buscar un vendedor registrado");
                    System.out.println("\n Por favor ingrese la cedula del vendedor: ");
                    String cedula = input.nextLine();
                    Vendedor buscado = buscarVendedor(cedula);
                    if (buscado == null) {
                        System.out.println("Ese vendedor no esta registrado");
                    } else {
                        Vista.verVendedorBuscado(buscado);
                    }
                
                    break;

                case 3:
                    ModificarVendedor();
                    break;

                case 4:
                    EliminarVendedor();
                    break;

                case 5:
                    Vista.verVendedores();
                    break;
                    
                case 6:
                    getByVendedor();
                    break;
                default:
                    System.out.println("Hasta luego, gracias por usar nuestro app");
                    break OUTER;
            }
        }
    }
    
     public static int capturarOpcion() {
        int opcion = 0;
        while (opcion < 1 || opcion > 7) { //Descartamos que el usuario nos de numeros fuera del rango
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
    
    
   public static void ingresarVendedor() {

        System.out.println("Por favor ingrese el numero de la cedula del nuevo vendedor: \n");
        String cedula = input.nextLine();

        System.out.println("Por favor ingrese el nombre del vendedor: \n");
        String nombre = input.nextLine();
        nombre= nombre.toUpperCase();

        System.out.println("Ingrese el correo electrònico: \n");
        String correo = input.nextLine();

        System.out.println("Numero celular del vendedor:\n");
        String telefono = input.nextLine();

        Vendedor nuevoVendedor = new Vendedor(cedula, nombre, correo, telefono);

        //Ahora lo mandamos a base de datos
        try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "INSERT INTO vendedor (cedula,  nombre, correo, telefono) "
                    + "VALUES('" + cedula + "','" + nombre + "',"
                    + "'" + correo + "','" + telefono + "');";

            stmt.executeUpdate(sql);
            System.out.println("Vendedor registrado correctamente");
        } catch (SQLException e) {
            System.out.println("No se pudo registrar el vendedor");
            e.printStackTrace();
        }

    } 
   
   public static Vendedor buscarVendedor(String cedula) {
        Vendedor resultado = null; //Por defecto, garantizo el return
        try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "SELECT * FROM vendedor WHERE cedula='" + cedula + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                resultado = new Vendedor(cedula, nombre, correo, telefono);
            }

        } catch (SQLException e) {
            System.out.println("No pudimos conectarnos y buscar en la base de datos.");
        }
        return resultado;
    }
   
   
   
   
   public static void ModificarVendedor() {
        System.out.println("Ingrese la cedula del vendedor modificar: ");
        String cedula= input.nextLine();

        Vendedor aModificar = buscarVendedor(cedula);
        if (aModificar != null) {
            System.out.println("Por favor el nombre del vendedor");
            String nombre =input.nextLine();

            System.out.println("Ingrese el correo del vendedor: \n");
            String correo = input.nextLine();

            System.out.println("Ingrese el telefono del vendedor: \n");
            String telefono = input.nextLine();

            try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
                //Consulta SQL para actualizar un objeto o registro
                String sql = "UPDATE vendedor SET nombre='" + nombre + "', "
                        + "correo='" + correo + "', telefono='" + telefono + 
                        "' WHERE cedula='" + cedula + "';";
                stmt.executeUpdate(sql);
                System.out.println("Vendedor actualizado correctamente");
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("No pudimos interactuar con la BD");
            }
        } else {
            System.out.println("Ese vendedor no esta registrado.\n");
        }
   }
   
   
   
   public static void EliminarVendedor() {
        System.out.println("Por favor ingrese el numero de cedula del vendedor a eliminar: ");
        String cedula = input.nextLine();
        Vendedor aEliminar = buscarVendedor(cedula);
        if (aEliminar != null) { //Verificamos que el objeto no este vacio
            try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
                String sql = "DELETE FROM vendedor WHERE cedula='" + cedula + "';";
                stmt.executeUpdate(sql);
                System.out.println("Registro de vendedor eliminado correctamente");
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("No se logró la interacción con la BD");
            }

        } else {
            System.out.println("Este vendedor no esta registrada. \n");
        }

    }
   
   
   
   public static String Directorio() {
        String resultado = "";
        try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "SELECT * FROM vendedor";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String telefono=rs.getString("telefono");
                resultado = "\nINFORMACION DEL VENDEDOR\nCedula: "
                        + cedula + "\nSeller Name: " + nombre + "\nCorreo Electronico: "
                        + correo + "\nTelefono: "
                        + telefono + "\n";
                                
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
        vendedor=vendedor.toUpperCase();
        String resultado = "";
        try ( Connection conn = DriverManager.getConnection(URL, USER, CLAVE);  Statement stmt = conn.createStatement();) {
            String sql = "SELECT * FROM ventas WHERE vendedor IN (SELECT cedula FROM vendedor WHERE nombre='"+vendedor+"');";
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
   
   
}
