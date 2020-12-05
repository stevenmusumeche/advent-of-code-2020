package Day04;

import java.util.ArrayList;
import java.util.List;

import util.InputLoader;

public class Base {
  protected List<Passport> parseInput() {
    List<String> input = InputLoader.loadForPackage(this.getClass().getPackageName());
    List<Passport> passports = new ArrayList<>();

    Passport currentPassport = new Passport();
    for (String line : input) {
      boolean isSeperator = line.equals("");
      if (isSeperator) {
        passports.add(currentPassport);
        currentPassport = new Passport();
      } else {
        currentPassport.addCreds(line);
      }
    }
    passports.add(currentPassport);

    return passports;
  }
}
