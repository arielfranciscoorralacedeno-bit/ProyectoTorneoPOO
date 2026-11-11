# TorneoManager - Sistema de Gestión de Torneos (Java MVC)

Sistema de gestión de torneos deportivos desarrollado en Java bajo el patrón de arquitectura **MVC (Modelo-Vista-Controlador)**. Este proyecto está diseñado para administrar equipos, generar emparejamientos automáticos mediante algoritmos recursivos, manejar la persistencia de datos mediante serialización y garantizar la robustez con excepciones personalizadas.

---

##  Características Principales
* **Arquitectura MVC:** Separación estricta de responsabilidades en paquetes organizados (`modelo`, `controlador`, `excepciones`).
* **Generación de Fixture (Recursivo):** Algoritmo optimizado mediante recursividad para la creación automática de calendarios de enfrentamientos.
* **Persistencia de Datos (Serialización):** Capacidad de guardar y cargar el estado completo de la competición utilizando objetos serializables (`PersistenciaTorneo`).
* **Robustez y Control de Errores:** Excepciones personalizadas para manejar escenarios no previstos de forma limpia.

---

##  Diagrama de Clases (UML)
A continuación se muestra el diseño arquitectónico de clases que modela la solución:

![Diagrama de Clases POO] (diagrama.png)

### Descripción de los Paquetes y Clases:
* **`modelo`**: Contiene las entidades principales del sistema:
  * **`Equipo`**: Modela la información básica de cada competidor (ID y nombre).
  * **`Partido`**: Administra los enfrentamientos entre dos equipos (equipo1 y equipo2).
  * **`Torneo`**: Núcleo del modelo que almacena la lista de equipos, partidos y métodos de lógica interna (como la generación recursiva del fixture y la gestión de archivos).
* **`controlador`**: Maneja el flujo de interacción y la lógica de ejecución del programa:
  * **`GestorTorneo`**: Controla el menú principal, la consola (`Scanner`) y coordina las acciones del usuario con el `Torneo`.
* **`excepciones`**: Clases orientadas al control de fallos específicos del negocio:
  * `NumeroEquiposInvalidoException`
  * `SorteoInvalidoException`
  * `EquipoNoEncontradoException`
* **Persistencia (`PersistenciaTorneo`)**: Implementa la interfaz `Serializable` para volcar el estado del torneo en disco (`torneo.ser`).

---

##  Tecnologías y Requisitos
* **Lenguaje:** Java (JDK 8 o superior recomendado)
* **IDE:** Eclipse IDE / Visual Studio Code
* **Control de Versiones:** Git & GitHub

---

##  Estructura del Proyecto
```text
├── controlador/
│   └── GestorTorneo                 # Lógica del flujo y consola del sistema
├── modelo/
│   ├── Torneo                       # Estructura central del torneo y métodos recursivos
│   ├── Equipo                       # Entidad de equipos participantes
│   └── Partido                      # Entidad de enfrentamientos
├── excepciones/
│   ├── EquipoNoEncontradoException  # Excepción personalizada
│   ├── NumeroEquiposInvalidoException # Excepción por límites de equipos
│   └── SorteoInvalidoException      # Excepción de validación de emparejamientos
├── .project                         # Configuración de Eclipse
├── .classpath                       # Rutas de compilación de Eclipse
└── .gitignore                       # Control de archivos excluidos (binarios .class y bin/)
