package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserServices {
    // This Annotation is necessary for Auto wiring
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public void saveEntry(UserEntity journalEntry ) {
        userRepository.save(journalEntry);
    }

//    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Boolean saveUserEntry(UserEntity user ) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singletonList("ROLE_USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.out.println("Error While Registering " + e);
            return false;
        }

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
