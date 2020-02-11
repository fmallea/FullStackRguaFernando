package Arreglos;
import java.util.Scanner;

public class EjemploArrerglo 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		// inicio declaraciones
      int suma = 0;
      Scanner N = new Scanner(System.in); //declarar el objeto scanner para poder leer desde teclado 
      int Tamanio = 10;
      int[] ArregloNum = new int [Tamanio];
   // fin declaraciones 
      System.out.println("Ingrese tamaño arreglo:");
      Tamanio = N.nextInt(); // asignar a la vareiable el dato ingresado por teclado
      
      for (int i=0; i<Tamanio; i++) // creando e ingresando datos al arreglo
      {
        System.out.println("Ingrese un dato para guardar en arreglo");
        ArregloNum[i] = N.nextInt();
      }
      System.out.println("----valores----");
     
      for (int i = 0; i < Tamanio; i++)
      {
    	  System.out.println(ArregloNum[i]);
    	  suma = suma + ArregloNum[i];
      }
      System.out.println("La suma de los valores del  arreglo es "+suma);
	}
}

