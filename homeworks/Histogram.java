/*
 * remove this before turning in
 * does not read in the files because is in a "package"
 */
package homeworks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Histogram {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 November 6
   * Abstract: This is a program that sorts the amount of letters
   * that are in a file that is read in.
   * This uses bubble sort.
   */

  public static void main(String[] args) {

    String fileName = getFileName();

    char[] letter = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
    int[] letterCount = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    read(letter, letterCount, fileName);
    sort(letter, letterCount);
    display(letter, letterCount);
  }

  public static void display(char[] letter, int[] letterCount) {
    System.out.println("Char occurrences");

    for (int i = 0; i < letterCount.length; i++) {
      if (letterCount[i] != 0) {
        System.out.println(letter[i] + " " + letterCount[i]);
      }
    }

    System.out.print("\n=============================\n");

    /*
     * printing the letters in reverse order from greatest to smallest
     * this is keeping in sync with the array of ints that holds the
     * greatest number at the end of the array ie index 10 and going backwards
     * until reaching 0
     */
    for (int i = letterCount[10]; i > 0; i--) {
      String fullString = "";

      for (int j = 0; j < letterCount.length; j++) {
        if (letterCount[j] >= i) {
          fullString += letter[j] + " ";
        }
        else {
          fullString += "  ";
        }
      }

      System.out.println("|    " + i + "|" + fullString);
    }

    System.out.print("-----------------------------\n" + "      ");

    for (int i = 0; i < letter.length; i++) {
      System.out.print(" " + letter[i]);
    }
}

  public static void sort(char[] letter, int[] letterCount) {
    // using a bubble sort algo to sort the array of ints least -> greatest
    for (int i = 0; i < letterCount.length - 1; i++) {
      for (int j = 0 ; j < letterCount.length - 1 - i; j++) {
        if (letterCount[j] > letterCount[j + 1]) {
          int temp = letterCount[j];
          char temp2 = letter[j];

          letterCount[j] = letterCount[j + 1];
          letterCount[j + 1] = temp;

          /*
           * at the same time moving the letter found
           * in the same index in the array of chars to
           * keep both arrays in sync
           */
          letter[j] = letter[j + 1];
          letter[j + 1] = temp2;
        }
      }
    }
  }

  public static void read(char[] letter, int[] letterCount, String filename) {
    File theFile = new File(filename);
    Scanner scanner;

    try {
      scanner = new Scanner(theFile);
    }
    catch (FileNotFoundException e) {
      System.out.println("File does noes exist or was not able to open");
      throw new RuntimeException(e);
    }

    /*
     * the array of chars is A - F, index 0 - 10 respectively
     * increasing the same index of the char array
     * in the array of ints to symbolize there
     * is a new letter found in the file read in
     */
    do {
      switch (scanner.nextLine().charAt(0)) {
        case 'A' -> letterCount[0]++;
        case 'B' -> letterCount[1]++;
        case 'C' -> letterCount[2]++;
        case 'D' -> letterCount[3]++;
        case 'E' -> letterCount[4]++;
        case 'F' -> letterCount[5]++;
        case 'G' -> letterCount[6]++;
        case 'H' -> letterCount[7]++;
        case 'I' -> letterCount[8]++;
        case 'J' -> letterCount[9]++;
        case 'K' -> letterCount[10]++;
        default -> scanner.nextLine();
      }
    } while (scanner.hasNextLine());
  }

  public static String getFileName() {
    Scanner newScanner = new Scanner(System.in);

    System.out.print("Input filename : ");

    return newScanner.nextLine();
  }
}