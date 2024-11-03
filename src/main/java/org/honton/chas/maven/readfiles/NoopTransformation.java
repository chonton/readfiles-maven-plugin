package org.honton.chas.maven.readfiles;

/**
 * @author Antoine Malliarakis
 */
final class NoopTransformation extends Transformation {
    NoopTransformation() {
        super();
    }
    @Override
    public String transform(String input) {
        return input;
    }
}
