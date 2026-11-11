// Version 
package modelo;
import java.io.Serializable;

public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private int partidosJugados;
    private int puntos;

 public Equipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.partidosJugados = 0;
        this.puntos = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

@Override
    public String toString() {
        return "Equipo [ID=" + id + ", Nombre=" + nombre + ", PJ=" + partidosJugados + ", Puntos=" + puntos + "]";
    }
}