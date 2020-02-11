package Arreglos;

public class EjercicioArreglos100 {

	public static void main(String[] args) 
	{
		// inicio declaraciones
	    int Suma=0;
	    int Promedio=0;
	    int CantM3=0;
	    int Largo=100;							// variable con definicion del largo de arreglo
	    int[] Arreglo100 = new int [Largo];		// Arreglo a usar para los 100 numeros
	    int[] ArregloResult = new int [3];		// Arreglo para guardar los resultados del ejercicio
	     // fin declaraciones 
	       
	    for (int i=0; i<Largo; i++) 			// ingresar los 100 numeros del 1 a 100
	    {
	    	Arreglo100[i] = i+1;
	    }
	    System.out.println("---------Mostrando 100 numeros---------");
	    for (int i=0; i<Largo; i++) 			// recorrer arreglo con datos
	    {
	    	System.out.println(Arreglo100[i]); 	// mostrar dato del arreglo
	    	Suma = Suma + Arreglo100[i];		// Sumar los valores del arreglo
	    	if (Arreglo100[i]%3==0)				// condicion para saber si es multiplo de 3
	    	{
	    		CantM3=CantM3+1;				// si cumple condicion cuenta los multiplo de 3
	    	}
	    		    	
	    }
	    System.out.println("-------Mostrando resultados-----------");
	    Promedio = Suma/Largo;					// calcula promedio
	    
	    // ingresar datos al arreglo de resultados
	    ArregloResult[0] = Suma; 				// se registra suma
	    ArregloResult[1] = Promedio;			// se registra Promedio
	    ArregloResult[2] = CantM3;				// se Registra Cantidad de multiplos de 3
	    
	    for (int j=0; j<3; j++)						// recorrer arreglo de resultados para mostrar
	    {
	    	switch (j)							// case para indicar el resultado que corresponde a cada uno
	    	{
	    	case 0: System.out.println("Suma: "+ArregloResult[j]);break;
	    	case 1: System.out.println("Promedio: "+ArregloResult[j]);break;
	    	case 2: System.out.println("Cantidad Multiplos de 3: "+ArregloResult[j]);break;
	    	}
	    	
	    }
	    System.out.println("-------Fin Ejercicio-----------");
	}

}
