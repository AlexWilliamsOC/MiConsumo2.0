package com.example.miconsumo20.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Plasticos {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;

    private String descripcion;

    private String usuario;

    private String origen;

    private String ubicacion;

    private String categoria;

    private String fotografia;

    public Plasticos(int id, String nombre, String descripcion, String usuario, String origen, String ubicacion, String categoria, String fotografia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.origen = origen;
        this.ubicacion = ubicacion;
        this.categoria = categoria;
        this.fotografia = fotografia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }
}
