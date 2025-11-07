package com.andres.pasaportecafeovalle.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "admin")
public class AdminEntity {

    @PrimaryKey(autoGenerate = true) private int id;
    @ColumnInfo(name = "nombre") private String nombre;
    @ColumnInfo(name = "email") private String email;
    @ColumnInfo(name = "clave") private String clave;
    @ColumnInfo(name = "estado") private String estado;

    public AdminEntity(String nombre, String email, String clave, String estado) {
        this.nombre = nombre; this.email = email; this.clave = clave; this.estado = estado;
    }
    @Ignore
    public AdminEntity(int id, String nombre, String email, String clave, String estado) {
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
