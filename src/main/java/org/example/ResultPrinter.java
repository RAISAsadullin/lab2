package org.example;

import java.util.List;

public class ResultPrinter {

    public void printResults(List<WikipediaArticle> articles) {

        System.out.println("Результаты поиска:");

        for (int i = 0; i < articles.size(); i++) {

            WikipediaArticle article = articles.get(i);

            System.out.println(
                    (i + 1) + ". " + article.getTitle()
            );
        }
    }
}
