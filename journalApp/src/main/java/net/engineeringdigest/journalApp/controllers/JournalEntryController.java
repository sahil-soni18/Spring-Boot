package net.engineeringdigest.journalApp.controllers;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Journals")      // Prefix for all the APIs in this class...
public class JournalEntryController {

    private Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping("/get-all")         // Not Necessary to write anything after get mapping annotation, then this will be treated as Get + http://localhost:8080/Journal
    public List<JournalEntry> getAll() {

        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping("/create")
    public Boolean createEntry(@RequestBody JournalEntry newEntry) {
        journalEntries.put(newEntry.getId(), newEntry);
        return true;
    }

    @GetMapping("/id/{JId}")
    public JournalEntry findById (@PathVariable ObjectId JId) {
        return journalEntries.get(JId);
    }

    @DeleteMapping("/id/{JId}")
    public JournalEntry deleteById (@PathVariable ObjectId JId) {
        return journalEntries.remove(JId);
    }

    @PutMapping("/id/{JId}")
    public JournalEntry updateById (@PathVariable ObjectId JId, @RequestBody JournalEntry updatedEntry) {
        return journalEntries.put(JId, updatedEntry);
    }
}
