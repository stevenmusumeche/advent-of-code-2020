package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputLoader {
  private InputLoader() {
    throw new IllegalStateException("Utility class");
  }

  public static List<String> load(String fileName) throws FileNotFoundException {
    ArrayList<String> data = new ArrayList<>();
    Scanner s = new Scanner(new File(fileName));
    while (s.hasNext()) {
      data.add(s.next());
    }
    s.close();

    return data;
  }
}
