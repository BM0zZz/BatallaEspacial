public class Batalla {
    public static void main(String[] args) throws InterruptedException {
        int pasos = 11;
        int totalNaves = 2;
        Controlando control = new Controlando(pasos, totalNaves);

        Thread zorg = new Thread(new Nave("SuperPepina", "SuperPepina", control));
        Thread blip = new Thread(new Nave("Aldeana", "Aldeana", control));

        zorg.start();
        blip.start();
        zorg.join();
        blip.join();
    }
}
