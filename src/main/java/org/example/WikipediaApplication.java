package org.example;

import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.List;

public class WikipediaApplication {

    private final ConsoleInput input;
    private final WikipediaApiClient api;
    private final WikipediaResponseParser parser;
    private final ResultPrinter printer;
    private final ArticleBrowser browser;

    public WikipediaApplication(
            ConsoleInput input,
            WikipediaApiClient api,
            WikipediaResponseParser parser,
            ResultPrinter printer,
            ArticleBrowser browser
    ) {
        this.input = input;
        this.api = api;
        this.parser = parser;
        this.printer = printer;
        this.browser = browser;
    }

    public void run() {

        System.out.println("Поиск статей в Википедии");

        while (true) {

            String query = input.readSearchQuery();

            if (query == null
                    || query.equals("0")
                    || query.equalsIgnoreCase("q")) {

                System.out.println("Программа завершена.");
                return;
            }

            if (query.isBlank()) {
                System.out.println(
                        "Запрос не может быть пустым."
                );
                continue;
            }

            try {

                String json = api.search(query);

                List<WikipediaArticle> articles =
                        parser.parse(json);

                if (articles.isEmpty()) {
                    System.out.println(
                            "По вашему запросу ничего не найдено."
                    );
                    continue;
                }

                printer.printResults(articles);

                int choice = input.readSelection(
                        articles.size()
                );

                if (choice == -1) {
                    System.out.println("Программа завершена.");
                    return;
                }

                if (choice == 0) {
                    continue;
                }

                WikipediaArticle selectedArticle =
                        articles.get(choice - 1);

                String url = browser.getArticleUrl(
                        selectedArticle
                );

                try {

                    browser.open(selectedArticle);

                    System.out.println(
                            "Статья открыта в браузере: " + url
                    );

                } catch (IOException
                         | SecurityException
                         | UnsupportedOperationException e) {

                    System.out.println(
                            "Не удалось открыть браузер: "
                                    + e.getMessage()
                    );

                    System.out.println(
                            "Откройте ссылку вручную: " + url
                    );
                }

            } catch (IOException e) {

                System.out.println(
                        "Ошибка сети: " + e.getMessage()
                );

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                System.out.println("Запрос был прерван.");
                return;

            } catch (JsonParseException e) {

                System.out.println(
                        "Ошибка обработки JSON: " + e.getMessage()
                );
            }

            System.out.println();
        }
    }
}
