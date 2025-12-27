import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {
    // iterate over the file lines
    // parse each line, translate an instruction, write the translated instruction to the iuput file
    try {
      try (Stream<String> lines = Files.lines(Path.of(args[0]))) {
        lines.forEach(System.out::println);
      }
    }
    catch (java.io.IOException ex) {
      System.out.printf("The file error: %s", ex);
    }
  }
}