import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class Library {
  /*
   * Abstract: the final library file--part 4--of project1
   * Creates a library that manages readers and books and shelves
   * Author: Keldin Maldonado
   * Date: 2022 - 11 - 20
   */
  public static final int LENDING_LIMIT = 5;

  private String name = "";
  private static int libraryCard = 0;
  private List<Reader> readers = new ArrayList<>();
  private HashMap<String, Shelf> shelves = new HashMap<>();
  private HashMap<Book, Integer> books = new HashMap<>();

  public Code init(String filename) {
    File file = new File(filename);
    Scanner scanner = null;

    try {
      scanner = new Scanner(file);
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
        // updating the counter so i know where to store the data in the array
        counter++;
        // updating the error code so i know what error code to send
        errorCodeCounter = errorCodeCounter+2;

        array[counter] = convertInt(temp, errorCode(errorCodeCounter));
      }
    }

    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist or was not able to open.");
      return Code.FILE_NOT_FOUND_ERROR;
    }

    // remember the amount of books in the csv file is stored in index 0 of array
    System.out.println(initBooks(array[0], scanner));
    listBooks();

    System.out.println(initShelves(array[1], scanner));
    listShelves(true);

    System.out.println(initReader(array[2], scanner));
    listReaders();


    return Code.SUCCESS;
  }

  public Code listShelves(boolean showbooks) {
    for (String shelfKey : shelves.keySet()) {
      int total = 0;

      for (Book bookKey : shelves.get(shelfKey).getBooks().keySet()) {
        total += shelves.get(shelfKey).getBookCount(bookKey);
      }

      System.out.print(total + " books on shelf " + shelves.get(shelfKey).toString() + "\n");

      if (showbooks) {
        for (Book bookKey : shelves.get(shelfKey).getBooks().keySet()) {
          System.out.println(bookKey.getTitle() + " by " + bookKey.getAuthor() + " ISBN:" + bookKey.getIsbn() + " " + shelves.get(shelfKey).getBookCount(bookKey));
        }

        System.out.println();
      }
    }

    return Code.SUCCESS;
  }

  private Code initReader(int readerCount, Scanner scan) {
    if (readerCount <= 0) {
      return Code.READER_COUNT_ERROR;
    }

    // this skips the first line that contains the amount of shelves
    scan.hasNextLine();
    scan.nextLine();

    for (int i = 0; i < readerCount; i++) {
      scan.hasNextLine();
      String[] readerSplit = scan.nextLine().split(",");

      int convertCardNum = convertInt(readerSplit[0], Code.READER_CARD_NUMBER_ERROR);

      // i need to know how many books the user has to be added to the account. need int to use...
      int howManyBooks = convertInt(readerSplit[3], Code.BOOK_COUNT_ERROR);

      Reader reader = new Reader(convertCardNum, readerSplit[1], readerSplit[2]);

      // this is the starting point where the first book from the reader read in is at
      int startPoint = 4;

      addReader(reader);

      for (int j = 0; j < howManyBooks; j++) {
        Book bookToAdd = getBookByISBN(readerSplit[startPoint]);

        LocalDate updateDate = convertDate(readerSplit[startPoint+1], Code.DATE_CONVERSION_ERROR);

        bookToAdd.setDueDate(updateDate);

        if (bookToAdd == null) {
          System.out.println("ERROR");
        }
        else {
          System.out.println(checkOutBook(reader, bookToAdd));
        }

        startPoint = startPoint +2;
      }
    }

    return Code.SUCCESS;
  }

  public Code addReader(Reader reader) {
    boolean yes = false;

    for (int i = 0; i < readers.size(); i++) {
      if (readers.get(i).equals(reader)) {
        yes = true;
        break;
      }
    }

    if (yes) {
      System.out.println(reader.getName() + " already has an account!");
      return Code.READER_ALREADY_EXISTS_ERROR;
    }

    for (int i = 0; i < readers.size(); i++) {
      if (readers.get(i).getName().equals(reader.getName()) && readers.get(i).getCardNumber() == reader.getCardNumber()) {
        System.out.println(readers.get(i).getName() + " and " + reader.getName() + " have the same card number!");
        return Code.READER_CARD_NUMBER_ERROR;
      }
    }

    if (reader.getCardNumber() > libraryCard) {
      libraryCard = reader.getCardNumber();
    }

    readers.add(reader);
    System.out.println(reader.getName() + " added to the library!");

    return Code.SUCCESS;
  }

  public Code checkOutBook(Reader reader, Book book) {
    if (!readers.contains(reader)) {
      System.out.println(reader.getName() + " doesn't have an account here");
      return Code.READER_NOT_IN_LIBRARY_ERROR;
    }

    if (reader.getBookCount() == LENDING_LIMIT) {
      System.out.println(reader.getName() + " has reached the lending limit, " + LENDING_LIMIT);
      return  Code.BOOK_LIMIT_REACHED_ERROR;
    }

    if (!books.containsKey(book)) {
      System.out.println("ERROR: could not find " + book);
      return Code.BOOK_NOT_IN_INVENTORY_ERROR;
    }

    if (!shelves.containsKey(book.getSubject())) {
      System.out.println("no shelf for " + book.getSubject() + " books!");
      return Code.SHELF_EXISTS_ERROR;
    }

    if (shelves.get(book.getSubject()).getBookCount(book) < 1) {
      System.out.println("ERROR: no copies of " + book + " remain");
      return Code.BOOK_NOT_IN_INVENTORY_ERROR;
    }

    for (int i = 0; i < readers.size(); i++) {
      if (readers.get(i) == reader) {
        Code returnedMessage = readers.get(i).addBook(book);

        if (returnedMessage != Code.SUCCESS) {
          System.out.println("Couldn't checkout " + book);
          return returnedMessage;
        }

        break;
      }
    }

    if (shelves.get(book.getSubject()).removeBook(book) == Code.SUCCESS) {
      System.out.println(book + " checked out successfully");
    }

    return Code.SUCCESS;
  }

  public Code removeReader(Reader reader) {
    if (!readers.contains(reader)) {
      System.out.println(reader + " is no part of this library");
      return Code.READER_NOT_IN_LIBRARY_ERROR;
    }

    // idk where the reader in the reader array is at, lets find out and save it
    int foundReader = 0;

    for (int i = 0; i < readers.size(); i++) {
      if (readers.get(i) == reader) {
        foundReader = i;
      }
    }

    if (readers.get(foundReader).getBookCount() != 0) {
      System.out.println(readers.get(foundReader) + " must return all books!");
      return Code.READER_STILL_HAS_BOOKS_ERROR;
    }

    readers.remove(foundReader);

    return Code.SUCCESS;
  }

  private Code initShelves(int shelfCount, Scanner scan) {
    if (shelfCount < 1) {
      return Code.SHELF_COUNT_ERROR;
    }

    System.out.println("parsing " + shelfCount + " shelves");

    // this skips the first line that contains the amount of shelves
    scan.hasNextLine();
    scan.nextLine();

    for (int i = 0; i < shelfCount; i++) {
      scan.hasNextLine();
      String[] shelfSplit = scan.nextLine().split(",");

      System.out.print("Parsing Shelf: ");
      for (int j = 0; j < shelfSplit.length; j++) {
        System.out.print(shelfSplit[j]);

        if (j < shelfSplit.length - 1) {
          System.out.print(",");
        }
      }

      System.out.println();

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
    if (shelves.containsKey(shelf.getSubject())) {
      System.out.println("ERROR: Shelf already exists " + shelf);
      return Code.SHELF_EXISTS_ERROR;
    }

    shelf.setShelfNumber(shelves.size()+1);

    shelves.put(shelf.getSubject(), shelf);

    for (Book key : books.keySet()) {
      if (key.getSubject().equals(shelf.getSubject())) {
        for (int i = 0; i < books.get(key); i++) {
          shelves.get(shelf.getSubject()).addBook(key);
          System.out.println(key.getTitle() +  " by " + key.getAuthor() + " ISBN:" + key.getIsbn() + " added to shelf " + shelf);
        }
      }
    }

    return Code.SUCCESS;
  }

  public Code addShelf(String shelfSubject) {
    Shelf shelf = new Shelf(shelves.size() + 1, shelfSubject);

    addShelf(shelf);

    return Code.SUCCESS;
  }

  private Code initBooks(int bookCount, Scanner scan) {
    if (bookCount < 1) {
      return Code.LIBRARY_ERROR;
    }

    System.out.println("parsing " + bookCount + " books");
    // this skips the first line that contains the amount of books
    scan.hasNextLine();
    scan.nextLine();

    for (int i = 0; i < bookCount; i++) {
      scan.hasNextLine();
      String[] bookSplit = scan.nextLine().split(",");

      System.out.print("parsing book: ");
      for (int j = 0; j < bookSplit.length; j++) {
        System.out.print(bookSplit[j]);

        if (j < bookSplit.length - 1) {
          System.out.print(",");
        }
      }

      System.out.println();

      int convertedPageC = convertInt(bookSplit[3], Code.PAGE_COUNT_ERROR);
      LocalDate convertedDueDate = convertDate(bookSplit[5], Code.DUE_DATE_ERROR);

      if (convertedPageC < 0) {
        return Code.PAGE_COUNT_ERROR;
      }

      if (convertedDueDate == null) {
        return Code.DATE_CONVERSION_ERROR;
      }

      Book book = new Book(bookSplit[Book.ISBN_], bookSplit[Book.TITLE_], bookSplit[Book.SUBJECT_], convertedPageC, bookSplit[Book.AUTHOR_], convertedDueDate);

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

  public int listReaders(boolean showBooks) {
    if (!showBooks) {
      for (int i = 0; i < readers.size(); i++) {
        System.out.println(readers.get(i).toString());
      }

      return readers.size();
    }

    for (int i = 0; i < readers.size(); i++) {
      System.out.println(readers.get(i).getName() + "(#" + readers.get(i).getCardNumber() + ") has the following books:");
      System.out.println(readers.get(i).getBooks());
    }

    return readers.size();
  }

  private Code addBookToShelf(Book book, Shelf shelf) {
    if (returnBook(book) == Code.SUCCESS) {
      return Code.SUCCESS;
    }

    if (!shelf.getSubject().equals(book.getSubject())) {
      return Code.SHELF_SUBJECT_MISMATCH_ERROR;
    }

    Code returnedAddToShelfM = shelf.addBook(book);

    if (returnedAddToShelfM != Code.SUCCESS) {
      System.out.println("Could not add " + book + " to shelf");
      return returnedAddToShelfM;
    }

    System.out.println(book + " added to shelf");
    return Code.SUCCESS;
  }

  public Code returnBook(Reader reader, Book book) {
    // i need to figure out where the reader in the arraylist lives in the library object
    int foundYou = 0;

    for (int i = 0; i < readers.size(); i++) {
      if (readers.get(i) == reader) {
        foundYou = i;
      }
    }

    if (!readers.get(foundYou).getBooks().contains(book)) {
      System.out.println(reader.getName() + " doesn't have " + book + " checked out");
      return Code.READER_DOESNT_HAVE_BOOK_ERROR;
    }

    if (!books.containsKey(book)) {
      return Code.BOOK_NOT_IN_INVENTORY_ERROR;
    }

    System.out.println(reader.getName() + " is returning " + book);
    Code returnedRemoveBookM = readers.get(foundYou).removeBook(book);

    if (returnedRemoveBookM != Code.SUCCESS) {
      System.out.println("Could not return " + book);
      return returnedRemoveBookM;
    }

    return returnBook(book);
  }

  public Code returnBook(Book book) {
    if (!shelves.containsKey(book.getSubject())) {
      System.out.println("No shelf for " + book);
      return Code.SHELF_EXISTS_ERROR;
    }

    shelves.get(book.getSubject()).addBook(book);
    System.out.println(book.getTitle() + " by " + book.getAuthor() + " ISBN:" + book.getIsbn() + " added to shelf " + shelves.get(book.getSubject()));

    return Code.SUCCESS;
  }

  public Shelf getShelf(Integer shelfNumber) {
    Shelf returnedShelf = null;

    for (String key: shelves.keySet()) {
      if (shelves.get(key).getShelfNumber() == shelfNumber) {
        returnedShelf = shelves.get(key);
      }
    }

    if (returnedShelf == null) {
      System.out.println("No shelf number " + shelfNumber + " found");
      return null;
    }

    return returnedShelf;
  }

  public Shelf getShelf(String subject) {
    Shelf returnedShelf = null;

    for (String key : shelves.keySet()) {
      if (key.equals(subject)) {
        returnedShelf = shelves.get(key);
      }
    }

    if (returnedShelf == null) {
      System.out.println("No shelf for " + subject + " books");
      return null;
    }

    return returnedShelf;
  }

  public Book getBookByISBN(String isbn) {
    Book returnedBook = null;

    for (Book key : books.keySet()) {
      if (key.getIsbn().equals(isbn)) {
        returnedBook = key;
      }
    }

    if (returnedBook == null) {
      System.out.println("ERROR: Could not find a book with isbn: " + isbn);
      return null;
    }

    return returnedBook;
  }

  public Reader getReaderByCard(int cardNumber) {
    Reader returnedReader = null;
    for (int i = 0; i < readers.size(); i++) {
      if (readers.get(i).getCardNumber() == cardNumber) {
        returnedReader = readers.get(i);
      }
    }

    if (returnedReader == null) {
      System.out.println("Could not find a reader with card #" + cardNumber);
      return null;
    }

    return returnedReader;
  }

  public int listBooks() {
    int total = 0;

    for (Book key: books.keySet()) {
      System.out.println(books.get(key) + " copies of " + key);
      total += books.get(key);
    }

    System.out.println();

    return total;
  }

  public Code addBook(Book newBook) {
    if (books.containsKey(newBook)) {
       books.replace(newBook, books.get(newBook) + 1);
       System.out.println(books.get(newBook) + " copies of " + newBook.getTitle() + " by " + newBook.getAuthor() + " ISBN:" + newBook.getIsbn() + " in the stacks.");
    }

    if (!books.containsKey(newBook)) {
      books.put(newBook, 1);
      System.out.println(newBook.getTitle() + " by " + newBook.getAuthor() + " ISBN:" + newBook.getIsbn() + " added to the stacks.");
    }

    if (!shelves.containsKey(newBook.getSubject())) {
     System.out.println("No shelf for " + newBook.getSubject() + " books");
     return Code.SHELF_EXISTS_ERROR;
    }

    shelves.get(newBook.getSubject()).addBook(newBook);

    return Code.SUCCESS;
  }

  static public LocalDate convertDate(String date, Code errorCode) {
    LocalDate localDate = LocalDate.of(1970, 01, 01);

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

    return Code.UNKNOWN_ERROR;
  }

  // constructor here
  public Library(String name) {
    this.name = name;
  }

  static public int getLibraryCardNumber() {
    return libraryCard++;
  }
}