package org.example.backtracking;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.example.GrafoDirigido;
import org.example.Procesador;
import org.example.Tarea;

public class Backtracking {
    
    Estado estado;
    private GrafoDirigido grafo;
    private ArrayList<Casilla> caminoFinalTablero;
    private HashMap<Casilla, Boolean> casillasVisitadas;
    private Integer costoCamino;
    private ArrayList<Tarea> tareas;
    private ArrayList<Procesador> procedores;
    private HashMap<Procesador, Integer> tiempoProcesadoFinal;
    private HashMap<Procesador, ArrayList<Tarea>> asignacionFinal;
    private ArrayList<Casilla> caminoFinalRobot;

    public Backtracking(GrafoDirigido grafo) {
        this.caminoFinalRobot = new ArrayList<>();
        this.estado =  new Estado();
        this.grafo = grafo;
        this.caminoFinalTablero = new ArrayList<>();
        this.casillasVisitadas = new HashMap<>();
        this.tareas = new ArrayList<>();
        this.tiempoProcesadoFinal = new HashMap<>();
        this.asignacionFinal = new HashMap<>();
        this.procedores = new ArrayList<>();
    }

/*Ejercicio 1

Se tiene un conjunto de salas comunicadas entre sí a través de puertas que se abren solamente en
un sentido. Una de las salas se denomina entrada y la otra salida. Construir un algoritmo que
permita ir desde la entrada a la salida atravesando la máxima cantidad de salas. Idea: podría
representar el problema mediante un grafo dirigido, donde cada nodo es una habitación, y cada
puerta es un arco dirigido hacia otra habitación.
 */

 public ArrayList<Integer> maximaCantidadDeSalasEntreEntradaYSalida(Integer entrada, Integer salida){
    ArrayList<Integer> caminoActual = new ArrayList<>();
    ArrayList<Integer> solucionMasLarga = new ArrayList<>();
    maximaCantidadDeSalasEntreEntradaYSalida(entrada, salida, caminoActual, solucionMasLarga);
    return solucionMasLarga;
 }

 private void maximaCantidadDeSalasEntreEntradaYSalida(int salaActual, int salida, ArrayList<Integer> caminoActual, ArrayList<Integer> solucionMasLarga){
    caminoActual.add(salaActual);

    if (salaActual == salida) {
        if (solucionMasLarga.isEmpty()) {
            solucionMasLarga.addAll(caminoActual);
        }
        else{
            if (caminoActual.size() > solucionMasLarga.size()) {
                solucionMasLarga.clear();
                solucionMasLarga.addAll(caminoActual);
            }
        }
    }
    else{
       Iterator<Integer> salasContiguas = this.grafo.obtenerAdyacentes(salaActual);
       while (salasContiguas.hasNext()) {
            int salaContiguaActual = salasContiguas.next();
            if(!caminoActual.contains(salaContiguaActual)){
                maximaCantidadDeSalasEntreEntradaYSalida(salaContiguaActual, salida, caminoActual, solucionMasLarga);
            }
       }

    }
    caminoActual.remove(caminoActual.size()-1);
 }


/*Ejercicio 2

Dado un laberinto consistente en una matriz cuadrada que tiene en cada posición un valor natural y
cuatro valores booleanos, indicando estos últimos si desde esa casilla se puede ir al norte, este, sur
y oeste, encontrar un camino de longitud mínima entre dos casillas dadas, siendo la longitud de un
camino la suma de los valores naturales de las casillas por las que pasa. Idea: podría representarse
el laberinto como una matriz, de objetos, donde cada objeto tiene el valor natural, y cuatro
booleanos, uno para cada dirección a la que se permite ir desde allí.
 */

public ArrayList<Casilla> getCaminoDeLongitudMinima(Casilla destino, Casilla origen){
    ArrayList<Casilla> caminoActual = new ArrayList<>();
    caminoActual.add(origen);
    this.costoCamino = origen.getValor();
    this.casillasVisitadas.put(origen, true);
    this.getCaminoDeLongitudMinima(destino, origen,caminoActual);
    return this.caminoFinalTablero;
}

 private void getCaminoDeLongitudMinima(Casilla destino, Casilla origen, ArrayList<Casilla> caminoActual) {
    if(origen.equals(destino)){
        if (this.caminoFinalTablero.isEmpty()) {
            this.caminoFinalTablero.addAll(caminoActual);
        }
        else{
            this.escogerCaminoMasBarato(caminoActual, this.caminoFinalTablero);
        }
    }
    else{
        ArrayList<Casilla> casillasVecinasOrigenActual = origen.getVecinos();
        for (Casilla casillaVecina : casillasVecinasOrigenActual) {
            if (!this.casillasVisitadas.get(casillaVecina)) {
                    caminoActual.add(casillaVecina);
                    this.casillasVisitadas.put(casillaVecina, true);
                    this.costoCamino += casillaVecina.getValor();

                    this.getCaminoDeLongitudMinima(destino, casillaVecina, caminoActual);

                    caminoActual.remove(casillaVecina);
                    this.casillasVisitadas.put(casillaVecina, false);
                    this.costoCamino -= casillaVecina.getValor();
            }
        }
    }
}

//Devuelve el camino mas barato entre la solucion actual y la final de ese momento
private void escogerCaminoMasBarato(ArrayList<Casilla> caminoActual, ArrayList<Casilla> caminoFinalTablero2) {
    throw new UnsupportedOperationException("Unimplemented method 'escogerCaminoMasBarato'");
}

/*Ejercicio 3

Suma de subconjuntos. Dados n números positivos distintos, se desea encontrar todas las
combinaciones de esos números tal que la suma sea igual a M.
*/

public ArrayList<ArrayList<Integer>> combinacionesPosibleQueSumenM(int m, ArrayList<Integer> candidatos){
    ArrayList<Integer> posibleCombinacion = new ArrayList<>();
    ArrayList<ArrayList<Integer>> combinacionesExitosas = new ArrayList<>();
    int posActualEnCandidatos = 0;
    combinacionesPosibleQueSumenM(m, candidatos, posibleCombinacion, combinacionesExitosas, posActualEnCandidatos);
    return combinacionesExitosas;
 }

private void combinacionesPosibleQueSumenM(int m, ArrayList<Integer> candidatos, ArrayList<Integer> posibleCombinacion,
        ArrayList<ArrayList<Integer>> combinacionesExitosas, int posCandidatos) {

    posibleCombinacion.add(candidatos.get(posCandidatos));
    
    if (sumarSubconjunto(posibleCombinacion) == m) {
        combinacionesExitosas.add(posibleCombinacion);
    }   
    else{
        while (posCandidatos < candidatos.size()) {
            int numero = candidatos.get(posCandidatos);
            if((sumarSubconjunto(posibleCombinacion) + numero) <= m){
                posCandidatos++;
                combinacionesPosibleQueSumenM(m, candidatos, posibleCombinacion, combinacionesExitosas, posCandidatos);
                posCandidatos--;
            }
        }
    }
    posibleCombinacion.remove(posibleCombinacion.size()-1);
}

private int sumarSubconjunto(ArrayList<Integer> posibleCombinacion) {
    int suma = 0;
    for (Integer integer : posibleCombinacion) {
         suma += integer;
    }
    return suma;
 } 

 /*Ejercicio 4

 Partición de conjunto. Dado un conjunto de n enteros se desea encontrar, si existe, una partición en
 dos subconjuntos disjuntos, tal que la suma de sus elementos sea la misma. */

 public ArrayList<ArrayList<Integer>> subconjuntosDisjuntosQueSumanIgual(ArrayList<Integer> conjunto){
    ArrayList<Integer> subconjunto1 = new ArrayList<>();
    ArrayList<Integer> subconjunto2 = new ArrayList<>();
    int sumaSub1 = 0;
    int sumaSub2 = 0;
    int posEnConjunto = 0;
    ArrayList<ArrayList<Integer>> combinacionesExitosas = new ArrayList<>();
    if(subconjuntosDisjuntosQueSumanIgual(conjunto, sumaSub1, sumaSub2, subconjunto1, subconjunto2, combinacionesExitosas, posEnConjunto)){
        return combinacionesExitosas;
    }
    
    return new ArrayList<>();
 }

private Boolean subconjuntosDisjuntosQueSumanIgual(ArrayList<Integer> conjunto, int sumaSub1,
        int sumaSub2, ArrayList<Integer> subconjunto1, ArrayList<Integer> subconjunto2,
        ArrayList<ArrayList<Integer>> combinacionesExitosas, int posEnConjunto) {
    
        if (posEnConjunto == conjunto.size()) {
            if(sumaSub1 == sumaSub2){
                combinacionesExitosas.add(subconjunto1);
                combinacionesExitosas.add(subconjunto2);
                return true;
            }
            return false;
        }

        int numero = conjunto.get(posEnConjunto);
        subconjunto1.add(numero);
        if(subconjuntosDisjuntosQueSumanIgual(conjunto, sumaSub1 + numero, sumaSub2, subconjunto1, subconjunto2, combinacionesExitosas, posEnConjunto)){
                return true;
        }
        subconjunto1.remove(subconjunto1.size()-1);

        subconjunto2.add(numero);
        if(subconjuntosDisjuntosQueSumanIgual(conjunto, sumaSub1, sumaSub2 + numero, subconjunto1, subconjunto2, combinacionesExitosas, posEnConjunto)){
                return true;
        }
        subconjunto2.remove(subconjunto1.size()-1);

        return false;
}

private int sumarSubconjunto(int sumaSub1) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sumarSubconjunto'");
}

/*Ejercicio 5

Asignación de tareas a procesadores. Se tienen m procesadores idénticos y n tareas con un tiempo
de ejecución dado. Se requiere encontrar una asignación de tareas a procesadores de manera de
minimizar el tiempo de ejecución del total de tareas.
 */

 public HashMap<Procesador, ArrayList<Tarea>> asignarTareasAProcesadores(){
    HashMap<Procesador, ArrayList<Tarea>> asignacionActual = new HashMap<>();
    HashMap<Procesador, Integer> tiempoProcesadoActual = new HashMap<>();
    int indiceTareas = 0;

    for(Procesador p : this.procedores){
        asignacionActual.put(p, new ArrayList<>());
        tiempoProcesadoActual.put(p, 0);
    }

    this.asignarTareasAProcesadores(asignacionActual, tiempoProcesadoActual, indiceTareas);
    return this.asignacionFinal;
 }

private void asignarTareasAProcesadores(HashMap<Procesador, ArrayList<Tarea>> asignacionActual, HashMap<Procesador, Integer> tiempoProcesadoActual, int indiceTareas) {
    if (indiceTareas == this.tareas.size()) {
        if (this.asignacionFinal.isEmpty()) {
            this.tiempoProcesadoFinal.putAll(tiempoProcesadoActual);
            this.asignacionFinal.putAll(asignacionActual);
        }
        else{
            this.quedarseConMejorTiempo(asignacionActual, tiempoProcesadoActual, this.tiempoProcesadoFinal);
        }
    }
    else{
        
        Tarea tareaActual = this.tareas.get(indiceTareas);

        for (Procesador p : this.procedores) {

            Integer tiempoTareaActual = tareaActual.getTiempoEjecucion();
            Integer tiempoAcumuladoProcesador = tiempoProcesadoActual.get(p);
            Integer tiempoNuevo = tiempoTareaActual + tiempoAcumuladoProcesador;

            tiempoProcesadoActual.put(p, tiempoNuevo);
            asignacionActual.get(p).add(tareaActual);

            asignarTareasAProcesadores(asignacionActual, tiempoProcesadoActual, indiceTareas+1);

            tiempoProcesadoActual.put(p, tiempoAcumuladoProcesador);
            asignacionActual.get(p).remove(tareaActual);
        }
    }
}

private void quedarseConMejorTiempo(HashMap<Procesador, ArrayList<Tarea>> asignacionActual, HashMap<Procesador, Integer> tiempoProcesadoActual,
        HashMap<Procesador, Integer> tiempoProcesadoFinal2) {
            
        int tiempoActual = 0;
        int tiempoFinal = 0;

        for(Procesador p : tiempoProcesadoActual.keySet()){
            tiempoActual += tiempoProcesadoActual.get(p);
        }

        for(Procesador p : tiempoProcesadoFinal2.keySet()){
            tiempoFinal += tiempoProcesadoFinal2.get(p);
        }

        if(tiempoActual < tiempoFinal){
            this.asignacionFinal.clear();
            this.tiempoProcesadoFinal.clear();
            this.asignacionFinal.putAll(asignacionActual);
            this.tiempoProcesadoFinal.putAll(tiempoProcesadoActual);
        }
    }

/*Ejercicio 6

Caballo de Atila. Por donde pisa el caballo de Atila jamás vuelve a crecer el pasto. El caballo fue
directamente hacia el jardín de n x n casillas. Empezó su paseo por una casilla cualquiera y volvió a
ella, es decir hizo un recorrido cerrado. No visitó dos veces una misma casilla, se movió de una
casilla a otra vecina en forma horizontal o vertical, pero nunca en diagonal. Por donde pisó el
caballo, el pasto jamás volvió a crecer. Luego de terminado el recorrido en algunas casillas todavía
había pasto (señal de que en ellas no había estado el caballo). Escriba un algoritmo que deduzca el
recorrido completo que hizo el caballo */

public ArrayList<ArrayList<Casilla>> caminoCaballoAtila(){
    ArrayList<Casilla> caminoPosible = new ArrayList<>();
    ArrayList<ArrayList<Casilla>> caminoSolucion = new ArrayList<>();
    Casilla origen = estado.traerCasillaDelBorde();//metodo que devuelve una casilla que se encuentre en el borde del tablero
    caminoCaballoAtila(origen, caminoPosible, caminoSolucion);
    return caminoSolucion;
}

private void caminoCaballoAtila(Casilla origen, ArrayList<Casilla> caminoPosible, ArrayList<ArrayList<Casilla>> caminoSolucion) {
    if(this.estado.caminoContieneOrigen(caminoPosible)){//metodo que devuelve si mi camino contiene el origen como ultima casilla pisada
        caminoSolucion.add(caminoPosible);
    }
    else{
        ArrayList<Casilla> casillasPosibles = origen.getVecinos();//metodo que devuelve a que casilla Vertical u Horizontal puede dirigirse el caballo
        for(Casilla c : casillasPosibles){
            if(!caminoPosible.contains(c)){
                caminoPosible.add(c);
                caminoCaballoAtila(c, caminoPosible, caminoSolucion);
                caminoPosible.remove(c); 
            }
        }
    }
}

/*Ejercicio 7

Tablero mágico. Dado un tablero de tamaño n x n, construir un algoritmo que ubique (si es posible)
n*n números naturales diferentes, entre 1 y un cierto k (con k>n*n), de manera tal que la suma de las
columnas y de las filas sea igual a S.
 */

public Boolean ubicarNumeros(int k, int s, ArrayList<Integer> numeros){
    int indiceNumeros = 0;
    return ubicarNumeros(k, s, numeros, this.estado, indiceNumeros);
}

private Boolean ubicarNumeros(int k, int s, ArrayList<Integer> numeros, Estado e, int indiceNumeros){
    if((indiceNumeros == numeros.size())){
        if(e.tableroCompleto() && e.tableroCumple(numeros)){//filas y columnas suman s
            return true;//solucion encontrada
        }
        return false;//no se encontro solucion
    }
    else{
        Integer n = numeros.get(indiceNumeros);
        ArrayList<Casilla> casillasdDisponibles = e.getCasillasDisponiblesEnTablero();
        for (Casilla c : casillasdDisponibles) {
                e.agregarElementoAlTablero(n, c);
                if (ubicarNumeros(k, s, numeros, e, indiceNumeros+1)) {
                    return true;//solucion encontrada
                }
                e.removerElementoDelTablero(c, n);
            }               
        }    
        return false;//no se encontro solucion
}

/*Ejercicio 8

Colocar un entero positivo (menor que un cierto valor entero k dado) en cada casilla de una
pirámide de base B (valor entero dado) de modo que cada número sea igual a la suma de las
casillas sobre las que está apoyado. Los números de todas las casillas deben ser diferentes */
   
public Estado completarPiramide(ArrayList<Integer> numeros, int k){
    Estado e = new Estado();
    completarPiramide(numeros, e, k);
    return e;
}

private void completarPiramide(ArrayList<Integer> numeros, Estado e, int k) {
    if(e.piramideEstaCompleta()){//metodo que devuelve si ya complete mi piramide al 100%
        if(e.piramideCumple(e.getPiramideActual())){
            e.agregarPiramideASolucion(e.getPiramideActual());
        }
    }
    else{
        for (Integer n : numeros) {
            if(n < k){
                numeros.remove(numeros.indexOf(n));
                e.addEnPiramide(n);
                if(e.sumaHijos(n)){//suma los numeros en donde esta parado, sin tener en cuenta los de la base (si no tiene hijos devuelve true)
                    completarPiramide(numeros, e, k);
                }                
                e.removerDePiramide(n);
                numeros.add(numeros.indexOf(n));
            }

        }
    }
}

/*Ejercicio 9

Dado un tablero de 4 x 4, en cuyas casillas se encuentran desordenados los números enteros del 1
al 15 y una casilla desocupada en una posición inicial dada, determinar una secuencia de pasos tal
intercambiando números contiguos (en horizontal y en vertical) con la casilla desocupada, los
números en el tablero queden ordenados (como muestra la figura) y la casilla desocupada quede en
la posición 4,4.
 */

public ArrayList<String> determinarPasos(ArrayList<Integer> numeros){
    Estado e = new Estado();
    ArrayList<String> pasos = new ArrayList<>();
    e.distribuirNumerosEntablero(numeros);//metodo que distribuye aleatoriamente los numeros en el tablero dejando una casilla desocupada.
    determinarPasos(e,pasos);
    return pasos;
}

private void determinarPasos(Estado e, ArrayList<String> pasos) {
    if(e.casillaVaciaEstaUbicada() && e.tableroEstaOrdenado()){//metodo que comprueba que la casilla vacia este ubicada en la pos (4,4) del tablero, y que este esté correctamente ordenado. 
        e.addNuevaSolucionDeTablero(pasos);//metodo que guarda una nueva solucion de pasos para completar correctamente el tablero.
    }
    else{
        ArrayList<Casilla> casillasIntercambiables = e.getCasillasContiguas(e.getCasillaActualEnTablero());//metodoque devuelve las casillas contiguas a la actual en vertical y horizontal.
        for (Casilla casilla : casillasIntercambiables) {
                if(e.tieneSentidoIntercambiarlos(e.getCasillaActualEnTablero().getValor(), casilla)) {//metodo que comprueba si es factible realizar un intercambio horizontal o vertical.
                    pasos.add(e.getTipoDeIntercambio(e.getCasillaActualEnTablero().getValor(), casilla));//metodo que devuelve cual fue el intercambio, si horizontal o vertical.
                    e.realizarIntercambio(e.getCasillaActualEnTablero(), casilla);//metodo que realiza el intercambio.

                    determinarPasos(e, pasos);

                    pasos.remove(pasos.size()-1);
                    e.deshacerIntercambio(e.getCasillaActualEnTablero(), casilla);
                }
        }
    }
}

/*Ejercicio 10

Utilizando la técnica Backtracking, escriba un algoritmo que dado un conjunto de números enteros,
devuelva (si existen) todos los subconjuntos de tamaño N (dado como parámetro), cuyas sumas
sean exactamente cero. Por ejemplo dado el conjunto {-7, -3, -2, -1, 5, 8 } y N = 3, los subconjuntos
que suman cero son: {-7, -1, 8} y {-3, -2, 5}. */ 

public ArrayList<ArrayList<Integer>> subconjuntosQueSumanCero(ArrayList<Integer> conjunto, Integer n){
    ArrayList<ArrayList<Integer>> subconjuntosQueCumplen = new ArrayList<>();
    Estado e = new Estado();
    Integer posEnConjunto = 0;
    subconjuntosQueCumplen(e, n, posEnConjunto, subconjuntosQueCumplen, conjunto);

    if (subconjuntosQueCumplen.isEmpty()) {
        return new ArrayList<>();
    }
    return subconjuntosQueCumplen;
}

private void subconjuntosQueCumplen(Estado e, Integer n, Integer posEnConjunto,
        ArrayList<ArrayList<Integer>> subconjuntosQueCumplen, ArrayList<Integer> conjunto) {
        
        if (e.sumarSubconjuntoCumple(e.getSubconjuntoActual()) && e.getSubconjuntoActual().size() == n){//si la suma del subconjunto es 0 y el tamaño = n, cumple.
            subconjuntosQueCumplen.add(new ArrayList<>(e.getSubconjuntoActual()));
        }
        else{
            for (int i = posEnConjunto; i<conjunto.size(); i++) {
                Integer num = conjunto.get(i);
                e.getSubconjuntoActual().add(num);
                this.subconjuntosQueCumplen(e, n, i + 1, subconjuntosQueCumplen, conjunto);
                e.getSubconjuntoActual().remove(e.getSubconjuntoActual().size()-1);
            }
        }
}

/*Ejercicio 11

El robot de limpieza necesita volver desde su posición actual hasta su base de carga. Dado que al
robot le queda poca batería, desea encontrar el camino más corto. El robot dispone de un mapa de
la casa representado como una matriz, donde cada celda es una posición de la casa. La matriz
posee un 0 si la celda está vacía, o un 1 si la celda presenta algún obstáculo (por ejemplo, un
mueble). Se desea encontrar entonces el camino más corto considerando que:

- Desde una celda solo te puedes mover a las celdas contiguas (izquierda, derecha, arriba y
abajo)

- El robot sólo puede caminar por celdas libres (no por celdas con obstáculos) */

public ArrayList<Casilla> trazarCaminoRobotLimpieza(Casilla origen, Casilla destino){
    ArrayList<Casilla> caminoParcial = new ArrayList<>();
    Estado e = new Estado();

    this.trazarCaminoRobotLimpieza(origen, destino, caminoParcial, e);

    return this.caminoFinalRobot;
}

private void trazarCaminoRobotLimpieza(Casilla origen, Casilla destino, ArrayList<Casilla> caminoParcial, Estado e) {
    if (caminoParcial.contains(destino)) {//si llegue a la base de carga (camino contiene casilla destino).
        if (this.caminoFinalRobot.isEmpty()) {
            this.caminoFinalRobot.addAll(caminoParcial);
        }
        else if(caminoParcial.size() < this.caminoFinalRobot.size()) {//me quedo con el camino mas corto.
            this.caminoFinalRobot.clear();
            this.caminoFinalRobot.addAll(caminoParcial);
        }
    }
    else{
        ArrayList<Casilla> casillasVecinas = e.getCasillasContiguas();//metodo que devuelve a las casillas a las me puedo mover dsd mi casilla actual (origen).
        for (Casilla casilla : casillasVecinas) {
            if (casilla.getValor() == 0) {//poda 1: Si la casilla no tiene obstaculos (contiene un 0), la agrego al camino.
                if (!caminoParcial.contains(casilla)) {//poda 2: Si la casilla no tiene obstaculos (contiene un 0), la agrego al camino.
                    if (caminoParcial.size() < this.caminoFinalRobot.size()) {//poda 3: mi camino actual debe ser mas corto que el final en todo momento anterior a llegar al destino (base de carga).
                        caminoParcial.add(casilla);
                        trazarCaminoRobotLimpieza(casilla, destino, caminoParcial, e);
                        caminoParcial.remove(caminoParcial.size()-1);
                    }
                }
            }
        }
    }
}

}



