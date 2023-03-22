package ru.liga;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Command {
    private final String cxd;

    private final String rate;

    public Boolean isCorrect() {
        return !rate.equals("incorrect");
    }

    public boolean isExit() {
        return rate.equals("exit");
    }
}
