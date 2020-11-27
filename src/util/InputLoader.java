package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputLoader {

  public static ArrayList<String> load(String fileName) throws FileNotFoundException {
    ArrayList<String> data = new ArrayList<String>();
    Scanner s = new Scanner(new File(fileName));
    while (s.hasNext()) {
      data.add(s.next());
    }
    s.close();

    return data;
  }
}
