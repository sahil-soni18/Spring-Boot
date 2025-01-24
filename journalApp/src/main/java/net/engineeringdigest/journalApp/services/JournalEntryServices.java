package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component    // This Annotation is necessary for Auto wiring
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry ) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(Long JId) {
        return journalEntryRepository.findById(JId);
    }

    public void deleteById (Long JId) {
        journalEntryRepository.deleteById(JId);
    }
}
