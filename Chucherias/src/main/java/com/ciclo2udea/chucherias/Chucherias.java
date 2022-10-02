package com.ciclo2udea.chucherias;

public class Chucherias { //superclase
    
    public static class empleado { //Creo una clase (que pertenece a la super clase chucherias
        //atributos de un empleado
        private String nombre;
        private String identificacion;
        private String correo;
        private String telefono;

        //Metodo constructor de objetos
        public empleado(String nombre, String identificacion, String correo, String telefono) {
            this.nombre = nombre;
            this.identificacion = identificacion;
            this.correo = correo;
            this.telefono = telefono;
        }

        public empleado() { //Metodo constructor vacio
        }
        
        //Getters
        public String getNombre() {
            return nombre;
        }

        public String getIdentificacion() {
            return identificacion;
        }

        public String getCorreo() {
            return correo;
        }

        public String getTelefono() {
            return telefono;
        }
        
        //Setters
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setIdentificacion(String identificacion) {
            this.identificacion = identificacion;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }  
        
        
        public String comidaFavorita(empleado empl){
        String mensaje=empl.getNombre()+ " tiene por comida favorita el churrasco.";
        return mensaje;
        }

        @Override
        public String toString() {
            return "empleado{" + "se llama= " + nombre + ", su identificacion= es " + identificacion + ", su correo correo es= " + correo + " y su telefono es=" + telefono + '}';
        }
        
        
        
    }

    public static void main(String[] args) {
        
        /*empleado Natali= new empleado("Natali Velàsquez","1231231233","he@he.com","321321321");
        System.out.println("Acabamos de crear un objeto empleado llamado "+Natali.getNombre());
        System.out.println("El numero telefonico de "+Natali.getNombre()+" es: ");
        System.out.println(Natali.getTelefono());
       
        System.out.println("\nTuvimos que cambiar su numero telefonico y ahora es:");
        Natali.setTelefono("3143143144");
        System.out.println(Natali.getTelefono());*/
        
        empleado Juan = new empleado("Juan David","1090090090","Juan@david.com","3213213211");
        System.out.println(Juan.toString());
        System.out.println(Juan.comidaFavorita(Juan));
        
    }
}
