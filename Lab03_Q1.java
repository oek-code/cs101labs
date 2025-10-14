package lab3;

import java.util.Scanner;

public class Lab03_Q1 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter your midterm score : ");
        int midtermScore = reader.nextInt();
        System.out.print("Enter your final exam score : ");
        int finalScore = reader.nextInt();
        double averageGrade = (midtermScore * 0.4) + (finalScore * 0.6);

        String letterGrade;
        boolean passed;

        if (finalScore < 50) {
            letterGrade = "FF";
            passed = false;
            
            System.out.println("Your letter grade is : " + letterGrade);
            System.out.printf("Your average is : " + "%.1f\n", averageGrade);
            System.out.println("You failed the course due to insufficient final exam score.");
            return;
        } else {
            // Determine letter grade based on average
            if (averageGrade >= 90)
                letterGrade = "AA";
            else if (averageGrade >= 85)
                letterGrade = "BA";
            else if (averageGrade >= 80)
                letterGrade = "BB";
            else if (averageGrade >= 75)
                letterGrade = "CB";
            else if (averageGrade >= 70)
                letterGrade = "CC";
            else if (averageGrade >= 60)
                letterGrade = "DC";
            else if (averageGrade >= 50)
                letterGrade = "DD";
            else
                letterGrade = "FF";

        }
        passed = (!letterGrade.equals("FF"));
        System.out.printf("Your average is  : " + "%.1f\n", averageGrade);
        System.out.println("Your letter grade is : " + letterGrade);
        if (passed) {
            System.out.println("You passed the course successfully. Congratulations!");
        } else {
            System.out.println("You failed the course.");

        }
    }
}
