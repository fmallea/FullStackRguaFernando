package Arreglos.Ejercicios;
import java.util.Scanner;
public class Arreglos1 
{
    
    public static void LlenarArreglo(int num) {
        
        int arreglo1[] = new int[num];
        int  suma =0;
        
        
        for (int i = 0; i < arreglo1.length; i++) {
            arreglo1[i]= (int) (Math.random()*9);
            
            suma=suma+arreglo1[i]; 
            
        }
        
        System.out.println("-----GENERACIÓN DE NUMEROS-----");
        for (int i = 0; i < arreglo1.length; i++) {
            System.out.println("Posicion ["+i+"] : "+arreglo1[i]);
        }
        System.out.println("-------------------------------");
        
        System.out.println("La suma de los numeros es :"+suma);
        
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 

        
        
        System.out.println("Ingrese la cantidad de datos que desea guardar :");
        LlenarArreglo(sc.nextInt());
        

    }

}