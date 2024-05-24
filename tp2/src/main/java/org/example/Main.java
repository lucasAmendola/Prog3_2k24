package org.example;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Recursividad re = new Recursividad();

        //re.convertToBinarie(37, 128, 0,"");

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(45);
        arr.add(5);
        arr.add(25);
        arr.add(103);
        arr.add(4);
        arr.add(22);
        arr.add(1);
        arr.add(67);
        arr.add(30);
        arr.add(55);

        System.out.println(arr);

        re.ordenamientoSeleccion(arr, 0);
    }
}