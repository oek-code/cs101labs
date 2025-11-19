package lab6;
import java.util.Scanner;

public class Lab06_Q1_Revision {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int r = in.nextInt();

        System.out.print("Enter number of columns: ");
        int c = in.nextInt();

        char[][] seats = new char[r][c];

        // Tüm koltukları '-' ile doldur
        int i = 0;
        while (i < r) {
            int j = 0;
            while (j < c) {
                seats[i][j] = '-';
                j++;
            }
            i++;
        }

        in.nextLine(); // nextInt tampon temizleme
        boolean error = false;

        // ==========================
        //   1) GRUP GİRİŞİ (split)
        // ==========================
        int row = 0;
        while (row < r) {

            System.out.print("Enter groups for row " + (row + 1) + ": ");
            String line = in.nextLine();

            String[] parts = line.split(",");  // virgülle böl
            int groupCount = parts.length;
            int[] groups = new int[groupCount];

            // parçaları sayılara çevir
            int a = 0;
            while (a < groupCount) {
                groups[a] = Integer.parseInt(parts[a].trim());
                a++;
            }

            // toplam kişi sayısı
            int totalPeople = 0;
            int g = 0;
            while (g < groupCount) {
                totalPeople += groups[g];
                g++;
            }

            // gerekli minimum boşluk (grup sayısı - 1)
            int neededSpaces = 0;
            if (groupCount > 1) {
                neededSpaces = groupCount - 1;
            }

            // kapasite yetmiyorsa ERROR
            if (!error && totalPeople + neededSpaces > c) {
                System.out.println("Error: groups exceed columns in row " + (row + 1));
                error = true;
            }

            // ==========================
            //   2) KOLTUKLARA YERLEŞTİR
            // ==========================
            if (!error) {

                int pos = 0;
                int gi = 0;

                while (gi < groupCount) {

                    int people = groups[gi];

                    // grup kadar x koy
                    int t = 0;
                    while (t < people) {
                        seats[row][pos] = 'x';
                        pos++;
                        t++;
                    }

                    // son grup değilse ve yer varsa 1 boşluk
                    if (gi < groupCount - 1 && pos < c) {
                        seats[row][pos] = '-';
                        pos++;
                    }

                    gi++;
                }
            }

            row++;
        }

        // ilk yerleşim sonucu yazdır
        if (!error) {
            printSeats(seats, r, c);
        }

        // ==========================
        //   3) SHIFT İŞLEMİ
        // ==========================
        if (!error) {

            System.out.print("Do you want to shift a row (y/n)? ");
            String ans = in.nextLine();

            if (ans.equalsIgnoreCase("y")) {

                System.out.print("Enter row number to shift (1-" + r + "): ");
                int rr = in.nextInt() - 1;
                in.nextLine();

                System.out.print("Direction (L/R): ");
                String dir = in.nextLine();

                boolean canShift = true;

                // === LEFT SHIFT ===
                if (dir.equalsIgnoreCase("L")) {

                    int j = 0;
                    while (j < c) {
                        if (seats[rr][j] == 'x' && j == 0) {
                            canShift = false;
                        }
                        j++;
                    }

                    if (canShift) {
                        int j2 = 0;
                        while (j2 < c - 1) {
                            seats[rr][j2] = seats[rr][j2 + 1];
                            j2++;
                        }
                        seats[rr][c - 1] = '-';
                    }
                }

                // === RIGHT SHIFT ===
                else if (dir.equalsIgnoreCase("R")) {

                    int j = 0;
                    while (j < c) {
                        if (seats[rr][j] == 'x' && j == c - 1) {
                            canShift = false;
                        }
                        j++;
                    }

                    if (canShift) {
                        int j2 = c - 1;
                        while (j2 > 0) {
                            seats[rr][j2] = seats[rr][j2 - 1];
                            j2--;
                        }
                        seats[rr][0] = '-';
                    }
                }

                else {
                    System.out.println("Invalid direction.");
                    canShift = false;
                }

                if (!canShift) {
                    System.out.println("Shift not possible (would move seats out of bounds).");
                } else {
                    printSeats(seats, r, c);
                }
            }
        }

        in.close();
    }

    // Koltuk çıktısı
    public static void printSeats(char[][] seats, int r, int c) {
        int i = 0;
        while (i < r) {
            int j = 0;
            while (j < c) {
                System.out.print(seats[i][j]);
                j++;
            }
            System.out.println();
            i++;
        }
    }
}
