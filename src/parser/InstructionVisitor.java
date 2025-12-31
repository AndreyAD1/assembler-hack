package parser;

public interface InstructionVisitor<R> {
  R visitInstructionA(AInstruction instruction);
  R visitInstructionC(CInstruction instruction);
  R visitAbsentInstruction(AbsentInstruction instruction);
  R visitPseudoInstruction(PseudoInstruction instruction);
}
