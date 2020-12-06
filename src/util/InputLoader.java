package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    while (s.hasNextLine()) {
      data.add(s.nextLine());
    }
    s.close();

    return data;
  }

  public static List<String> loadForPackage(String packageName) {
    try {
      String path = Paths.get("").toAbsolutePath().toString();
      String fileName = path + "/src/" + packageName + "/input.txt";
      ArrayList<String> data = new ArrayList<>();
      Scanner s = new Scanner(new File(fileName));
      while (s.hasNextLine()) {
        data.add(s.nextLine());
      }
      s.close();

      return data;
    } catch (FileNotFoundException e) {
      return new ArrayList<>();
    }
  }

  public static String loadAsString(String packageName) {
    try {
      String path = Paths.get("").toAbsolutePath().toString();
      String fileName = path + "/src/" + packageName + "/input.txt";
      return Files.readString(Paths.get(fileName));
    } catch (IOException e) {
      return "";
    }
  }

}
