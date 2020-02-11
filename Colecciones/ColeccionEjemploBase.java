package Colecciones;
import java.util.*;

public class ColeccionEjemploBase 
{

	public static void main(String[] args) 
	{
		ArrayList<Double> Coleccion1 = new ArrayList();
		double dato=0;
        double n=0;
		for (int i=0; i<10;i++)								
		{	
			n=n+i;
			Coleccion1.add(i, n);;
		}
		
		for (int j=0; j<10; j++)
		{
			dato=Coleccion1.get(j); 
			System.out.println(" valor--- ["+j+"]: "+dato);
			
		}
	}

}
