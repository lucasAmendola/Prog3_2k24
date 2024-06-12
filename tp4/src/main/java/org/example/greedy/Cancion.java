package org.example.greedy;

public class Cancion {
    private String nombre;
    private String genero;
    private Integer duracion;
    private Integer kilobytes;

    public Cancion(String nombre, String genero, Integer duracion, Integer kilobytes) {
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.kilobytes= kilobytes;
    }

    public String getGenero() {
        return genero;
    }
    public Integer getDuracion() {
        return duracion;
    }

    public Integer getKilobytes() {
        return kilobytes;
    }



    public String getNombre() {
        return nombre;
    }
    
}
