import java.util.concurrent.ThreadLocalRandom;

public class Nave implements Runnable {
    final String civilizacion;   // "Zorg" o "Blip"
    final String nombreVisible;  // para el print
    final Controlando control;

    // Estado que sólo modifica la SalaDeControl en secciones sincronizadas
    int posicion = 0;

    public Nave(String civilizacion, String nombreVisible, Controlando control) {
        this.civilizacion = civilizacion;
        this.nombreVisible = nombreVisible;
        this.control = control;
    }

    @Override
    public void run() {
        int pasosMax = control.getPasosMax();
        for (int tirada = 1; tirada <= pasosMax; tirada++) {
            int avance = ThreadLocalRandom.current().nextInt(1, 11); // 1..10
            control.registrarAvance(this, tirada, avance);
            try { Thread.sleep(5); } catch (InterruptedException ignored) {}
        }
        control.notificarFin(this);
    }
}
