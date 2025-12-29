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
    String[] equalSplit = strippedLine.split("=");
    if (equalSplit.length > 2) {
      throw new IllegalArgumentException(
              String.format("the invalid line: expect at most one '=': %s", line)
      );
    }

    String destination = getDestination(line, equalSplit);
    String[] semicolonSplit = strippedLine.split(";");
    if (destination != null) {
      semicolonSplit = equalSplit[1].split(";");
    }
    String computation = semicolonSplit[0];

    String jump = null;
    if (semicolonSplit.length == 2) {
      jump = semicolonSplit[1];
    }
    return new CInstruction(destination, computation, jump);
  }

  private static @Nullable String getDestination(String line, String[] equalSplit) {
    String destination = null;
    if (equalSplit.length == 2) {
      destination = equalSplit[0];
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
