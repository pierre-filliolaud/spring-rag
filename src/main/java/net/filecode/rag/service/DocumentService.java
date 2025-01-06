package net.filecode.rag.service;

import net.filecode.rag.domain.Document;
import net.filecode.rag.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final Logger logger = LoggerFactory.getLogger(DocumentService.class);
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
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
}
