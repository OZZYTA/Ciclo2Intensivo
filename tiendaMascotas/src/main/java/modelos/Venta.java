package modelos;

import java.util.Date;

public class Venta {
    Date fecha;
    int numVenta;
    String cliente;
    String producto;
    Double precio;
    int cantidad;
    String Vendedor;
    

    public Venta(Date fecha, int numVenta, String cliente, String producto, Double precio, int cantidad, String Vendedor) {
        this.fecha = fecha;
        this.numVenta = numVenta;
        this.cliente = cliente;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.Vendedor = Vendedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(int numVenta) {
        this.numVenta = numVenta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getVendedor() {
        return Vendedor;
    }

    public void setVendedor(String Vendedor) {
        this.Vendedor = Vendedor;
    }

    @Override
    public String toString() {
        return "Venta{" + "fecha=" + fecha + ", numVenta=" + numVenta + ", cliente=" + cliente + ", producto=" + producto + ", precio=" + precio + ", cantidad=" + cantidad + ", Vendedor=" + Vendedor + '}';
    }
}
