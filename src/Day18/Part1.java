package Day18;

import util.InputLoader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part1 {

  Pattern p = Pattern.compile("^(?<grouping>(?<op>[*+] ?)?(?<val>[\\d]+ ?)).*?$");
  Pattern parensP = Pattern.compile("^(?<op>[*+] ?)?\\(.*?$");

  enum Operation {
    Multiply,
    Add
  }

  public static void main(String[] args) {
    Part1 program = new Part1();
    long answer = program.run();
    System.out.println("answer " + answer);
  }

  private long run() {
    List<String> lines = InputLoader.loadForPackage(this.getClass().getPackageName());
    List<Long> answers = lines.stream().map(this::processLine).collect(Collectors.toList());
    return answers.stream().mapToLong(Long::longValue).sum();
  }

  protected long processLine(String line) {
    String remaining = line;

    long answer = 0;
    while (remaining.length() > 0) {
      Matcher mp = parensP.matcher(remaining);
      if (mp.matches()) {
        // we have a group that we need to recursively parse/solve
        int[] parenBounds = findParensBounds(remaining);
        Operation operation = parseOp(mp.group("op"));
        long primitive = processLine(remaining.substring(parenBounds[0] + 1, parenBounds[1]));
        // answer = performOp(answer, primitive, operation);

        remaining =
            (mp.group("op") == null ? "" : mp.group("op"))
                + primitive
                + remaining.substring(parenBounds[1] + 1);
      } else {
        // we have a primitive that we can process
        Matcher m = p.matcher(remaining);
        if (!m.matches()) {
          throw new RuntimeException("invariant");
        }
        long primitive = Integer.parseInt(m.group("val").trim());
        Operation operation = parseOp(m.group("op"));

        answer = performOp(answer, primitive, operation);
        remaining = remaining.substring(m.group("grouping").length());
      }
    }

    return answer;
  }

  protected int[] findParensBounds(String input) {
    int firstParens = input.indexOf("(");
    int[] bounds = {firstParens, -1};
    int numOpen = 0;
    for (int i = firstParens; i < input.length(); i++) {
      if (input.charAt(i) == '(') numOpen++;
      if (input.charAt(i) == ')') numOpen--;
      if (numOpen == 0) {
        bounds[1] = i;
        break;
      }
    }

    return bounds;
  }

  protected Operation parseOp(String op) {
    if (op == null) return null;

    if (op.trim().equals("*")) return Operation.Multiply;
    else if (op.trim().equals("+")) return Operation.Add;

    throw new RuntimeException("invariant");
  }

  protected long performOp(long curAnswer, long primitive, Operation op) {
    if (op == null) return primitive;

    if (op.equals(Operation.Add)) {
      return curAnswer + primitive;
    } else if (op.equals(Operation.Multiply)) {
      return curAnswer * primitive;
    }

    throw new RuntimeException("invariant");
  }
}
