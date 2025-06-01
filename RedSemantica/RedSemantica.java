package RedSemantica;
import java.util.*;

class RedSemantica {
    Map<String, NodoConcepto> conceptos;

    RedSemantica() {
        conceptos = new HashMap<>();
    }

    NodoConcepto obtenerOcrearConcepto(String nombre) {
        return conceptos.computeIfAbsent(nombre, NodoConcepto::new);
    }

    void agregarRelacion(String origen, String tipo, String destino) {
        NodoConcepto nodoOrigen = obtenerOcrearConcepto(origen);
        NodoConcepto nodoDestino = obtenerOcrearConcepto(destino);
        nodoOrigen.agregarRelacion(tipo, nodoDestino);
    }

    boolean consultar(String origen, String tipo, String destino) {
        NodoConcepto nodoOrigen = conceptos.get(origen);
        if (nodoOrigen == null) return false;

        Set<NodoConcepto> visitados = new HashSet<>();
        Queue<NodoConcepto> cola = new LinkedList<>();
        cola.add(nodoOrigen);

        while (!cola.isEmpty()) {
            NodoConcepto actual = cola.poll();
            if (actual.nombre.equals(destino)) return true;

            visitados.add(actual);
            for (NodoConcepto vecino : actual.obtenerRelaciones(tipo)) {
                if (!visitados.contains(vecino)) {
                    cola.add(vecino);
                }
            }
        }

        return false;
    }

    void mostrarRed() {
        for (NodoConcepto concepto : conceptos.values()) {
            for (String tipo : concepto.relaciones.keySet()) {
                for (NodoConcepto destino : concepto.relaciones.get(tipo)) {
                    System.out.println(concepto + " --[" + tipo + "]--> " + destino);
                }
            }
        }
    }
}
