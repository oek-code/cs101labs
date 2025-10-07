

package lab2;

import java.util.Scanner;

public class Lab02_Q1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the volume of the cone:");
        double VolumeOfCone = input.nextDouble();

        System.out.println("Enter the height of the cone: ");
        double HeightOfCone = input.nextDouble();
        input.close();

        double radius = Math.sqrt((3*VolumeOfCone)/(HeightOfCone*Math.PI)) ;
        System.out.printf(" The radius of the cone is: %.1f%n" , radius);

        double  SurfaceArea = Math.PI * radius * radius + Math.PI * radius * Math.sqrt(radius * radius + HeightOfCone * HeightOfCone);
        System.out.printf("The surface area of the cone is: %.1f " , SurfaceArea);
            
        

    }   
}