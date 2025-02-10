package net.engineeringdigest.journalApp.controllers;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UserServices userServices;



    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            System.out.println("Authentication is NULL");
        } else {
            System.out.println("Authentication object: " + authentication.toString());
            System.out.println("Principal: " + authentication.getPrincipal());
            System.out.println("Authorities: " + authentication.getAuthorities());
        }

        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("User not authenticated", HttpStatus.UNAUTHORIZED);
        }

        String userName = authentication.getName();


        UserEntity userInDB = userServices.findByUserName(userName);
        try {
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userServices.saveUserEntry(userInDB);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userServices.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
