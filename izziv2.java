import java.util.*;

public class izziv2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] tabela = new int[n];
        for (int i = 0; i < n; i++) {
            tabela[i] = sc.nextInt();
        }
        HeapSort(tabela, n);
    }

    public static void HeapSort(int[] t, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            pogrezni(t, i, n);
        }
        print(t, n);
        int r = n - 1;
        while (r > 0) {
            int x = t[0];
            t[0] = t[r];
            t[r] = x;
            pogrezni(t, 0, r);
            print(t, r);
            r--;
        }
    }

    public static void pogrezni(int t[], int i, int r) {
        int largest = i;
        int l = 2 * i + 1;
        int d = 2 * i + 2;
        if (l < r && t[l] > t[largest])
            largest = l;
        if (d < r && t[d] > t[largest])
            largest = d;
        if (largest != i) {
            int x = t[i];
            t[i] = t[largest];
            t[largest] = x;
            pogrezni(t, largest, r);
        }
    }

    public static void print(int[] t, int n) {
        int potenca = 1;
        int j = 1;
        for (int i = 0; i < n; i++) {
            if (j == 0) {
                System.out.printf("| ");
                j = potenca << 1;
                potenca = j;
            }
            System.out.print(t[i]);
            if (i < n - 1)
                System.out.print(" ");
            j--;
        }
        System.out.println();
    }
}