package ru.mondayish.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Selection {
    private final List<Double> elements;
}
