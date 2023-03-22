package ru.liga;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class Curs {
    private Integer nominal;

    private LocalDate data;

    private Double curs;

    private String cdx;
}
