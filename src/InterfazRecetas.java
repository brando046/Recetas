
// InterfazRecetas.java
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class InterfazRecetas extends JFrame {
    private GestorRecetas gestor;
    private JComboBox<String> categoriaCombo;
    private JList<Receta> listaRecetas;
    private JTextArea detallesArea;
    private DefaultListModel<Receta> modeloLista;

    public InterfazRecetas() {
        gestor = new GestorRecetas();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Gestor de Recetas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unused")
    private void inicializarComponentes() {
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de búsqueda y filtros
        JPanel searchPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField searchField = new JTextField();
        ArrayList<String> categorias = new ArrayList<>(gestor.getCategorias());
        categorias.add(0, "Todas");
        categoriaCombo = new JComboBox<>(categorias.toArray(new String[0]));
        JButton searchNombreBtn = new JButton("Buscar por Nombre");
        JButton searchIngredienteBtn = new JButton("Buscar por Ingrediente");
        JButton nuevaRecetaBtn = new JButton("Nueva Receta");

        searchPanel.add(new JLabel("Buscar:"));
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Categoría:"));
        searchPanel.add(categoriaCombo);
        searchPanel.add(searchNombreBtn);
        searchPanel.add(searchIngredienteBtn);

        // Lista de recetas
        modeloLista = new DefaultListModel<>();
        listaRecetas = new JList<>(modeloLista);
        JScrollPane listaScroll = new JScrollPane(listaRecetas);

        // Área de detalles
        detallesArea = new JTextArea();
        detallesArea.setEditable(false);
        JScrollPane detallesScroll = new JScrollPane(detallesArea);

        // Panel izquierdo
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(searchPanel, BorderLayout.NORTH);
        leftPanel.add(listaScroll, BorderLayout.CENTER);
        leftPanel.add(nuevaRecetaBtn, BorderLayout.SOUTH);

        // Organizar paneles
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(detallesScroll, BorderLayout.CENTER);

        add(mainPanel);

        // Eventos
        searchNombreBtn.addActionListener(e -> actualizarLista(gestor.buscarPorNombre(searchField.getText())));

        searchIngredienteBtn
                .addActionListener(e -> actualizarLista(gestor.buscarPorIngrediente(searchField.getText())));

        categoriaCombo.addActionListener(e -> {
            String categoriaSeleccionada = (String) categoriaCombo.getSelectedItem();
            if (categoriaSeleccionada != null) {
                if (categoriaSeleccionada.equals("Todas")) {
                    actualizarLista(gestor.getTodasLasRecetas());
                } else {
                    actualizarLista(gestor.getRecetasCategoria(categoriaSeleccionada));
                }
            }
        });

        listaRecetas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetallesReceta(listaRecetas.getSelectedValue());
            }
        });

        nuevaRecetaBtn.addActionListener(e -> mostrarDialogoNuevaReceta());

        actualizarLista(gestor.getTodasLasRecetas());
    }

    private void actualizarLista(List<Receta> recetas) {
        modeloLista.clear();
        for (Receta receta : recetas) {
            modeloLista.addElement(receta);
        }
    }

    private void mostrarDetallesReceta(Receta receta) {
        if (receta == null) {
            detallesArea.setText("");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("RECETA: ").append(receta.getNombre()).append("\n");
        sb.append("Categoría: ").append(receta.getCategoria()).append("\n");
        sb.append("Tiempo de preparación: ").append(receta.getTiempoPreparacion()).append(" minutos\n\n");

        sb.append("INGREDIENTES:\n");
        for (Ingrediente ing : receta.getIngredientes()) {
            sb.append("- ").append(ing.toString()).append("\n");
        }

        sb.append("\nPASOS:\n");
        List<String> pasos = receta.getPasos();
        for (int i = 0; i < pasos.size(); i++) {
            sb.append(i + 1).append(". ").append(pasos.get(i)).append("\n");
        }

        detallesArea.setText(sb.toString());
    }

    private void mostrarDialogoNuevaReceta() {
        JTextField nombreField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextField tiempoField = new JTextField();

        Object[] message = {
                "Nombre:", nombreField,
                "Categoría:", categoriaField,
                "Tiempo (minutos):", tiempoField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Nueva Receta",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreField.getText().trim();
                String categoria = categoriaField.getText().trim();
                int tiempo = Integer.parseInt(tiempoField.getText().trim());

                Receta nuevaReceta = new Receta(nombre, categoria);
                nuevaReceta.setTiempoPreparacion(tiempo);

                // Agregar ingredientes
                while (true) {
                    JTextField nombreIngField = new JTextField();
                    JTextField cantidadField = new JTextField();
                    JTextField unidadField = new JTextField();

                    Object[] ingMessage = {
                            "Nombre del ingrediente:", nombreIngField,
                            "Cantidad:", cantidadField,
                            "Unidad:", unidadField
                    };

                    option = JOptionPane.showConfirmDialog(this, ingMessage,
                            "Agregar Ingrediente", JOptionPane.OK_CANCEL_OPTION);

                    if (option != JOptionPane.OK_OPTION)
                        break;

                    double cantidad = Double.parseDouble(cantidadField.getText().trim());
                    Ingrediente ing = new Ingrediente(
                            nombreIngField.getText().trim(),
                            cantidad,
                            unidadField.getText().trim());
                    nuevaReceta.agregarIngrediente(ing);
                }

                // Agregar pasos
                while (true) {
                    String paso = JOptionPane.showInputDialog(this,
                            "Ingrese el siguiente paso (Cancelar para terminar):");
                    if (paso == null)
                        break;
                    nuevaReceta.agregarPaso(paso.trim());
                }

                gestor.agregarReceta(nuevaReceta);
                actualizarComboCategoria();
                actualizarLista(gestor.getRecetasCategoria(categoria));

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, ingrese valores numéricos válidos",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarComboCategoria() {
        ArrayList<String> categorias = new ArrayList<>(gestor.getCategorias());
        categorias.add(0, "Todas");

        categoriaCombo.removeAllItems();
        for (String categoria : categorias) {
            categoriaCombo.addItem(categoria);
        }
    }

}