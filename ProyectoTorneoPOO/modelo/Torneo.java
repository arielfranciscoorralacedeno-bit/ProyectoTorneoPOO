package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Torneo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private List<Equipo> equipos;
    private List<Partido> partidos;

    public Torneo(String nombre) {
        this.nombre = nombre;
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public List<Equipo> getEquipos() { return equipos; }
    public List<Partido> getPartidos() { return partidos; }

    @Override
    public String toString() {
        return "Torneo: " + nombre + " [Equipos: " + equipos.size() + ", Partidos: " + partidos.size() + "]";
    }
   // Método auxiliar para validar potencia de 2
    private boolean esPotenciaDeDos(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // Método recursivo actualizado con Excepciones Personalizadas
    public List<Partido> generarSorteoRecursivo(List<Equipo> listaEquipos) throws excepciones.NumeroEquiposInvalidoException, excepciones.SorteoInvalidoException {
        
        // 1. Validar si el número de equipos es potencia de 2 (Ej: 2, 4, 8, 16)
        if (!esPotenciaDeDos(listaEquipos.size()) || listaEquipos.size() < 2) {
            throw new excepciones.NumeroEquiposInvalidoException(
                "El número de equipos (" + listaEquipos.size() + ") no es válido. Debe ser una potencia de 2 (2, 4, 8, 16)."
            );
        }

        List<Partido> partidosFase = new ArrayList<>();
        
        // Caso base: si quedan exactamente 2 equipos
        if (listaEquipos.size() == 2) {
            if (listaEquipos.get(0).getNombre().equalsIgnoreCase(listaEquipos.get(1).getNombre())) {
                throw new excepciones.SorteoInvalidoException("Error: Un equipo no puede jugar contra sí mismo.");
            }
            partidosFase.add(new Partido(listaEquipos.get(0), listaEquipos.get(1)));
            return partidosFase;
        }
        
        // Caso recursivo: emparejar extremos
        if (listaEquipos.size() > 2) {
            Equipo local = listaEquipos.remove(0);
            Equipo visitante = listaEquipos.remove(listaEquipos.size() - 1);
            
            if (local.getNombre().equalsIgnoreCase(visitante.getNombre())) {
                throw new excepciones.SorteoInvalidoException("Error lógico en el sorteo de oponentes.");
            }
            
            partidosFase.add(new Partido(local, visitante));
            partidosFase.addAll(generarSorteoRecursivo(listaEquipos));
        }
        
        return partidosFase;
     } // Método para guardar el torneo en un archivo
    public void guardarTorneo(String rutaArchivo) throws java.io.IOException {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(rutaArchivo))) {
            oos.writeObject(this);
        }
    }

    // Método para cargar el torneo desde un archivo
    public static Torneo cargarTorneo(String rutaArchivo) throws java.io.IOException, ClassNotFoundException {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(rutaArchivo))) {
            return (Torneo) ois.readObject();
        }
    }
} // <--- Esta es la última llave de cierre de la clase Torneo} // Cierra el método generarSorteoRecursivo


       // Cierra la clase Torneo