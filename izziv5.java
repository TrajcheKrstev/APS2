import java.util.*;

public class izziv5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String niz = sc.nextLine();
        String[] nizArray = niz.split(" ");
        int[] input = new int[nizArray.length];
        for (int i = 0; i < input.length; i++) {
            input[i] = Integer.parseInt(nizArray[i]);
        }

        findMax(input, 0, input.length - 1);
    }

    public static int findMax(int[] tabela, int left, int right) {
        if (left == right) {
            print(tabela, left, right, tabela[left]);
            return tabela[left];
        }
        int mid = (right + left) / 2;
        int maxLeft = findMax(tabela, left, mid);
        int maxRight = findMax(tabela, mid + 1, right);
        int maxCrossed = findMaxCrossed(tabela, left, mid, right);
        int max = Math.max(maxRight, Math.max(maxLeft, maxCrossed));
        print(tabela, left, right, max);
        return max;
    }

    public static int findMaxCrossed(int[] tabela, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += tabela[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += tabela[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        int max = leftSum + rightSum;
        return max;
    }

    public static void print(int[] tabela, int left, int right, int vsota) {
        System.out.print("[");
        for (int i = left; i < right; i++) {
            System.out.printf("%d, ", tabela[i]);
        }
        System.out.printf("%d]: %d\n", tabela[right], vsota);
    }
}