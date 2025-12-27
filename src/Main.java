import java.nio.file.Path;
import java.util.Scanner;

import parser.Instruction;
import parser.Parser;
import translator.Translator;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {
    // iterate over the file lines
    // parse each line, translate an instruction, write the translated instruction to the iuput file
    try {
      try (Scanner scanner = new Scanner(Path.of(args[0]))) {
        Parser parser = new Parser();
        Translator translator = new Translator();
        while (scanner.hasNextLine()) {
          Instruction instruction = parser.getInstruction(scanner.nextLine());
          String machineInstruction = instruction.accept(translator);
          System.out.println(machineInstruction);
        }
      }
    }
    catch (java.io.IOException ex) {
      System.out.printf("The file error: %s", ex);
    }
  }
}