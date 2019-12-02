package ch.mbl.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FuelMassCalculator {

    final String INPUT_PATH = "/home/mathias/dev/perso/advent-of-code/resources/01-input";

    public FuelMassCalculator() {

    }

    public void puzzle01() {
        try {
            final List<Long> masses = readMasses(INPUT_PATH);
            Long total = 0L;

            for (Long m : masses) {
                total += getFuelMass(m);
            }

            System.out.println(total);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void puzzle02() {
        try {
            final List<Long> masses = readMasses(INPUT_PATH);
            Long total = 0L;

            for (Long m : masses) {
                total += getFuelMass(m, 0);
            }

            System.out.println(total);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Long> readMasses(String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<Long> lines = new ArrayList<>();
        String line = reader.readLine();

        while (line != null) {
            lines.add(Long.parseLong(line));
            line = reader.readLine();
        }

        return lines;
    }

    public Long getFuelMass(Long moduleWeight, long sum) {
        if (moduleWeight <= 0)
            return sum;

        Long mass = getFuelMass(moduleWeight);

        if (mass > 0)
            sum += mass;

        return getFuelMass(mass, sum);
    }

    public Long getFuelMass(Long moduleWeight) {
        Long mass = (long) Math.floor(moduleWeight / 3);
        return mass - 2;

    }
}