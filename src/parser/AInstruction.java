package parser;

public record AInstruction(AValue value) implements Instruction {

  @Override
  public <R> R accept(InstructionVisitor<R> visitor) {
    return visitor.visitInstructionA(this);
  }
}
