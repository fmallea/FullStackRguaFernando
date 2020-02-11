package Arreglos.Ejercicios;

import java.util.Scanner;

public class Ejercicio2 
{
	// declarar variables globales
	
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) 
	{
		int largo = 0;			//declarar variable para definir largo de arreglo
		
		System.out.println("Ingrese cantidad der numeros a ingresar");
		largo = sc.nextInt();
		int[] Arreglo2 = new int [largo];
		CrearArreglo(Arreglo2,largo);
		MostrarArreglo(Arreglo2);
		double promedio=SumarDatosArreglo(Arreglo2,0 )/largo;
		System.out.println("El promedio es: "+ promedio);
		
	}
	
	public static void CrearArreglo(int[] arreglo, int Tamaño) // Funcion para crear arreglo
	{
		for (int i = 0; i < Tamaño; i++) 
		{
            arreglo[i]= (int) (Math.random()*20);				// generar numeros aleatorias entre 0 y 20
		}
	}
	
	public static void MostrarArreglo(int[] arreglo)	// Funcion para recorrer arreglo
	{
		int Suma = 0;
		System.out.println("---------- Numeros creados----------");
		for(int i=0; i<arreglo.length; i++)
		{
			System.out.println("Arreglo["+i+"] --> "+arreglo[i]);
		}
	}
	public static int SumarDatosArreglo(int[] arreglo, int Sum)
	{
		for(int i=0; i<arreglo.length; i++)
		{
			Sum=Sum+arreglo[i];
			
		}
		System.out.println("La suma de valores es: "+Sum);
		return Sum;
	}
	

}
