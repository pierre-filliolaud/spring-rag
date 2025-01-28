package net.filecode.rag.repository;

import net.filecode.rag.domain.Document;
import net.filecode.rag.repository.dto.DocumentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query(
            "select distinct new net.filecode.rag.repository.dto.DocumentDTO(document.title, document.content) from Document document"
    )
    List<DocumentDTO> findAllDocumentDTO();
}
