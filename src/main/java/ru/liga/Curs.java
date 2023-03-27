package ru.liga;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class Curs {
    private final Integer nominal;

    private final LocalDate data;

    private final Double curs;

    private final String cdx;
}
