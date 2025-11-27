
package lab7.Cs_HW2;

import java.util.Random;

public class Cs_HW2 {
    public static char findFirstRepeatA(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j))
                    return s.charAt(i);
            }

        }
        return '_';
    }

    public static char findFirstRepeatB(String s) {

        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            count[ch - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (count[ch - 'a'] > 1) {
                return ch;
            }
        }

        return '_';
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {

            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        int[] sizes = { 100, 1000, 10000, 100000 };

        System.out.printf("%-10s %-15s %-15s\n", "Size", "Time A (ns)", "Time B (ns)");
        System.out.println("---------------------------------------------");

        for (int size : sizes) {

            String input = generateRandomString(size);

            long startTimeA = System.nanoTime();
            findFirstRepeatA(input);
            long endTimeA = System.nanoTime();
            long durationA = endTimeA - startTimeA;

            long startTimeB = System.nanoTime();
            findFirstRepeatB(input);
            long endTimeB = System.nanoTime();
            long durationB = endTimeB - startTimeB;

            System.out.printf("%-10d %-15d %-15d\n", size, durationA, durationB);
        }
    }
}
