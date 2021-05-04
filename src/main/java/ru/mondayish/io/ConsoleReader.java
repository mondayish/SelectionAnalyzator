package ru.mondayish.io;

import ru.mondayish.models.Selection;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleReader {

    private static final String INPUT_SIZE_MESSAGE = "Введите размер выборки(от 10 до 100): ";
    private static final String INPUT_ELEMENTS_MESSAGE = "Введите элементы: ";


    public Selection readSelection() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        int selectionSize = 0;
        while (selectionSize < 10 || selectionSize > 100) {
            System.out.print(INPUT_SIZE_MESSAGE);
            selectionSize = scanner.nextInt();
        }
        System.out.println(INPUT_ELEMENTS_MESSAGE);
        List<Double> elements = IntStream.range(0, selectionSize)
                .mapToObj(i -> scanner.nextDouble()).collect(Collectors.toList());
        return new Selection(elements);
    }
}
