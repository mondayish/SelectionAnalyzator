package ru.mondayish;

import ru.mondayish.io.ConsoleReader;
import ru.mondayish.io.ConsoleWriter;
import ru.mondayish.math.CharacteristicsCalculator;
import ru.mondayish.models.Selection;

import java.util.InputMismatchException;

public class Runner {

    private static final String INPUT_ERROR_MESSAGE = "Ошибка, вводите аккуратнее!";

    public static void main(String[] args) {
        try {
            Selection selection = new ConsoleReader().readSelection();
            new ConsoleWriter().writeCalculationResults(CharacteristicsCalculator.calculate(selection));
        } catch (InputMismatchException e) {
            System.err.println(INPUT_ERROR_MESSAGE);
        }
    }
}
