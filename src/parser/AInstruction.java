package parser;

public class AInstruction implements Instruction {
  int value;

  public AInstruction(int value) {
    this.value = value;
  }

  public int getValue() {return this.value;};

  @Override
  public <R> R accept(InstructionVisitor<R> visitor) {
    return visitor.visitInstructionA(this);
  }
}
