package org.example.backtracking;

import java.util.ArrayList;
import java.util.HashMap;

import org.example.Procesador;
import org.example.Tarea;

public class Estado {

    public Estado(int pos, ArrayList<Integer> solucion) {
    }

    public Estado(ArrayList<Integer> conjuntoP, ArrayList<Integer> subconjuntoP, ArrayList<ArrayList> soluciones) {
    }

    public Estado(ArrayList<Integer> solucion) {
    }

    public Estado() {
    }

    /*public Estado(ArrayList<Integer> conjuntoC, ArrayList<ArrayList> soluciones) {
    }*/

    /*public Estado(ArrayList<Procesador> procesadores, ArrayList<Tarea> tareas) {
    }*/

    public Estado(ArrayList<Integer> conjuntoP, ArrayList<Integer> subconjuntoP, ArrayList<ArrayList> soluciones,
            int posEnConjunto) {
    }

    public Estado(ArrayList<Integer> conjuntoC, int posActualEnConjuntoC) {
    }

    public Estado(ArrayList<Integer> conjuntoC, ArrayList<ArrayList> soluciones, int posActualEnConjuntoC) {
    }

    public Estado(ArrayList<Integer> conjuntoC, int posEnConjuntoC, ArrayList<ArrayList> soluciones) {
    }

    public Estado(int pos) {
    }

    public boolean SolucionTieneDestino() {
        return false;
    }

    public boolean caminoTieneDestino() {
        return false;
    }

    public ArrayList<ArrayList> getCaminoActual() {
        return null;
    }

    public int getCaminoSolucion() {
        return 0;
    }

    public int getSumCaminoActual() {
        return 0;
    }

    public int getSumCaminoSolucion() {
        return 0;
    }

    public void addNuevaSolucion() {
    }

    public void limpiarSolucion() {
    }

    public int[] getCasillasPosibles(int casillaI) {
        return null;
    }

    public boolean caminoContieneCasilla(int casilla) {
        return false;
    }

    public void removerDelCamino(int casilla) {
    }

    public void addCasillaACaminoActual(int casilla) {
    }

    public void devolverCasillaAlCamino(int casilla) {
    }

    public void removerCasillaDelasPosibilidades(int casilla) {
    }

    public void removerCasillaDelCaminoActual(int casilla) {
    }

    public void devolverCasillaALasPosibilidades(int casilla) {
    }

    public ArrayList<ArrayList> getSoluciones() {
        return null;
    }

    public int sumarSubConjuntoP(ArrayList<Integer> subconjuntoP) {
        return 0;
    }


    
    public void addnuevaSolucion(ArrayList<Integer> subconjuntoP) {
    }

    public int[] getConjuntoP() {
        return null;
    }

    public void removerDelConjuntoP(int i) {
    }

    public void devolverAlConjuntoP(int i) {
    }

    public void removerCasillaDelPatio(int casilla) {
    }

    public boolean contieneCasillaInicial() {
        return false;
    }

    public boolean piramideEstaCompleta() {
        return false;
    }

    public void agregarPiramideCompleetaAsolucion() {
    }

    public boolean sumaHijos(int i) {
        return false;
    }

    public void addEnPiramide(int i) {
    }

    public void removerDePiramide(int i) {
    }

    public Object getPiramideActual() {
        return null;
    }

    public void agregarPiramideASolucion(Object piramideActual) {
    }

    public void addNuevaSolucion(ArrayList<Integer> subcActual) {
    }

    public int getPosBaseDeCarga() {
        return 0;
    }

    public ArrayList<ArrayList> getSolucion() {
        return null;
    }

    public int[] getPosicionesAdyacentesDisponibles(int posI) {
        return null;
    }

    public void removerPosDelasPosibilidades(int posHabilitadas) {
    }

    public void devolverPosAlCamino(int posHabilitada) {
    }

    public ArrayList<ArrayList> getTareas() {
        return null;
    }

    public void addNuevaAsignacion(ArrayList<HashMap<Procesador, Tarea>> asignacion) {
    }

    public Procesador[] getProcesadoresDisponibles() {
        return null;
    }

    public Tarea[] getTareasPorHacer() {
        return null;
    }

    public void removeTarea(Tarea t) {
    }

    public static void removeProcesador(Procesador p) {
    }

    public void reponerProcesador(Procesador p) {
    }

    public void reponerTarea(Tarea t) {
    }

    public ArrayList<ArrayList> getAsignacionFinal() {
        return null;
    }

    public int gerTiempoAsignacion(ArrayList<ArrayList> asignacionFinal) {
        return 0;
    }

    public int getTiempoAsignacion(ArrayList<HashMap<Procesador, Tarea>> asignacion) {
        return 0;
    }

    public int getSumaSubconjunto(ArrayList<Integer> subconjunto) {
        return 0;
    }

    public boolean tableroCompleto() {
        return false;
    }

    public ArrayList<Integer> getTableroActual() {
        return null;
    }

    public ArrayList<Casilla> getCasillasDisponiblesEnTablero(){
        return new ArrayList<>();
    }

    public Casilla getCasillaTablero() {
        return null;
    }

    public void agregarElementoAlTablero(int casillaTablero, int i) {
    }

    public int getSumaFilaComlumnaTablero(ArrayList<Integer> tableroActual) {
        return 0;
    }

    public void removerElementoDelTablero(int casillaTablero, int i) {
    }

    public void reponerEnElConjunto(int i) {
    }

    public Casilla traerCasillaDelBorde() {
        return new Casilla(null, null, null, null, null,null,null);
    }

    public void addCaminoSolucion(ArrayList<ArrayList> caminoActual) {
    }

    public ArrayList<ArrayList> getConjunto() {
        return null;
    }

    public int getPosActualEnConjuntoC() {
        return 0;
    }

    public int obtenerEnConjunto(int posActualEnConjuntoC) {
        return 0;
    }

    public void insertarEnConjuntoActual(int i) {
    }

    public void incrementarPosActual() {
    }

    public void decrementarPosActual() {
    }

    public ArrayList<Integer> getConjuntoC() {
        return null;
    }

    public void agregarNumeroEnTablero(int casillaTablero, int i) {
    }

    public void RemoverNumeroEnTablero(int casillaTablero, int i) {
    }

    public boolean sumarSubconjuntosCumple() {
        return false;
    }

    public ArrayList<Integer>[] getSubconjunto() {
        return null;
    }

    public boolean permutacionCompleta(ArrayList<Integer> permutacion) {
        return false;
    }

    public int sumarSubconjunto() {
        return 0;
    }

    public boolean sumarSubconjuntoActualCumple(ArrayList<Integer> subconjunto) {
        return false;
    }

    public ArrayList<Integer> tableroActual() {
        return null;
    }

    public ArrayList<ArrayList> getReinas() {
        return null;
    }

    public boolean reinaAmenazada(ArrayList<Integer> fila, ArrayList<Integer> columna) {
        return false;
    }

    public void agregarEnTablero(int reina, ArrayList<Integer> fila, Object column) {
    }

    public void agregarEnTableroActual(int reina, ArrayList<Integer> fila, ArrayList<Integer> columna) {
    }

    public void sacarDelTableroActual(int reina, ArrayList<Integer> fila, ArrayList<Integer> columna) {
    }

    public boolean tableroCumple(ArrayList<Integer> tableroActual) {
        return false;
    }

    public boolean caminoContieneOrigen(ArrayList<Casilla> caminoSolucion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'caminoContieneOrigen'");
    }

    public void agregarNumeroEnTablero(Integer n, Casilla casillasDisponible) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarNumeroEnTablero'");
    }

    public void removerElementoDelTablero(Integer n, Casilla casillasDisponible) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removerElementoDelTablero'");
    }

    public void agregarElementoAlTablero(Integer n, Casilla c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarElementoAlTablero'");
    }

    public void removerElementoDelTablero(Casilla c, Integer n) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removerElementoDelTablero'");
    }

    public boolean piramideCumple(Object piramideActual) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'piramideCumple'");
    }

    public void distribuirNumeroEntablero(ArrayList<Integer> numeros) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'distribuirNumeroEntablero'");
    }

    public void distribuirNumerosEntablero(ArrayList<Integer> numeros) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'distribuirNumerosEntablero'");
    }

    public boolean casillaEstaUbicada() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'casillaEstaUbicada'");
    }

    public void addNuevaSolucionDeTablero() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNuevaSolucionDeTablero'");
    }

    public void addNuevaSolucionDeTablero(ArrayList<String> pasos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNuevaSolucionDeTablero'");
    }

    public ArrayList<Casilla> getCasillasContiguas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCasillasContiguas'");
    }

    public Casilla getCasillaActualEnTablero() {
        return new Casilla(null, null, null, null, null, null, null);
    }

    public ArrayList<Casilla> getCasillasContiguas(Object casillaActualEnTablero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCasillasContiguas'");
    }

    public boolean casillaVaciaEstaUbicada() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'casillaVaciaEstaUbicada'");
    }

    public boolean tieneSentidoIntercambiarlos(Integer valor, Casilla casilla) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tieneSentidoIntercambiarlos'");
    }

    public String getTipoDeIntercambio(Integer integer, Casilla casilla) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipoDeIntercambio'");
    }

    public void realizarIntercambio(String tipoDeIntercambio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realizarIntercambio'");
    }

    public void realizarIntercambio(Casilla casillaActualEnTablero, Casilla casilla) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realizarIntercambio'");
    }

    public void deshacerIntercambio(Casilla casillaActualEnTablero, Casilla casilla) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deshacerIntercambio'");
    }

    public boolean tableroEstaOrdenado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tableroEstaOrdenado'");
    }

    public ArrayList<Integer> getSubconjuntoActual() {
        return new ArrayList<>();
    }

    public boolean sumarSubconjuntoCumple(boolean subconjuntoActual) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sumarSubconjuntoCumple'");
    }

    public boolean sumarSubconjuntoCumple(ArrayList<Integer> subconjuntoActual) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sumarSubconjuntoCumple'");
    }

    public void nuevoSubConjuntoActual() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nuevoSubConjuntoActual'");
    }

}
