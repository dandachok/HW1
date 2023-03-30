package ru.liga.hw1;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Command {

    private static final String INCORRECT = "incorrect";

    private static final String EXIT = "exit";



    private final String cxd;

    private final String command;

    Command(String command) {
        cxd = "";
        this.command = command;
    }
    public static Command getIncorrect() {
        return new Command(INCORRECT);
    }

    public static Command getExit() {
        return new Command(EXIT);
    }

    public Boolean isCorrect() {
        return !command.equals(INCORRECT);
    }

    public boolean isExit() {
        return command.equals(EXIT);
    }
}
