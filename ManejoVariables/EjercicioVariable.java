package ManejoVariables;
import java.util.*;

public class EjercicioVariable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Declaración de variables
		int Numero=0;
		int Suma=0;
		int Number=0;
		Scanner Dato = new Scanner(System.in);
		
		String comentario="";
		double Promedio=0;
		
		System.out.println("Ingrese numero 1");
		Numero = Dato.nextInt();
		System.out.println("Ingrese numero 2");
		Number = Dato.nextInt();
		
		Suma=Numero + Number;
		
		Promedio=Suma/2;
		
		System.out.println("La suma es: "+Suma);
		System.out.println("el promedio es: "+Promedio);
		System.out.println("-------------");
		//comentario="El valor de la suma es:";
		System.out.println("Ingrese comentario");
		Dato.nextLine();
		comentario = Dato.nextLine();
		System.out.println(comentario+"  "+Suma);		
	}

}
