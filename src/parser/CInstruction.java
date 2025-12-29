package parser;

public record CInstruction(String destination, String computation,
                           String jump) implements Instruction {

  @Override
  public <R> R accept(InstructionVisitor<R> visitor) {
    return visitor.visitInstructionC(this);
  }
}
