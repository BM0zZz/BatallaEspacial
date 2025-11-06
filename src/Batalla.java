public class Batalla {
    public static void main(String[] args) throws InterruptedException {
        int pasos = 11;
        int totalNaves = 2;
        Controlando control = new Controlando(pasos, totalNaves);

        Thread zorg = new Thread(new Nave("zorg", "zorg", control));
        Thread blip = new Thread(new Nave("blip", "blip", control));

        zorg.start();
        blip.start();
        zorg.join();
        blip.join();
    }
}
