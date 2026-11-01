package org.example;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class WikipediaApiClient {

    private static final String API_URL =
            "https://ru.wikipedia.org/w/api.php"
                    + "?action=query"
                    + "&list=search"
                    + "&format=json"
                    + "&utf8=1"
                    + "&srnamespace=0"
                    + "&srlimit=10"
                    + "&srsearch=";

    private static final String USER_AGENT =
            "WikipediaSearchLab2/1.0 "
                    + "(https://github.com/RAISAsadullin/lab2)";

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public String search(String query)
            throws IOException, InterruptedException {

        String encodedQuery = URLEncoder.encode(
                query, StandardCharsets.UTF_8
        );

        String url = API_URL + encodedQuery;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", USER_AGENT)
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString(
                        StandardCharsets.UTF_8
                )
        );
        if (response.statusCode() != 200) {
            throw new IOException(
                    "Ошибка сервера: " + response.statusCode()
            );
        }
        return response.body();
    }
}
