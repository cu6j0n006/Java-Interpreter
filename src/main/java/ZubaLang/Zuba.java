package ZubaLang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Zuba {

    static boolean hadError = false;

    public static void main(String[] args) throws IOException {
        if (args.length > 1){
            System.out.println("Usage: java Zuba <input_file> <output_file>");
            System.exit(1); // or 64
        }
        else if (args.length == 1){
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }
    private static void runFile(String fileName) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        run(new String(bytes, Charset.defaultCharset()));

        if (hadError){ // error in the exit code
            System.exit(65);
        }
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);  //read bytes and decode to characters
        BufferedReader reader = new BufferedReader(input);

        for (;;){
            System.out.println(">> ");
            String line = reader.readLine();
            if (line == null){break;}
            run(line);
            hadError = false;
        }
    }
    private static void run(String source) throws IOException {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
    static void error(int line, String message) {
        report(line, "", message);
    }
    private static void report(int line, String error, String message) {
        System.err.println(
                "[line " + line + "] Error" + error + ": " + message);
        hadError = true;
    }
}
