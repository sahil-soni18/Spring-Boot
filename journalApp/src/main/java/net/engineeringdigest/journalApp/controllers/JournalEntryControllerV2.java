package net.engineeringdigest.journalApp.controllers;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.services.JournalEntryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/JournalV2")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @GetMapping("/get-all")         // Not Necessary to write anything after get mapping annotation, then this will be treated as Get + http://localhost:8080/Journal
    public List<JournalEntry> getAll() {
        return journalEntryServices.getAll();

    }

    @PostMapping("/create")
    public Boolean createEntry(@RequestBody JournalEntry newEntry) {
        newEntry.setDate(LocalDateTime.now());
        journalEntryServices.saveEntry(newEntry);
        return true;
    }

    @GetMapping("/id/{JId}")
    public Optional<JournalEntry> findById (@PathVariable Long JId) {

        return journalEntryServices.findById(JId);
    }

    @DeleteMapping("/id/{JId}")
    public boolean deleteById (@PathVariable Long JId) {
        journalEntryServices.deleteById(JId);
        return true;
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
