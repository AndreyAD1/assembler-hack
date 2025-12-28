package translator;

import parser.AInstruction;
import parser.AbsentInstruction;
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

  @Override
  public String visitAbsentInstruction(AbsentInstruction instruction) {
    return null;
  }
}
