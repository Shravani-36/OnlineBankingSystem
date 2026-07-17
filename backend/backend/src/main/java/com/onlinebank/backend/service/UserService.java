package com.onlinebank.backend.service;

import com.onlinebank.backend.entity.User;
import com.onlinebank.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register User
    public User registerUser(User user) {
        user.setBalance(0.0);
        return userRepository.save(user);
    }

    // Deposit
    public User depositMoney(Long id, Double amount) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            return userRepository.save(user);
        }

        return null;
    }

    // Withdraw
    public User withdrawMoney(Long id, Double amount) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null && user.getBalance() >= amount) {

            user.setBalance(user.getBalance() - amount);

            return userRepository.save(user);
        }

        return null;
    }

    // Get User By ID
    public User getUserById(Long id) {

        return userRepository.findById(id).orElse(null);

    }

    // Transfer
    public String transferMoney(Long fromId, Long toId, Double amount) {

        User sender = userRepository.findById(fromId).orElse(null);
        User receiver = userRepository.findById(toId).orElse(null);

        if (sender == null || receiver == null) {
            return "User not found";
        }

        if (sender.getBalance() < amount) {
            return "Insufficient Balance";
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userRepository.save(sender);
        userRepository.save(receiver);

        return "Money Transferred Successfully";
    }

    // Update User
    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {

            user.setFullName(updatedUser.getFullName());
            user.setEmail(updatedUser.getEmail());

            return userRepository.save(user);
        }

        return null;
    }

    // Delete User
    public String deleteUser(Long id) {

        userRepository.deleteById(id);

        return "User Deleted Successfully";
    }

    // Search User By Email
    public User searchUser(String email) {

        return userRepository.findByEmail(email);

    }
    // Login User
    public User login(String email, String password) {

        return userRepository.findByEmailAndPassword(email, password);

    }

}