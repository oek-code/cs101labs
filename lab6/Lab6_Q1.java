package lab6;
import java.util.Scanner;

public class Lab6_Q1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int r = in.nextInt();

        System.out.print("Enter number of columns: ");
        int c = in.nextInt();

        char[][] seats = new char[r][c];

        int i, j;

        for (i = 0; i < r; i++) {
            for (j = 0; j < c; j++) {
                seats[i][j] = '-';
            }
        }

        int[] groups = new int[r];
        boolean error = false;

        System.out.println("Enter the groups for " + r + " rows:");

        for (i = 0; i < r; i++) {
            if (!error) {
                if (!in.hasNextInt()) {
                    System.out.println("Error: expected " + r + " group sizes but got " + i + ".");
                    error = true;
                } else {
                    groups[i] = in.nextInt();
                }
            }
        }

        for (i = 0; i < r; i++) {
            if (!error) {
                int k = groups[i];
                if (k > c) {
                    System.out.println("Error: group size " + k +
                                       " exceeds columns " + c +
                                       " in row " + (i + 1) + ".");
                    error = true;
                }
            }
        }

        if (!error) {
            for (i = 0; i < r; i++) {
                int k = groups[i];

                if (k == 1) {
                    seats[i][0] = 'x';
                }

                else if (k > 1) {
                    int totalDistance = c - 1;
                    int gaps = k - 1;

                    int g = totalDistance / gaps;
                    int R = totalDistance % gaps;

                    int pos = 0;
                    seats[i][pos] = 'x';

                    for (int p = 1; p < k; p++) {
                        if (p <= R) {
                            pos += (g + 1);
                        } else {
                            pos += g;
                        }
                        seats[i][pos] = 'x';
                    }
                }
            }
        }

        if (!error) {
            for (i = 0; i < r; i++) {
                for (j = 0; j < c; j++) {
                    System.out.print(seats[i][j]);
                }
                System.out.println();
            }
        }

        in.close();
    }
}
