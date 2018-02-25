# readfiles-maven-plugin

Read files into maven properties.

Mojo details at [plugin info](https://chonton.github.io/readfiles-maven-plugin/0.0.1/plugin-info.html)

Just one goal: [readfiles](https://chonton.github.io/readfiles-maven-plugin/0.0.1/readfiles-mojo.html) 
sets maven properties to the contents of files.  Each file is read fully and the contents are
set to maven properties of the same name as the files.
 
| Parameter | Default | Description |
|-----------|---------|-------------|
|files      |         | The list of files to read |
|encoding   |${project.build.sourceEncoding}|The character encoding of the file (UTF-8 if not specified)|
|prefix     |         | Prefix each property name with this value |
|skip       |${readfiles.skip}|Skip executing the plugin|

Typical use:

```xml
  <build>
    <plugins>

      <plugin>
        <groupId>org.honton.chas</groupId>
        <artifactId>readfiles-maven-plugin</artifactId>
        <version>0.0.1</version>
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
