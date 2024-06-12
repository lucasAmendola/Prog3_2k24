package org.example.greedy;

import java.sql.Date;

public class Actividad {
    Date horaIni;
    Date horaFin;
    
    public Actividad(Date horaIni, Date horaFin) {
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    public Date getHoraIni() {
        return horaIni;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    
}
