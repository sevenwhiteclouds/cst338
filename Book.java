import java.time.LocalDate;

public class Book
{
  /*
   * Author: Keldin Maldonado
   * Date: 2022 October 28
   * Description: The first file in the library project.
   * This class is a Plain Old Java Object (POJO) and represents a
   * book in a library.
   */

  public static final int ISBN_ = 0;
  public static final int TITLE_ = 1;
  public static final int SUBJECT_ = 2;
  public static final int PAGE_COUNT_ = 3;
  public static final int AUTHOR_ = 4;
  public static final int DUE_DATE_ = 5;

  private String isbn;
  private String title;
  private String subject;
  private int pageCount;
  private String author;
  private LocalDate dueDate;
}