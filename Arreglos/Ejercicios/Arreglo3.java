package Arreglos.Ejercicios;

import java.util.Scanner;

public class Arreglo3 
{
	public static void main(String[] args) 
	{
		 int i;
	        int[] numeros = new int[14];
	        Scanner teclado = new Scanner(System.in);
	        System.out.println("---Ingresa 14 numeros al azar---");
	        for(i=0; i<numeros.length; i++)
	        {
	            System.out.printf("Ingresa número %d: ", i+1);
	            numeros[i] = teclado.nextInt();
	        }
	}
}