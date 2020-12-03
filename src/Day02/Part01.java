package Day02;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.InputLoader;

public class Part01 {
  public static void main(String[] args) {
    try {
      Part01 program = new Part01();
      int answer = program.run();
      System.out.printf("Answer %d%n", answer);
    } catch (Exception e) {
      System.out.println("Exception!");
      System.out.println(e);
    }
  }

  private int run() {
    List<String> input = loadInput();
    int numValid = 0;
    for (String line : input) {
      ParsedLine parsedLine = parseLine(line);
      if(parsedLine.isValidPassword()) {
        numValid++;
      }
    }

    return numValid;
  }

  private ParsedLine parseLine(String line) {
    Pattern pattern = Pattern.compile("([\\d]+)\\-([\\d]+) ([a-z]): (.*?$)");
    Matcher matcher = pattern.matcher(line);
    if(!matcher.matches()) {
      throw new RuntimeException();
    }
    PasswordRule rule = new PasswordRule(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
        matcher.group(3).charAt(0));

    return new ParsedLine(rule, matcher.group(4));
  }

  private List<String> loadInput() {
    try {
      String packageName = this.getClass().getPackageName();
      String path = Paths.get("").toAbsolutePath().toString();
      return InputLoader.load(path + "/src/" + packageName + "/input.txt");
    } catch (FileNotFoundException e) {
      return new ArrayList<>();
    }
  }
}

class ParsedLine {
  private PasswordRule rule;
  private String password;

  public ParsedLine(PasswordRule rule, String password) {
    this.rule = rule;
    this.password = password;
  }

  public PasswordRule getRule() {
    return rule;
  }

  public String getPassword() {
    return password;
  }

  public boolean isValidPassword() {
    long count = password.chars().filter(ch -> ch == rule.getLetter()).count();

    if(count < rule.getMin() || count > rule.getMax()) {
      return false;
    }

    return true;
  }
}

class PasswordRule {
  private int min;
  private int max;
  private char letter;

  public PasswordRule(int min, int max, char letter) {
    this.min = min;
    this.max = max;
    this.letter = letter;
  }

  public int getMin() {
    return this.min;
  }

  public int getMax() {
    return this.max;
  }

  public char getLetter() {
    return letter;
  }

  public String toString() {
    return "PasswordRule: " + this.min + " " + this.max + " " + this.letter;
  }
}