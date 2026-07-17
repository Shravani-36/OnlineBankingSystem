package com.onlinebank.backend.controller;

import com.onlinebank.backend.entity.User;
import com.onlinebank.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public User login(@RequestBody User user) {

        return userService.login(user.getEmail(), user.getPassword());

    }

    // Deposit
    @PutMapping("/deposit/{id}/{amount}")
    public User depositMoney(@PathVariable Long id,
                             @PathVariable Double amount) {

        return userService.depositMoney(id, amount);
    }

    // Withdraw
    @PutMapping("/withdraw/{id}/{amount}")
    public User withdrawMoney(@PathVariable Long id,
                              @PathVariable Double amount) {

        return userService.withdrawMoney(id, amount);
    }

    // Get User By ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {

        return userService.getUserById(id);

    }

    // Transfer
    @PutMapping("/transfer/{fromId}/{toId}/{amount}")
    public String transferMoney(@PathVariable Long fromId,
                                @PathVariable Long toId,
                                @PathVariable Double amount) {

        return userService.transferMoney(fromId, toId, amount);

    }

    // Update
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {

        return userService.updateUser(id, user);

    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        return userService.deleteUser(id);

    }

    // Search By Email
    @GetMapping("/email/{email}")
    public User searchUser(@PathVariable String email) {

        return userService.searchUser(email);

    }

}