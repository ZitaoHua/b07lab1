import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Driver 
{
    public static void main(String[] args) throws IOException 
    {
        Polynomial p1 = new Polynomial(new double[]{6, -2, 5}, new int[]{0, 1, 3});   //6-2x+5x^3
        Polynomial p2 = new Polynomial(new double[]{1, 1}, new int[]{0, 1});          //1+x
        System.out.println("Polynomial p1: " + p1);
        System.out.println("Polynomial p2: " + p2);
        //evaluate
        System.out.println("p1(2) = " + p1.evaluate(2));
        System.out.println("p2(2) = " + p2.evaluate(2));
        //multiply
        Polynomial product = p1.multiply(p2);
        System.out.println("p1 * p2 = " + product);
        File file = new File("poly.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("5-3x2+7x8");
        writer.close();
        Polynomial polyFromFile = new Polynomial(file);
        System.out.println("Polynomial read from file: " + polyFromFile);
        polyFromFile.saveToFile("out.txt");
        System.out.println("Polynomial saved to out.txt");
    }
}