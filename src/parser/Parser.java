package parser;

public class Parser implements IParser {
  @Override
  public Instruction getInstruction(String line) {
    if (line == null || line.isBlank()) {
      return new AbsentInstruction();
    }
    return new AInstruction();
  }
}
