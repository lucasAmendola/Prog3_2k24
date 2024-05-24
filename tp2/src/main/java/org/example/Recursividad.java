package org.example;

import java.util.ArrayList;

public class Recursividad {

    public boolean estaOrdenado(ArrayList<Integer> arr, int pos) {
        if (pos < arr.size() - 1) {
            int p1 = arr.get(pos);
            int p2 = arr.get(pos + 1);
            if (p1 <= p2) {
                return estaOrdenado(arr, pos + 1);
            } else {
                return false;
            }
        }
        return true;
    }

    //Implemente un algoritmo recursivo para buscar un elemento en un arreglo ordenado
    //ascendentemente.
    public int buscarElementoEnAscendente(ArrayList<Integer> arr, int x, int ini, int fin) {
        if (ini <= fin) {
            int midle = (ini + fin) / 2;
            if (x == arr.get(midle)) {
                return midle; //devuelve la pos en el arreglo donde se encuentra el elemento
            }
            if (x < arr.get(midle)) {//si el elemento es menor al medio, el nuevo fin es el medio, inicio se mantiene igual
                return buscarElementoEnAscendente(arr, x, ini, midle - 1);
            } else {//si el elemento es mayor al medio, el nuevo ini es el medio, fin se mantiene igual
                return buscarElementoEnAscendente(arr, x, midle + 1, fin);
            }
        }
        return -1;
    }

    //Implemente un algoritmo recursivo que convierta un número en notación decimal a su
    //equivalente en notación binaria. 128 - 64 - 32 - 16 - 8 - 4 - 2 - 1

    public void convertToBinarie(int x, int binarie,int sum, String convertion){
        if(binarie < 1){
            System.out.println(convertion);
            return;
        }
        if(binarie > x){
            convertion += "0";
            convertToBinarie(x, binarie/2, sum, convertion);
        }
        else {
            if((sum + binarie) <= x){
                sum+=binarie;
                convertion += "1";
                if(binarie == 1){
                    convertToBinarie(x, 0, sum, convertion);
                }
                else {
                    convertToBinarie(x, binarie/2, sum, convertion);
                }
            }
            else
                convertion += "0";
                convertToBinarie(x, binarie/2, sum, convertion);
        }
    }

    //Dado un arreglo ordenado de números distintos A se desea construir un algoritmo que
    //determine si alguno de los elementos de dicho arreglo contiene un valor igual a la posición en la
    //cuál se encuentra, es decir, A[i] = i.

    public boolean existeElemento(ArrayList<Integer> arr, int pos){
        if(pos == arr.get(pos)){
            return true;
        }
        if(pos  < arr.size()-1){
            return existeElemento(arr, pos +1);
        }
        return false;
    }

    //Implemente un algoritmo de ordenamiento por selección en un arreglo.

    public void ordenamientoSeleccion(ArrayList<Integer> arr, int pos){
        if(pos <= arr.size()){
            for(int i = pos+1; i<= arr.size()-1; i++){
                 int menor = arr.get(pos);
                 int menorActual = arr.get(i);
                 if(menorActual < menor){
                     int aux = menor;
                     menor = menorActual;
                     arr.add(pos, menor);
                     arr.add(i, aux);
                 }
                ordenamientoSeleccion(arr, pos+1);
            }
        }
        System.out.println(arr);
    }
}
