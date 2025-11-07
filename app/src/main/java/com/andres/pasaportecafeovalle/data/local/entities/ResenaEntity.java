package com.andres.pasaportecafeovalle.data.local.entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "resenas")
public class ResenaEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "id_cliente") private int idCliente;
    @ColumnInfo(name = "id_sucursal") private int idSucursal;
    @ColumnInfo(name = "valoracion_general") private int valoracionGeneral;
    @ColumnInfo(name = "valoracion_atencion") private int valoracionAtencion;
    @ColumnInfo(name = "valoracion_comida") private int valoracionComida;
    @ColumnInfo(name = "valoracion_ambiente") private int valoracionAmbiente;
    @ColumnInfo(name = "opinion") private String opinion;
    @ColumnInfo(name = "foto_uri") private String fotoUri;
    @ColumnInfo(name = "fecha") private String fecha;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdSucursal() { return idSucursal; }
    public void setIdSucursal(int idSucursal) { this.idSucursal = idSucursal; }
    public int getValoracionGeneral() { return valoracionGeneral; }
    public void setValoracionGeneral(int v) { this.valoracionGeneral = v; }
    public int getValoracionAtencion() { return valoracionAtencion; }
    public void setValoracionAtencion(int v) { this.valoracionAtencion = v; }
    public int getValoracionComida() { return valoracionComida; }
    public void setValoracionComida(int v) { this.valoracionComida = v; }
    public int getValoracionAmbiente() { return valoracionAmbiente; }
    public void setValoracionAmbiente(int v) { this.valoracionAmbiente = v; }
    public String getOpinion() { return opinion; }
    public void setOpinion(String opinion) { this.opinion = opinion; }
    public String getFotoUri() { return fotoUri; }
    public void setFotoUri(String fotoUri) { this.fotoUri = fotoUri; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
