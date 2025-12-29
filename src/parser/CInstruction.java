package parser;

public class CInstruction implements Instruction {
  private final String destination;

  public CInstruction(String destination) {
    this.destination = destination;
  }

  public String getDestination() {return this.destination;};

  @Override
  public <R> R accept(InstructionVisitor<R> visitor) {
    return visitor.visitInstructionC(this);
  }
}
