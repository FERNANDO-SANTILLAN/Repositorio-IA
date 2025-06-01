package MetodosdeBusqueda;
import java.util.*;

class Nodo {
    String nombre;
    int costo;
    Nodo padre;
    
    public Nodo(String nombre, int costo, Nodo padre) {
        this.nombre = nombre;
        this.costo = costo;
        this.padre = padre; 
    }
}

class Grafo {
    private final Map<String, List<Nodo>> adyacencia = new HashMap<>();
    
    public void agregarArista(String origen, String destino, int costo) {
        adyacencia.putIfAbsent(origen, new ArrayList<>());
        adyacencia.get(origen).add(new Nodo(destino, costo, null));
    }
    
    public ResultadoBusqueda busquedaDFS(String inicio, String objetivo) {
        Set<String> visitados = new HashSet<>();
        Stack<Nodo> stack = new Stack<>();
        stack.push(new Nodo(inicio, 0, null));
        int nodosExplorados = 0;
        long tiempoInicio = System.nanoTime();
        
        while (!stack.isEmpty()) {
            Nodo actual = stack.pop();
            if (visitados.contains(actual.nombre)) continue;
            visitados.add(actual.nombre);
            nodosExplorados++;
            
            if (actual.nombre.equals(objetivo)) {
                long tiempoFin = System.nanoTime();
                return new ResultadoBusqueda(reconstruirCamino(actual), nodosExplorados, tiempoFin - tiempoInicio);
            }
            
            for (Nodo vecino : adyacencia.getOrDefault(actual.nombre, new ArrayList<>())) {
                stack.push(new Nodo(vecino.nombre, 0, actual));
            }
        }
        return null;
    }
    
    public ResultadoBusqueda busquedaBFS(String inicio, String objetivo) {
        Set<String> visitados = new HashSet<>();
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(new Nodo(inicio, 0, null));
        int nodosExplorados = 0;
        long tiempoInicio = System.nanoTime();
        
        while (!queue.isEmpty()) {
            Nodo actual = queue.poll();
            if (visitados.contains(actual.nombre)) continue;
            visitados.add(actual.nombre);
            nodosExplorados++;
            
            if (actual.nombre.equals(objetivo)) {
                long tiempoFin = System.nanoTime();
                return new ResultadoBusqueda(reconstruirCamino(actual), nodosExplorados, tiempoFin - tiempoInicio);
            }
            
            for (Nodo vecino : adyacencia.getOrDefault(actual.nombre, new ArrayList<>())) {
                queue.add(new Nodo(vecino.nombre, 0, actual));
            }
        }
        return null;
    }
    
    public ResultadoBusqueda busquedaUCS(String inicio, String objetivo) {
        PriorityQueue<Nodo> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.costo));
        pq.add(new Nodo(inicio, 0, null));
        Map<String, Integer> costos = new HashMap<>();
        costos.put(inicio, 0);
        int nodosExplorados = 0;
        long tiempoInicio = System.nanoTime();
        
        while (!pq.isEmpty()) {
            Nodo actual = pq.poll();
            nodosExplorados++;
            
            if (actual.nombre.equals(objetivo)) {
                long tiempoFin = System.nanoTime();
                return new ResultadoBusqueda(reconstruirCamino(actual), nodosExplorados, tiempoFin - tiempoInicio);
            }
            
            for (Nodo vecino : adyacencia.getOrDefault(actual.nombre, new ArrayList<>())) {
                int nuevoCosto = actual.costo + vecino.costo;
                if (!costos.containsKey(vecino.nombre) || nuevoCosto < costos.get(vecino.nombre)) {
                    costos.put(vecino.nombre, nuevoCosto);
                    pq.add(new Nodo(vecino.nombre, nuevoCosto, actual));
                }
            }
        }
        return null;
    }
    
    private List<String> reconstruirCamino(Nodo nodo) {
        List<String> camino = new ArrayList<>();
        while (nodo != null) {
            camino.add(nodo.nombre);
            nodo = nodo.padre;
        }
        Collections.reverse(camino);
        return camino;
    }
}

class ResultadoBusqueda {
    List<String> camino;
    int nodosExplorados;
    long tiempoEjecucion;
    
    public ResultadoBusqueda(List<String> camino, int nodosExplorados, long tiempoEjecucion) {
        this.camino = camino;
        this.nodosExplorados = nodosExplorados;
        this.tiempoEjecucion = tiempoEjecucion;
    }
    
    @Override
    public String toString() {
        return "Camino: " + camino + ", Nodos explorados: " + nodosExplorados + ", Tiempo: " + tiempoEjecucion + " ns";
    }
}

public class ComparacionBusqueda {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.agregarArista("A", "B", 1);
        grafo.agregarArista("A", "C", 4);
        grafo.agregarArista("B", "D", 2);
        grafo.agregarArista("C", "D", 1);
        grafo.agregarArista("D", "E", 5);
        
        System.out.println("DFS: " + grafo.busquedaDFS("A", "E"));
        System.out.println("BFS: " + grafo.busquedaBFS("A", "E"));
        System.out.println("UCS: " + grafo.busquedaUCS("A", "E"));
    }
}
