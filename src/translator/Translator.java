package translator;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

import parser.AInstruction;
import parser.AValue;
import parser.AbsentInstruction;
import parser.CInstruction;
import parser.Constant;
import parser.InstructionVisitor;
import parser.Symbol;
import parser.ValueVisitor;

public class Translator implements InstructionVisitor<String>, ValueVisitor<String> {
  private static final String A_INSTRUCTION_BIT = "0";
  private static final String C_INSTRUCTION_BITS = "111";
  private static final Map<String, String> destinationMap = Map.of(
          "M", "001",
          "D", "010",
          "MD", "011",
          "A", "100",
          "AM", "101",
          "AD", "110",
          "AMD", "111"
  );
  private static final Map<String, String> jumpMap = Map.of(
          "JGT", "001",
          "JEQ", "010",
          "JGE", "011",
          "JLT", "100",
          "JNE", "101",
          "JLE", "110",
          "JMP", "111"
  );
  private static final Map<String, String> computationMap = Map.ofEntries(
          Map.entry("0", "0101010"),
          Map.entry("1", "0111111"),
          Map.entry("-1", "0111010"),
          Map.entry("D", "0001100"),
          Map.entry("A", "0110000"),
          Map.entry("!D", "0001101"),
          Map.entry("!A", "0110001"),
          Map.entry("-D", "0001111"),
          Map.entry("-A", "0110011"),
          Map.entry("D+1", "0011111"),
          Map.entry("A+1", "0110111"),
          Map.entry("D-1", "0001110"),
          Map.entry("A-1", "0110010"),
          Map.entry("D+A", "0000010"),
          Map.entry("D-A", "0010011"),
          Map.entry("A-D", "0000111"),
          Map.entry("D&A", "0000000 "),
          Map.entry("D|A", "0010101"),
          Map.entry("M", "1110000"),
          Map.entry("!M", "1110001"),
          Map.entry("-M", "1110011"),
          Map.entry("M+1", "1110111"),
          Map.entry("M-1", "1110010"),
          Map.entry("D+M", "1000010"),
          Map.entry("D-M", "1010011"),
          Map.entry("M-D", "1000111"),
          Map.entry("D&M", "1000000"),
          Map.entry("D|M", "1010101")
  );
  private Map<String, Integer> symbolTable;

  public Translator(Map<String, Integer> symbolTable) {
    this.symbolTable = symbolTable;
  }

  @Override
  public String visitInstructionA(AInstruction instruction) {
    AValue instructionValue = instruction.value();
    return instructionValue.accept(this);
  }

  @Override
  public String visitInstructionC(CInstruction instruction) {
    String destination = instruction.destination();
    String jump = instruction.jump();
    String destinationBits = "000";
    String jumpBits = "000";
    if (destination != null) {
      destinationBits = destinationMap.get(destination);
    }
    if (jump != null) {
      jumpBits = jumpMap.get(jump);
    }
    String computationBits = computationMap.get(instruction.computation());
    return C_INSTRUCTION_BITS + computationBits + destinationBits + jumpBits;
  }

  @Override
  public String visitAbsentInstruction(AbsentInstruction instruction) {
    return null;
  }

  @Override
  public String visitConstant(Constant constant) {
    String binaryValue = String.format(
            "%15s", Integer.toBinaryString(constant.getValue())
    ).replace(" ", "0");
    return A_INSTRUCTION_BIT + binaryValue;
  }

  @Override
  public String visitSymbol(Symbol symbol) {
    return "";
  }
}
