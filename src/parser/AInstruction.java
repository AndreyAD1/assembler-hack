package parser;

public class AInstruction implements Instruction {
  @Override
  public <R> R accept(InstructionVisitor<R> visitor) {
    return visitor.visitInstructionA(this);
  }
}
