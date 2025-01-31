package net.engineeringdigest.journalApp.controllers;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.services.JournalEntryServices;
import net.engineeringdigest.journalApp.services.UserServices;
import org.bson.types.ObjectId;
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
    @Autowired
    private UserServices userServices;

    @GetMapping("/{userName}/get-all")                // Not Necessary to write anything after get mapping annotation, then this will be treated as Get + http://localhost:8080/Journal
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName) {
        UserEntity user = userServices.findByUserName(userName);

        List<JournalEntry> journalEntries = user.getJournalEntries();

        if (journalEntries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        }

        return ResponseEntity.status(HttpStatus.OK).body(journalEntries); // 200 OK

    }

    @PostMapping("{userName}/create")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry newEntry, @PathVariable String userName ) {
        try {
            newEntry.setDate(LocalDateTime.now());
            journalEntryServices.saveEntry(newEntry, userName);
            return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
        } catch ( Exception e ) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{JId}")
    public ResponseEntity<JournalEntry> findById (@PathVariable ObjectId JId) {

        Optional<JournalEntry> journalEntry = journalEntryServices.findById(JId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{userName}/id/{JId}")
    public ResponseEntity<?> deleteById (@PathVariable ObjectId JId, @PathVariable String userName) {     // ? Means WildCard Entity, Can return now any type of class.
        try {
            journalEntryServices.deleteById(JId, userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch ( Exception e ) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }


    }

    @PutMapping("/{userName}/id/{JId}")   // Not using userName now, but we'll use when we'll add authentication.
    public JournalEntry updateById (@PathVariable ObjectId JId, @RequestBody JournalEntry updatedEntry, @PathVariable String userName) {
        JournalEntry currentEntries = journalEntryServices.findById(JId).orElse(null);
        if ( currentEntries != null ) {
            currentEntries.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals(" ")? updatedEntry.getTitle(): currentEntries.getTitle());
            currentEntries.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().equals(" ")? updatedEntry.getContent(): currentEntries.getContent());
        }
        journalEntryServices.saveJournalEntry(currentEntries);
        return currentEntries;
    }
}
