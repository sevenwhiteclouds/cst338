// only required when inside folder with this name
// turned in without this
package project_01;

import java.util.ArrayList;
import java.util.List;

public class Reader {
  /*
   * Author: Keldin Maldonado
   * Date: 2022 November 1
   * Description: The second file in the library project.
   * This class is a Plain Old Java Object (POJO) and represents a
   * reader in a library.
   */

  public static final int CARD_NUMBER_ = 0;
  public static final int NAME_ = 1;
  public static final int PHONE_ = 2;
  public static final int BOOK_COUNT_ = 3;
  public static final int BOOK_START_ = 4;

  private int cardNumber;
  private String name;
  private String phone;
  private List<Book> books = new ArrayList<>();

  public Reader(int cardNumber, String name, String phone) {
    this.cardNumber = cardNumber;
    this.name = name;
    this.phone = phone;
  }

  public void setCardNumber(int cardNumber) {
    this.cardNumber = cardNumber;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  public int getCardNumber() {
    return cardNumber;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public List<Book> getBooks() {
    return books;
  }

  public int getBookCount() {
    return books.size();
  }

  public String addBook(Book newBook) {

    if (hasBook(newBook)) {
     return "Code.BOOK_ALREADY_CHECK_OUT_ERROR";
    }
    else {
      books.add(newBook);
      return "Code.SUCCESS";
    }
  }

  public String removeBook(Book removeThis) {

    if (hasBook(removeThis)) {
      books.remove(removeThis);
      return "Code.SUCCESS";
    }
    else if (!hasBook(removeThis)) {
      return "Code.READER_DOESNT_HAVE_BOOK_ERROR";
    }
    else {
      return "Code.READER_COULD_NOT_REMOVE_BOOK_ERROR";
    }
  }

  public boolean hasBook(Book checkBook) {

    boolean bookExists = false;

    for (int i = 0; i < books.size(); i++) {
      if (checkBook == books.get(i)) {
        bookExists = true;
        break;
      }
    }

    return bookExists;
  }

  @Override
  public String toString() {
    return name + " (#" + cardNumber + ")" + " has checked out " + books;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Reader reader = (Reader) o;

    if (getCardNumber() != reader.getCardNumber()) return false;
    if (getName() != null ? !getName().equals(reader.getName()) : reader.getName() != null) return false;
    return getPhone() != null ? getPhone().equals(reader.getPhone()) : reader.getPhone() == null;
  }

  @Override
  public int hashCode() {
    int result = getCardNumber();
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
    return result;
  }
}
