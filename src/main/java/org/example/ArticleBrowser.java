package org.example;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class ArticleBrowser {

    public String getArticleUrl(WikipediaArticle article) {

        return "https://ru.wikipedia.org/w/index.php?curid="
                + article.getPageId();
    }

    public void open(WikipediaArticle article) throws IOException {

        if (!Desktop.isDesktopSupported()) {
            throw new IOException(
                    "Работа с браузером не поддерживается."
            );
        }

        Desktop desktop = Desktop.getDesktop();

        if (!desktop.isSupported(Desktop.Action.BROWSE)) {
            throw new IOException(
                    "Открытие ссылок не поддерживается."
            );
        }

        String url = getArticleUrl(article);

        desktop.browse(URI.create(url));
    }
}
