package parser;

public interface Instruction {
  <R> R accept(InstructionVisitor<R> visitor);
}
