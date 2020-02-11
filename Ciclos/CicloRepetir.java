package Ciclos;

public class CicloRepetir 
{
    public static void main(String args[])
    {
        int C=1;
        int son = 0;
        while (C < 100)
    	{
            System.out.println("Numero "+C);
            C=C+2;
            son = son + 1;
        }
        System.out.print("El Numero de impares es "+son);
    }
}


