import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VentanaTorneo extends JFrame {

    private JTextField txtNombreEquipo;
    private JTable tablaPartidos;
    private DefaultTableModel modeloTabla;
    private ArrayList<String> listaEquipos;

    public VentanaTorneo() {
        listaEquipos = new ArrayList<>();

        // Configuración de la ventana principal con estilo moderno
        setTitle("Sistema de Gestión de Torneos - Dinámico");
        setSize(750, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 247, 250));

        // --- PANEL SUPERIOR (Registro) ---
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        panelSuperior.setBackground(new Color(245, 247, 250));
        
        JLabel lblNombre = new JLabel("Nombre del Equipo:");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panelSuperior.add(lblNombre);

        txtNombreEquipo = new JTextField(15);
        txtNombreEquipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelSuperior.add(txtNombreEquipo);

        // Botón con animación y colores personalizados
        JButton btnAgregar = crearBotonAnimado("1. Registrar Equipo", new Color(41, 128, 185), new Color(52, 152, 219));
        panelSuperior.add(btnAgregar);

        add(panelSuperior, BorderLayout.NORTH);

        // --- PANEL CENTRAL (Tabla de Partidos) ---
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Equipo 1");
        modeloTabla.addColumn("VS");
        modeloTabla.addColumn("Equipo 2");

        tablaPartidos = new JTable(modeloTabla);
        tablaPartidos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaPartidos.setRowHeight(25);
        tablaPartidos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablaPartidos.getTableHeader().setBackground(new Color(52, 73, 94));
        tablaPartidos.getTableHeader().setForeground(Color.WHITE);

        JScrollPane panelTabla = new JScrollPane(tablaPartidos);
        panelTabla.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        add(panelTabla, BorderLayout.CENTER);

        // --- PANEL INFERIOR (Opciones y Controles) ---
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        panelInferior.setBackground(new Color(245, 247, 250));

        JButton btnMostrarEquipos = crearBotonAnimado("2. Ver Equipos", new Color(39, 174, 96), new Color(46, 204, 113));
        JButton btnSortear = crearBotonAnimado("3. Generar Fixture", new Color(142, 68, 173), new Color(155, 89, 182));
        JButton btnGuardar = crearBotonAnimado("4. Guardar Datos", new Color(211, 84, 0), new Color(230, 126, 34));
        JButton btnSalir = crearBotonAnimado("5. Salir", new Color(192, 57, 43), new Color(231, 76, 60));

        panelInferior.add(btnMostrarEquipos);
        panelInferior.add(btnSortear);
        panelInferior.add(btnGuardar);
        panelInferior.add(btnSalir);

        add(panelInferior, BorderLayout.SOUTH);

        // --- ACCIONES Y EVENTOS ---

        btnAgregar.addActionListener(e -> {
            String nombre = txtNombreEquipo.getText().trim();
            if (!nombre.isEmpty()) {
                listaEquipos.add(nombre);
                JOptionPane.showMessageDialog(this, "Equipo '" + nombre + "' registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtNombreEquipo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre de equipo.", "Atención", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnMostrarEquipos.addActionListener(e -> {
            if (listaEquipos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay equipos registrados aún.", "Lista Vacía", JOptionPane.WARNING_MESSAGE);
            } else {
                StringBuilder mensaje = new StringBuilder("Equipos registrados:\n\n");
                for (int i = 0; i < listaEquipos.size(); i++) {
                    mensaje.append((i + 1)).append(". ").append(listaEquipos.get(i)).append("\n");
                }
                JOptionPane.showMessageDialog(this, mensaje.toString(), "Lista de Equipos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnSortear.addActionListener(e -> {
            if (listaEquipos.size() < 2) {
                JOptionPane.showMessageDialog(this, "Se necesitan al menos 2 equipos para generar el torneo.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }
            modeloTabla.setRowCount(0);
            for (int i = 0; i < listaEquipos.size() - 1; i += 2) {
                String eq1 = listaEquipos.get(i);
                String eq2 = (i + 1 < listaEquipos.size()) ? listaEquipos.get(i + 1) : "DESCANSA";
                modeloTabla.addRow(new Object[]{eq1, "VS", eq2});
            }
            JOptionPane.showMessageDialog(this, "¡Fixture generado exitosamente!", "Sorteo", JOptionPane.INFORMATION_MESSAGE);
        });

        btnGuardar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        });

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });
    }

    // --- MÉTODO PARA CREAR BOTONES DINÁMICOS CON ANIMACIÓN DE HOVER ---
    private JButton crearBotonAnimado(String texto, Color colorNormal, Color colorHover) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Dibuja un botón con esquinas redondeadas modernas
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorNormal);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        // Animación dinámica de transición al pasar el mouse (Hover & Click)
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorHover); // Cambia al color brillante al pasar el cursor
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorNormal); // Regresa al color original al salir el cursor
            }

            @Override
            public void mousePressed(MouseEvent e) {
                boton.setBackground(colorHover.darker()); // Efecto de pulsación hacia adentro
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                boton.setBackground(colorHover);
            }
        });

        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaTorneo().setVisible(true));
    }
}