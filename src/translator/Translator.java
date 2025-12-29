package translator;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

import parser.AInstruction;
import parser.AbsentInstruction;
import parser.CInstruction;
import parser.InstructionVisitor;

public class Translator implements InstructionVisitor<String> {
  private static final String A_INSTRUCTION_BIT = "0";
  private static final String C_INSTRUCTION_BIT = "1";
  private static final int MAX_BIT_NUMBER = 15;
  private static final Map<String, String> destinationMap = Map.of(
          "M", "001",
          "D", "010",
          "MD", "011",
          "A", "100",
          "AM", "101",
          "AD", "110",
          "AMD", "111"
  );

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
    String destination = instruction.destination();
    String destinationBits = destinationMap.getOrDefault(destination, "000");
    return C_INSTRUCTION_BIT + destinationBits;
  }

  @Override
  public String visitAbsentInstruction(AbsentInstruction instruction) {
    return null;
  }
}
