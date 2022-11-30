import java.util.*;

public class Shelf {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 November 7
   * Abstract: This is the third part of project 1.
   * This is the source code for the creation of the shelves part
   * of the library we are building.
   */

  public static final int SHELF_NUMBER_ = 0;
  public static final int SUBJECT_ = 1;

  private int shelfNumber;
  private String subject;
  private HashMap<Book, Integer> books = new HashMap<>();

  public Shelf(int shelfNumber, String subject) {
    this.shelfNumber = shelfNumber;
    this.subject = subject;
  }

  public String listBooks() {
    String fullBook = "";
    int totalBooks = 0;

    // I need to capture all keys to figure out how many of those books exist
    // also keeping track of the total count of keys aka books
    for (Book key : books.keySet()) {
      totalBooks += getBookCount(key);

      fullBook += key.toString() + " " + getBookCount(key) + "\n";
    }

    // removing the last \n aka new line that was added from loop above
    fullBook = fullBook.substring(0, fullBook.length() - 1);

    return totalBooks + " books on shelf: " + this + "\n" + fullBook;
  }


  public Code removeBook(Book book) {
    if (!books.containsKey(book)) {
      System.out.println(book.getTitle() + " is not on shelf " + getSubject());
      return Code.BOOK_NOT_IN_INVENTORY_ERROR;
    }
    else if (books.containsKey(book) && getBookCount(book) == 0) {
      System.out.println("No copies of " + book.getTitle() + " remain on shelf " + getSubject());
      return Code.BOOK_NOT_IN_INVENTORY_ERROR;
    }
    else if (books.containsKey(book) && getBookCount(book) > 0) {
      books.replace(book, books.get(book) - 1);

      System.out.println(book + " successfully removed from shelf " + getSubject());
      return Code.SUCCESS;
    }
    else {
      return Code.UNKNOWN_ERROR;
    }
  }

  public Code addBook(Book book) {
    if (!books.containsKey(book) && !book.getSubject().equals(subject)) {
      return Code.SHELF_SUBJECT_MISMATCH_ERROR;
    }
    else if (!books.containsKey(book) && book.getSubject().equals(subject)) {
      books.put(book, 1);
      System.out.println(book + "added to shelf " + this.toString());
      return Code.SUCCESS;
    }
    else if (books.containsKey(book)) {
      books.replace(book, books.get(book) + 1);
      System.out.println(book + "added to shelf " + this.toString());
      return Code.SUCCESS;
    }
    else {
      return Code.UNKNOWN_ERROR;
    }
  }



  public int getBookCount(Book book) {
    return books.getOrDefault(book, -1);
  }

  public int getShelfNumber() {
    return shelfNumber;
  }

  public String getSubject() {
    return subject;
  }

  public HashMap<Book, Integer> getBooks() {
    return books;
  }

  public void setBooks(HashMap<Book, Integer> books) {
    this.books = books;
  }

  public void setShelfNumber(int shelfNumber) {
    this.shelfNumber = shelfNumber;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Shelf shelf = (Shelf) o;

    if (getShelfNumber() != shelf.getShelfNumber()) return false;
    return getSubject() != null ? getSubject().equals(shelf.getSubject()) : shelf.getSubject() == null;
  }

  @Override
  public int hashCode() {
    int result = getShelfNumber();
    result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return shelfNumber + " : " + subject;
  }
}
