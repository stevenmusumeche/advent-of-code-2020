package Day02;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.InputLoader;

public class Base {
  ParsedLine parseLine(String line) {
    Pattern pattern = Pattern.compile("([\\d]+)\\-([\\d]+) ([a-z]): (.*?$)");
    Matcher matcher = pattern.matcher(line);
    if (!matcher.matches()) {
      throw new RuntimeException();
    }
    PasswordRule rule = new PasswordRule(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
        matcher.group(3).charAt(0));

    return new ParsedLine(rule, matcher.group(4));
  }

  List<String> loadInput() {
    try {
      String packageName = this.getClass().getPackageName();
      String path = Paths.get("").toAbsolutePath().toString();
      return InputLoader.load(path + "/src/" + packageName + "/input.txt");
    } catch (FileNotFoundException e) {
      return new ArrayList<>();
    }
  }
}
