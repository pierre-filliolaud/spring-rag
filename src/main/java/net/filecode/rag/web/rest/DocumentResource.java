package net.filecode.rag.web.rest;

import net.filecode.rag.domain.Document;
import net.filecode.rag.service.DocumentAiService;
import net.filecode.rag.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class DocumentResource {

    private final Logger log = LoggerFactory.getLogger(DocumentResource.class);

    private final ExecutorService nonBlockingService = Executors.newCachedThreadPool();

    private final DocumentService documentService;
    private final DocumentAiService documentAiService;

    public DocumentResource(DocumentService documentService, DocumentAiService documentAiService) {
        this.documentService = documentService;
        this.documentAiService = documentAiService;
    }

    @PostMapping("/documents")
    public ResponseEntity<Document> createDocument(@RequestBody Document document) throws URISyntaxException {
        log.debug("REST request to save Document : {}", document);
        Document result = documentService.buildAndSave(document);

        return ResponseEntity
                .created(new URI("/documents/" + result.getId()))
                .body(result);
    }

    @GetMapping("/documents")
    public List<Document> getAllDocuments() {
        log.debug("REST request to get all Documents");
        return documentService.findAll();
    }

    @GetMapping("/documents/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable Long id) {
        log.debug("REST request to get Document : {}", id);
        Optional<Document> document = documentService.findById(id);
        return ResponseEntity.of(document);
    }

    @DeleteMapping("/documents/{id}")
    public void deleteDocument(@PathVariable Long id) {
        log.debug("REST request to delete Document : {}", id);
        documentService.deleteById(id);
    }

    @PostMapping("/documents/chat/{user}")
    public String chat(@PathVariable UUID user, @RequestBody String query) throws InterruptedException {
        SseEmitter emitter = new SseEmitter();
        AtomicBoolean completed = new AtomicBoolean(false);
        AtomicReference<String> myMessage = new AtomicReference<>();
        nonBlockingService.execute(() -> documentAiService.chat(user, query)
                .onNext(message -> {
                    try {
                        sendMessage(emitter, message);
                        myMessage.set(message);
                    }
                    catch (IOException e) {
                        log.error("Error while writing next token", e);
                        emitter.completeWithError(e);
                    }
                })
                .onComplete(token -> {
                    emitter.complete();
                    completed.set(true);
                })
                .onError(error -> {
                    log.error("Unexpected chat error", error);
                    try {
                        sendMessage(emitter, error.getMessage());
                    }
                    catch (IOException e) {
                        log.error("Error while writing next token", e);
                    }
                    emitter.completeWithError(error);
                })
                .start());
        while (!completed.get()) {
            Thread.sleep(1000);
        }
//        return emitter;
        return myMessage.get();
    }

    @PostMapping("/documents/chat2/{user}")
    public String chat2(@RequestBody String query) throws InterruptedException {
        String result = documentService.chat(query);

        return result;
    }

    private static void sendMessage(SseEmitter emitter, String message) throws IOException {
        String token = message
                // Hack line break problem when using Server Sent Events (SSE)
                .replace("\n", "<br>")
                // Escape JSON quotes
                .replace("\"", "\\\"");
        emitter.send("{\"t\": \"" + token + "\"}");
    }
}
