import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

import parser.Instruction;
import parser.Parser;
import translator.Translator;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String @NotNull [] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("Expect a path to a file to translate");
    }
    // iterate over the file lines
    // parse each line, translate an instruction, write the translated instruction to the iuput file
    Path fileToTranslate = Path.of(args[0]);
    if (!Files.exists(fileToTranslate)) {
      throw new IllegalArgumentException(String.format(
        "the file '%s' doesn't seem to exist", fileToTranslate)
      );
    }
    Path outputFilepath = getOutputFilepath(fileToTranslate);
    try {
      try (Scanner scanner = new Scanner(fileToTranslate);
           BufferedWriter writer = Files.newBufferedWriter(outputFilepath)) {
        Parser parser = new Parser();
        Translator translator = new Translator(Map.of());
        while (scanner.hasNextLine()) {
          Instruction instruction = parser.getInstruction(scanner.nextLine());
          String machineInstruction = instruction.accept(translator);
          if (machineInstruction == null) {
            continue;
          }
          writer.write(machineInstruction);
          // don't add an empty last line
          if (scanner.hasNextLine()) {
            writer.newLine();
          }
        }
      }
    }
    catch (java.io.IOException ex) {
      System.out.printf("The file error: %s", ex);
    }
  }

  private static @NotNull Path getOutputFilepath(Path fileToTranslate) {
    String inputFileName = fileToTranslate.getFileName().toString();
    if (!inputFileName.endsWith(".asm")) {
      throw new IllegalArgumentException(
        String.format("the unexpected file extension - '%s'. Expect '*.asm'.", inputFileName)
      );
    }
    String outputFileName = inputFileName.replaceFirst(".asm$", ".hack");
    Path fileDirectory = fileToTranslate.getParent();
    Path outputFilepath = Path.of(outputFileName);
    if (fileDirectory != null) {
      outputFilepath = fileDirectory.resolve(outputFileName);
    }
    return outputFilepath;
  }
}