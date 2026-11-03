package org.example;

public class Main {

    public static void main(String[] args) {

        ConsoleInput input = new ConsoleInput();

        WikipediaApiClient api =
                new WikipediaApiClient();

        WikipediaResponseParser parser =
                new WikipediaResponseParser();

        ResultPrinter printer = new ResultPrinter();

        ArticleBrowser browser = new ArticleBrowser();

        WikipediaApplication application =
                new WikipediaApplication(
                        input,
                        api,
                        parser,
                        printer,
                        browser
                );

        application.run();
    }
}
