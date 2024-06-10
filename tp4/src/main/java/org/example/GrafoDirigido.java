package org.example;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {
    private int cantArcos;

    //hash map, contiene un vertice (clave int), y un array de sus arcos
    private HashMap<Integer, ArrayList<Arco<T>>> vertexHashMap;
    private HashMap<Integer, String> visitedHashMap;

    public GrafoDirigido() {
        this.cantArcos = 0;
        this.vertexHashMap = new HashMap<Integer, ArrayList<Arco<T>>>();
        this.visitedHashMap = new HashMap<Integer, String>();
    }
    @Override
    public void agregarVertice(int verticeId) {
        ArrayList<Arco<T>> arcos = new ArrayList<>();
        this.vertexHashMap.put(verticeId, arcos);
    }

    @Override
    public void borrarVertice(int verticeId) {
        if(this.vertexHashMap.containsKey(verticeId)){
            this.vertexHashMap.remove(verticeId);
        }
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(this.vertexHashMap.containsKey(verticeId1) && this.vertexHashMap.containsKey(verticeId2)){
            Arco<T> arcoNuevo = new Arco<T>(verticeId1,verticeId2,etiqueta, verticeId2);
            this.vertexHashMap.get(verticeId1).add(arcoNuevo);
            this.cantArcos = this.cantArcos + 1;
        }
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if(vertexHashMap.containsKey(verticeId1)){
            vertexHashMap.get(verticeId1).remove(this.obtenerArco(verticeId1,verticeId2));
            cantArcos--;
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertexHashMap.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        return obtenerArco(verticeId1, verticeId2) != null;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        if(this.vertexHashMap.containsKey(verticeId1)){
            ArrayList<Arco<T>> arcos = this.vertexHashMap.get(verticeId1);
            Arco<T> arcoBuscado = new Arco<>(verticeId1,verticeId2,null, 12);
            if(arcos != null && arcos.contains(arcoBuscado)){
                return arcos.get(arcos.indexOf(arcoBuscado));
            }
            return null;
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return this.vertexHashMap.size();
    }

    @Override
    public int cantidadArcos() {
        return this.cantArcos;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        Iterator<Integer> vertices = this.vertexHashMap.keySet().iterator();
        return vertices;
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        if(this.vertexHashMap.containsKey(verticeId)){
            ArrayList<Integer> adyacentes = new ArrayList<>();
            Iterator<Arco<T>> iteratorArcos = this.obtenerArcos(verticeId);
            while (iteratorArcos.hasNext()) {
                Arco<T> arco = iteratorArcos.next();
                adyacentes.add(arco.getVerticeDestino());
            }
            return adyacentes.iterator();
        }
        return null;
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> nueva = new ArrayList<Arco<T>>();

        for (Map.Entry<Integer, ArrayList<Arco<T>>> entrada : this.vertexHashMap.entrySet()){

            for (Arco<T> arco : entrada.getValue()) {
                nueva.add(arco);
            }
        }

        return nueva.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        return this.vertexHashMap.get(verticeId).iterator();
    }

    public ArrayList<Integer> DFS(){
        ArrayList<Integer> camino = new ArrayList<>();
        Iterator<Integer> vertices = this.visitedHashMap.keySet().iterator();

        while (vertices.hasNext()){
               int vertice = vertices.next();
               visitedHashMap.put(vertice, "blanco");
        }

        vertices = this.visitedHashMap.keySet().iterator();

        while (vertices.hasNext()){
            int vertice = vertices.next();
            if(visitedHashMap.get(vertice) == "blanco"){
                DFS(vertice, camino);
            }
        }
        return camino;
    }

    private void DFS(int vertice, ArrayList<Integer> camino) {
        this.visitedHashMap.put(vertice, "amarillo");
        camino.add(vertice);
        Iterator<Integer> adyacentes = this.obtenerAdyacentes(vertice);

        while (adyacentes.hasNext()){
            int vAdyacente = adyacentes.next();
            if(this.visitedHashMap.get(vAdyacente) == "blanco"){
                DFS(vAdyacente, camino);
            }
        }
        camino.remove(camino.size()-1);
    }

    //Implemente un algoritmo que determine si un grafo dirigido tiene algún ciclo.

    public boolean tieneCiclo(){
        ArrayList<Integer> camino = new ArrayList<>();
        Iterator<Integer> vertices = this.visitedHashMap.keySet().iterator();
        Boolean ciclo = false;

        while (vertices.hasNext()){
            int vertice = vertices.next();
            visitedHashMap.put(vertice, "blanco");
        }

        vertices = this.visitedHashMap.keySet().iterator();

        while (vertices.hasNext()){
            int vertice = vertices.next();
            if(visitedHashMap.get(vertice) == "blanco"){
                 tieneCiclo(vertice, camino, ciclo);
            }
        }
        return ciclo;
    }

    private void tieneCiclo(int vertice, ArrayList<Integer> camino, Boolean ciclo) {
        this.visitedHashMap.put(vertice, "amarillo");
        camino.add(vertice);
        Iterator<Integer> adyacentes = this.obtenerAdyacentes(vertice);

        while (adyacentes.hasNext()){
            int vAdyacente = adyacentes.next();
            if(this.visitedHashMap.get(vAdyacente) == "blanco"){
                tieneCiclo(vAdyacente, camino, ciclo);
            }
            else
                ciclo = true;
                return;
        } 
        this.visitedHashMap.put(vertice, "negro");
        camino.remove(camino.size()-1);
    }

    //Escriba un algoritmo que dado un grafo G y un vértice v de dicho grafo, devuelva una lista
    //con todos los vértices a partir de los cuales exista un camino en G que termine en v

    public ArrayList<Integer> caminoExistenteEntreVertices(int v){
        ArrayList<Integer> camino = new ArrayList<>();
        ArrayList<Integer> solucion = new ArrayList<>();

        Iterator<Integer> vertices = this.obtenerVertices();

        while (vertices.hasNext()) {
              int vAct = vertices.next();
              this.visitedHashMap.put(vAct, "blanco");
        }

        vertices = this.obtenerVertices();

        while (vertices.hasNext()) {
              int vActual = vertices.next();
              if (this.visitedHashMap.get(vActual) == "blanco") {
                    caminoExistenteEntreVertices(vActual, v, camino, solucion);
              }
        }
        return solucion;
    }    

    private void caminoExistenteEntreVertices(int act, int v, ArrayList<Integer> camino, ArrayList<Integer> solucion) {
        camino.add(act);
        this.visitedHashMap.put(act, "amarillo");

        if(act == v){
            if(!solucion.contains(camino.get(0))){
                solucion.add(camino.get(0));
            }   
        }
        else{
            Iterator<Integer> adyacentes = this.obtenerAdyacentes(act);
            while (adyacentes.hasNext()) {
                    Integer adyAct = adyacentes.next();
                    if (this.visitedHashMap.get(adyAct) != "amarillo") {
                        caminoExistenteEntreVertices(adyAct, v, camino, solucion);
                    }
                }   
            }    

        visitedHashMap.put(act, "blanco"); // Desmarcar el vértice
        camino.remove(camino.size()-1);
    }

    //Escribir un algoritmo que, dado un grafo dirigido y dos vértices i, j de este grafo, devuelva el
    //camino simple (sin ciclos) de mayor longitud del vértice i al vértice j. Puede suponerse que
    //el grafo de entrada es acíclico.

    public ArrayList<Integer> caminoMasLargo(int v1, int v2){
        ArrayList<Integer> camino = new ArrayList<>();
        ArrayList<Integer> solucion = new ArrayList<>();
        caminoMasLargo(v1, v2, camino, solucion);
        return solucion;
    }

    private void caminoMasLargo(int vActual, int v2, ArrayList<Integer> caminoActual, ArrayList<Integer> caminoSolucion) {
        caminoActual.add(vActual);

        if(vActual == v2){
            if(caminoSolucion.isEmpty()){
                caminoSolucion.addAll(caminoActual);
            }
            else if(caminoActual.size() > caminoSolucion.size()){
                    caminoSolucion.clear();
                    caminoSolucion.addAll(caminoActual);
            }
        }
        else{
            Iterator<Integer> adyacentes = this.obtenerAdyacentes(vActual);
            while (adyacentes.hasNext()) {
                    Integer adyAct = adyacentes.next();
                    if(!caminoActual.contains(adyAct)){
                        caminoMasLargo(adyAct, v2, caminoActual, caminoSolucion);
                    }
                }
            }

     caminoActual.remove(caminoActual.size()-1);
}

    //Supongamos una conexión entre computadoras (1, ... ,n) que se encuentra modelada
    //mediante un grafo. Se requiere, si existe, dar una conexión entre dos computadoras a y b
    //existentes sabiendo que la computadora i está fuera de servicio.

    public ArrayList<Integer> darConexion(int a, int b, int i){
        ArrayList<Integer> camino = new ArrayList<>();
        ArrayList<Integer> solucion = new ArrayList<>();
        if(darConexion(a, b, i, camino, solucion)){
            return solucion;
        }
        return new ArrayList<>();
    }

    private Boolean darConexion(int act, int b, int i, ArrayList<Integer> camino, ArrayList<Integer> solucion) {
        camino.add(act);

        if (act == b ) {

            solucion.addAll(camino);
            return true;
        }
        else{
            Iterator<Integer> adyacentes = this.obtenerAdyacentes(act);
            while (adyacentes.hasNext()) {
                    Integer ady = adyacentes.next();
                    if(!camino.contains(ady) && ady != i){
                       if(darConexion(ady, b, i, camino, solucion)){
                            return true;
                       }
                    }
                }
        }
        camino.remove(camino.size()-1);
        return false;
    }

    //Supongamos que una ciudad se encuentra modelada mediante un grafo, donde cada nodo
    //es una esquina, y las aristas representan las calles. Diseñe un algoritmo tal que dadas dos
    //esquinas, devuelva el camino más corto entre ambas de manera de caminar la menor
    //cantidad de cuadras posible.

    /*Utilizamos BFS para encontrar el camino mas corto o mas barato entre 2 vertices*/

    public ArrayList<Integer> BFSforest(int d){
        Iterator<Integer> vertices = this.obtenerVertices();
        ArrayList<Integer> camino = new ArrayList<>();
        ArrayList<Integer> solucion = new ArrayList<>();

        while (vertices.hasNext()){
            int v = vertices.next();
            this.visitedHashMap.put(v, "blanco");
        }

        vertices = this.obtenerVertices();

        while(vertices.hasNext()){
            int v = vertices.next();
            if (this.visitedHashMap.get(v) == "blanco"){
                 BFSvisit(v,d,camino,solucion);
            }
        }
        return solucion;
    }

    private void BFSvisit(int v, int d, ArrayList<Integer> camino, ArrayList<Integer> solucion) {
        ArrayList<Integer>cola=new ArrayList<>();
        this.visitedHashMap.replace(v,"amarillo");
        cola.add(v);
        Iterator<Integer>adyacentes;

        while (cola.get(0) != d || !cola.isEmpty()){
                adyacentes = this.obtenerAdyacentes(cola.get(0));
                camino.add(cola.get(0));
                cola.remove(0);
                while (adyacentes.hasNext()){
                    int ady = adyacentes.next();
                    if (this.visitedHashMap.get(ady) == "blanco"){
                        cola.add(ady);
                        this.visitedHashMap.replace(ady, "amarillo");
                    }
                }
        }
        solucion.addAll(camino);
    }

    //Dados un grafo G con sus vértices rotulados con colores y dos vértices v1 y v2, escriba un
    //algoritmo que encuentre un camino desde el vértice v1 al vértice v2 tal que no pase por
    //vértices rotulados con el color rojo.

    public ArrayList<Integer> encontrarCamino(int v1, int v2){
        ArrayList<Integer>camino = new ArrayList<>();
        ArrayList<Integer>solucion = new ArrayList<>();
        if(encontrarCamino(v1, v2, camino, solucion)){
            return solucion;
        }
        return new ArrayList<>();
    }

    private Boolean encontrarCamino(int act, int v2, ArrayList<Integer> camino, ArrayList<Integer> solucion) {
        camino.add(act);

        if(act == v2){
            solucion.addAll(camino);
            return true;
        }
        else{
            Iterator<Integer> adyacentes = this.obtenerAdyacentes(act);
            while (adyacentes.hasNext()) {
                    Integer adyAct = adyacentes.next();
                    if(!camino.contains(adyAct) && this.visitedHashMap.get(adyAct) != "rojo"){
                        if (encontrarCamino(adyAct, v2, camino, solucion)) {
                            return true;
                        }
                    }
                }
                
           }
           camino.remove(camino.size()-1);
        return false;
    }

    //Dado un grafo no orientado que modela las rutas de la provincia de Buenos Aires, devolver
    //todos los caminos alternativos que se pueden tomar para ir desde la ciudad de Buenos Aires
    //a la ciudad de Tandil, considerando que en el tramo Las Flores-Rauch está cortado al tránsito.

    public ArrayList<ArrayList<Integer>> caminosBairesTandil(Integer baires, Integer tandil){
        ArrayList<Integer>camino = new ArrayList<>();
        ArrayList<ArrayList<Integer>> caminosSolucion = new ArrayList<>();
        caminosBairesTandil(baires, tandil, camino, caminosSolucion);
        return caminosSolucion;
    }

    private void caminosBairesTandil(Integer origen, Integer tandil,
                                     ArrayList<Integer> camino, ArrayList<ArrayList<Integer>> caminosSolucion) {
        camino.add(origen);

        if (origen.equals(tandil)) {
            ArrayList<Integer> caminoCopia = new ArrayList<>(camino);
            caminosSolucion.add(caminoCopia);
        }
        else{
            Iterator<Integer> ciudadesAdyacentes = this.obtenerAdyacentes(origen);
            while (ciudadesAdyacentes.hasNext()) {
                Integer ciudadActual = ciudadesAdyacentes.next();
                if(!camino.contains(ciudadActual)) {
                    if (this.visitedHashMap.get(origen).equals("Las Flores") &&
                            this.visitedHashMap.get(ciudadActual).equals("Rauch")) {
                            continue;
                    }
                    caminosBairesTandil(ciudadActual, tandil, camino, caminosSolucion);
                }
            }
        }
        camino.remove(camino.size()-1);
    }

    /*Se dispone de un conjunto de tareas, donde cada tarea tiene un nombre, una descripción y
    una duración (medida en horas). Se sabe también que hay una dependencia en el orden
    posible en el cual se pueden ejecutar estas tareas y un tiempo de espera entre dos tareas
    consecutivas (también medido en horas). Por ejemplo, si la tarea B depende de la tarea A y
    tiene un tiempo de espera de 5 horas; significa que:
    ● B no puede ejecutarse antes que A y,
    ● B debe ejecutarse 5 horas después de haber finalizado la ejecución de A.
    Objetivo
    Implementar un algoritmo que obtenga la secuencia de ejecución crítica de estas tareas, es
    decir, la secuencia de tareas que resulta en el máximo tiempo empleado para su ejecución.
    Por ejemplo: si partimos de la siguiente configuración podemos encontrar el camino crítico en
    la secuencia de tareas [0, 2, 5, 6, 10], ya que su tiempo de ejecución es la duración de cada
    tarea más el tiempo de espera entre cada par de tareas: 70 horas. */

    public ArrayList<Integer> secuenciaEjecucionTareas(GrafoDirigido<T> tareas){
        ArrayList<Integer> secuenciaFinal = new ArrayList<Integer>();
        ArrayList<Integer> secuenciaPosible = new ArrayList<Integer>();
       
        Iterator<Integer> verticesTareas = tareas.obtenerVertices();

        while (verticesTareas.hasNext()) {
                int v = verticesTareas.next();
                this.visitedHashMap.put(v, "white");
        }

        verticesTareas = tareas.obtenerVertices();

        while (verticesTareas.hasNext()) {
                int v = verticesTareas.next();
                if(this.visitedHashMap.get(v).equals("white")){
                    secuenciaEjecucionTareas(v, tareas, secuenciaFinal, secuenciaPosible);
                }
        }

        return secuenciaFinal;
    }
    private void secuenciaEjecucionTareas(int t, GrafoDirigido tareas, ArrayList<Integer> secuenciaFinal, ArrayList<Integer> secuenciaPosible) {
        secuenciaPosible.add(t);
        this.visitedHashMap.put(t, "yellow");
        Iterator<Integer> tareasDependientes = tareas.obtenerAdyacentes(t);

        if(!tareasDependientes.hasNext()){
            if(secuenciaFinal.isEmpty()){
                secuenciaFinal.addAll(secuenciaPosible);
            }
            else{
                int tiempoSecPos = tiempoEjecucionTotal(secuenciaPosible);
                int tiempoSecFin = tiempoEjecucionTotal(secuenciaFinal);
                if(tiempoSecPos > tiempoSecFin){
                    secuenciaFinal.clear();
                    secuenciaFinal.addAll(secuenciaPosible);
                }
            }
        }
        else{
            while (tareasDependientes.hasNext()) {
                    int tDependiente = tareasDependientes.next();
                    if(this.visitedHashMap.get(tDependiente).equals("white")){
                        secuenciaEjecucionTareas(tDependiente, tareas, secuenciaFinal, secuenciaPosible);
                    }
            }   
        }
        this.visitedHashMap.put(t, "black");
        secuenciaPosible.remove(secuenciaPosible.size()-1);
    }

    private int tiempoEjecucionTotal(ArrayList<Integer> secuenciaPosible) {
        int tiempoEjecucion = 0;
        for (Integer v : secuenciaPosible) {
            tiempoEjecucion += secuenciaPosible.get(v); //el "id" de la tarea equivale a su tiempo de ejecucion
             ArrayList<Arco<T>> arcosSecuencia = this.vertexHashMap.get(v);
             for(Arco<T> a : arcosSecuencia){
                if(a.getVerticeDestino() == secuenciaPosible.get(v + 1)){
                    tiempoEjecucion += a.getPeso();
                    break;
                }
             }
        }
        return tiempoEjecucion;
    }
}