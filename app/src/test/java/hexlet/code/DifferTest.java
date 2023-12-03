package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    @Test
    void testGenerate() throws Exception {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        String data1 = """
                {
                  "host": "hexlet.io",
                  "timeout": 50,
                  "proxy": "123.234.53.22",
                  "follow": false
                }""";

        String data2 = """
                {
                  "timeout": 20,
                  "verbose": true,
                  "host": "hexlet.io"
                }""";

        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");

        Files.writeString(filePath1, data1);
        Files.writeString(filePath2, data2);

        assertThat(Differ.generate(filePath1.toFile(), filePath2.toFile())).isEqualTo(expected);

        Files.delete(filePath1);
        Files.delete(filePath2);
    }
}
