package com.example.tp2_lab3.model;

public class Usuario {
    private int dni;
    private String apellido;
    private String nombre;
    private String email;
    private String contrasena;

    public Usuario() {
    }

    public Usuario(int dni, String apellido, String nombre, String email, String contrasena) {
        this.nombre = nombre;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dni=" + dni +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }

}
