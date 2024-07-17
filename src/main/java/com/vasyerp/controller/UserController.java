package com.vasyerp.controller;

import com.vasyerp.entity.User;
import com.vasyerp.exception.CustomizedException;
import com.vasyerp.exception.ExceptionTO;
import com.vasyerp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save-user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            String response = userService.createUser(user);
            return new ResponseEntity<String>(response, HttpStatus.CREATED);
        } catch (CustomizedException e) {
            ExceptionTO error = new ExceptionTO(e.getError(), e.getMsg(), "Save User Failed!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getUserById(@RequestHeader("apiKey") String apiKey, @PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (CustomizedException e) {
            ExceptionTO error = new ExceptionTO(e.getError(), e.getMsg(), "User not found with id: " + id);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers(@RequestHeader("apiKey") String apiKey) {
        try {
            List<User> users = userService.getAllUsers();
            if (users != null && !users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                ExceptionTO error = new ExceptionTO(107L, "No data is present", "No users found in the database");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        } catch (CustomizedException e) {
            ExceptionTO error = new ExceptionTO(e.getError(), e.getMsg(), "Get All Users Failed");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@RequestHeader("apiKey") String apiKey, @PathVariable Long id) {
        try {
            String response = userService.deleteUser(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CustomizedException e) {
            ExceptionTO error = new ExceptionTO(e.getError(), e.getMsg(), "Delete User Failed");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@RequestHeader("apiKey") String apiKey, @PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (CustomizedException e) {
            ExceptionTO error = new ExceptionTO(e.getError(), e.getMsg(), "Update User Failed");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
