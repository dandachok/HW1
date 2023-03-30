package ru.liga.hw1;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class Curs {
    private final Integer nominal;

    private final LocalDate data;

    private final BigDecimal curs;

    private final String cdx;
}
