package net.engineeringdigest.journalApp.controllers;

import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/get-all")
    public List<UserEntity> getAll() {
        return userServices.getAll();
    }

    @PostMapping("/Register")
    public Boolean createUser (@RequestBody UserEntity user ) {
        try {
            userServices.saveEntry(user);
            return true;
        } catch ( Exception e ) {
            return false;
        }
    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<?> updateUser (@RequestBody UserEntity user, @PathVariable String userName ) {
        UserEntity userInDB = userServices.findByUserName(userName);
        if ( userInDB != null ) {
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userServices.saveEntry(userInDB);
            return new ResponseEntity<>(userInDB, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
