package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServices {
    // This Annotation is necessary for Auto wiring
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(UserEntity journalEntry ) {
        userRepository.save(journalEntry);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(ObjectId JId) {
        return userRepository.findById(JId);
    }

    public void deleteById (ObjectId JId) {
        userRepository.deleteById(JId);
    }
    public UserEntity findByUserName ( String userName ) {
        return userRepository.findByUserName(userName);
    }
}
