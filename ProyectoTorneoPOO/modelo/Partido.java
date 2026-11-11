package modelo;

import java.io.Serializable;

public class Partido implements Serializable {
    private static final long serialVersionUID = 1L;

    private Equipo local;
    private Equipo visitante;
    private int golesLocal;
    private int golesVisitante;
    private boolean jugado;

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.jugado = false;
    }

    public Equipo getLocal() { return local; }
    public Equipo getVisitante() { return visitante; }
    
    public int getGolesLocal() { return golesLocal; }
    public void setGolesLocal(int golesLocal) { this.golesLocal = golesLocal; }
    
    public int getGolesVisitante() { return golesVisitante; }
    public void setGolesVisitante(int golesVisitante) { this.golesVisitante = golesVisitante; }
    
    public boolean isJugado() { return jugado; }
    public void setJugado(boolean jugado) { this.jugado = jugado; }

    @Override
    public String toString() {
        return local.getNombre() + " " + golesLocal + " - " + golesVisitante + " " + visitante.getNombre();
    }
}