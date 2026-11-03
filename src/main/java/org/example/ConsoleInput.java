package org.example;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleInput {

    private final Scanner scanner =
            new Scanner(System.in, StandardCharsets.UTF_8);

    public String readSearchQuery() {

        System.out.print(
                "Введите запрос (0 или q - выход): "
        );

        if (!scanner.hasNextLine()) {
            return null;
        }

        return scanner.nextLine().trim();
    }

    public int readSelection(int articleCount) {

        while (true) {

            System.out.print(
                    "Выберите номер статьи "
                            + "(0 - новый поиск, q - выход): "
            );

            if (!scanner.hasNextLine()) {
                return -1;
            }

            String value = scanner.nextLine().trim();

            if (value.equalsIgnoreCase("q")) {
                return -1;
            }

            try {

                int number = Integer.parseInt(value);

                if (number >= 0 && number <= articleCount) {
                    return number;
                }

            } catch (NumberFormatException e) {
                // Некорректное число: повторяем ввод.
            }

            System.out.println(
                    "Неверный выбор. Введите число от 0 до "
                            + articleCount + " или q."
            );
        }
    }
}
