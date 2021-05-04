package ru.mondayish.io;

import ru.mondayish.models.SelectionCharacteristics;

public class ConsoleWriter {

    private static final String VARIATION_RANGE_MESSAGE = "Вариационный ряд: ";
    private static final String MIN_MESSAGE = "Первая порядковая статистика: ";
    private static final String MAX_MESSAGE = "n-я порядковая статистика: ";
    private static final String RANGE_MESSAGE = "Размах выборки: ";
    private static final String MATH_EXPECTATION_MESSAGE = "Выборочное среднее: ";
    private static final String STANDARD_DEVIATION_MESSAGE = "Среднеквадратическое отклонение: ";

    public void writeCalculationResults(SelectionCharacteristics characteristics) {
        System.out.print(VARIATION_RANGE_MESSAGE + characteristics.getVariationRange() + '\n');
        System.out.print(MIN_MESSAGE + characteristics.getMin() + '\n');
        System.out.print(MAX_MESSAGE + characteristics.getMax() + '\n');
        System.out.print(RANGE_MESSAGE + characteristics.getRange() + '\n');
        System.out.print(MATH_EXPECTATION_MESSAGE + characteristics.getMathExpectation() + '\n');
        System.out.print(STANDARD_DEVIATION_MESSAGE + characteristics.getStandardDeviation() + '\n');
    }
}
