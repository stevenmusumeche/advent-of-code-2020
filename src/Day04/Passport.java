package Day04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Passport {
  private Map<String, String> fields = new HashMap<>();

  public boolean isValid() {
    if (fields.size() < 7) {
      return false;
    }

    if(fields.size() == 7 && fields.containsKey("cid")) {
      return false;
    }

    // System.out.println("The set is: " + fields.entrySet());

    return validateFields();
  }

  private boolean validateFields() {
    if(!validateBirthYear()) {
      return false;
    }
    if (!validateIssueYear()) {
      return false;
    }
    if (!validateExpirationYear()) {
      return false;
    }
    if (!validateHeight()) {
      return false;
    }
    if (!validateHairColor()) {
      return false;
    }
    if (!validateEyeColor()) {
      return false;
    }
    if (!validatePassportId()) {
      return false;
    }
    return true;
  }

  private boolean validateBirthYear() {
    return validateInt("byr", 1920, 2002);
  }

  private boolean validateIssueYear() {
    return validateInt("iyr", 2010, 2020);
  }

  private boolean validateExpirationYear() {
    return validateInt("eyr", 2020, 2030);
  }

  private boolean validateHeight() {
    String raw = fields.get("hgt");
    if (raw == null) {
      return false;
    }
    int value = Integer.parseInt(raw.substring(0, raw.length() - 2));
    String unit = raw.substring(raw.length() - 2);
    if(unit.equals("cm")) {
      return value >= 150 && value <= 193;
    } else if (unit.equals("in")) {
      return value >= 59 && value <= 76;
    }
    return false;
  }

  private boolean validateHairColor() {
    String raw = fields.get("hcl");
    if (raw == null) {
      return false;
    }

    return Pattern.compile("#[a-f\\d]{6}").matcher(raw).matches();
  }

  private boolean validateEyeColor() {
    String raw = fields.get("ecl");
    if (raw == null) {
      return false;
    }

    Set<String> validVals = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth" ));

    return validVals.contains(raw);
  }

  private boolean validatePassportId() {
    String raw = fields.get("pid");
    if (raw == null) {
      return false;
    }

    return Pattern.compile("^[\\d]{9}$").matcher(raw).matches();
  }

  private boolean validateInt(String key, int min, int max) {
    String raw = fields.get(key);
    if (raw == null) {
      return false;
    }
    int value = Integer.parseInt(raw);
    return value >= min && value <= max;
  }

  public void addCreds(String creds) {
    String[] pairs = creds.split(" ");
    for (String pair : pairs) {
      String[] pieces = pair.split(":");
      fields.put(pieces[0], pieces[1]);
    }
  }
}
