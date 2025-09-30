/*
 * A simple program to fix syntax errors.
 * This program prints information about the lab schedule and grading policy.
 *
 */
public class Lab01_Q1 { 
  
   
   public static void main( String args[] ) 
   {
      // Constants
      final double GRADE_PERCENT = 15.0;  // grading policy
      final int LAB_COUNT= 9;  
      final double GRADE_PER_LAB = (GRADE_PERCENT / LAB_COUNT );
       
      // Variables
      String courseSemester;     
      
      // This program prints information about the lab schedule and grade policy //
         
           
      // First initialize the lab information
      courseSemester = "CS101 FALL 2025";           
      
      // Print out this lab section info
      System.out.println( "Hello everyone, below are some course details" );
      System.out.println( "Welcome to " + courseSemester + " Lab 01" );
      
      // Print out the grading policy
      System.out.println( "There are: "+ LAB_COUNT + " lab sessions in this course." );
      System.out.println( "Labs contribute to " + GRADE_PERCENT + "% of your total grade." );
      System.out.printf("This lab :\t%.2f\tpoints\n", GRADE_PER_LAB);
      System.out.println("All labs :\t" + GRADE_PERCENT  + "\tpoints");

      System.out.println( "Please come prepared..." );
      
      // Wish them luck and finish
      System.out.println();
      System.out.println( "Good luck!" );      
   }
   
}
