package org.example;

public class WikipediaArticle {

    private final long pageId;
    private final String title;

    public WikipediaArticle(long pageId, String title) {
        this.pageId = pageId;
        this.title = title;
    }

    public long getPageId() {
        return pageId;
    }

    public String getTitle() {
        return title;
    }
}
