package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.util.ArrayList;
import java.util.List;

public class WikipediaResponseParser {

    private final Gson gson = new Gson();

    public List<WikipediaArticle> parse(String json) {

        ApiResponse response = gson.fromJson(
                json, ApiResponse.class
        );

        if (response == null
                || response.query == null
                || response.query.search == null) {

            throw new JsonParseException(
                    "Некорректный ответ Википедии."
            );
        }

        List<WikipediaArticle> articles = new ArrayList<>();

        for (SearchEntry entry : response.query.search) {

            if (entry != null
                    && entry.pageid != null
                    && entry.pageid > 0
                    && entry.title != null
                    && !entry.title.isBlank()) {

                WikipediaArticle article = new WikipediaArticle(
                        entry.pageid,
                        entry.title
                );

                articles.add(article);
            }
        }

        return articles;
    }

    private static class ApiResponse {
        private QueryData query;
    }

    private static class QueryData {
        private List<SearchEntry> search;
    }

    private static class SearchEntry {
        private Long pageid;
        private String title;
    }
}
