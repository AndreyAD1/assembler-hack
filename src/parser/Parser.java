package parser;

import org.jetbrains.annotations.Nullable;

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
      String value = strippedLine.substring(1);
      int parsedValue;
      try {
        parsedValue = Integer.parseUnsignedInt(value, 10);
      } catch (NumberFormatException ex) {
        throw new IllegalArgumentException(
          String.format("The unexpected value. Expect positive number. Got: %s", line)
        );
      }
      return new AInstruction(parsedValue);
    }

    String destination = getDestination(line, strippedLine);
    return new CInstruction(destination, null, null);
  }

  private static @Nullable String getDestination(String line, String strippedLine) {
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
    return destination;
  }
}
