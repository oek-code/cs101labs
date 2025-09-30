import java.util.Scanner;

public class Lab01_Q3 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("How many Kilograms would you like to lose?: ");
        double KgToLose=input.nextDouble();

         System.out.println("In how many days would you like to lose?: ");
         int NumberOfDaysToLose = input.nextInt();
         System.out.println("You want to lose: " + NumberOfDaysToLose + " kilograms");
//Constants
  
   
   final int MaintainWeight = 2000;
   final int CaloriesDeficit = 7700;
//Macros
final int CarbPercent = 50;
final int FatPercent = 30;
final int ProteinPercent = 20;
//Calories per gram
final int CarbCalPerGram = 4;  
final int ProteinCalPerGram = 4;
final int FatCalPerGram = 9;

//Calculations 
double totalCaloriesToLose=KgToLose * CaloriesDeficit;
double dailyCaloriesDeficit=totalCaloriesToLose/NumberOfDaysToLose;
double dailyCalories=MaintainWeight-dailyCaloriesDeficit;

double carbCalories = dailyCalories * CarbPercent / 100;  
double fatCalories = dailyCalories * FatPercent / 100;
double proteinCalories = dailyCalories * ProteinPercent / 100;



double carbGrams = carbCalories / CarbCalPerGram;
double fatGrams = fatCalories / FatCalPerGram;
double proteinGrams = proteinCalories / ProteinCalPerGram;

//Output

System.out.println("Kilograms to lose :"+ KgToLose );
System.out.println(" Days to lose " + KgToLose +":" + NumberOfDaysToLose);
System.out.println("To lose " + KgToLose +   " kilograms in " + NumberOfDaysToLose + " days you will need a daily deficit of " + dailyCaloriesDeficit + " calories");
 System.out.println("MACRO\t" + "RECOMMENDED PERCENT\t" + "CALORIES PER GRAM\t" + "RECOMMENDED CALORIES\t" + "\t" + "GRAMS");
 System.out.println("CARBOHYRDATE" + "\t" + "\t" + CarbPercent + "\t" + "\t" + "\t" +  CarbCalPerGram + "\t" + "\t" + "\t" + carbCalories + "\t" + "\t" + carbGrams);
 System.out.println("FAT" + "\t" + "\t" + "\t" + FatPercent + "\t" + "\t" + "\t" + FatCalPerGram + "\t"+ "\t" + "\t" + fatCalories+ "\t" + "\t" + fatGrams);
 System.out.println("PROTEIN" + "\t" + "\t" + "\t" + ProteinPercent + "\t" + "\t" + "\t" + ProteinCalPerGram + "\t"+ "\t" + "\t" + proteinCalories+ "\t" + "\t" + proteinGrams);
  }
}