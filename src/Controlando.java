import java.util.*;
import java.util.stream.Collectors;

public class Controlando {
    private final int pasosMax;
    private int navesTerminadas = 0;
    private final int totalNaves;


    private final Map<String, Integer> avancePorCiv = new HashMap<>();

    public Controlando(int pasosMax, int totalNaves) {
        this.pasosMax = pasosMax;
        this.totalNaves = totalNaves;
    }

    public int getPasosMax() { return pasosMax; }


    public synchronized void registrarAvance(Nave nave, int tirada, int avance) {
        nave.posicion += avance;
        avancePorCiv.merge(nave.civilizacion, avance, Integer::sum);

        System.out.println("Nave " + nave.nombreVisible + ": Tirada " + tirada
                + " - He avanzado " + nave.posicion + " metros!");
    }

    public synchronized void notificarFin(Nave nave) {
        navesTerminadas++;
        if (navesTerminadas == totalNaves) {

            System.out.println("Batalla espacial concluida.");

            // Mostrar totales por civilización
            for (Map.Entry<String, Integer> e : avancePorCiv.entrySet()) {
                System.out.println("Total " + e.getKey() + ": " + e.getValue() + " metros");
            }

            // Calcular ganador o empate
            if (!avancePorCiv.isEmpty()) {
                int max = Collections.max(avancePorCiv.values());
                var top = avancePorCiv.entrySet()
                        .stream()
                        .filter(en -> en.getValue() == max)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

                if (top.size() == 1) {
                    System.out.println("Esta batalla la ha ganado la civilización " + top.get(0));
                } else {
                    System.out.println("Empate entre: " + String.join(" y ", top));
                }
            }
        }
    }
}
