package ru.liga;

import lombok.val;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Terminal {
    private static final Map<String, String> cdx = new HashMap<>() {{
        put("USD", "Доллар США");
        put("TRY", "Турецкая лира");
        put("EVR", "Евро");
    }};

    private static final String USAGE = """
            Команды:
            \trate {USD,TRY,EVR} {tomorrow, week} - прогноз валюты
            \t\tUSD - Доллар США
            \t\tTRY - Турецкая лира
            \t\tEVR - Евро
            \t\ttomorrow - на завтра
            \t\tweek - на неделю
            \texit - закрыть программу
            """;
    private static final String RATE = "rate";
    private static final String WEEK = "week";
    private static final String TOMORROW = "tomorrow";
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("E dd,MM,yyyy", new Locale("ru"));
    private final BufferedReader reader;

    Terminal(BufferedReader reader) {
        this.reader = reader;
    }

    public Command getCommand() {

        // Reading data using readLine
        try {
            String str = reader.readLine();
            if (str == null || "exit".equals(str)) {
                return Command.getExit();
            }

            val line = Arrays.asList(str.split(" "));
            if (line.size() != 3
                    || ! RATE.equals(line.get(0))
                    || !cdx.containsKey(line.get(1))
                    || (!WEEK.equals(line.get(2))
                    && !TOMORROW.equals(line.get(2)))) {
                System.out.println("Incorrect command");
                return Command.getIncorrect();
            }
            return new Command(cdx.get(line.get(1)), line.get(2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printUsage() {
        System.out.println(USAGE);
    }

    public void printPrediction(List<Curs> curses) {
        for (val curs : curses) {
            System.out.println(curs.getData().format(formatter) + " - " + curs.getCurs());
        }
    }
}
