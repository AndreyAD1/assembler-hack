package translator;

import parser.AInstruction;
import parser.CInstruction;
import parser.InstructionVisitor;

public class Translator implements InstructionVisitor<String> {
  @Override
  public String visitInstructionA(AInstruction instruction) {
    return "testA";
  }

  @Override
  public String visitInstructionC(CInstruction instruction) {
    return "testC";
  }
}
