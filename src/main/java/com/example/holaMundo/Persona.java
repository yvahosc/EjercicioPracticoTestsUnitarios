package com.example.holaMundo;

public class Persona {
    private String nombre;
    private String documento;
    private String apellido;
    private String ciudad;

    public Persona(){

    }

    public Persona(String nombre, String documento, String apellido, String ciudad) {
        this.nombre = nombre;
        this.documento = documento;
        this.apellido = apellido;
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
