package Day02;

public class ParsedLine {
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
    char first = password.charAt(rule.getMin() - 1);
    char last = password.charAt(rule.getMax() - 1);
    boolean firstMatches = first == rule.getLetter();
    boolean lastMatches = last == rule.getLetter();

    if(firstMatches == lastMatches) {
      return false;
    }

    return true;
  }
}