package Condiciones;
import java.util.Scanner;

public class EjemploSiEntonces 
{
	public static void main(String[] args) 
	{
		int t = 0;
		Scanner N = new Scanner(System.in);
		
		System.out.println("Ingrese Temperatura en Grados C°");
		t = N.nextInt();
		
		if (t<0)
		{
			System.out.println("Hace mucho frio, bajo cero");
		}else
	    if (t>0 & t<10)
	    {
	    	System.out.println("Hace frio, pero sobre cero");
	    }else
	    if (t>10 & t<15)
	    {
	    	System.out.println("Hace algo de frio");
	    }else
	    if (t>15 & t<20)
	    {
	    	System.out.println("No hace frio, esta agradable");
	    }
	    else
	    {
	    	System.out.println("No hace frio, hace calor");
	    }
	}
}

