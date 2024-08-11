public class MaxDivisoresSingleThread {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int maxDivisores = 0;
        int numeroComMaisDivisores = 0;

        for (int i = 1; i <= 20000; i++) {
            int divisores = contarDivisores(i);
            if (divisores > maxDivisores) {
                maxDivisores = divisores;
                numeroComMaisDivisores = i;
            }
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
