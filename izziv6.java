import java.util.Arrays;
import java.util.Scanner;

class Matrix {

    private int[][] m;

    public int n; // only square matrices

    public Matrix(int n) {

        this.n = n;

        m = new int[n][n];

    }

    // set value at i,j
    public void setV(int i, int j, int val) {

        m[i][j] = val;

    }

    // get value at index i,j
    public int v(int i, int j) {

        return m[i][j];

    }

    // return a square submatrix from this
    public Matrix getSubmatrix(int startRow, int startCol, int dim) {

        Matrix subM = new Matrix(dim);

        for (int i = 0; i < dim; i++)

            for (int j = 0; j < dim; j++)

                subM.setV(i, j, m[startRow + i][startCol + j]);

        return subM;

    }

    // write this matrix as a submatrix from b (useful for the result of
    // multiplication)
    public void putSubmatrix(int startRow, int startCol, Matrix b) {

        for (int i = 0; i < b.n; i++)

            for (int j = 0; j < b.n; j++)

                setV(startRow + i, startCol + j, b.v(i, j));

    }

    // matrix addition
    public Matrix sum(Matrix b) {

        Matrix c = new Matrix(n);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                c.setV(i, j, m[i][j] + b.v(i, j));

            }

        }

        return c;

    }

    // matrix subtraction
    public Matrix sub(Matrix b) {

        Matrix c = new Matrix(n);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                c.setV(i, j, m[i][j] - b.v(i, j));

            }

        }

        return c;

    }

    public int vsota() {
        int vsota = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                vsota += this.v(i, j);
            }
        }
        return vsota;
    }

    // simple multiplication
    public Matrix mult(Matrix b) {
        // TODO
        Matrix c = new Matrix(b.n);
        for (int i = 0; i < b.n; i++) {
            for (int j = 0; j < b.n; j++) {
                int c_ij = 0;
                for (int k = 0; k < b.n; k++) {
                    c_ij += this.v(i, k) * b.v(k, j);
                } // end of k loop
                c.setV(i, j, c_ij);
            } // end of j loop
        }
        return c;
    }

    // Strassen multiplication
    public Matrix multStrassen(Matrix b, int cutoff) {
        // TODO
        Matrix c = new Matrix(n);
        if (n == cutoff) {
            c = this.mult(b);
        } else {
            Matrix a11 = this.getSubmatrix(0, 0, n / 2);
            Matrix a12 = this.getSubmatrix(0, n / 2, n / 2);
            Matrix a21 = this.getSubmatrix(n / 2, 0, n / 2);
            Matrix a22 = this.getSubmatrix(n / 2, n / 2, n / 2);
            Matrix b11 = b.getSubmatrix(0, 0, n / 2);
            Matrix b12 = b.getSubmatrix(0, n / 2, n / 2);
            Matrix b21 = b.getSubmatrix(n / 2, 0, n / 2);
            Matrix b22 = b.getSubmatrix(n / 2, n / 2, n / 2);

            Matrix vsotaA11_A22 = a11.sum(a22);
            Matrix vsotaA11_A12 = a11.sum(a12);
            Matrix vsotaB11_B22 = b11.sum(b22);
            Matrix vsotaB21_B22 = b21.sum(b22);
            Matrix vsotaB12_B11 = b12.sum(b11);
            Matrix vsotaA22_A21 = a22.sum(a21);
            Matrix subA12_A22 = a12.sub(a22);
            Matrix subB11_B21 = b21.sub(b11);
            Matrix subA21_A11 = a21.sub(a11);
            Matrix subB22_B12 = b12.sub(b22);
            Matrix m1 = vsotaA11_A22.multStrassen(vsotaB11_B22, cutoff);
            Matrix m2 = vsotaA22_A21.multStrassen(b11, cutoff);
            Matrix m3 = a11.multStrassen(subB22_B12, cutoff);
            Matrix m4 = a22.multStrassen(subB11_B21, cutoff);
            Matrix m5 = vsotaA11_A12.multStrassen(b22, cutoff);
            Matrix m6 = subA21_A11.multStrassen(vsotaB12_B11, cutoff);
            Matrix m7 = subA12_A22.multStrassen(vsotaB21_B22, cutoff);

            System.out.println("m1: " + m1.vsota());
            System.out.println("m2: " + m2.vsota());
            System.out.println("m3: " + m3.vsota());
            System.out.println("m4: " + m4.vsota());
            System.out.println("m5: " + m5.vsota());
            System.out.println("m6: " + m6.vsota());
            System.out.println("m7: " + m7.vsota());

            Matrix c11 = m1.sum(m4).sub(m5).sum(m7);
            Matrix c12 = m3.sum(m5);
            Matrix c21 = m2.sum(m4);
            Matrix c22 = m1.sub(m2).sum(m3).sum(m6);

            c.putSubmatrix(0, 0, c11);
            c.putSubmatrix(0, n / 2, c12);
            c.putSubmatrix(n / 2, 0, c21);
            c.putSubmatrix(n / 2, n / 2, c22);

        }
        return c;
    }

}

public class izziv6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int meja = sc.nextInt();
        Matrix a = new Matrix(n);
        Matrix b = new Matrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a.setV(i, j, sc.nextInt());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b.setV(i, j, sc.nextInt());
            }
        }

        Matrix c = a.multStrassen(b, meja);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(c.v(i, j) + " ");
            }
            System.out.println();
        }

    }

}
