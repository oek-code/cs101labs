import java.util.Scanner;

public class Lab02_Q3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter student information: ");
        String info = input.nextLine().trim();

        


       
        int hash1 = info.indexOf('#'); 
        String firstName = info.substring(0, hash1).trim();

       
        int slash = info.indexOf('/');
        String lastName = info.substring(hash1 +1 , slash).trim();

        

      
        int dash = info.indexOf('-');
        String department = info.substring(slash +1, dash).trim();

       
        int hash2 = info.lastIndexOf('#');
        String university = info.substring(dash +1 , hash2).trim();

      
      
        int colon = info.indexOf(':');
        String hour = info.substring(hash2 +1 , colon).trim();
        String minute = info.substring(colon + 1).trim();

        
        System.out.println(firstName + " " + lastName +
                " registered to " + university + " " + department +
                " at " + minute + " past " + hour + ".");
    }
}

