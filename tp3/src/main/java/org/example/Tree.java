package org.example;

import java.util.ArrayList;

public class Tree {

    private TreeNode root;
    private Tree left;
    private Tree right;

    public Tree() {
        this.root = null;
        this.left = null;
        this.right = null;
    }

    public void add(Integer value) {
        if (this.root == null)
            this.root = new TreeNode(value);
        else
            this.add(this.root,value);
    }

    private void add(TreeNode actual, Integer value) {
        if (actual.getValue() > value) {
            if (actual.getLeft() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setLeft(temp);
            } else {
                add(actual.getLeft(),value);
            }
        } else if (actual.getValue() < value) {
            if (actual.getRight() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setRight(temp);
            } else {
                add(actual.getRight(),value);
            }
        }
    }

    public TreeNode getRoot() {
        return root;
    }

   public boolean hasElem(Integer e, TreeNode n){
        if(n.getValue().equals(e)){
            return true;
        }
        if(e < n.getValue()){
            if(n.getLeft() != null){
                return hasElem(e, n.getLeft());
            }
            else
                return false;
        }
        if(e > n.getValue()){
            if(n.getRight() != null){
                return hasElem(e, n.getRight());
            }
            else
                return false;
        }
       return false;
   }

   public boolean isEmpty(){
        if(this.getRoot() == null){
            return true;
        }
        else
            return false;
   }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0; // Altura de un árbol vacío es 0
        } else {
            // Calcula la altura de los subárboles izquierdo y derecho
            int leftHeight = getHeight(root.getLeft());
            int rightHeight = getHeight(root.getRight());

            // Retorna la altura máxima entre los subárboles izquierdo y derecho más uno (para el nodo actual)
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }

    public ArrayList<TreeNode> longestBranch(){
        ArrayList<TreeNode> actual = new ArrayList<>();
        ArrayList<TreeNode> solucion = new ArrayList<>();

        return longestBranch(actual, solucion, this.root);
    }

    private ArrayList<TreeNode> longestBranch(ArrayList<TreeNode> actual, ArrayList<TreeNode> solucion, TreeNode node) {
        if(node != null) {
            actual.add(node);
            if (node.getLeft() != null) {
                longestBranch(actual, solucion, node.getLeft());
            }
            if (node.getRight() != null) {
                longestBranch(actual, solucion, node.getRight());
            }

            if (actual.size() > solucion.size()) {
                solucion.clear();
                solucion.addAll(actual);
            }
            actual.remove(actual.size() - 1);
        }
        return solucion;
    }

    public ArrayList<TreeNode> getFrontera(){
        ArrayList<TreeNode> solucion = new ArrayList<>();

        return getFrontera(solucion, this.root);
    }

    private ArrayList<TreeNode> getFrontera(ArrayList<TreeNode> solucion, TreeNode node) {
        if (node != null) {
            if(node.getLeft() == null && node.getRight() == null) {
                solucion.add(node);
            }
            if (node.getLeft() != null) {
                getFrontera(solucion, node.getLeft());
            }
            if (node.getRight() != null) {
                getFrontera(solucion, node.getRight());
            }
        }
        return solucion;
    }

    //Dado un árbol binario de búsquedas que almacena números enteros, implementar un algoritmo
    //que retorne la suma de todos los nodos internos del árbol.

    public int sumaInternos(){
        int suma = 0;
        return sumaInternos(suma, this.root);
    }

    private int sumaInternos(int suma, TreeNode node) {
        if((node.getRight() == null && node.getLeft() == null) || node == null  ){
            return 0;
        }
        suma += node.getValue();
        if(node.getLeft() != null){
            suma += sumaInternos(suma, node.getLeft());
        }
        if(node.getRight() != null){
            suma += sumaInternos(suma, node.getRight());
        }
        return suma;
    }

    //Dado un árbol binario de búsqueda que almacena
    //números enteros y un valor de entrada K, implementar un
    //algoritmo que permita obtener un listado con los valores
    //de todas las hojas cuyo valor supere K. Por ejemplo, para
    //el árbol de la derecha, con un valor K = 8, el resultado
    //debería ser [9, 11]

    public ArrayList<Integer> nodosQueSuperanK(int k){
        ArrayList<Integer> valoresMayores = new ArrayList<>();
        return nodosQueSuperanK(valoresMayores, k, this.root);
    }

    private ArrayList<Integer> nodosQueSuperanK(ArrayList<Integer> valoresMayores, int k, TreeNode node) {
        if(node.getLeft() == null && node.getRight() == null){
            if(node.getValue() > k){
                valoresMayores.add(node.getValue());
            }
        }
        if(node.getLeft() != null){
            nodosQueSuperanK(valoresMayores, k, node.getLeft());
        }
        if(node.getRight() != null){
            nodosQueSuperanK(valoresMayores, k, node.getRight());
        }

        return valoresMayores;
    }

    //Se posee un árbol binario (no de búsqueda), donde los nodos internos están vacíos, mientras
    //que las hojas tienen valores enteros. Se debe implementar un método que recorra el árbol y
    //coloque valores en los nodos vacíos (los nodos internos). El valor de cada nodo interno debe
    //ser igual al valor de su hijo derecho, menos el valor de su hijo izquierdo. En caso de que el
    //nodo tenga un solo hijo, el valor del hijo faltante se reemplaza por un 0.



    //Dado un árbol binario donde todos los nodos poseen un carácter, de manera que cada rama del
    //árbol contiene una palabra, implementar un algoritmo que busque y retorne todas las palabras
    //que posea exactamente N vocales (ni más ni menos). Por ejemplo, para el siguiente árbol, con
    //una entrada de N = 1, el algoritmo debería retornar [“MAL”]. En cambio, para un N = 2, debería
    //retornar [“MANA”, “MANO”, “MISA”].

    public ArrayList<String> retornarPalabras(int vocales){
        ArrayList<String> palCumplen = new ArrayList<>();
        String palabra = "";
        int contador = 0;
        return retornarPalabras(palabra, contador, vocales, palCumplen, this.root);
    }

    private ArrayList<String> retornarPalabras(String palabra, int contador, int vocales, ArrayList<String> palCumplen, TreeNode node) {
        if(node == null){
            return null;
        }
        palabra += node.getValue().toString();
        if(node.getValue() == 1 || node.getValue() == 2 || node.getValue() == 3 ||
                node.getValue() == 4 || node.getValue() == 5){
            contador++;
        }
        if(node.getLeft() != null){
            retornarPalabras(palabra, contador,vocales,palCumplen,node.getLeft());
        }
        if(node.getRight() != null){
            retornarPalabras(palabra, contador,vocales,palCumplen,node.getRight());
        }
        if(contador == vocales){
            palCumplen.add(palabra);
        }
        return palCumplen;
    }
}