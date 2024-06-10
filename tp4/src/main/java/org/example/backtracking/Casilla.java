package org.example.backtracking;

import java.util.ArrayList;

public class Casilla {
    private Integer valor;
    private Boolean norte;
    private Boolean sur;
    private Boolean este;
    private Boolean oeste;
    private Boolean vertical;
    private Boolean horizontal;

    public Casilla(Integer valor, Boolean norte, Boolean sur, Boolean este, Boolean oeste, Boolean vertical, Boolean horizontal) {
        this.valor = valor;
        this.norte = norte;
        this.sur = sur;
        this.este = este;
        this.oeste = oeste;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }


    public ArrayList<Casilla> getVecinos(){
        return new ArrayList<>();
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Boolean getNorte() {
        return norte;
    }

    public void setNorte(Boolean norte) {
        this.norte = norte;
    }

    public Boolean getSur() {
        return sur;
    }

    public void setSur(Boolean sur) {
        this.sur = sur;
    }

    public Boolean getEste() {
        return este;
    }

    public void setEste(Boolean este) {
        this.este = este;
    }

    public Boolean getOeste() {
        return oeste;
    }

    public void setOeste(Boolean oeste) {
        this.oeste = oeste;
    }

    public Boolean getVertical() {
        return vertical;
    }

    public void setVertical(Boolean arriba) {
        this.vertical = arriba;
    }

    public Boolean getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Boolean abajo) {
        this.horizontal = abajo;
    }

    
}
