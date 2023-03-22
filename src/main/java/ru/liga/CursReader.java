package ru.liga;

import lombok.val;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CursReader {
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Curs read() {

        try (InputStream inputStream = getClass().getResourceAsStream("/dollar_evro_lira.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader.readLine();
            val line = Arrays.asList(reader.readLine().split(";"));
            return new Curs(Integer.parseInt(line.get(0)),
                    LocalDate.parse(line.get(1), DateTimeFormatter.ofPattern("MM/dd/yyyy", new Locale("ru"))),
                    Double.parseDouble(line.get(2)),
                    line.get(3));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    List<Curs> read(int count, String cdx) {
        List<Curs> curses = new ArrayList<>();
        try (InputStream inputStream = getClass().getResourceAsStream("/dollar_evro_lira.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> line = null;
            while(reader.ready()) {
                line = Arrays.asList(reader.readLine().split(";"));
                if (line.get(3).equals(cdx)) {
                    break;
                }
            }
            curses.add(parseToCurs(line));
            for (int i = 0; i < count - 1; ++i) {
                line = Arrays.asList(reader.readLine().split(";"));
                curses.add(parseToCurs(line));
            }
            return curses;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Curs parseToCurs (List<String> line) {
        if (line == null) {
            throw new IllegalArgumentException();
        }
        return new Curs(Integer.parseInt(line.get(0)),
                LocalDate.parse(line.get(1), DateTimeFormatter.ofPattern("M/d/yyyy", new Locale("ru"))),
                Double.parseDouble(line.get(2)),
                line.get(3));
    }
}
