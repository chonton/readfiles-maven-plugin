package org.honton.chas.maven.readfiles;

import java.util.regex.Pattern;

/**
 * @author Antoine Malliarakis
 */
final class ReplaceAllTransformation extends Transformation {
  private final Pattern currentPattern;
  private final String replacement;

  ReplaceAllTransformation(Pattern currentPattern, String replacement) {
    super();
    this.currentPattern = currentPattern;
    this.replacement = replacement;
  }

  @Override
  public String transform(String input) {
    return currentPattern.matcher(input).replaceAll(replacement);
  }
}
