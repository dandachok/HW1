package ru.liga;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class Curs {
    private Integer nominal;

    private LocalDate data;

    private Double curs;

    private String cdx;
}
