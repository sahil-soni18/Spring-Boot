package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component    // This Annotation is necessary for Auto wiring
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserServices userServices;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) throws Exception {
        try {
            UserEntity user = userServices.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userServices.saveEntry(user);
        } catch ( Exception e ) {
            throw new Exception("Something Went Wrong While Saving the Journal Entry", e);
        }
    }

    public void saveJournalEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);

    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId JId) {
        return journalEntryRepository.findById(JId);
    }

    public void deleteById (ObjectId JId, String userName) {
        UserEntity user = userServices.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(JId));
        userServices.saveEntry(user);
        journalEntryRepository.deleteById(JId);
    }
}
