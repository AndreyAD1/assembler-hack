package parser;

public class Symbol implements AValue {
  private final String value;

  Symbol(String value) {
    this.value = value;
  }

  public String getValue() {return this.value;}

  @Override
  public <R> R accept(ValueVisitor<R> visitor) {
    return visitor.visitSymbol(this);
  }
}
