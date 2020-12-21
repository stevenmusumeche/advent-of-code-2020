package Day18;

import util.InputLoader;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

abstract class Base {
  Pattern p = Pattern.compile("^(?<grouping>(?<op>[*+] ?)?(?<val>[\\d]+ ?)).*?$");
  Pattern parensP = Pattern.compile("^(?<op>[*+] ?)?\\(.*?$");

  enum Operation {
    Multiply,
    Add
  }

  final protected Operation parseOp(String op) {
    if (op == null) return null;

    if (op.trim().equals("*")) return Operation.Multiply;
    else if (op.trim().equals("+")) return Operation.Add;

    throw new RuntimeException("invariant");
  }

  final protected int[] findParensBounds(String input) {
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

  final protected long performOp(long curAnswer, long primitive, Operation op) {
    if (op == null) return primitive;

    if (op.equals(Operation.Add)) {
      return curAnswer + primitive;
    } else if (op.equals(Operation.Multiply)) {
      return curAnswer * primitive;
    }

    throw new RuntimeException("invariant");
  }
}
