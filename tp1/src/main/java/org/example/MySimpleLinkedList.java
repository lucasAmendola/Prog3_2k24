package org.example;

import java.util.Iterator;

public class MySimpleLinkedList<T>{

    private Node<T> first;
    private int size;

    public MySimpleLinkedList() {
        this.first = null;
        this.size = 0;
    }

    public void insertFront(T info) {
        Node<T> tmp = new Node<T>(info,null);
        tmp.setNext(this.first);
        this.first = tmp;
        size++;
    }

    public T extractFront() {
        if(this.first != null){
            T node = this.first.getInfo();
            this.first = this.first.getNext();
            this.size = size-1;
            return node;
        }
        else
            return null;
    }

    public boolean isEmpty() {
        if(this.first == null){
            return true;
        }
        else
            return false;
    }

    public T get(int index) {
        int contador = 0;
        if(index < size){
            Node <T> firstNode = this.first;
            T node = firstNode.getInfo();
            while (contador<index){
                    node = firstNode.getNext().getInfo();
                    contador++;
            }
            return node;
        }
        else
           return null;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        // TODO
        return "";
    }

    //que reciba un elemento y retorne el índice donde está almacenado ese
    //elemento, o -1 si el elemento no existe en la lista.
    public int indexOf(T node){
        Node <T> actualNode = this.first;
        int cont = 1;
        if(!node.equals(actualNode.getInfo())) {
            while(cont < size) {
                Node <T> firstNode = this.first.getNext();
                if(node.equals(firstNode.getInfo())){
                    return cont;
                }
                cont++;
            }
            return -1;
        }
        else {
            return 0;
        }
    }

    public MyIterator getIterador() {
        return new MyIterator<>(this.first);
    }

    public int getSize() {
        return size;
    }

    public Node<T> getFirst() {
        return first;
    }

    //Escriba un procedimiento que dadas dos listas construya otra con los elementos comunes,
    //suponiendo que: a) Las listas están desordenadas y la lista resultante debe quedar ordenada. b)
    //Las listas están ordenadas y la lista resultante debe mantenerse ordenada.
    public MySimpleLinkedList<T> commosItemsList(MySimpleLinkedList list1, MySimpleLinkedList list2){
        MySimpleLinkedList<T> commonsElements = new MySimpleLinkedList<>();
        int size= list1.getSize();
        int cont = 0;
        Node<T> l1ActualElm = list1.getFirst();
        Node<T> l2ActualElm = list2.getFirst();
       while(cont < size){
            if(l1ActualElm.getInfo().equals(l2ActualElm.getInfo())){
                commonsElements.insertFront(l1ActualElm.getInfo());
            }
            l1ActualElm = l1ActualElm.getNext();
            l2ActualElm = l2ActualElm.getNext();
            cont++;
        }
       return commonsElements;
    }

    //Escriba una función que dadas dos listas construya otra con los elementos que están en la
    //primera pero no en la segunda.
    public MySimpleLinkedList<T> diferentItemsList(MySimpleLinkedList list1, MySimpleLinkedList list2){
        MySimpleLinkedList<T> difElements = new MySimpleLinkedList<>();
        int size1= list1.getSize();
        int size2= list2.getSize();
        int cont1 = 0;
        int cont2 = 0;
        Node<T> l1ActualElm = list1.getFirst();
        Node<T> l2ActualElm = list2.getFirst();
        int coincidencia = 0;
        while(cont1 < size1){
            while (cont2 < size2){
                    if(l1ActualElm.getInfo().equals(l2ActualElm.getInfo())){
                        coincidencia++;
                    }
                    l2ActualElm = l2ActualElm.getNext();
                    cont2++;
            }
            if(coincidencia == 0){
                difElements.insertFront(l1ActualElm.getInfo());
            }
            coincidencia = 0;
            l1ActualElm = l1ActualElm.getNext();
            cont1++;
        }
        return difElements;
    }

}
