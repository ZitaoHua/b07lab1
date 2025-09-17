import java.lang.Math;

public class Polynomial 
{
    private double[] coeffi;
    public Polynomial() 
    {
        this.coeffi = new double[]{0};
    }

    public Polynomial(double[] argu) 
    {
        this.coeffi = new double[argu.length];
        for (int i = 0; i < argu.length; i++) 
        {
            this.coeffi[i] = argu[i];
        }
    }

    public Polynomial add(Polynomial argu) 
    {
        int newLength = Math.max(this.coeffi.length, argu.coeffi.length);
        double[] newCoeffi = new double[newLength];
        for (int i = 0; i < this.coeffi.length; i++) 
        {
            newCoeffi[i] = this.coeffi[i];
        }
        for (int i = 0; i < argu.coeffi.length; i++) 
        {
            newCoeffi[i] += argu.coeffi[i];
        }
        return new Polynomial(newCoeffi);
    }
    
    public double evaluate(double argu) 
    {
        double result = 0;
        for (int i = 0; i < this.coeffi.length; i++) 
        {
            result += Math.pow(argu, i) * this.coeffi[i];
        }
        return result;
    }
    
    public boolean hasRoot(double argu) 
    {
        return this.evaluate(argu) == 0.0;
    }
}