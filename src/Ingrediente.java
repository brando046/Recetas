// Ingrediente.java
import java.io.Serializable;

public class Ingrediente implements Serializable {
    private String nombre;
    private double cantidad;
    private String unidad;
    
    public Ingrediente(String nombre, double cantidad, String unidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }
    
    public String getNombre() { return nombre; }
    public double getCantidad() { return cantidad; }
    public String getUnidad() { return unidad; }
    
    @Override
    public String toString() {
        return String.format("%.2f %s de %s", cantidad, unidad, nombre);
    }
}