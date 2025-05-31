package Puzzle;
import java.util.*;

public class Main {

    public static void busquedaAnchura(EstadoPuzzle inicio) {
        Queue<EstadoPuzzle> cola = new LinkedList<>();
        Set<EstadoPuzzle> visitados = new HashSet<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            EstadoPuzzle actual = cola.poll();

            if (actual.esMeta()) {
                System.out.println("¡Solución encontrada con búsqueda por anchura!");
                actual.imprimirTablero();
                return;
            }

            for (EstadoPuzzle vecino : actual.obtenerVecinos()) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }

        System.out.println("No se encontró solución con búsqueda por anchura.");
    }

    public static void busquedaProfundidad(EstadoPuzzle inicio, int limite) {
        Stack<EstadoPuzzle> pila = new Stack<>();
        Set<EstadoPuzzle> visitados = new HashSet<>();

        pila.push(inicio);
        visitados.add(inicio);

        while (!pila.isEmpty()) {
            EstadoPuzzle actual = pila.pop();

            if (actual.esMeta()) {
                System.out.println("¡Solución encontrada con búsqueda por profundidad!");
                actual.imprimirTablero();
                return;
            }

            if (actual.pasos.length() < limite) {
                for (EstadoPuzzle vecino : actual.obtenerVecinos()) {
                    if (!visitados.contains(vecino)) {
                        visitados.add(vecino);
                        pila.push(vecino);
                    }
                }
            }
        }

        System.out.println("No se encontró solución con búsqueda por profundidad.");
    }

    public static void busquedaAEstrella(EstadoPuzzle inicio) {
        PriorityQueue<EstadoPuzzle> cola = new PriorityQueue<>(Comparator.comparingInt(e -> e.pasos.length() + e.heuristicaManhattan()));
        Set<EstadoPuzzle> visitados = new HashSet<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            EstadoPuzzle actual = cola.poll();

            if (actual.esMeta()) {
                System.out.println("¡Solución encontrada con búsqueda A* (heurística Manhattan)!");
                actual.imprimirTablero();
                return;
            }

            for (EstadoPuzzle vecino : actual.obtenerVecinos()) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }

        System.out.println("No se encontró solución con búsqueda A*.");
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Estado inicial del puzzle
        int[][] inicial = {
            {2, 8, 3},
            {1, 6, 4},
            {7, 0, 5}
        };

        EstadoPuzzle inicio = new EstadoPuzzle(inicial, "");

        System.out.println("==== Menú de búsqueda para el 8-Puzzle ====");
        System.out.println("1. Búsqueda por anchura (BFS)");
        System.out.println("2. Búsqueda por profundidad (DFS)");
        System.out.println("3. Búsqueda heurística (A*)");
        System.out.print("Seleccione una opción (1-3): ");

        int opcion = entrada.nextInt();

        System.out.println("\nEstado inicial:");
        inicio.imprimirTablero();

        switch (opcion) {
            case 1:
                busquedaAnchura(inicio);
                break;
            case 2:
                busquedaProfundidad(inicio, 30);
                break;
            case 3:
                busquedaAEstrella(inicio);
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
}

