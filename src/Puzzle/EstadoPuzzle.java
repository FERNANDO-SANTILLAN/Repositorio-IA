package Puzzle;
import java.util.*;

class EstadoPuzzle {
    int[][] tablero;
    String pasos;
    int filaCero, columnaCero;

    EstadoPuzzle(int[][] tablero, String pasos) {
        this.tablero = new int[3][3];
        for (int i = 0; i < 3; i++)
            this.tablero[i] = tablero[i].clone();
        this.pasos = pasos;
        encontrarCero();
    }

    void encontrarCero() {
        for (int fila = 0; fila < 3; fila++)
            for (int col = 0; col < 3; col++)
                if (tablero[fila][col] == 0) {
                    filaCero = fila;
                    columnaCero = col;
                }
    }

    boolean esMeta() {
        int[][] meta = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
        };
        return Arrays.deepEquals(this.tablero, meta);
    }

    List<EstadoPuzzle> obtenerVecinos() {
        List<EstadoPuzzle> vecinos = new ArrayList<>();
        int[] direccionFila = {-1, 1, 0, 0};
        int[] direccionCol = {0, 0, -1, 1};
        String[] movimientos = {"U", "D", "L", "R"};

        for (int i = 0; i < 4; i++) {
            int nuevaFila = filaCero + direccionFila[i];
            int nuevaColumna = columnaCero + direccionCol[i];

            if (nuevaFila >= 0 && nuevaFila < 3 && nuevaColumna >= 0 && nuevaColumna < 3) {
                int[][] nuevoTablero = new int[3][3];
                for (int f = 0; f < 3; f++)
                    nuevoTablero[f] = tablero[f].clone();

                nuevoTablero[filaCero][columnaCero] = nuevoTablero[nuevaFila][nuevaColumna];
                nuevoTablero[nuevaFila][nuevaColumna] = 0;

                vecinos.add(new EstadoPuzzle(nuevoTablero, pasos + movimientos[i]));
            }
        }
        return vecinos;
    }

    int heuristicaManhattan() {
        int distancia = 0;
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                int valor = tablero[fila][col];
                if (valor != 0) {
                    int filaObjetivo = (valor - 1) / 3;
                    int colObjetivo = (valor - 1) % 3;
                    distancia += Math.abs(fila - filaObjetivo) + Math.abs(col - colObjetivo);
                }
            }
        }
        return distancia;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EstadoPuzzle)) return false;
        EstadoPuzzle otro = (EstadoPuzzle) o;
        return Arrays.deepEquals(this.tablero, otro.tablero);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tablero);
    }

    void imprimirTablero() {
        for (int[] fila : tablero) {
            for (int num : fila) {
                System.out.print((num == 0 ? " " : num) + " ");
            }
            System.out.println();
        }
        System.out.println("Pasos: " + pasos + "\n");
    }
}
