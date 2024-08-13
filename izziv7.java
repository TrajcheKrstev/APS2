import java.util.*;

public class izziv7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = n;
        while (true) {
            p = getNextPrime(p);
            int[] koreni = findPrimitiveRoots(p, n);
            if (koreni[0] != 0) {
                System.out.print(p + ":");
                for (int i = 0; i < koreni.length; i++) {
                    if (koreni[i] == 0)
                        break;
                    System.out.print(" " + koreni[i]);
                }
                System.out.println();

                int w = koreni[0];
                int[][] matrix = new int[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int stevilo = 0;
                        if (i > 1) {
                            stevilo = matrix[i - 1][j] * matrix[1][j] % p;
                        } else {
                            stevilo = (int) Math.pow(w, i * j) % p;
                        }
                        matrix[i][j] = stevilo;
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.printf("%2d ", matrix[i][j]);
                    }
                    System.out.println();
                }
                break;
            }

        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int getNextPrime(int n) {
        while (true) {
            n++;
            if (isPrime(n)) {
                return n;
            }
        }
    }

    public static int[] findPrimitiveRoots(int p, int n) {
        int[] primitiveRoots = new int[n];
        int index = 0;
        for (int i = 2; i < p; i++) {
            if (isPrimitiveRoot(i, p, n)) {
                primitiveRoots[index++] = i;
                if (index == n) {
                    break;
                }
            }
        }
        return primitiveRoots;
    }

    public static boolean isPrimitiveRoot(int g, int p, int n) {
        if ((long) Math.pow(g, n) % p != 1)
            return false;
        long current = 1;
        for (int i = 1; i < n; i++) {
            current = (long) Math.pow(g, i) % p;
            if (current == 1) {
                return false;
            }
        }
        return true;
    }
}