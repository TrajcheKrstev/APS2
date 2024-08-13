import java.util.*;

public class izziv10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int[][] tabela = new int[N + 1][K];
        System.out.print("    ");
        for (int i = 1; i <= K; i++)
            System.out.printf("%4d", i);
        System.out.println();
        for (int i = 0; i <= N; i++) {
            System.out.printf("%4d", i);
            for (int j = 0; j < K; j++) {
                if (i == 0)
                    tabela[i][j] = 0;
                else if (i == 1)
                    tabela[i][j] = 1;
                else if (j == 0)
                    tabela[i][j] = i;
                else {
                    int min = Integer.MAX_VALUE;
                    for (int x = 1; x < i; x++) {
                        int stevilo = Math.max(tabela[x - 1][j - 1], tabela[i - x][j]);
                        if (stevilo < min)
                            min = stevilo;
                    }
                    tabela[i][j] = min + 1;
                }
                System.out.printf("%4d", tabela[i][j]);
            }
            System.out.println();
        }
    }
}