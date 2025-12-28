package parser;

import java.util.List;

public class Parser implements IParser {
  final static private List<String> ALLOWED_DESTINATIONS = List.of(new String[]{
    "M", "D", "MD", "A", "AM", "AD", "AMD"
  });

  @Override
  public Instruction getInstruction(String line) {
    if (line == null || line.isBlank()) {
      return new AbsentInstruction();
    }
    // remove all whitespaces
    String strippedLine = line.replaceAll("\s", "");
    if (strippedLine.startsWith("//")) {
      return new AbsentInstruction();
    }
    if (strippedLine.startsWith("@")) {
      return new AInstruction();
    }

    String[] splitDestination = strippedLine.split("=");
    if (splitDestination.length > 2) {
      throw new IllegalArgumentException(
        String.format("the invalid line: expect at most one '=': %s", line)
      );
    }
    String destination = null;
    if (splitDestination.length == 2) {
      destination = splitDestination[0];
      if (!ALLOWED_DESTINATIONS.contains(destination)) {
        throw new IllegalArgumentException(
          String.format(
            "Unexpected destination. Expect one of %s. Got: %s", ALLOWED_DESTINATIONS,
            line
          )
        );
      }
    }
    return new CInstruction(destination);
  }
}
