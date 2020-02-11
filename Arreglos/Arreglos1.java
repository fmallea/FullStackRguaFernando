package Arreglos;

public class Arreglos1 
{

	public static void main(String[] args) 
	{
		int[] Arreglo1 = new int [10];
		
		//Arreglo1[0]=12;
		//Arreglo1[1]=4;
		
		for (int i=0; i<10; i++)
		{
			Arreglo1[i]=i+1;
		}
		
		for (int j=0; j<10; j++)
		{
			System.out.println(" valor ["+j+"]: "+Arreglo1[j]);
		}
	}
}
