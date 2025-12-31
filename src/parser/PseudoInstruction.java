package parser;

public class PseudoInstruction implements Instruction {
  private final String label;

  PseudoInstruction(String label) {
    this.label = label;
  }

  public String getLabel() {return this.label;}

  @Override
  public <R> R accept(InstructionVisitor<R> visitor) {
    return visitor.visitPseudoInstruction(this);
  }
}
