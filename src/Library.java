import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {
  public static final int LENDING_LIMIT = 5;

  private String name = "";
  private static int libraryCard = 0;
  private List<Reader> readers = new ArrayList<>();
  private HashMap<String, Shelf> shelves = new HashMap<>();
  private HashMap<Book, Integer> books = new HashMap<>();
}