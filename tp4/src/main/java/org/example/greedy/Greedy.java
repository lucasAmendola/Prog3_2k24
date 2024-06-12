package org.example.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.example.Arco;
import org.example.Grafo;
import org.example.GrafoDirigido;
import org.example.backtracking.Casilla;
import org.example.backtracking.Estado;

public class Greedy {

    private ArrayList<Integer> billetes;
    private HashMap<Integer, String> colorVertice;

    public Greedy(ArrayList<Integer> billetes) {
        this.billetes = billetes;
        this.colorVertice = new HashMap<Integer, String>();
    }
    
/*Ejercicio 1

Cambio de monedas: Dado un conjunto C de N tipos de monedas con un número ilimitado de
ejemplares de cada tipo, se requiere formar, si se puede, una cantidad M empleando el mínimo
número de ellas.

Por ejemplo, un cajero automático dispone de billetes de distintos valores: 100$, 25$, 10$, 5$ y 1$,
si se tiene que pagar 289$, la mejor solución consiste en dar 10 billetes: 2 de 100$, 3 de 25$, 1 de
10$ y 4 de 1$.*/

public ArrayList<Integer> devolverCambio(int vuelto){
    ArrayList<Integer> cambio = new ArrayList<>();
    int vueltoParcial = 0;
    devolverCambio(vuelto, vueltoParcial, cambio);
    return cambio;

}

private void devolverCambio(int vuelto, int vueltoParcial, ArrayList<Integer> cambio) {
    while(vueltoParcial < vuelto) {//itero mientras el vueltoParcial no sea el vuelto que buscamos.
        Integer billete = agarrarBilleteMasAlto(vuelto-vueltoParcial);
        if (billete != null) {//si el billete no es nulo, hay cambio disponible.
            cambio.add(billete);
            vueltoParcial+=billete;
        }
        else{
            System.out.println("no hay cambio disponible");
        }
    }
}

private Integer agarrarBilleteMasAlto(int vueltoActual) {
   for (Integer billete : billetes) {//se supone que los billetes estan ordenados descendentemente.
        if (billete <= vueltoActual) {
            return billete;
        }
   }
   return null;
}

/*Ejercicio 3

Maximizar el número de actividades compatibles. Se tienen n actividades que necesitan utilizar un
recurso, tal como una sala de conferencias, en exclusión mutua. Cada actividad i tiene asociado un
tiempo de comienzo ci y un tiempo de finalización fi de utilización del recurso, con ci < fi. Si la
actividad i es seleccionada se llevará a cabo durante el intervalo [ci, fi). Las actividades i y j son
compatibles si los intervalos [ci, fi) y [cj, fj) no se superponen (es decir, ci > fj o cj > fi). El problema
consiste en encontrar la cantidad máxima de actividades compatibles entre sí.
 */

 public ArrayList<Actividad> armarAgenda(ArrayList<Actividad> actividades){
   ArrayList<Actividad> agendaDelDia = new ArrayList<>();
   armarAgenda(actividades, agendaDelDia);
   return agendaDelDia;
}

private void armarAgenda(ArrayList<Actividad> actividades, ArrayList<Actividad> agendaDelDia) {
    int indiceActividades = 0;
    while (indiceActividades < actividades.size()) {
            Actividad a = obtenerTareaQueAnterTermine(actividades);
            if (esFactible(a)) {//metodo que comprueba que la actividad se puede agregar a la agenda sin que se genere superposicion
                agendaDelDia.add(a);
            }
            indiceActividades++;
    }
}

private Actividad obtenerTareaQueAnterTermine(ArrayList<Actividad> actividades) {
    Actividad a1 = actividades.get(0);
    for (int i = 1; i < actividades.size(); i++) {
         if (a1.horaFin.after(actividades.get(i).horaFin)) {
             a1 = actividades.get(i);
         }
    }
    return a1;
}

private boolean esFactible(Actividad a) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'esFactible'");
}

/*Ejercicio 5

Desde un cierto conjunto grande de ciudades del interior de una provincia, se desean transportar
cereales hasta alguno de los 3 puertos pertenecientes al litoral de la provincia. Se pretende
efectuar el transporte total con mínimo costo sabiendo que el flete es más caro cuanto más
distancia tiene que recorrer. Dé un algoritmo que resuelva este problema, devolviendo para cada
ciudad el camino que debería recorrer hacia el puerto de menor costo.*/


/*Ejercicio 6

Problema del viajante. Dado un grafo ponderado de ciudades conectadas todas con todas,
implemente un algoritmo que brinde una aproximación al problema del viajante.
 */

public ArrayList<Integer> problemaViajante(GrafoDirigido ciudades, Integer origen){
    ArrayList <Integer> recorrido = new ArrayList<>();
    ArrayList <Integer> visitadas = new ArrayList<>();
    recorrido.add(origen);
    visitadas.add(origen);

    while (visitadas.size() < ciudades.cantidadVertices()) {
            Integer ciudad = ciudadMasCercana(visitadas, ciudades, origen);
            if (ciudad != null) {
                    visitadas.add(ciudad);
                    recorrido.add(ciudad);
                    origen = ciudad;
            }
    }

    recorrido.add(origen);
    return recorrido;
}

private Integer ciudadMasCercana(ArrayList<Integer> visitadas, GrafoDirigido ciudades, Integer origen) {
    Integer d = null;//inicializo mi siguiente ciudad en null
    Integer menorDistancia = Integer.MAX_VALUE;
    Iterator <Integer> ady = ciudades.obtenerAdyacentes(origen);
    while (ady.hasNext()) {
            Integer nc = ady.next();
            Arco arcoOrigenCiudadMasCercana = ciudades.obtenerArco(origen, nc);
            if (!visitadas.contains(nc)) {
                 if ((arcoOrigenCiudadMasCercana != null) && arcoOrigenCiudadMasCercana.getPeso() < menorDistancia) {//me quedo con la ciudad mas cercana a mi ciudad actual;
                        menorDistancia = arcoOrigenCiudadMasCercana.getPeso();
                        d = nc;
                }
            }
    }
    return d;//retorno ciudad adyacente mas cercana
}

/*Ejercicio 7

Armando CDs. Dado un conjunto de archivos de canciones, donde cada uno tiene la información de
nombre, género, duración del tema, y tamaño en kilobytes, se desea grabar un disco CD (que tiene
una capacidad máxima de M kilobytes) de modo tal de:
● Variante A: Maximizar la capacidad ocupada del disco CD.
● Variante B: Maximizar la cantidad de canciones que se pueden grabar en el CD.
Para ambas variantes se quiere, además, que el CD no contenga más de 3 canciones de un mismo
género */

public void grabarCd(ArrayList<Cancion> canciones, int capacidadCD){
    ArrayList<Cancion> varianteA = new ArrayList<>();
    ArrayList<Cancion> varianteB = new ArrayList<>();
    int espacioOcupadoParcial = 0;

    while (espacioOcupadoParcial < capacidadCD) {
            Cancion c = escogerCancionVarianteA(canciones, varianteA);//metodo que retonar la cancion mas pesada en KB.
            if (c != null && esFactible(c, espacioOcupadoParcial, capacidadCD, canciones)) {//metodo que comprueba que sea factible agregar la cancion comprobando que no se sobrepase el espacio en KB del CD y el limite de genero.
                varianteA.add(c);
                espacioOcupadoParcial += c.getKilobytes();
            }
    }

    espacioOcupadoParcial = 0;

    while (espacioOcupadoParcial < capacidadCD) {
            Cancion c = escogerCancionVarianteB(canciones, varianteA);//metodo que retonar la cancion mas liviana en KB
            if (c != null && esFactible(c, espacioOcupadoParcial, capacidadCD,canciones)) {//metodo que comprueba que sea factible agregar la cancion comprobando que no se sobrepase el espacio en KB del CD y el limite de genero.
                varianteB.add(c);
                espacioOcupadoParcial += c.getKilobytes();
            }
    }

    System.out.println("Solucion Variante A: ");
    System.out.println("\n");
    for (Cancion cancion : varianteA) {
        System.out.println("Nombre: " + cancion.getNombre() + 
                           "\n" + 
                           "Genero: " + cancion.getGenero() + 
                           "\n" + 
                           "Duracion: " + cancion.getDuracion());
    }

    System.out.println("\n");
    System.out.println("Solucion Variante B: ");
    for (Cancion cancion : varianteB) {
        System.out.println("Nombre: " + cancion.getNombre() + 
                           "\n" + 
                           "Genero: " + cancion.getGenero() + 
                           "\n" + 
                           "Duracion: " + cancion.getDuracion());
    }
}

private Cancion escogerCancionVarianteB(ArrayList<Cancion> canciones, ArrayList<Cancion> varianteA) {
    Cancion cancionEscogida = null;
    for (Cancion c : canciones) {
        if(cancionEscogida == null || (cancionEscogida.getDuracion() > c.getDuracion())){
            cancionEscogida = c;
        }
    }
    return cancionEscogida;
}

private boolean esFactible(Cancion c, int espacioOcupadoParcial, int capacidadCD, ArrayList<Cancion> canciones) {
    String genero = c.getGenero();
    int cantGenero = 1;

    if ((espacioOcupadoParcial + c.getDuracion()) <= capacidadCD) {
        for (Cancion cancion : canciones) {
             if (cancion.getGenero().equals(genero)) {
                 cantGenero++;
             }
        }
        if (cantGenero>3) {
            return false;
        }
        else{
            return true;
        }
    }
    return false;
}

private Cancion escogerCancionVarianteA(ArrayList<Cancion> canciones, ArrayList<Cancion> varianteA) {
    Cancion cancionEscogida = null;
    for (Cancion c : canciones) {
        if(cancionEscogida == null || (cancionEscogida.getDuracion() < c.getDuracion())){
            cancionEscogida = c;
        }
    }
    return cancionEscogida;
}


/*Ejercicio 8

Coloreo de un grafo. Dado un grafo se desea colorear cada uno de sus vértices utilizando la menor
cantidad posible de colores totales, sabiendo que dos vértices adyacentes no podrán utilizar el
mismo color */

public void colorearGrafo(ArrayList <String> colores, Grafo g){
    Iterator <Integer> vertices = g.obtenerVertices();

    while (vertices.hasNext()) {
        Integer v = vertices.next();
        this.colorVertice.put(v, null);
    }

    vertices = g.obtenerVertices();

    while (vertices.hasNext()) {
        Integer vAct = vertices.next();
        Iterator <Integer> verticesAdy = g.obtenerAdyacentes(vAct);
        Iterator <String> coloresDisponibles = colores.iterator();
        while (coloresDisponibles.hasNext()) {
            String color = coloresDisponibles.next();
            if (esFactible(vAct, verticesAdy, color)) {
                this.colorVertice.put(vAct, color);
                break;
            }   
        }
    }
}

private boolean esFactible(Integer vAct, Iterator<Integer> verticesAdy, String color) {
  
    while (verticesAdy.hasNext()) {
           Integer vAdyActual = verticesAdy.next();
           if (this.colorVertice.get(vAdyActual).equals(color)) {
                return false;
           }    
    }
    return true;
}

/*Ejercicio 9

Se posee una matriz cuadrada de tamaño N x N donde en cada celda de la matriz se aloja un
número entero NO negativo (es decir, >= 0). Dada una celda de origen y una celda de destino, se
desea encontrar, de ser posible, el camino de mayor costo entre el origen y el destino. El costo del
camino será medido por la suma de los valores de las celdas que conforman dicho camino. Los
movimientos válidos desde una celda son arriba, abajo, derecha e izquierda, un camino no puede
pasar dos veces por una misma celda, y siempre que nos movemos de una celda C1 a una celda C2,
el valor de la celda C2 debe ser mayor al valor de la celda C1. Por ejemplo, si estoy en una celda
con valor 10, no puedo ir a una celda de valor 8, pero si puedo ir a una de valor 12.
Se pide plantear un algoritmo mediante estrategia Greedy*/

public ArrayList<Casilla> caminoMayorCostoEnMatrizNumerica(Casilla origen, Casilla destino){
    ArrayList<Casilla> camino = new ArrayList<>();
    Estado e = new Estado();
    Integer costoFinal = 0;
    costoFinal += origen.getValor();
    camino.add(origen);

    while (origen != destino) {
          Casilla sCasilla = e.seleccionarCasillaVecinaConMayorValor(origen);//metodo que devuelve la casilla vecina a origen de mayor valor.
          if (esFactible(e, camino, sCasilla, origen)) {//comprueba que a la siguiente a la que me mueva no este ya en el camino y que no sea menor a la casilla actual.
                camino.add(sCasilla);
                costoFinal += sCasilla.getValor();
                origen = sCasilla;
          }
          else{
            System.out.println("No es posible seguir avanzando con el origen dado.");
            break;
          }
    }
    System.out.println("costo final camino mas caro: " + costoFinal);
    return camino;
}

private boolean esFactible(Estado e, ArrayList<Casilla> camino, Casilla sCasilla, Casilla actual) {

    if (!camino.contains(sCasilla) && sCasilla.getValor() > actual.getValor()) {
        return true;
    }

    return false;
}

}
