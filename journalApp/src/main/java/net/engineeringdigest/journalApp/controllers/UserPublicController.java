package net.engineeringdigest.journalApp.controllers;

import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/user")
public class UserPublicController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/Register")
    public Boolean createUser (@RequestBody UserEntity user ) {
        try {
            System.out.println(user.getPassword());
            userServices.saveUserEntry(user);
            return true;
        } catch ( Exception e ) {
            System.out.println("Error in Controller while registering " + e);
            return false;
        }
    }
}
