package Day14;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instruction {
  final Pattern memPattern =
      Pattern.compile("^mem\\[(?<memoryAddress>[\\d]+)] = (?<value>[\\d]+)$");
  final private int memoryAddress;
  final private int value;

  public Instruction(String line) {
    Matcher m = memPattern.matcher(line);
    if (!m.matches()) {
      throw new RuntimeException("unable to parse");
    }

    memoryAddress = Integer.parseInt(m.group("memoryAddress"));
    value = Integer.parseInt(m.group("value"));
  }

  public int getMemoryAddress() {
    return memoryAddress;
  }

  public int getValue() {
    return value;
  }
}
