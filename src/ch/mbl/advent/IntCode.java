package ch.mbl.advent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class IntCode {

    final static  String CODE_PATH     = "/home/mathias/dev/perso/advent-of-code/resources/02-intcode";
    private final int    OPCODE_ADD    = 1;
    private final int    OPCODE_MUL    = 2;
    private final int    OPCODE_END    = 99;
    private final int    OPCODE_OFFSET = 4;
    final         String SEQUENCE;
    private       int[]  intcode;

    public IntCode() throws Exception {
        SEQUENCE = readFile((CODE_PATH));
        reset();
    }

    public void reset() throws Exception {
        if (SEQUENCE == null || SEQUENCE.length() == 0)
            throw new Exception("Invalid sequence");

        intcode = buildInitialArray(SEQUENCE);
    }

    public void set(int position, int value) {
        if (position >= 0 && position < intcode.length)
            intcode[position] = value;
    }

    public int get(int position) {
        if (position >= 0 && position < intcode.length)
            return intcode[position];

        return 0;
    }

    public int result() {
        return get(0);
    }

    public void runProgram() {
        int instructionPointer = 0;
        boolean finished = false;

        while (instructionPointer < intcode.length && !finished) {
            finished = doCalculation(instructionPointer);
            instructionPointer += OPCODE_OFFSET;
        }
    }

    private boolean doCalculation(int instructionPointer) {
        final int OPCODE = intcode[instructionPointer];

        if (OPCODE == OPCODE_END)
            return true;

        final int NOUN = intcode[intcode[instructionPointer + 1]];
        final int VERB = intcode[intcode[instructionPointer + 2]];
        final int resultPosition = intcode[instructionPointer + 3];

        if (OPCODE == OPCODE_ADD) {
            intcode[resultPosition] = NOUN + VERB;
        }

        if (OPCODE == OPCODE_MUL) {
            intcode[resultPosition] = NOUN * VERB;
        }

        return false;
    }

    private int[] buildInitialArray(String sequence) {
        String arr[] = sequence.split(",");
        return Stream.of(arr).mapToInt(Integer::parseInt).toArray();
    }

    private String readFile(String path) {
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            line = reader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return line;
    }
}
