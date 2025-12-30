package parser;

public interface AValue {
  <R> R accept(ValueVisitor<R> visitor);
}
