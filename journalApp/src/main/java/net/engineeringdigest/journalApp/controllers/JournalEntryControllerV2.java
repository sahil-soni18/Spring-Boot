package net.engineeringdigest.journalApp.controllers;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.services.JournalEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/JournalV2")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @GetMapping("/get-all")                // Not Necessary to write anything after get mapping annotation, then this will be treated as Get + http://localhost:8080/Journal
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> journalEntries = journalEntryServices.getAll();

        if (journalEntries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        }

        return ResponseEntity.status(HttpStatus.OK).body(journalEntries); // 200 OK
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry newEntry) {
        try {
            newEntry.setDate(LocalDateTime.now());
            journalEntryServices.saveEntry(newEntry);
            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
        } catch ( Exception e ) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{JId}")
    public ResponseEntity<JournalEntry> findById (@PathVariable Long JId) {

        Optional<JournalEntry> journalEntry = journalEntryServices.findById(JId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/id/{JId}")
    public ResponseEntity<?> deleteById (@PathVariable Long JId) {     // ? Means WildCard Entity, Can return now any type of class.
        try {
            journalEntryServices.deleteById(JId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch ( Exception e ) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }


    }

    @PutMapping("/id/{JId}")
    public JournalEntry updateById (@PathVariable Long JId, @RequestBody JournalEntry updatedEntry) {
        JournalEntry currentEntries = journalEntryServices.findById(JId).orElse(null);
        if ( currentEntries != null ) {
            currentEntries.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals(" ")? updatedEntry.getTitle(): currentEntries.getTitle());
            currentEntries.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().equals(" ")? updatedEntry.getContent(): currentEntries.getContent());
        }
        journalEntryServices.saveEntry(currentEntries);
        return currentEntries;
    }
}
