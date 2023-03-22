package ru.liga;

import lombok.val;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Terminal {
    private final Map<String, String> cdx  = new HashMap<>() {{
        put("USD", "Доллар США");
        put("TRY", "Турецкая лира");
        put("EVR", "Евро");
    }};

    private final BufferedReader reader;

    Terminal(BufferedReader reader) {
        this.reader = reader;
    }
    public Command getCommand() {

        // Reading data using readLine
        try {
            String str = reader.readLine();
            if (str == null || str.equals("exit")) {
                return new Command("", "exit");
            }
            val line = Arrays.asList(str.split(" "));
            if (line.size() != 3
                    || !line.get(0).equals("rate")
                    || !cdx.containsKey(line.get(1))
                    || (!line.get(2).equals("week")
                    && !line.get(2).equals("tomorrow"))) {
                System.out.println("Incorrect command");
                return new Command("", "incorrect");
            }
            return new Command(cdx.get(line.get(1)), line.get(2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printUsage() {
        System.out.println("""
                Команды:
                \trate {USD,TRY,EVR} {tomorrow, week} - прогноз валюты
                \t\tUSD - Доллар США
                \t\tTRY - Турецкая лира
                \t\tEVR - Евро
                \t\ttomorrow - на завтра
                \t\tweek - на неделю
                \texit - закрыть программу
                """);
    }

    public void printPrediction(List<Curs> curses) {
        for (val curs : curses) {
            System.out.println(curs.getData().format(DateTimeFormatter.ofPattern("E dd,MM,yyyy", new Locale("ru"))) + " - " + curs.getCurs());
        }
    }
}
