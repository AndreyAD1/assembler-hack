package translator;

import java.util.HashMap;
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
  private Map<String, Integer> symbolTable = new HashMap<>();
  private int availableMemoryLocation = 16;

  public Translator(Map<String, Integer> pseudoCommands) {
    this.symbolTable.putAll(pseudoCommands);
    this.symbolTable.put("SP", 0);
    this.symbolTable.put("LCL", 1);
    this.symbolTable.put("ARG", 2);
    this.symbolTable.put("THIS", 3);
    this.symbolTable.put("THAT", 4);
    this.symbolTable.put("R0", 0);
    this.symbolTable.put("R1", 1);
    this.symbolTable.put("R2", 2);
    this.symbolTable.put("R3", 3);
    this.symbolTable.put("R4", 4);
    this.symbolTable.put("R5", 5);
    this.symbolTable.put("R6", 6);
    this.symbolTable.put("R7", 7);
    this.symbolTable.put("R8", 8);
    this.symbolTable.put("R9", 9);
    this.symbolTable.put("R10", 10);
    this.symbolTable.put("R11", 11);
    this.symbolTable.put("R12", 12);
    this.symbolTable.put("R13", 13);
    this.symbolTable.put("R14", 14);
    this.symbolTable.put("R15", 15);
    this.symbolTable.put("SCREEN", 16384);
    this.symbolTable.put("KBD", 24576);
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
    return this.getAInstruction(constant.getValue());
  }

  @Override
  public String visitSymbol(Symbol symbol) {
    String value = symbol.getValue();
    if (!this.symbolTable.containsKey(value)) {
      this.symbolTable.put(value, this.availableMemoryLocation);
      this.availableMemoryLocation++;
    }
    int address = this.symbolTable.get(value);
    return this.getAInstruction(address);
  }

  private String getAInstruction(int value) {
    String binaryValue = String.format(
            "%15s", Integer.toBinaryString(value)
    ).replace(" ", "0");
    return A_INSTRUCTION_BIT + binaryValue;
  }
}
