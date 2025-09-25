import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException 
    {
        Polynomial p = new Polynomial();
        System.out.println("p(3) = " + p.evaluate(3));
        double[] c1 = {6, -2, 5};   // 6 - 2x + 5x^3
        int[] e1 = {0, 1, 3};
        Polynomial p1 = new Polynomial(c1, e1);

        double[] c2 = {-9}; // -9x^4
        int[] e2 = {4};
        Polynomial p2 = new Polynomial(c2, e2);

        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));

        if (s.hasRoot(1)) 
        {
            System.out.println("1 is a root of s");
        } 
        else 
        {
            System.out.println("1 is not a root of s");
        }
        Polynomial m = p1.multiply(p2);
        System.out.println("p1 * p2 = " + m);
        File f = new File("poly.txt");   // poly.txt should contain something like: 5-3x2+7x8
        Polynomial fromFile = new Polynomial(f);
        System.out.println("from file: " + fromFile);

        // test saveToFile
        fromFile.saveToFile("out.txt");
        System.out.println("Polynomial saved to out.txt");
    }
}
