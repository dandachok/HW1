package ru.liga;

import lombok.val;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        Terminal terminal = new Terminal(reader);
        Predicter predicter = new Predicter();

        while(true) {

            val command = terminal.getCommand();
            if (command.isExit()) {
                break;
            }
            if (command.isCorrect()) {
                terminal.printPrediction(predicter.getPredict(command));
            }
        }

    }
}
