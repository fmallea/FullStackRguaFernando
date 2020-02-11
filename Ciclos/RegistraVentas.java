package Ciclos;
import java.util.Scanner;

public class RegistraVentas {

	public static void main(String[] args) 
	{
		Scanner N = new Scanner(System.in);
		int Ventas = 0; // cantidad de ventas a registrar
	    int TotalVenta =0; // suma de las ventas registradas
	    // fin declaraciones 
	    System.out.println("Ingrese Ventas a realizar:");
	    Ventas = N.nextInt(); // leer cantidad de ventas a registrar
	    int[] RegistroVenta = new int [Ventas]; // Arreglo para registrar cada venta
	    if (Ventas <= 0) // Si la cantidad de ventas es menor o igual a 0, entonces...
	    {
	      System.out.println("Ingreso 0, No se van a realizar Ventas");
	    }
	    int Cantidad=0; // contador de cantidad
	    while (Ventas!=Cantidad)
	    {
	        System.out.println("Ingrese venta a registrar N° "+Cantidad);
	        RegistroVenta[Cantidad] = N.nextInt(); // leer cantidad de ventas a registrar
	        TotalVenta=TotalVenta+RegistroVenta[Cantidad]; // sumar venta registrada
	        Cantidad = Cantidad + 1; // contar venta registrada
	    }	
	    System.out.println("El total de las ventas registradas es: "+TotalVenta);
	    System.out.println("De un total de: "+Cantidad+" realizadas");
	    System.out.println("Las ventas requeridas fueron: "+Ventas);
	}

}
