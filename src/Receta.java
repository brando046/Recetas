// Receta.java
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Receta implements Serializable {
    private String nombre;
    private String categoria;
    private List<Ingrediente> ingredientes;
    private List<String> pasos;
    private int tiempoPreparacion; // en minutos
    
    public Receta(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.ingredientes = new ArrayList<>();
        this.pasos = new ArrayList<>();
    }
    
    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }
    
    public void agregarPaso(String paso) {
        pasos.add(paso);
    }
    
    public void setTiempoPreparacion(int minutos) {
        this.tiempoPreparacion = minutos;
    }
    
    // Getters
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public List<Ingrediente> getIngredientes() { return new ArrayList<>(ingredientes); }
    public List<String> getPasos() { return new ArrayList<>(pasos); }
    public int getTiempoPreparacion() { return tiempoPreparacion; }
    
    @Override
    public String toString() {
        return nombre + " (" + categoria + ")";
    }
}