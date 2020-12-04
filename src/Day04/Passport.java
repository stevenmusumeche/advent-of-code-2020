package Day04;

import java.util.HashMap;
import java.util.Map;

public class Passport {
  public Map<String, String> fields = new HashMap<>();

  public boolean isValid() {
    if (fields.size() == 8) {
      return true;
    }

    if(fields.size() == 7 && !fields.containsKey("cid")) {
      return true;
    }

    return false;
  }

  public void addCreds(String creds) {
    String[] pairs = creds.split(" ");
    for (String pair : pairs) {
      String[] pieces = pair.split(":");
      fields.put(pieces[0], pieces[1]);
    }
  }
}
