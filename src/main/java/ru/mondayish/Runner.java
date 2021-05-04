package ru.mondayish;

import ru.mondayish.io.ConsoleReader;
import ru.mondayish.io.ConsoleWriter;
import ru.mondayish.math.CharacteristicsCalculator;
import ru.mondayish.math.Dataset;
import ru.mondayish.models.Selection;
import ru.mondayish.models.SelectionCharacteristics;
import ru.mondayish.ui.BarChart;
import ru.mondayish.ui.DistributionFunctionChart;
import ru.mondayish.ui.PolygonChart;

import java.util.InputMismatchException;

public class Runner {

    private static final String INPUT_ERROR_MESSAGE = "Ошибка, вводите аккуратнее!";

    public static void main(String[] args) {
        try {
            Selection selection = new ConsoleReader().readSelection();
            SelectionCharacteristics characteristics = CharacteristicsCalculator.calculate(selection);
            new ConsoleWriter().writeCalculationResults(characteristics);

            PolygonChart polygonChart = new PolygonChart("Полигон частот", Dataset.createPolygonDataset(selection));
            polygonChart.pack();
            polygonChart.setVisible(true);

            BarChart barChart = new BarChart("Гистограмма частот", Dataset.createBarDataset(selection, characteristics));
            barChart.pack();
            barChart.setVisible(true);

            DistributionFunctionChart dfChart = new DistributionFunctionChart("Функция распределения", Dataset.createDistributionFunctionDataset(selection, characteristics));
            dfChart.pack();
            dfChart.setVisible(true);
        } catch (InputMismatchException e) {
            System.err.println(INPUT_ERROR_MESSAGE);
        }
    }
}
