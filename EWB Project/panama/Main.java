package panama;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.Error;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Main {
    public static void main(String... args) {
        args
    }

    /** Return a Scanner reading from the file named NAME. */
    private Scanner getInput(String name) {
        try {
            return new Scanner(new File(name));
        } catch (IOException excp) {
            throw new Error("could not open file");
        }
    }

    /** Check ARGS and open the necessary files (see comment on main). */
    Main(String[] args) {
        if (args.length < 1 || args.length > 3) {
            throw new Error("Only 1, 2, or 3 command-line arguments allowed");
        }
        _csv = getInput(args[0]);
        if (args.length > 1) {
            _input = getInput(args[1]);
        } else {
            _input = new Scanner(System.in);
        }

        if (args.length > 2) {
            _output = getOutput(args[2]);
        } else {
            _output = System.out;
        }
    }

    /** Configures a graph from the gives dataset. */
    private void process() {


}
private Scanner _csv;
    private Scanner _input;
    private PrintStream _output;
}
