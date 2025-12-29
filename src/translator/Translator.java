package translator;

import parser.AInstruction;
import parser.AbsentInstruction;
import parser.CInstruction;
import parser.InstructionVisitor;

public class Translator implements InstructionVisitor<String> {
  @Override
  public String visitInstructionA(AInstruction instruction) {
    return String.format("A instruction: %s", instruction.getValue());
  }

  @Override
  public String visitInstructionC(CInstruction instruction) {
    return String.format("C instruction: %s", instruction.getDestination());
  }

  @Override
  public String visitAbsentInstruction(AbsentInstruction instruction) {
    return null;
  }
}
