import java.util.*;

public class izziv1 {
    public static void main(String[] args) {
        System.out.println("     n     |  Linearno  |   Dvojisko");
        System.out.println("-----------+------------+-------------");
        for (int i = 1000; i <= 100000; i += 1000) {
            System.out.printf("%10d | %10d | %10d\n", i, timeLinear(i), timeBinary(i));
        }

        // koda za risanje grafa

        // double[] x1 = new double[100];
        // double[] x2 = new double[100];
        // double[] y1 = new double[100];
        // double[] y2 = new double[100];
        // for (int i = 1000, j = 0; i <= 100000; i += 1000, j++) {
        // x1[j] = i;
        // y1[j] = timeLinear(i);
        // x2[j] = i;
        // y2[j] = timeBinary(i);
        // }

        // StdDraw.setXscale(-1000, 110000);
        // StdDraw.setYscale(-1000, 13000);

        // for (int i = 0; i < 99; i++) {
        // StdDraw.line(x1[i], y1[i], x1[i + 1], y1[i + 1]);
        // StdDraw.line(x2[i], y2[i], x2[i + 1], y2[i + 1]);
        // }
        // StdDraw.text(50000, -500, "Binary Search");
        // StdDraw.text(50000, 8000, "Linear Search", 35);
    }

    public static long timeLinear(int n) {
        int[] tabela = generateTable(n);
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Random rand = new Random();
            int stevilo = rand.nextInt(n + 1) + 1;
            int index = findLinear(tabela, stevilo);
        }
        long executionTime = System.nanoTime() - startTime;
        return executionTime / 1000;
    }

    public static long timeBinary(int n) {
        int[] tabela = generateTable(n);
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            Random rand = new Random();
            int stevilo = rand.nextInt(n + 1) + 1;
            int index = findBinary(tabela, 0, tabela.length - 1, stevilo);
        }
        long executionTime = System.nanoTime() - startTime;
        return executionTime / 1000;
    }

    public static int findLinear(int[] a, int v) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == v)
                return i;
        }
        return -1;
    }

    public static int findBinary(int[] a, int l, int r, int v) {
        while (l <= r) {
            int stevilo = (l + r) / 2;
            if (a[stevilo] < v)
                l = stevilo + 1;
            else if (a[stevilo] > v)
                r = stevilo - 1;
            else
                return stevilo;
        }
        return -1;
    }

    public static int[] generateTable(int n) {
        int[] table = new int[n];
        for (int i = 0; i < n; i++) {
            table[i] = i + 1;
        }
        return table;
    }
}