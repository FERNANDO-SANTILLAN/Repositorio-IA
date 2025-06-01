package RedSemantica;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        RedSemantica red = new RedSemantica();

        // Relaciones predefinidas (se pueden cambiar o añadir mas)
        red.agregarRelacion("canario", "es_un", "pajaro");
        red.agregarRelacion("pajaro", "es_un", "animal");
        red.agregarRelacion("canario", "puede", "volar");
        red.agregarRelacion("pajaro", "tiene", "alas");

        int opcion;
        do {
            System.out.println("\n--- Red Semántica ---");
            System.out.println("1. Mostrar relaciones");
            System.out.println("2. Consultar relación");
            System.out.println("3. Agregar nueva relación");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nRelaciones actuales en la red:");
                    red.mostrarRed();
                    break;

                case 2:
                    System.out.print("Introduce el concepto origen: ");
                    String origen = entrada.nextLine();
                    System.out.print("Introduce el tipo de relación (ej. es_un, puede, tiene): ");
                    String tipo = entrada.nextLine();
                    System.out.print("Introduce el concepto destino: ");
                    String destino = entrada.nextLine();

                    boolean resultado = red.consultar(origen, tipo, destino);
                    System.out.println("\n¿" + origen + " " + tipo + " " + destino + "? " + (resultado ? "Sí" : "No"));
                    break;

                case 3:
                    System.out.print("Introduce el concepto origen: ");
                    String nuevoOrigen = entrada.nextLine();
                    System.out.print("Introduce el tipo de relación: ");
                    String nuevoTipo = entrada.nextLine();
                    System.out.print("Introduce el concepto destino: ");
                    String nuevoDestino = entrada.nextLine();

                    red.agregarRelacion(nuevoOrigen, nuevoTipo, nuevoDestino);
                    System.out.println("Relación agregada correctamente.");
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        entrada.close();
    }
}

