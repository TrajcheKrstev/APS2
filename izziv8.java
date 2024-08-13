import java.util.*;

class Complex {
    double re;
    double im;

    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public String toString() {
        double tRe = (double) Math.round(re * 100000) / 100000;
        double tIm = (double) Math.round(im * 100000) / 100000;
        if (tIm == 0)
            return tRe + "";
        if (tRe == 0)
            return tIm + "i";
        if (tIm < 0)
            return tRe + "-" + (-tIm) + "i";
        return tRe + "+" + tIm + "i";
    }

    public Complex conj() {
        return new Complex(re, -im);
    }

    // sestevanje
    public Complex plus(Complex b) {
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // odstevanje
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // mnozenje z drugim kompleksnim stevilo
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // mnozenje z realnim stevilom
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    // reciprocna vrednost kompleksnega stevila
    public Complex reciprocal() {
        double scale = re * re + im * im;
        return new Complex(re / scale, -im / scale);
    }

    // deljenje
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    // e^this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    // potenca komplesnega stevila
    public Complex pow(int k) {

        Complex c = new Complex(1, 0);
        for (int i = 0; i < k; i++) {
            c = c.times(this);
        }
        return c;
    }
}

public class izziv8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int velikost = (int) Math.pow(2, Math.ceil(Math.log(2 * n) / Math.log(2)));
        Complex[] prvi = new Complex[velikost];
        Complex[] drugi = new Complex[velikost];
        double temp;
        for (int i = 0; i < velikost; i++) {
            if (i < n) {
                temp = sc.nextDouble();
                prvi[i] = new Complex(temp, 0);
            } else
                prvi[i] = new Complex(0, 0);
        }
        for (int i = 0; i < velikost; i++) {
            if (i < n) {
                temp = sc.nextDouble();
                drugi[i] = new Complex(temp, 0);
            } else
                drugi[i] = new Complex(0, 0);
        }
        Complex[] rezultat = new Complex[velikost];
        Complex[] A = FFT(1, prvi);
        Complex[] B = FFT(1, drugi);
        Complex[] C = new Complex[velikost];
        for (int i = 0; i < velikost; i++) {
            C[i] = A[i].times(B[i]);
        }
        Complex[] c = FFT(-1, C);
        for (int i = 0; i < velikost; i++) {
            System.out.print(c[i].times(1.0 / velikost) + " ");
        }
        System.out.println();
    }

    public static Complex[] FFT(int m, Complex[] a) {
        int n = a.length;

        if (n == 1)
            return a;

        int length = -1;
        if (n % 2 == 0)
            length = n / 2;
        else
            length = n / 2 + 1;

        Complex[] even = new Complex[length];
        Complex[] odd = new Complex[n / 2];
        for (int i = 0; i < n / 2; i++) {
            even[i] = a[2 * i];
            odd[i] = a[2 * i + 1];
        }
        Complex[] q = FFT(m, even);
        Complex[] r = FFT(m, odd);

        Complex[] y = new Complex[n];
        Complex wk = new Complex(1, 0);
        Complex w = new Complex(0, m * 2 * Math.PI / n).exp();
        for (int k = 0; k < n / 2; k++) {
            y[k] = q[k].plus(wk.times(r[k]));
            y[k + n / 2] = q[k].minus(wk.times(r[k]));
            wk = wk.times(w);
        }
        for (int i = 0; i < y.length; i++) {
            System.out.print(y[i] + " ");
        }
        System.out.println();
        return y;
    }
}