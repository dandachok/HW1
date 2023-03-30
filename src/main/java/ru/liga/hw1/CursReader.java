package ru.liga.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CursReader {

    private final String SPLITER = ";";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy", new Locale("ru"));

    private final String SOURCE_FILE = "/dollar_evro_lira.csv";
    List<Curs> read(int count, String cdx) {
        List<Curs> curses = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream(SOURCE_FILE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> line = null;
            while (reader.ready()) {
                line = Arrays.asList(reader.readLine().split(SPLITER));
                if (line.get(3).equals(cdx)) {
                    break;
                }
            }
            curses.add(parseToCurs(line));
            for (int i = 0; i < count - 1; ++i) {
                line = Arrays.asList(reader.readLine().split(SPLITER));
                curses.add(parseToCurs(line));
            }
            return curses;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Curs parseToCurs(List<String> line) {
        if (line == null) {
            throw new IllegalArgumentException();
        }
        return new Curs(Integer.parseInt(line.get(0)),
                LocalDate.parse(line.get(1), formatter),
                new BigDecimal(line.get(2)),
                line.get(3));
    }
}
