// GestorRecetas.java
import java.io.*;
import java.util.*;

public class GestorRecetas {
    private TreeMap<String, TreeMap<String, Receta>> recetasPorCategoria;
    private static final String ARCHIVO_DATOS = "recetas.dat";
    
    public GestorRecetas() {
        recetasPorCategoria = new TreeMap<>();
        cargarDatos();
    }
    
    public void agregarReceta(Receta receta) {
        String categoria = receta.getCategoria();
        if (!recetasPorCategoria.containsKey(categoria)) {
            recetasPorCategoria.put(categoria, new TreeMap<>());
        }
        recetasPorCategoria.get(categoria).put(receta.getNombre(), receta);
        guardarDatos();
    }
    
    public List<Receta> buscarPorNombre(String nombre) {
        List<Receta> resultados = new ArrayList<>();
        String nombreBusqueda = nombre.toLowerCase();
        
        for (TreeMap<String, Receta> categoria : recetasPorCategoria.values()) {
            for (Receta receta : categoria.values()) {
                if (receta.getNombre().toLowerCase().contains(nombreBusqueda)) {
                    resultados.add(receta);
                }
            }
        }
        return resultados;
    }
    
    public List<Receta> buscarPorIngrediente(String ingrediente) {
        List<Receta> resultados = new ArrayList<>();
        String ingredienteBusqueda = ingrediente.toLowerCase();
        
        for (TreeMap<String, Receta> categoria : recetasPorCategoria.values()) {
            for (Receta receta : categoria.values()) {
                for (Ingrediente ing : receta.getIngredientes()) {
                    if (ing.getNombre().toLowerCase().contains(ingredienteBusqueda)) {
                        resultados.add(receta);
                        break;
                    }
                }
            }
        }
        return resultados;
    }
    
    public List<Receta> getRecetasCategoria(String categoria) {
        if (recetasPorCategoria.containsKey(categoria)) {
            return new ArrayList<>(recetasPorCategoria.get(categoria).values());
        }
        return new ArrayList<>();
    }
    
    public Set<String> getCategorias() {
        return new TreeSet<>(recetasPorCategoria.keySet());
    }

    public List<Receta> getTodasLasRecetas() {
    List<Receta> todasLasRecetas = new ArrayList<>();
    for (TreeMap<String, Receta> categoriaMapa : recetasPorCategoria.values()) {
        todasLasRecetas.addAll(categoriaMapa.values());
    }
    return todasLasRecetas;
}    
    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_DATOS))) {
            recetasPorCategoria = (TreeMap<String, TreeMap<String, Receta>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            recetasPorCategoria = new TreeMap<>();
        }
    }
    
    private void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(recetasPorCategoria);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}