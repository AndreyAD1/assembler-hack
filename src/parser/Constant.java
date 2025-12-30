package parser;

public class Constant implements AValue {
  private final int value;

  Constant(int value) {
    this.value = value;
  }

  public int getValue() {return this.value;}

  @Override
  public <R> R accept(ValueVisitor<R> visitor) {
    return visitor.visitConstant(this);
  }
}
