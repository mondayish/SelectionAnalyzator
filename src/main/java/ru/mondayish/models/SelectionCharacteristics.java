package ru.mondayish.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SelectionCharacteristics {
    private final List<Double> variationRange;
    private final Double max;
    private final Double min;
    private final Double range;
    private final Double mathExpectation;
    private final Double standardDeviation;
}
