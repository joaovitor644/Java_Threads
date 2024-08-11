import java.util.ArrayList;
import java.util.List;

public class MaxDivisoresMultiThread {
    private static final int NUM_THREADS = 4;
    private static final int INTERVALO = 20000;
    private static int maxDivisores = 0;
    private static int numeroComMaisDivisores = 0;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        List<Thread> threads = new ArrayList<>();
        int intervaloPorThread = INTERVALO / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            final int inicio = i * intervaloPorThread + 1;
            final int fim = (i + 1) * intervaloPorThread;

            Thread thread = new Thread(() -> {
                int maxDiv = 0;
                int numComMaxDiv = 0;

                for (int j = inicio; j <= fim; j++) {
                    int divisores = contarDivisores(j);
                    if (divisores > maxDiv) {
                        maxDiv = divisores;
                        numComMaxDiv = j;
                    }
                }

                synchronized (MaxDivisoresMultiThread.class) {
                    if (maxDiv > maxDivisores) {
                        maxDivisores = maxDiv;
                        numeroComMaisDivisores = numComMaxDiv;
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Número com mais divisores: " + numeroComMaisDivisores);
        System.out.println("Quantidade de divisores: " + maxDivisores);
        System.out.println("Tempo de execução: " + (endTime - startTime) + " ms");
    }

    private static int contarDivisores(int numero) {
        int contagem = 0;
        for (int i = 1; i <= numero; i++) {
            if (numero % i == 0) {
                contagem++;
            }
        }
        return contagem;
    }
}
