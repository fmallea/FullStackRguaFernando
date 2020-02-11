package Condiciones;

import java.util.Scanner;

public class EstructuraCase {

	public static void main(String[] args) // TODO Auto-generated method stub
	{
		int d = 0;
		Scanner N = new Scanner(System.in);
		
		System.out.println("Numero del dia de la semana");
		d = N.nextInt();

		switch (d) 
		{
		case 1: System.out.println("1 es Lunes"); break;
		case 2:System.out.println("2 es Martes"); break;
		case 3:System.out.println("3 es Miercoles"); break;
		case 4:System.out.println("4 es Jueves"); break;
		case 5:System.out.println("5 es Viernes"); break;
		case 6:System.out.println("6 es Sabado"); break;
		case 7:System.out.println("7 es Domingo"); break;
		default: System.out.println("No existe un dia para ese numero"); break;
		}
		System.out.println("------------ otro case------------");
		switch ((1<=d && d<=7) ? 4:7)
		{
		case 1: System.out.println("1 es Lunes"); break;
		case 2:System.out.println("2 es Martes"); break;
		case 3:System.out.println("3 es Miercoles"); break;
		case 4:System.out.println("4 es Jueves"); break;
		case 5:System.out.println("5 es Viernes"); break;
		case 6:System.out.println("6 es Sabado"); break;
		case 7:System.out.println("7 es Domingo"); break;
		default: System.out.println("No existe un dia para ese numero"); break;	
		}
	}

}
