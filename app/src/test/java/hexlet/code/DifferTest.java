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

        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");

        assertThat(Differ.generate(filePath1.toFile(), filePath2.toFile())).isEqualTo(expected);
    }
}
