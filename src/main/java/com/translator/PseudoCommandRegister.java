package com.translator;

import java.util.HashMap;
import java.util.Map;

import com.parser.AInstruction;
import com.parser.AbsentInstruction;
import com.parser.CInstruction;
import com.parser.InstructionVisitor;
import com.parser.PseudoInstruction;

public class PseudoCommandRegister implements InstructionVisitor<Void> {
  private HashMap<String, Integer> commandsByLine;
  private int lineNumber;

  public PseudoCommandRegister() {
    this.commandsByLine = new HashMap<>();
    this.lineNumber = 0;
  }

  @Override
  public Void visitInstructionA(AInstruction instruction) {
    this.lineNumber++;
    return null;
  }

  @Override
  public Void visitInstructionC(CInstruction instruction) {
    this.lineNumber++;
    return null;
  }

  @Override
  public Void visitAbsentInstruction(AbsentInstruction instruction) {
    return null;
  }

  @Override
  public Void visitPseudoInstruction(PseudoInstruction instruction) {
    this.commandsByLine.putIfAbsent(instruction.getLabel(), this.lineNumber);
    return null;
  }

  public Map<String, Integer> getSymbolTable() {
    return Map.copyOf(this.commandsByLine);
  }
}
