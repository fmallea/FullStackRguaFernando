package Ciclos;
import java.util.*;

public class EjemploFor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float Suma = 0;
        float Promedio = 0;
        int Numero = 0;
        
        for (int i=1; i<101;i++)
        {
        	Suma = Suma + i;
        	Numero = Numero + 1;
        }
        Promedio = Suma / Numero;
        
        System.out.println("cantidad de numeros: "+Numero);
        System.out.println("Resultado Suma: "+Suma);
        System.out.println("Resultado Promedio: "+Promedio);
	}

}
