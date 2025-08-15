package com.suman.finance.personal_finance_backend.service;

import com.suman.finance.personal_finance_backend.model.UserEntity;
import com.suman.finance.personal_finance_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
