package org.honton.chas.maven.readfiles;

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

  /**
   * Prefix for the target properties
   */
  @Parameter(defaultValue = "")
  private String prefix;

  /**
   * Whether to trim leading and trailing whitespace characters.
   * 
   * @since 0.0.2
   */
  @Parameter(defaultValue = "false", property = "readfiles.trim")
  private boolean trim;

  /**
   * Charset encoding of the source files.  Defaults to UTF-8
   */
  @Parameter(defaultValue = "${project.build.sourceEncoding}")
  private String encoding;

  /**
   * Skip executing this plugin
   */
  @Parameter(defaultValue = "false", property = "readfiles.skip")
  private boolean skip;

  @Parameter(defaultValue = "${project.properties}", readonly = true, required = true)
  private Properties projectProperties;

  private Charset charset;

  /**
   * List of files to read
   */
  @Parameter
  private File[] files;

  public void execute() throws MojoExecutionException {
    if (skip) {
      getLog().info("skipping");
      return;
    }

    charset = createCharset();
    try {
      for (File file : files) {

        String propertyName = createPropertyName(file);
        String propertyValue = readFileFully(file);
        if (getLog().isDebugEnabled()) {
          getLog().debug(propertyName + " = " + propertyValue);
        } else {
          getLog().info("setting " + propertyName);
        }

        projectProperties.setProperty(propertyName, propertyValue);
      }

    } catch (IOException io) {
      throw new MojoExecutionException(io.getMessage(), io);
    }
  }

  private String createPropertyName(File file) {
    return prefix != null ? prefix + file.getName() : file.getName();
  }

  private Charset createCharset() {
    return encoding != null ? Charset.forName(encoding) : StandardCharsets.UTF_8;
  }

  private String readFileFully(File file) throws IOException {
    byte[] encoded = Files.readAllBytes(file.toPath());
    final String result = new String(encoded, charset);
    if (this.trim) {
      return result.trim();
    }
    return result;
  }
}
