import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter x: ");
        double x = in.nextDouble();
        System.out.print("Enter k: ");
        int k = in.nextInt();
        in.close();

        double answer = Math.exp(x);
        System.out.printf("Not my Answer:\t%." + k + "f%n", answer);

        double approximation = Exponenta.calculateExponential(x, k);
        System.out.printf("My Answer:\t\t%." + k + "f%n", approximation);
    }
}

class Exponenta {
    public static double calculateExponential(double x, int k) {
        double result = 1.0;
        double term = 1.0;
        int i = 1;
        double tolerance = Math.pow(10, -k);

        do {
            term *= x / i;
            result += term;
            i++;
        } while (Math.abs(term) > tolerance);

        return result;
    }
}
