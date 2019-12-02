package ch.mbl.advent;

public class Main {

    public static void main(String[] args) {
        day2_02();
    }

    public static void day1() {
        new FuelMassCalculator().puzzle01();
        new FuelMassCalculator().puzzle02();
    }

    public static void day2_01() {
        try {
            IntCode intCode = new IntCode();
            intCode.set(1, 12);
            intCode.set(2, 2);
            intCode.runProgram();
            System.out.println(intCode.get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void day2_02() {
        IntCode intCode = null;

        try {
            intCode = new IntCode();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        final int MATCH = 19690720;

        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                intCode.set(1, noun);
                intCode.set(2, verb);
                intCode.runProgram();

                System.out.println("trying noun = " + noun + " and verb = " + verb);

                if (intCode.result() == MATCH) {
                    System.out.println("found matching noun = " + noun + " verb = " + verb);
                    return;
                }

                try {
                    intCode.reset();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }


    }


}
