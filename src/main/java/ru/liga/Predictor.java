package ru.liga;

import lombok.val;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Predictor {
    private final CursReader reader = new CursReader();
    List<Curs> getPredict(Command command) {

        val curses = reader.read(7, command.getCxd());
        List<Curs> result = new ArrayList<>();
        if (command.getRate().equals("tomorrow")) {
            result.add(new Curs(1, LocalDate.now().plusDays(1), avr(curses), command.getCxd()));
            return result;
        }
        for (int i = 0; i < 7; ++i) {
            result.add(new Curs(1, LocalDate.now().plusDays(i + 1), avr(curses), command.getCxd()));
            curses.set(i, result.get(i));
        }
        return result;
    }

    private double avr(List<Curs> curses) {
        return curses.stream()
                .map(x -> x.getCurs() / x.getNominal())
                .reduce(0., Double::sum) / curses.size();
    }
}
