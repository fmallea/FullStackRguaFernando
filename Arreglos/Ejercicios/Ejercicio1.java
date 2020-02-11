package Arreglos.Ejercicios;

import java.util.Scanner;

public class Ejercicio1 
{
	// declarar variables globales
	public static int[] Arreglo1 = new int [14];
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		System.out.println("Ingresar 14 numeros:");
			
		CrearArreglo(Arreglo1.length);			// llamar funcion llenar arreglo, con el largo del arreglo
		MostrarArreglo(Arreglo1.length); 		// llamar funcion mostrar arreglo, con el largo del arreglo
	}
	
	public static void CrearArreglo(int D)		//funcion requiere un numero de entrada
	{
		
		for (int i=0; i<D; i++)					// recorrer hasta el dato de entrada de funcion
		{
			Arreglo1[i]=sc.nextInt();			//leer dato e ingresar en arreglo
		}
	}
	
	public static void MostrarArreglo(int B)	//funcion requiere un numero de entrada
	{
		for (int j=0; j<B; j++)					// recorrer hasta el dato de entrada de funcion
		{
			System.out.println(" valor ["+j+"]: "+Arreglo1[j]);
		}
	}

}
