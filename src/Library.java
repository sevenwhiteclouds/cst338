import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class Library {
  // TODO: re-download main and also util file
  public static final int LENDING_LIMIT = 5;

  private String name = "";
  private static int libraryCard = 0;
  private List<Reader> readers = new ArrayList<>();
  private HashMap<String, Shelf> shelves = new HashMap<>();
  private HashMap<Book, Integer> books = new HashMap<>();

  public Code init(String filename) {
    File file = new File(filename);
    Scanner scanner = null;

    // this second scanner is needed to pass to the initBooks
    // will be init in the same try block below
    // doing all this here because i didn't want to create a new try block below
    // to handle exception
    Scanner scanner2 = null;

    try {
      scanner = new Scanner(file);
      scanner2 = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist or was not able to open.");
      return Code.FILE_NOT_FOUND_ERROR;
    }

    /*
     * using an array to easily access the strings converted to int with a counter
     * books = index 0
     * shelves = index 1
     * readers = index 3
     */
    int[] array = new int[3];

    // this keeps track of how many times we have encountered a 1 length string
    int counter = 0;

    // this is used to keep track of the current error code that is used
    int errorCodeCounter = -2;

    /* this does not need to go through any sort of loop because
     * the first value of the csv file will always be corresponding
     * to the number of books
     * the counter is also set to index 0 at this point
     */
    array[counter] = convertInt(scanner.nextLine(), errorCode(errorCodeCounter));

    // changing this to -6 because in the csv file shelves comes before readers
    // but in the code.java file, reader comes before reader
    // need to work backwards
    errorCodeCounter = -6;

    while (scanner.hasNextLine()) {
      String temp = scanner.nextLine();

      if (temp.length() == 1) {
        // updating the counter so i know where store the data in the array
        counter++;
        // updating the error code so i know what error code to send
        errorCodeCounter = errorCodeCounter+2;

        array[counter] = convertInt(temp, errorCode(errorCodeCounter));
      }
    }

    // remember the amount of books in the csv file is stored in index 0 of array
    initBooks(array[0], scanner2);

    // TODO: figure out what to do with this
    listBooks();

    // TODO: init the shelves here after the books have been init
    initShelves(array[1], scanner2);

    // TODO: still need to write listShelves();

    initReader(array[2], scanner2);

    // TODO: remove this aka fix this with! this is only temp
    return Code.SUCCESS;
  }

  private Code initReader(int readerCount, Scanner scan) {
    if (readerCount <= 0) {
      return Code.READER_COUNT_ERROR;
    }

    // this skips the first line that contains the amount of shelves
    scan.nextLine();

    for (int i = 0; i < readerCount; i++) {
      String[] readerSplit = scan.nextLine().split(",");

      int convertCardNum = convertInt(readerSplit[0], Code.READER_CARD_NUMBER_ERROR);

      Reader reader = new Reader(convertCardNum, readerSplit[1], readerSplit[2]);

      addReader(reader);


    }

    return Code.SUCCESS;
  }

  public Code addReader(Reader reader) {
    if (readers.contains(reader)) {
      System.out.println(reader.getName() + " already has an account!");
      return Code.READER_ALREADY_EXISTS_ERROR;
    }

    for (int i = 0; i < readers.size(); i++) {
      if (readers.get(i).getName() == reader.getName() && readers.get(i).getCardNumber() == reader.getCardNumber()) {
        System.out.println(readers.get(i).getName() + " and " + reader.getName() + " have the same card number!");
        return Code.READER_CARD_NUMBER_ERROR;
      }
    }

    if (reader.getCardNumber() > libraryCard) {
      libraryCard = reader.getCardNumber();
    }

    readers.add(reader);
    System.out.println(reader + " added to the library!");

    return Code.SUCCESS;
  }
  private Code initShelves(int shelfCount, Scanner scan) {
    if (shelfCount < 1) {
      return Code.SHELF_COUNT_ERROR;
    }

    // this skips the first line that contains the amount of shelves
    scan.nextLine();

    for (int i = 0; i < shelfCount; i++) {
      String[] shelfSplit = scan.nextLine().split(",");

      int convertShelfNum = convertInt(shelfSplit[0], Code.SHELF_COUNT_ERROR);

      if (convertShelfNum < 0) {
        return Code.SHELF_NUMBER_PARSE_ERROR;
      }

      Shelf shelf = new Shelf(convertShelfNum, shelfSplit[1]);

      addShelf(shelf);
    }

    if (shelves.size() != shelfCount) {
      System.out.println("Number of shelves doesn't match expected");
      return Code.SHELF_NUMBER_PARSE_ERROR;
    }

    return Code.SUCCESS;
  }

  public Code addShelf(Shelf shelf) {
    // TODO: check if this would work better with separate if statements
    // TODO: was not able to figure out what HashMap plus one meant
    if (shelves.containsKey(shelf.getSubject())) {
      System.out.println("ERROR: Shelf already exists [shelf]");
      return Code.SHELF_EXISTS_ERROR;
    }
    else if (!shelves.containsKey(shelf.getSubject())) {
      shelves.put(shelf.getSubject(), shelf);
    }

    // TODO: remove this or update? idk, this is temp
    return Code.SUCCESS;
  }

  public Code addShelf(String shelfSubject) {
    Shelf shelf = new Shelf(shelves.size() + 1, shelfSubject);

    addShelf(shelf);

    // TODO: remove this or update? idk, this is temp
    return Code.SUCCESS;
  }

  private Code initBooks(int bookCount, Scanner scan) {
    if (bookCount < 1) {
      return Code.LIBRARY_ERROR;
    }

    // this skips the first line that contains the amount of books
    scan.nextLine();

    for (int i = 0; i < bookCount; i++) {
      String[] bookSplit = scan.nextLine().split(",");

      int convertedPageC = convertInt(bookSplit[3], Code.PAGE_COUNT_ERROR);
      LocalDate convertedDueDate = convertDate(bookSplit[5], Code.DUE_DATE_ERROR);

      if (convertedPageC < 0) {
        return Code.PAGE_COUNT_ERROR;
      }

      if (convertedDueDate == null) {
        return Code.DATE_CONVERSION_ERROR;
      }

      // TODO: need to use the constant field in the Book.java file--using hard code for now
      Book book = new Book(bookSplit[0], bookSplit[1], bookSplit[2], convertedPageC, bookSplit[4], convertedDueDate);

      addBook(book);
    }

    return Code.SUCCESS;
  }

  public int listReaders() {
    for (int i = 0; i < readers.size(); i++) {
      System.out.println(readers.get(i).toString());
    }

    return readers.size();
  }

  // TODO: still need to do the list readers with boolean here

  public int listBooks() {
    // TODO: may still need to do the printing of the books
    int total = 0;

    for (Book key: books.keySet()) {
      total += books.get(key);
    }

    return total;
  }

   public Code addBook(Book newBook) {
    // TODO: check if this would work better with separate if statements
    if (!books.containsKey(newBook)) {
      books.put(newBook, 1);
      System.out.println(newBook.getTitle() + " added to the stacks.");
    }
    else if (books.containsKey(newBook)) {
      books.replace(newBook, books.get(newBook) + 1);
      System.out.println(books.get(newBook) + " copies of " + newBook.getTitle() + " in the stacks.");
    }

     if (!shelves.containsKey(newBook.getSubject())) {
       System.out.println("No shelf for " + newBook.getSubject() + " books");
       return Code.SHELF_EXISTS_ERROR;
     }

     // TODO: still need to do the condition if the shelf does exists

     // TODO: remove this, this is only temp
     return Code.SUCCESS;
   }

  static public LocalDate convertDate(String date, Code errorCode) {
    LocalDate localDate = LocalDate.of(1970, 01, 01);

    // TODO: maybe use if else statements for this?
    if (date.equals("0000")) {
      return localDate;
    }

    if (!date.contains("-")) {
      System.out.println("ERROR: date conversion error, could not parse " + date);
      System.out.println("Using default date " + localDate);
      return localDate;
    }

    String[] dateSplit = date.split("-");

    // array to hold all the converted string numbers from the string split above
    // year is at index 0, month at index 1, day at index 2
    int[] array = new int[3];

    // counter for the array above
    int counter = 0;

    // converting the string array of year, month, and day to int array
    for (int i = 0; i < dateSplit.length; i++) {
      array[counter] = convertInt(dateSplit[i], errorCode);
      counter++;
    }

    // this is checking to see if there is a 0 in the array of the now converted date
    if (array[0] < 0 || array[1] < 0 || array[2] < 0) {
      if (array[0] < 0) {
        System.out.println("Error converting date: Year " + array[0]);
      }

      if (array[1] < 0) {
        System.out.println("Error converting date: Month " + array[1]);
      }

      if (array[2] < 0) {
        System.out.println("Error converting date: Day " + array[2]);
      }

      System.out.println("Using default date (01-jan-1970)");
      return localDate;
    }

    // after all the checks above the date is finally updated and then returned
    localDate = LocalDate.of(array[0], array[1], array[2]);

    return localDate;
  }

  static public int convertInt(String recordCountString, Code code) {
    int sToInt = 0;

    try {
      sToInt = Integer.parseInt(recordCountString);
    } catch (NumberFormatException e) {
      switch (code) {
        case BOOK_COUNT_ERROR -> {
          System.out.println("Value which caused the error: " + recordCountString);
          System.out.println("Error: Could not read number of books");
          return -2;
        }
        case PAGE_COUNT_ERROR -> {
          System.out.println("Value which caused the error: " + recordCountString);
          System.out.println("Error: could not parse page count");
          return -8;
        }
        case DATE_CONVERSION_ERROR -> {
          System.out.println("Value which caused the error: " + recordCountString);
          System.out.println("Error: Could not parse date component");
          return -101;
        }
        default -> {
          System.out.println("Value which caused the error: " + recordCountString);
          System.out.println("Error: Unknown conversion error");
          return -999;
        }
      }
    }

    return sToInt;
  }

  private Code errorCode(int codeNumber) {
    for (Code code : Code.values()) {
      if (code.getCode() == codeNumber) {
        return code;
      }
    }


    // TODO: update this, i don't know what this is really for
    return Code.UNKNOWN_ERROR;
  }


  // constructor here
  public Library(String name) {
    this.name = name;
  }
}