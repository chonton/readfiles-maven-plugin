# readfiles-maven-plugin

Read files into maven properties.

Mojo details at [plugin info](https://chonton.github.io/readfiles-maven-plugin/0.0.1/plugin-info.html)

Just one goal: [readfiles](https://chonton.github.io/readfiles-maven-plugin/0.0.1/readfiles-mojo.html) 
sets maven properties to the contents of files.  Each file is read fully and the contents are
set to maven properties of the same name as the files.

Optionally, the contents of the file can be trimmed of whitespace, or transformed using a series of 
regular expression patterns and replacements.
 
| Parameter         | Default | Property                        | Description                                    |
|-------------------|---------|---------------------------------|------------------------------------------------|
| files             |         |                                 | The list of files to read                      |
| encoding          | UTF-8   | ${project.build.sourceEncoding} | The character encoding of the file             |
| prefix            |         | ${readfiles.prefix}             | Prefix each property name with this value      |
| regexReplacements |         |                                 | List of pattern replacements                   |
| skip              | false   | ${readfiles.skip}               | Skip executing the plugin                      |
| trim              | false   | ${readfiles.skip}               | Trim whitespace from beginning and end of file |

## regexReplacement Elements
| Parameter         | Description                                                                                                                                    |
|-------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| pattern           | A [java regular expression](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/regex/Pattern.html)                         |
| replacement       | The [replaceAll value](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/regex/Matcher.html#replaceAll(java.lang.String)) |

## Typical use:

```xml
  <build>
    <plugins>

      <plugin>
        <groupId>org.honton.chas</groupId>
        <artifactId>readfiles-maven-plugin</artifactId>
        <version>0.1.0</version>
        <executions>
          <execution>
            <id>configurations</id>
            <goals>
              <goal>readfiles</goal>
            </goals>
            <configuration>
              <files>
                <!-- set application.json property -->
                <file>${basedir}/src/main/resources/application.json</file>
                <!-- set environment.conf property -->
                <file>${basedir}/src/main/resources/environment.conf</file>
              </files>
            </configuration>
          </execution>
          <execution>
            <id>index.html</id>
            <goals>
              <goal>readfiles</goal>
            </goals>
            <configuration>
              <encoding>iso-8859-1</encoding>
              <prefix>page.</prefix>
              <files>
                <!-- set page.index.html property -->
                <file>${basedir}/src/main/resources/index.html</file>
              </files>
            </configuration>
          </execution>
        </executions>
      </plugin>

    </plugins>
  </build>
```

## Pattern Replacement:

```xml
  <plugin>
    <groupId>org.honton.chas</groupId>
    <artifactId>readfiles-maven-plugin</artifactId>
    <configuration>
      <files>
        <file>${basedir}/.nvmrc</file>
      </files>
      <regexReplacements>
        <regexReplacement>
          <pattern>^v?(\S+)\s*$</pattern>
          <replacement>v$1</replacement>
        </regexReplacement>
      </regexReplacements>
    </configuration>
  </plugin>
```
