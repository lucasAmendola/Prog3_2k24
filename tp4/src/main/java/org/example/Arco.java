package org.example;

public class Arco<T> {

    private int verticeOrigen;
    private int verticeDestino;
    private T etiqueta;
    private int peso;

    public Arco(int verticeOrigen, int verticeDestino, T etiqueta,  int peso) {
        this.verticeOrigen = verticeOrigen;
        this.verticeDestino = verticeDestino;
        this.etiqueta = etiqueta;
        this.peso = peso;
    }

    public int getVerticeOrigen() {
        return verticeOrigen;
    }

    public int getVerticeDestino() {
        return verticeDestino;
    }

    public T getEtiqueta() {
        return etiqueta;
    }

    public int getPeso() {
        return peso;
    }

    
}
