package org.example;

import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ConsoleInput input = new ConsoleInput();
        WikipediaApiClient api = new WikipediaApiClient();
        WikipediaResponseParser parser = new WikipediaResponseParser();
        ResultPrinter printer = new ResultPrinter();

        String query = input.readSearchQuery();

        if (query.isBlank()) {
            System.out.println("Запрос не может быть пустым.");
            return;
        }

        try {

            String json = api.search(query);

            List<WikipediaArticle> articles = parser.parse(json);

            if (articles.isEmpty()) {
                System.out.println(
                        "По вашему запросу ничего не найдено."
                );
                return;
            }

            printer.printResults(articles);

        } catch (IOException e) {

            System.out.println(
                    "Ошибка сети: " + e.getMessage()
            );

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            System.out.println("Запрос был прерван.");

        } catch (JsonParseException e) {

            System.out.println(
                    "Ошибка обработки JSON: " + e.getMessage()
            );
        }
    }
}
