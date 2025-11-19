package lab6;
import java.util.Scanner;

public class Lab06_Q1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int r = in.nextInt();

        System.out.print("Enter number of columns: ");
        int c = in.nextInt();

        char[][] seats = new char[r][c];

        // Başlangıçta tüm koltukları '-'
        int i = 0;
        while (i < r) {
            int j = 0;
            while (j < c) {
                seats[i][j] = '-';
                j++;
            }
            i++;
        }

        in.nextLine(); // buffer temizleme

        int[] groups = new int[r];
        boolean error = false;

        // ================================
        //    COMMA-SEPARATED INPUT
        // ================================
        System.out.print("Enter the groups for " + r + " rows and " + c + " columns: ");
        String line = in.nextLine();

        // split ile parçala
        String[] parts = line.split(",");

        // eğer eksik grup varsa
        if (parts.length != r) {
            System.out.println("Error: expected " + r + " group sizes but got " + parts.length + ".");
            error = true;
        }

        // array'e grupları doldur
        int k = 0;
        while (k < parts.length && !error) {
            groups[k] = Integer.parseInt(parts[k].trim());
            k++;
        }

        // ================================
        //     HER SATIR İÇİN KONTROL
        // ================================
        int row = 0;
        while (row < r) {

            if (!error) {

                int people = groups[row];

                if (people > c) {
                    System.out.println("Error: group size " + people +
                                       " exceeds columns " + c +
                                       " in row " + (row + 1) + ".");
                    error = true;
                }
            }

            row++;
        }

        // ================================
        //     GAP ALGORİTMASI UYGULA
        // ================================
        if (!error) {

            int rowIndex = 0;

            while (rowIndex < r) {

                int kPeople = groups[rowIndex];

                if (kPeople == 1) {
                    seats[rowIndex][0] = 'x';
                }

                else if (kPeople > 1) {

                    int totalDist = c - 1;
                    int gaps = kPeople - 1;

                    int g = totalDist / gaps;
                    int R = totalDist % gaps;

                    int pos = 0;
                    seats[rowIndex][pos] = 'x';

                    int p = 1;
                    while (p < kPeople) {

                        if (p <= R)
                            pos += (g + 1);
                        else
                            pos += g;

                        seats[rowIndex][pos] = 'x';
                        p++;
                    }
                }

                rowIndex++;
            }
        }

        // ================================
        //        SONUÇ YAZDIR
        // ================================
        if (!error) {
            int r2 = 0;
            while (r2 < r) {
                int c2 = 0;
                while (c2 < c) {
                    System.out.print(seats[r2][c2]);
                    c2++;
                }
                System.out.println();
                r2++;
            }
        }

        in.close();
    }
}
