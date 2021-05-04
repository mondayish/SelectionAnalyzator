package ru.mondayish.math;

import ru.mondayish.models.Selection;
import ru.mondayish.models.SelectionCharacteristics;

import java.util.List;
import java.util.stream.Collectors;

public class CharacteristicsCalculator {

    public static SelectionCharacteristics calculate(Selection selection) {
        List<Double> variationRange = selection.getElements().stream().sorted().collect(Collectors.toList());
        Double min = variationRange.get(0);
        Double max = variationRange.get(variationRange.size() - 1);
        Double range = max - min;
        Double mathExpectation = variationRange.stream().reduce(Double::sum).orElseThrow() / variationRange.size();
        Double standardDeviation = Math.sqrt(variationRange.stream().map(x -> Math.pow(x - mathExpectation, 2))
                .reduce(Double::sum).orElseThrow() / variationRange.size());

        return SelectionCharacteristics.builder().variationRange(variationRange).max(max).min(min)
                .range(range).mathExpectation(mathExpectation).standardDeviation(standardDeviation).build();
    }
}
