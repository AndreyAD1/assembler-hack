package parser;

public class CInstruction implements Instruction {
  @Override
  public <R> R accept(InstructionVisitor<R> visitor) {
    return visitor.visitInstructionC(this);
  }
}
