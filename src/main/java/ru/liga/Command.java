package ru.liga;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Command {
    private final String cxd;

    private final String rate;

    public Boolean isCorrect() {
        if (rate.equals("incorrect")) {
            return false;
        }
        return true;
    }

    public boolean isExit() {
        return rate.equals("exit");
    }
}
