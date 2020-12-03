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
    long count = password.chars().filter(ch -> ch == rule.getLetter()).count();

    if (count < rule.getMin() || count > rule.getMax()) {
      return false;
    }

    return true;
  }
}