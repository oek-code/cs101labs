    package lab3;

    import java.util.Scanner;

    public class Lab03_Q2 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the applicant's age : ");
            int userAge = input.nextInt();
            System.out.print("Enter the applicant's annual income: ");
            double userAnnualIncome = input.nextDouble();
            System.out.print("Enter the applicant's credit score: ");
            int userCreditScore = input.nextInt();
            System.out.print("Enter the number of existing credit cards: ");
            int usersExistingCards = input.nextInt();
            System.out.print("Enter the monthly rent/mortgage payment: ");
            int usersMonthlyRentPay = input.nextInt();
            input.close();

            final int BASE_ELIGIBILITY_SCORE = 100;
            int approvedScore=BASE_ELIGIBILITY_SCORE;
            boolean approved = true;
            String failReason = "";

            if (userAge >= 18 && userAge <= 25) {
                approvedScore +=  10;
            }

            else if (userAge >= 26 && userAge <= 35) {
                approvedScore +=20;
            } else if (userAge >= 36 && userAge <= 50) {
                approvedScore += 25;
            } else if (userAge >= 51 && userAge <= 65) {
                approvedScore += 20;

            }

            int incomePoints = (int) ((userAnnualIncome / 10000) * 5);
            if (incomePoints > 200)
                incomePoints = 200;
            approvedScore += incomePoints ;

            if (userCreditScore >= 300 && userCreditScore <= 579)
                approvedScore += 0;
            else if (userCreditScore >= 580 && userCreditScore <= 669)
                approvedScore +=  50;
            else if (userCreditScore >= 670 && userCreditScore <= 739)
                approvedScore +=  100;
            else if (userCreditScore >= 740 && userCreditScore <= 799)
                approvedScore += 150;
            else if (userCreditScore >= 800 && userCreditScore <= 850)
                approvedScore +=  200;

            approvedScore -=  usersExistingCards * 10;

            int rentDeduction = (int) ((usersMonthlyRentPay / 500.0) * 5);
            approvedScore -= rentDeduction;

            System.out.println("Total eligibility score: " + approvedScore);

            if (userAge < 18) {
                failReason += "Age is below 18.\n";
                approved = false;
            }
            if (userAnnualIncome < 15000) {
                failReason += "Annual income is below $15,000.\n";
                approved = false;
            }
            if (userCreditScore < 580) {
                failReason += "Credit score is below 580.\n";
                approved = false;
            }
            if (usersExistingCards > 5) {
                failReason += "Number of existing credit cards exceeds 5.\n";
                approved = false;
            }
            if (approvedScore < 250) {
                failReason += "Total eligibility score is below 250 points.\n";
                approved = false;
            }

            if (approved) {
                System.out.println("The applicant is approved for the credit card.");
            } else {
                System.out.println("The applicant is not approved for the credit card. Reason:");
                System.out.println(failReason);

            }
        }
    }
