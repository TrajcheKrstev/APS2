import java.lang.reflect.Array;
import java.util.*;

public class izziv4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] tabela = new int[n];
        for (int i = 0; i < n; i++) {
            tabela[i] = sc.nextInt();
            // System.out.println(stEnic(tabela[i]));
        }
        uredi(tabela);
    }

    public static void uredi(int[] tabela) {
        int[] tabelaEnic = new int[tabela.length];
        for (int i = 0; i < tabela.length; i++) {
            tabelaEnic[i] = stEnic(tabela[i]);
        }
        int[] c = new int[32];
        for (int i = 0; i < tabela.length; i++) {
            c[tabelaEnic[i]]++;
        }
        for (int i = 1; i < 32; ++i) {
            c[i] += c[i - 1];
        }
        int[] tabela2 = new int[tabela.length];
        for (int i = tabela2.length - 1; i >= 0; i--) {
            tabela2[c[tabelaEnic[i]] - 1] = tabela[i];
            --c[tabelaEnic[i]];
            System.out.println("(" + tabela[i] + "," + (c[tabelaEnic[i]]) + ")");
        }
        System.out.print(tabela2[0]);
        for (int i = 1; i < tabela2.length; i++) {
            System.out.print(" " + tabela2[i]);
        }
        System.out.println();
    }

    public static int stEnic(int n) {
        int stevec = 0;
        while (n > 0) {
            if (n % 2 == 1)
                stevec++;
            n /= 2;
        }
        return stevec;
    }
}