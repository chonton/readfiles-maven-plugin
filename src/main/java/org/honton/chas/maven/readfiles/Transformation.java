package org.honton.chas.maven.readfiles;

abstract class Transformation {

    Transformation() {
        super();
    }
    
    public abstract String transform(String input);
}
