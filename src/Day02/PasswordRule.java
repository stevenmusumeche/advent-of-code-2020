package Day02;

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