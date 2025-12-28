package parser;

public class AbsentInstruction implements Instruction {
  @Override
  public <R> R accept(InstructionVisitor<R> visitor) {
    return visitor.visitAbsentInstruction(this);
  }
}
