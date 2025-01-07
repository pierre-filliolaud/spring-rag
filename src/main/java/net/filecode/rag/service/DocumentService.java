package net.filecode.rag.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.data.document.splitter.DocumentByLineSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import net.filecode.rag.domain.Document;
import net.filecode.rag.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final Logger logger = LoggerFactory.getLogger(DocumentService.class);
    private final DocumentRepository documentRepository;

    private final InMemoryEmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;

    public DocumentService(DocumentRepository documentRepository, InMemoryEmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
        this.documentRepository = documentRepository;
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
    }

    public Document buildAndSave(Document document) {
        logger.debug("Request to buildAndSave Document : {}", document);
        return documentRepository.save(document);
    }

    public Optional<Document> findById(Long id) {
        logger.debug("Request to find Document by id : {}", id);
        return documentRepository.findById(id);
    }

    public List<Document> findAll() {
        logger.debug("Request to find all Documents");
        return documentRepository.findAll();
    }

    @EventListener
    public void loadDocumentDataToEmbeddingStoreOnStartup(ApplicationStartedEvent event) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<Document> documentsPage = documentRepository.findAll(pageable);

        String documentsAsJson = convertListToJson(documentsPage.getContent());

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(new DocumentByLineSplitter(1000, 200))
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        ingestor.ingest(new dev.langchain4j.data.document.Document(documentsAsJson));
    }

    public String convertListToJson(List<Document> documents) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convert List<Vet> to JSON string
            StringBuilder jsonArray = new StringBuilder();
            for (Document document : documents) {
                String jsonElement = objectMapper.writeValueAsString(document);
                jsonArray.append(jsonElement).append("\n"); // For use of the
                // DocumentByLineSplitter
            }
            return jsonArray.toString();
        }
        catch (JsonProcessingException e) {
            logger.error("Problems encountered when generating JSON from the documents list", e);
            return null;
        }
    }
}
