package parser;

public interface ValueVisitor<R> {
  R visitConstant(Constant constant);
  R visitSymbol(Symbol symbol);
}
