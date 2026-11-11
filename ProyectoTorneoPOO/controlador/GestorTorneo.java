package controlador;

import java.util.List;
import java.util.Scanner;
import modelo.Equipo;
import modelo.Partido;
import modelo.Torneo;

public class GestorTorneo {
    private Torneo torneo = new Torneo("Torneo Principal");
    private Scanner scanner;

    public GestorTorneo(String nombreTorneo) {
        this.torneo = new Torneo(nombreTorneo);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE TORNEO: " + torneo.getNombre() + " ---");
            System.out.println("1. Agregar Equipo");
            System.out.println("2. Ver Equipos");
            System.out.println("3. Realizar Sorteo Recursivo");
            System.out.println("4. Guardar Torneo");
            System.out.println("5. Cargar Torneo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("¡Error! Debe ingresar un número válido.");
                scanner.nextLine(); // Limpia la entrada incorrecta
                opcion = 0; 
                continue;
            }
            
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1: {
                    System.out.print("Ingrese ID del equipo: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese nombre del equipo: ");
                    String nombre = scanner.nextLine();
                    torneo.agregarEquipo(new Equipo(id, nombre));
                    System.out.println("¡Equipo agregado con éxito!");
                    break;
                }

                case 2: {
                    System.out.println("\n--- LISTA DE EQUIPOS ---");
                    for (Equipo e : torneo.getEquipos()) {
                        System.out.println(e);
                    }
                    break;
                }

                case 3: {
                    try {
                        List<Partido> partidos = torneo.generarSorteoRecursivo(new java.util.ArrayList<>(torneo.getEquipos()));
                        System.out.println("¡Sorteo generado con éxito!");
                        for (Partido partido : partidos) {
                            System.out.println(partido);
                        }
                    } catch (excepciones.NumeroEquiposInvalidoException | excepciones.SorteoInvalidoException e) {
                        System.out.println("Error en el sorteo: " + e.getMessage());
                    }
                    break;
                }

                case 4: {
                    try {
                        torneo.guardarTorneo("torneo.ser");
                        System.out.println("¡Torneo guardado correctamente en archivo!");
                    } catch (java.io.IOException e) {
                        System.out.println("Error al guardar el archivo: " + e.getMessage());
                    }
                    break;
                }

                case 5: {
                    try {
                        torneo = modelo.Torneo.cargarTorneo("torneo.ser");
                        System.out.println("¡Torneo cargado con éxito desde el archivo!");
                    } catch (java.io.IOException | ClassNotFoundException e) {
                        System.out.println("Error al cargar el archivo: " + e.getMessage());
                    }
                    break;
                }

                case 6: {
                    System.out.println("Saliendo del sistema...");
                    break;
                }

                default: {
                    System.out.println("Opción inválida.");
                    break;
                }
            }
        } while (opcion != 6);
    }
}