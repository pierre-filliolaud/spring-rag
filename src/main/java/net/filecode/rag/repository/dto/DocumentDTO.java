package net.filecode.rag.repository.dto;

public class DocumentDTO {
    private final String title;

    private final String content;

    public DocumentDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
