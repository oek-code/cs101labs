package lab2;

import java.util.Scanner;

public class Lab02_Q2 {
public static void main(String[] args) {
    Scanner input= new Scanner(System.in);
    System.out.println(" Enter the mass of the first object(kg): ");
    double FirstObjectKg = input.nextDouble();
    System.out.println(" Enter the mass of the second object(kg): ");
    double SecondObjectKg=input.nextDouble();
     input.close();
    
     final String Alt10="10km";
     final String Alt100="100km";
     final String Alt1000="1000km";
     final String StndrtAlt="0km";
   
     double  StndrtGravityAcc =9.80665;
    double GravityAcc10Alt=9.77594;
    double GravityAcc100Alt=9.5059;
    double GravityAcc1000Alt=7.32628;

    double StandartFirstObjectWeight = StndrtGravityAcc*FirstObjectKg;
    double FirstObjectWeight10Alt = GravityAcc10Alt*FirstObjectKg;
    double FirstObjectWeight100Alt= GravityAcc100Alt*FirstObjectKg;
    double FirstObjectWeight1000Alt= GravityAcc1000Alt*FirstObjectKg;

    double StandartSecondObjectWeight = StndrtGravityAcc*SecondObjectKg;
    double SecondObjectWeight10Alt = GravityAcc10Alt*SecondObjectKg;
    double SecondObjectWeight100Alt =GravityAcc100Alt*SecondObjectKg;
    double SecondObjectWeight1000Alt = GravityAcc1000Alt*SecondObjectKg;

    System.out.println("\t" + "\t" + "\t" + StndrtAlt + "\t" + Alt10 + "\t" + Alt100 + "\t" + Alt1000 );
    System.out.printf("OBJECT ONE (" + FirstObjectKg + ")" +  "\t%.1f" , StandartFirstObjectWeight);
    System.out.printf("\t%.1f" , FirstObjectWeight10Alt);
    System.out.printf("\t%.1f" , FirstObjectWeight100Alt);
    System.out.printf("\t%.1f%n" , FirstObjectWeight1000Alt);
   System.out.printf("OBJECT TWO (" + SecondObjectKg + ")" + "\t%.1f" , StandartSecondObjectWeight);
   System.out.printf("\t%.1f" , SecondObjectWeight10Alt);
   System.out.printf("\t%.1f" , SecondObjectWeight100Alt);
   System.out.printf("\t%.1f" , SecondObjectWeight1000Alt);
  }  
}



