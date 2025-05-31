package SistemaExperto;
import java.util.Scanner;

public class SistemaExpertoDifuso {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sistema Experto: Evaluación de Riesgo al Conducir ===");
        System.out.print("Introduce la velocidad del vehículo (km/h): ");
        double velocidad = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Introduce el clima (soleado, nublado, lluvioso): ");
        String clima = scanner.nextLine().toLowerCase();

        // fuzzificación: obtener grados de pertenencia
        double velocidadBaja = velocidad <= 40 ? 1 : velocidad <= 60 ? (60 - velocidad) / 20.0 : 0;
        double velocidadAlta = velocidad >= 80 ? 1 : velocidad >= 60 ? (velocidad - 60) / 20.0 : 0;

        double climaBueno = clima.equals("soleado") ? 1 : clima.equals("nublado") ? 0.5 : 0;
        double climaMalo = clima.equals("lluvioso") ? 1 : clima.equals("nublado") ? 0.5 : 0;

        // inferencia difusa: Reglas
        double riesgoBajo = Math.min(velocidadBaja, climaBueno);            // si velocidad baja Y clima bueno = riesgo bajo
        double riesgoMedio = Math.max(Math.min(velocidadBaja, climaMalo),   // velocidad baja y clima malo
                                      Math.min(velocidadAlta, climaBueno)); // o velocidad alta y clima bueno
        double riesgoAlto = Math.min(velocidadAlta, climaMalo);             // velocidad alta y clima malo = riesgo alto

        // defuzzificación (simplificada): elegir el mayor grado
        String riesgoFinal;
        if (riesgoAlto >= riesgoMedio && riesgoAlto >= riesgoBajo) {
            riesgoFinal = "ALTO";
        } else if (riesgoMedio >= riesgoBajo) {
            riesgoFinal = "MEDIO";
        } else {
            riesgoFinal = "BAJO";
        }

        System.out.println("\n--- Resultados ---");
        System.out.printf("Grado de Riesgo Bajo: %.2f\n", riesgoBajo);
        System.out.printf("Grado de Riesgo Medio: %.2f\n", riesgoMedio);
        System.out.printf("Grado de Riesgo Alto: %.2f\n", riesgoAlto);
        System.out.println("Evaluación final del riesgo: " + riesgoFinal);

        scanner.close();
    }
}

