import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 October 31
   * This class is used to demo reading input from users,
   * finding the root of our project,
   * and general Java knowledge.
   */

  public static void main(String[] args) {
    System.out.println("hello world!");
    Scanner scan = null;

    String filename = "myFile.txt";
    File f = new File(filename);

    try {
      if (f.createNewFile()) {
        System.out.println(filename + " created successfully!");
      } else {
        System.out.println(filename + " already exists.");
      }
      scan = new Scanner(f);
    } catch (IOException e) {
      System.out.println("Could not create " + filename);
    }

    FileReader freader = new FileReader();

    while (scan != null && scan.hasNext()) {
      String input = scan.nextLine();
      System.out.println(input + ": ");
      freader.listening(input.split(","));
    }
  }

  private void listening(String[] splitString) {
     for (String s : splitString) {
       System.out.println(s.trim());
     }
  }
}