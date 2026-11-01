package org.example;

import java.util.Scanner;

    public class ConsoleInput {

        private final Scanner scanner = new Scanner(System.in);

        public String readSearchQuery() {
            System.out.print("Введите запрос: ");
            return scanner.nextLine().trim();
        }

    }
