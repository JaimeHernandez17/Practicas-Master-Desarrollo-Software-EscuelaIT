public class SumarNumerosPrimosHasta50 {

    public static void main(String[] args) {

        final int LIMITE = 50;
        int suma = 0;

        System.out.println("=== Suma de números primos entre los primeros 50 números ===\n");
        System.out.print("Números primos encontrados: ");

        for (int i = 2; i <= LIMITE; i++) {

            boolean esPrimo = true;

            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    esPrimo = false;
                    break;
                }
            }

            if (esPrimo) {
                suma += i;
                System.out.print(i + " ");
            }
        }
        System.out.println("\n");
        System.out.println("Suma total: " + suma);

    }
}
