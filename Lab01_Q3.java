public class Lab01_Q3 {

    public static void main(String[] args) {
//Constants
  int KgToLose = 2;
   int NumberOfDaysToLose = 10;
   final int MaintainWeight = 2000;
   final int CaloriesDeficit = 7700;
//Macros
final int CarbPercent = 50;
final int FatPercent = 30;
final int ProteinPercent = 20;
//Calories per gram
final int CarbCal = 4;  
final int ProteinCal = 4;
final int FatCal = 9;

//Calculations 
int totalCaloriesToLose=KgToLose * CaloriesDeficit;
int dailyCaloriesDeficit=totalCaloriesToLose/NumberOfDaysToLose;
int dailyCalories=MaintainWeight-dailyCaloriesDeficit;

int carbCalories = dailyCalories * CarbPercent / 100;  
int fatCalories = dailyCalories * FatPercent / 100;
int proteinCalories = dailyCalories * ProteinPercent / 100;

int carbGrams = carbCalories / CarbCal;
int fatGrams = fatCalories / FatCal;
int proteinGrams = proteinCalories / ProteinCal;

//Output

System.out.println("Kilograms to lose :"+ KgToLose );
System.out.println(" Days to lose " + KgToLose +":" + NumberOfDaysToLose);
System.out.println("To lose " + KgToLose +   " kilograms in " + NumberOfDaysToLose + " days you will need a daily deficit of " + dailyCaloriesDeficit + " calories");


    }
}