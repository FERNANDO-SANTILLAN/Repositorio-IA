package RedSemantica;
import java.util.*;

class NodoConcepto {
    String nombre;
    Map<String, List<NodoConcepto>> relaciones;

    NodoConcepto(String nombre) {
        this.nombre = nombre;
        this.relaciones = new HashMap<>();
    }

    void agregarRelacion(String tipo, NodoConcepto destino) {
        relaciones.putIfAbsent(tipo, new ArrayList<>());
        relaciones.get(tipo).add(destino);
    }

    List<NodoConcepto> obtenerRelaciones(String tipo) {
        return relaciones.getOrDefault(tipo, new ArrayList<>());
    }

    @Override
    public String toString() {
        return nombre;
    }
}
