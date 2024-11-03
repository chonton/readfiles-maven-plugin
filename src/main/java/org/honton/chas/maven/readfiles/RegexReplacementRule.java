package org.honton.chas.maven.readfiles;

import java.util.regex.Pattern;

/**
 * Instances of this class shall represent a regular expression based replacement rule.
 */
public final class RegexReplacementRule {
    
    private Pattern pattern;
    
    private String replacement;

    /**
     * Compiles this instance into a string transformation operation.
     * 
     * @return the resulting transformation
     * @throws IllegalStateException if any parameter is missing
     */
    Transformation compile() {
        if (null == this.pattern) {
            throw new IllegalStateException("pattern is missing");
        }
        final Pattern currentPattern = this.pattern;
        String replacement = this.replacement;
        if (replacement == null) {
            replacement = "";
        }
        return new ReplaceAllTransformation(currentPattern, replacement);
    }

    /**
     * Sets the value to use for all regular expression matches.
     * 
     * @param replacement if <code>null</code> no "replace all" logic is to be performed
     */
    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    /**
     * Sets the regular expression pattern used by this instance.
     * 
     * @param pattern the regular expression which this instance will use
     * @throws NullPointerException if the specified pattern parameter is <code>null</code>
     * @throws IllegalArgumentException if the specified pattern parameter is invalid
     */
    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

}
