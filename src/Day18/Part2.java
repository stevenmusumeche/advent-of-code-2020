package Day18;

import util.InputLoader;

import java.util.*;
import java.util.stream.Collectors;

public class Part2 extends Base {
  public static void main(String[] args) {
    Part2 program = new Part2();
    long answer = program.run();
    System.out.println("answer " + answer);
  }

  protected final long run() {
    List<String> lines = InputLoader.loadForPackage(this.getClass().getPackageName());
    List<Long> answers =
        lines.stream()
            .map(
                line -> {
                  List<String> tokens = tokenize(line);
                  return solve(tokens);
                })
            .collect(Collectors.toList());
    return answers.stream().mapToLong(Long::longValue).sum();
  }

  protected long solveOnlyPrimitives(String expression) {
    if (!isOnlyPrimitives(expression)) throw new RuntimeException("Can't run on non-primitives");
    List<Long> withSolvedAddition = solveAddition(expression);
    return withSolvedAddition.stream().reduce((long) 1, (a, b) -> a * b);
  }

  private List<Long> solveAddition(String expression) {
    String[] orig = expression.split(" ");
    List<Long> newPieces = new ArrayList<>();
    if (orig.length == 1) {
      newPieces.add(Long.parseLong(expression));
      return newPieces;
    }
    int i = 0;
    while (i < orig.length - 1) {
      Operation nextOp = parseOp(orig[i + 1]);
      if (nextOp.equals(Operation.Multiply)) {
        newPieces.add(Long.parseLong(orig[i]));
      } else if (nextOp.equals(Operation.Add)) {
        long answer = Long.parseLong(orig[i]) + Long.parseLong(orig[i + 2]);
        orig[i + 2] = String.valueOf(answer);
      }
      i += 2;
    }
    newPieces.add(Long.parseLong(orig[i]));

    return newPieces;
  }

  private boolean isOnlyPrimitives(String expresssion) {
    return !expresssion.contains("(");
  }

  private List<String> tokenize(String expression) {
    List<String> tokens = new ArrayList<>();
    String remaining = expression;

    while (remaining.length() > 0) {
      String firstChar = String.valueOf(remaining.charAt(0));
      boolean isCompound = firstChar.equals("(");
      boolean isPrimitive = firstChar.matches("[\\d]");

      if (isCompound) {
        // get the entire token
        int[] parensBounds = findParensBounds(remaining);
        String token = remaining.substring(parensBounds[0] + 1, parensBounds[1]);
        tokens.add(token);
        remaining = remaining.substring(token.length() + 2).trim();
      } else if (isPrimitive) {
        // starts with primitive
        String primitive;
        if (remaining.contains(" ")) {
          primitive = remaining.substring(0, remaining.indexOf(" "));
        } else {
          primitive = remaining;
        }

        tokens.add(primitive);
        remaining = remaining.substring(primitive.length()).trim();
      } else {
        tokens.add(firstChar);
        remaining = remaining.substring(2).trim();
      }
    }

    return tokens;
  }

  protected long solve(List<String> tokens) {
    boolean allPrimitives = tokens.stream().allMatch(this::isOnlyPrimitives);
    if (allPrimitives) {
      // WE FINALLY BROKE IT DOWN TO ONLY PRIMITIVES AND OPERATIONS
      return calcAnswer(tokens);
    }

    List<String> newTokens = new ArrayList<>();
    tokens.forEach(token -> newTokens.add(recursiveHelper(token)));
    return solve(newTokens);
  }

  private long calcAnswer(List<String> tokens) {
    List<String> solvedTokens =
        tokens.stream()
            .map(
                token -> {
                  if (token.matches("^[+*]$")) return token;
                  return String.valueOf(solveOnlyPrimitives(token));
                })
            .collect(Collectors.toList());
    return solveOnlyPrimitives(String.join(" ", solvedTokens));
  }

  protected String recursiveHelper(String token) {
    if (token.matches("^[+*]$")) {
      return token;
    } else if (isOnlyPrimitives(token)) {
      return String.valueOf(solveOnlyPrimitives(token));
    } else {
      // compound group
      List<String> tokens = tokenize(token);
      long answer = solve(tokens);
      return String.valueOf(answer);
    }
  }
}
