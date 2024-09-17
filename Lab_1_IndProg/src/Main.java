import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter x");
        double x = in.nextDouble();
        System.out.println("Enter k");
        int k = in.nextInt();

        double Answer = Math.exp(x);

        String formattedAnswer = String.format("%." + k + "f", Answer);
        System.out.println("Not my Answer\t" + formattedAnswer);

        double myAnswer = 1.0;
        double term = 1.0;
        int i = 1;

        do {
            term *= x / i;
            myAnswer += term;
            i++;
        } while (Math.abs(term) >= Math.pow(10, -k));

        String formattedMyAnswer = String.format("%." + k + "f", myAnswer);
        System.out.println("My Answer\t\t" + formattedMyAnswer);
    }
}
