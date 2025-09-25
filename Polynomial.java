import java.lang.Math;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class Polynomial 
{
    private double[] coeffi;
    private int[] expo;
    public Polynomial() 
    {
        this.coeffi = new double[0];
        this.expo = new int[0];
    }
    public Polynomial(double[] coeffi, int[] expo) 
    {
        this.coeffi = coeffi;
        this.expo = expo;
    }
    public Polynomial(File file) throws IOException 
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        br.close();
        double[] coe = new double[line.length()];
        int[] ex = new int[line.length()];
        int size = 0;
        String term = "";
        for (int i = 0; i < line.length(); i++) 
        {
            char ch = line.charAt(i);
            if (ch == '+' || ch == '-') 
            {
                if (!term.isEmpty()) 
                {
                    double c;
                    int e;
                    if (term.contains("x")) 
                    {
                        String[] ns = term.split("x");
                        if (ns[0].equals("") || ns[0].equals("+")) c = 1;
                        else if (ns[0].equals("-")) c = -1;
                        else c = Double.parseDouble(ns[0]);
                        if (ns.length == 1 || ns[1].equals("")) e = 1;
                        else e = Integer.parseInt(ns[1]);
                    } 
                    else 
                    {
                        c = Double.parseDouble(term);
                        e = 0;
                    }

                    coe[size] = c;
                    ex[size] = e;
                    size++;
                }
                term = "" + ch; 
            } 
            else 
            {
                term += ch;
            }
        }
        if (!term.isEmpty()) 
        {
            double c;
            int e;
            if (term.contains("x")) 
            {
                String[] ns = term.split("x");
                if (ns[0].equals("") || ns[0].equals("+")) c = 1;
                else if (ns[0].equals("-")) c = -1;
                else c = Double.parseDouble(ns[0]);

                if (ns.length == 1 || ns[1].equals("")) e = 1;
                else e = Integer.parseInt(ns[1]);
            } 
            else 
            {
                c = Double.parseDouble(term);
                e = 0;
            }
            coe[size] = c;
            ex[size] = e;
            size++;
        }
        this.coeffi = new double[size];
        this.expo = new int[size];
        for (int i = 0; i < size; i++) 
        {
            this.coeffi[i] = coe[i];
            this.expo[i] = ex[i];
        }
    }
    
    public Polynomial add(Polynomial argu) 
    {
        double[] temCoeffi = new double[this.coeffi.length + argu.coeffi.length];
        int[] temExpo = new int[this.expo.length + argu.expo.length];
        int size = 0;
        for(int i = 0; i< this.coeffi.length; i++)
        {
        	temCoeffi[size] = this.coeffi[i];
        	temExpo[size] = this.expo[i];
        	size++;
        }
        
        for(int j = 0; j < argu.coeffi.length; j++)
        {
        	int found = 0;
        	for(int k = 0; k < size; k++)
        	{
        		if(temExpo[k] == argu.expo[j])
        		{
        			temCoeffi[k] += argu.coeffi[j];
        			found = 1;
        			break;
        		}
        	}
        	if(found != 1)
        	{
        		temCoeffi[size] = argu.coeffi[j];
        		temExpo[size] = argu.expo[j];
        		size++;
        	}
        }
        
        int count = 0;
        for(int i= 0; i < size; i++)
        {
        	if(temCoeffi[i] != 0)
        	{
        		count++;
        	}
        }
        double[] newCoeffi = new double[count];
        int[] newExpo = new int[count];
        int idx = 0;
        for(int i = 0; i < size; i++)
        {
        	if(temCoeffi[i] != 0)
        	{
        		newCoeffi[idx] = temCoeffi[i];
        		newExpo[idx] = temExpo[i];
        		idx++;
        	}
        }
        
        Polynomial result = new Polynomial();
        result.coeffi = newCoeffi;
        result.expo = newExpo;
        return result;
    }
    public  Polynomial multiply(Polynomial argu)
    {
    	double[] tempCoeffi = new double[this.coeffi.length * argu.coeffi.length];
        int[] tempExpo = new int[this.coeffi.length * argu.coeffi.length];
        int size = 0;
        for (int i = 0; i < this.coeffi.length; i++) 
        {
            for (int j = 0; j < argu.coeffi.length; j++) 
            {
                double c = this.coeffi[i] * argu.coeffi[j];
                int e = this.expo[i] + argu.expo[j];
                int found = 0;
                for (int k = 0; k < size; k++) 
                {
                    if (tempExpo[k] == e) 
                    {
                        tempCoeffi[k] += c;
                        found = 1;
                        break;
                    }
                }
                if (found != 1) 
                {
                    tempCoeffi[size] = c;
                    tempExpo[size] = e;
                    size++;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < size; i++) 
        {
            if (tempCoeffi[i] != 0) 
            {
                count++;
            }
        }
        double[] newCoeffi = new double[count];
        int[] newExpo = new int[count];
        int idx = 0;
        for (int i = 0; i < size; i++) 
        {
            if (tempCoeffi[i] != 0) 
            {
                newCoeffi[idx] = tempCoeffi[i];
                newExpo[idx] = tempExpo[i];
                idx++;
            }
        }

        Polynomial result = new Polynomial();
        result.coeffi = newCoeffi;
        result.expo = newExpo;
        return result;
    }
    public double evaluate(double argu) 
    {
        double result = 0;
        for (int i = 0; i < this.coeffi.length; i++) 
        {
            result += Math.pow(argu, expo[i]) * this.coeffi[i];
        }
        return result;
    }
    
    public boolean hasRoot(double argu) 
    {
        return this.evaluate(argu) == 0.0;
    }
    
    public void saveToFile(String path) throws IOException 
    {
    	FileWriter w = new FileWriter(path);
        w.write(this.toString());
    	w.close();
    }

    public String toString()             //helper method: convert poly to string
    {
        if (coeffi.length == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coeffi.length; i++) 
        {
            if (coeffi[i] == 0) continue;
            if (sb.length() == 0) 
            {
                if (coeffi[i] < 0) sb.append("-");
            } 
            else 
            {
                if (coeffi[i] < 0) sb.append("-");
                else sb.append("+");
            }

            double absC = Math.abs(coeffi[i]);

            if (expo[i] == 0) 
            {
                sb.append(absC);
            } 
            else 
            {
                if (absC != 1.0) sb.append(absC);
                sb.append("x");
                if (expo[i] != 1) sb.append(expo[i]);
            }
        }
        return sb.toString();
    }
}