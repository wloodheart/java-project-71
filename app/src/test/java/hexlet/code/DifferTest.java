package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    String expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

    @Test
    void testGenerate() throws Exception {

        Path filePath1 = Path.of("src/test/resources/file1.json");
        Path filePath2 = Path.of("src/test/resources/file2.json");

        assertThat(Differ.generate(filePath1.toFile(), filePath2.toFile())).isEqualTo(expected);
    }

    @Test
    void testGenerateYml() throws Exception {

        Path filePath1 = Path.of("src/test/resources/file1.yaml");
        Path filePath2 = Path.of("src/test/resources/file2.yaml");

        assertThat(Differ.generate(filePath1.toFile(), filePath2.toFile())).isEqualTo(expected);
    }
}
