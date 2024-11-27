import java.util.Random;
import java.util.Scanner;

public class Main {
    private Random rand;

    private Random random;

    public void UniformRandomGenerator() {
        this.random = new Random();
    }

    /**
     * Generuje liczbę zmiennoprzecinkową o rozkładzie jednostajnym w zakresie [min, max).
     */
    public double generate(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());

        
        int count = 0;
        for(int i = 1; i <= n; i++){
            int x = rand.nextInt( b - a - 1) + a;
            double xx = rand.nextDouble();
            int y = rand.nextInt(m - 1);
            double yy = rand.nextDouble();
            xx += x;
            yy += y;
            if(valueOfFunction(xx) <= yy){
                count++;
            }
        }
        double apro = ((b - a) * m * count) / (1.0 * n);
        System.out.println(apro);
    }

    public static double valueOfFunction(double x){
        return Math.pow(x, 1.0/3.0);
    }

}