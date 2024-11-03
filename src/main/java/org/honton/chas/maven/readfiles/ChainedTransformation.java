package org.honton.chas.maven.readfiles;

/**
 * @author Antoine Malliarakis
 */
final class ChainedTransformation extends Transformation {
  private final Transformation firstTransfo;
  private final Transformation secondTransfo;

  public ChainedTransformation(Transformation firstTransfo, Transformation secondTransfo) {
    super();
    this.firstTransfo = firstTransfo;
    this.secondTransfo = secondTransfo;
  }

  @Override
  public String transform(String input) {
    return secondTransfo.transform(firstTransfo.transform(input));
  }
}
