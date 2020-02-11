package Colecciones;
import java.util.*;

public class EjemploColeccionSimple {

	public static void main(String[] args) 
	{
		// inicio declaraciones
		ArrayList<Integer> Coleccion1 = new ArrayList();	// declarar celeccion de numeros enteros
		Scanner sc = new Scanner(System.in);				// declarar el scanner para leer datos de teclado
		int Numero = 0;										// declarar la variable que recibe el dato de teclado
		int Positivo = 0, Negativo = 0, Ceros = 0;
		
		System.out.println("Ingrese 10 numeros al azar");
		
		for (int i=0; i<10;i++)								//llenar la coleccion
		{
			Numero = sc.nextInt();							// leer numero a ingresar
			Coleccion1.add(i,Numero);						// guardar el numero en la coleccion en la posicion deseada
		}
		
		for (int j=0; j<Coleccion1.size();j++)				// recorrer la coleccion
		{
			System.out.println("Numero ingresado en posicion: "+j+" es el -> "+Coleccion1.get(j)); // accede a la coleccion en una posicion determinada
		    if (Coleccion1.get(j)>0)						// si el numero es mayor a cero, es positivo 
		    {
		    	Positivo = Positivo + 1;
		    } else
		    	if (Coleccion1.get(j)<0)					// si el numero es menor a cero, es negativo
		    		{
		    			Negativo = Negativo + 1;
		    		} else
		    			Ceros = Ceros + 1;					// por descarte... es cero
		}
		System.out.println("-------Resultados--------");
		System.out.println("Numeros positivos: "+Positivo);
		System.out.println("Numeros negativos: "+Negativo);
		System.out.println("Numeros ceros: "+Ceros);
	}

}
