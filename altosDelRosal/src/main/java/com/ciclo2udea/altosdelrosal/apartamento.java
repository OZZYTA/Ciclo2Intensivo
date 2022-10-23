package com.ciclo2udea.altosdelrosal;

public class apartamento {
    String numApartamento;
    String tipoApt;
    int precio;
    String nombreComprador;
    String docComprador;

    public apartamento(String numApartamento, String tipoApt, int precio, String nombreComprador, String docComprador) {
        this.numApartamento = numApartamento;
        this.tipoApt = tipoApt;
        this.precio = precio;
        this.nombreComprador = nombreComprador;
        this.docComprador = docComprador;
    }

    public String getNumApartamento() {
        return numApartamento;
    }

    public void setNumApartamento(String numApartamento) {
        this.numApartamento = numApartamento;
    }

    public String getTipoApt() {
        return tipoApt;
    }

    public void setTipoApt(String tipoApt) {
        this.tipoApt = tipoApt;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getDocComprador() {
        return docComprador;
    }

    public void setDocComprador(String docComprador) {
        this.docComprador = docComprador;
    }
    
    
}
