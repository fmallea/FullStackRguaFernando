package Arreglos.Ejercicios;

import java.util.Scanner;
import java.util.Random;

public class Recorrido {
	public static void rellenar(int[] arreglo, int tamano) {
		Random random = new Random();
		for(int i=0;i<tamano;i++) {
			arreglo[i]=random.nextInt(10);		
		}
	}
	private static void mostrar(int[] arreglo) {
		int suma=0;
		for(int i=0;i<arreglo.length;i++) {
			System.out.println("Agregando numero al azar...");
			System.out.println(arreglo[i]);
			suma += arreglo[i];
		}
		System.out.println("La suma de los numeros al azar es: "+Integer.toString(suma));
	}

	public static void main(String[] args) {
		Scanner leer = new Scanner(System.in);
		System.out.println("Ingrese tamaño del arreglo: ");
		int tamano = leer.nextInt();
		int[] arreglo = new int[tamano];
		rellenar(arreglo,tamano);
		mostrar(arreglo);
		leer.close();
	}
}