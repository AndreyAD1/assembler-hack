package translator;

import parser.AInstruction;
import parser.AbsentInstruction;
import parser.CInstruction;
import parser.InstructionVisitor;

public class Translator implements InstructionVisitor<String> {
  private static final String A_INSTRUCTION_BIT = "0";
  private static final int MAX_BIT_NUMBER = 15;

  @Override
  public String visitInstructionA(AInstruction instruction) {
    int instructionValue = instruction.value();
    int numberOfLeadingZeros = MAX_BIT_NUMBER - Integer.highestOneBit(instructionValue);
    if (instructionValue == 0) {
      return A_INSTRUCTION_BIT + "0".repeat(MAX_BIT_NUMBER);
    }
    String binaryValue =  Integer.toBinaryString(instructionValue);
    return A_INSTRUCTION_BIT + "0".repeat(numberOfLeadingZeros) + binaryValue;
  }

  @Override
  public String visitInstructionC(CInstruction instruction) {
    return String.format("C instruction: %s", instruction.destination());
  }

  @Override
  public String visitAbsentInstruction(AbsentInstruction instruction) {
    return null;
  }
}
