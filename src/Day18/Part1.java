package Day18;

import util.InputLoader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Part1 extends Base {

  public static void main(String[] args) {
    Part1 program = new Part1();
    long answer = program.run();
    System.out.println("answer " + answer);
  }

  protected final long run() {
    List<String> lines = InputLoader.loadForPackage(this.getClass().getPackageName());
    List<Long> answers = lines.stream().map(this::processLine).collect(Collectors.toList());
    return answers.stream().mapToLong(Long::longValue).sum();
  }

  protected long processLine(String remaining) {
    long answer = 0;
    while (remaining.length() > 0) {
      Matcher mp = parensP.matcher(remaining);
      if (mp.matches()) {
        // we have a group that we need to recursively parse/solve
        int[] parenBounds = findParensBounds(remaining);
        long primitive = processLine(remaining.substring(parenBounds[0] + 1, parenBounds[1]));
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
}
