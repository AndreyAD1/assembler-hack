package parser;

public record AInstruction(int value) implements Instruction {
  ;

  @Override
  public <R> R accept(InstructionVisitor<R> visitor) {
    return visitor.visitInstructionA(this);
  }
}
