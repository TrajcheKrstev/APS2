import java.util.*;

public class izziv3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] tabela1 = new int[n];
        int[] tabela2 = new int[m];
        for (int i = 0; i < n; i++) {
            tabela1[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            tabela2[i] = sc.nextInt();
        }

        int[] merged = mergeSort(tabela1, tabela2);
        System.out.print(merged[0]);
        for (int i = 1; i < merged.length; i++) {
            System.out.printf(" %d", merged[i]);
        }

    }

    public static int[] mergeSort(int[] t1, int[] t2) {
        int[] novaTabela = new int[t1.length + t2.length];
        int i = 0, j = 0;
        for (int k = 0; k < novaTabela.length; k++) {
            if (i >= t1.length && j < t2.length) {
                novaTabela[k] = t2[j++];
                System.out.print("b");
            } else if (i < t1.length && j >= t2.length) {
                novaTabela[k] = t1[i++];
                System.out.print("a");
            } else if (t1[i] <= t2[j]) {
                novaTabela[k] = t1[i++];
                System.out.print("a");
            } else {
                novaTabela[k] = t2[j++];
                System.out.print("b");
            }

        }
        System.out.println();
        return novaTabela;
    }
}