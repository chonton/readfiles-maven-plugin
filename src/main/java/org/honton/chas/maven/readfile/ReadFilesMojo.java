package org.honton.chas.maven.readfile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Properties;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Read files into maven properties.  The contents of each file is read in full.  The content is
 * then assigned to a maven property of the same name.
 */
@Mojo(name = "readfiles", defaultPhase = LifecyclePhase.INITIALIZE)
public class ReadFilesMojo extends AbstractMojo {

  @Parameter(defaultValue = "${project.properties}", readonly = true, required = true)
  private Properties projectProperties;

  /**
   * Prefix for the target properties
   */
  @Parameter(defaultValue = "readfiles.")
  private String prefix;

  /**
   * Charset encoding of the source files.  Defaults to UTF-8
   */
  @Parameter(defaultValue = "${project.build.sourceEncoding}")
  private String encoding;

  private Charset charset;

  /**
   * List of files to read
   */
  @Parameter
  private File[] files;

  public void execute() throws MojoExecutionException {
    charset = createCharset();
    try {
      for (File file : files) {

        String propertyName = prefix + file.getName();
        String propertyValue = readFileFully(file);
        getLog().debug(propertyName + " = " + propertyValue);

        projectProperties.setProperty(propertyName, propertyValue);
      }

    } catch (IOException io) {
      throw new MojoExecutionException(io.getMessage(), io);
    }
  }

  private Charset createCharset() {
    return encoding != null ? Charset.forName(encoding) : StandardCharsets.UTF_8;
  }

  private String readFileFully(File file) throws IOException {
    byte[] encoded = Files.readAllBytes(file.toPath());
    return new String(encoded, charset);
  }
}
