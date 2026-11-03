
package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        ConsoleInput input = new ConsoleInput();
        WikipediaApiClient api = new WikipediaApiClient();

        String query = input.readSearchQuery();

        if (query.isBlank()) {
            System.out.println("Запрос не может быть пустым.");
            return;
        }

        try {
            String result = api.search(query);

            System.out.println("Ответ Википедии получен:");
            System.out.println(result);

        } catch (IOException e) {
            System.out.println("Ошибка сети: " + e.getMessage());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Запрос был прерван.");
        }
    }
}
