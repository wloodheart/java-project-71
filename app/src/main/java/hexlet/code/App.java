package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
//        version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"},
            description = "output format: [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish")
    private String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private static File file1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private static File file2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(file1, file2, format));
        return 0;
    }
}
