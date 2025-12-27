package parser;

public class Parser implements IParser {
  @Override
  public Instruction getInstruction(String line) {
    return new AInstruction();
  }
}
