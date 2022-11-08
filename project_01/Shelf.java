package project_01;

import java.util.HashMap;

public class Shelf {
  public static final int SHELF_NUMBER_ = 0;
  public static final int SUBJECT_ = 0;

  private int selfNumber;
  private String subject;
  private HashMap<Book, Integer> books = new HashMap<>();

  public Shelf(int selfNumber, String subject) {
    this.selfNumber = selfNumber;
    this.subject = subject;
  }

  // TODO: perhaps this has to work with the string function that i have not implemented
  public String listBooks() {
    //return "books on shelf: " + getSelfNumber() + " : " + getSubject() + "\n" +


    return "work in progress..";
  }

  public Code removeBook(Book book) {
    if (!books.containsKey(book)) {
      System.out.println(book.getTitle() + " is not on shelf " + getSubject());
      return Code.BOOK_NOT_IN_INVENTORY_ERROR;
    }
    else if (books.containsKey(book) && getBookCount(book) == -1) {
      System.out.println("No copies of " + book.getTitle() + " remain on shelf " + getSubject());
      return Code.BOOK_NOT_IN_INVENTORY_ERROR;
    }
    else if (books.containsKey(book) && getBookCount(book) != -1) {
      books.replace(book, books.get(book) - 1);

      System.out.println(book.getTitle() + " successfully removed from shelf " + getSubject());
      return Code.SUCCESS;
    }
    else {
      return Code.UNKNOWN_ERROR;
    }
  }

  public Code addBook(Book book) {
    if (!books.containsKey(book) && book.getSubject() != subject) {
      return Code.SHELF_SUBJECT_MISMATCH_ERROR;
    }
    else if (!books.containsKey(book) && book.getSubject() == subject) {
      books.put(book, 1);
      return Code.SUCCESS;
    }
    else if (books.containsKey(book)) {
      books.replace(book, books.get(book) + 1);
      return Code.SUCCESS;
    }
    else {
      return Code.UNKNOWN_ERROR;
    }
  }

  public int getBookCount(Book book) {
    return books.getOrDefault(book, -1);
  }

  public int getSelfNumber() {
    return selfNumber;
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

  public void setSelfNumber(int selfNumber) {
    this.selfNumber = selfNumber;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }
}
