package com.andres.pasaportecafeovalle.view.modelo;

public class AdminModel {

    private int id;
    private String nombre, email, clave, estado;

    public AdminModel(int id, String nombre, String email, String clave, String estado) {
        this.id = id; this.nombre = nombre; this.email = email; this.clave = clave; this.estado = estado;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getClave() { return clave; }
    public String getEstado() { return estado; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setClave(String clave) { this.clave = clave; }
    public void setEstado(String estado) { this.estado = estado; }
}
