public class SumarPrimeros50NumerosPrimos {

    public static void main(String[] args) {

        final int CANTIDAD_PRIMOS = 50;
        int suma = 0;
        int contadorPrimos = 0;
        int numeroActual = 2; // El primer número primo

        System.out.println("=== Suma de los 50 primeros números primos ===\n");
        System.out.print("Números primos encontrados: ");

        while (contadorPrimos < CANTIDAD_PRIMOS) {

            boolean esPrimo = true;

            for (int j = 2; j <= Math.sqrt(numeroActual); j++) {
                if (numeroActual % j == 0) {
                    esPrimo = false;
                    break;
                }
            }

            if (esPrimo) {
                suma += numeroActual;
                contadorPrimos++;
                System.out.print(numeroActual + " ");
            }
            numeroActual++;
        }
        System.out.println("\n");
        System.out.println("Suma total: " + suma);

    }
}
