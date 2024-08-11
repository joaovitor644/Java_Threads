public class Contador {
    private static int valor = 0;
    private static final Object lock = new Object();

    public static void incrementar() {
      //  synchronized (lock) { 
            valor++;
      //  }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[16];


        for (int i = 0; i < 16; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    incrementar();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < 16; i++) {
            threads[i].join();
        }


        System.out.println("Valor final: " + valor);
    }
}
