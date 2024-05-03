package org.soneech.truereview.service;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.exception.UserNotFoundException;
import org.soneech.truereview.model.User;
import org.soneech.truereview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Value("${app.roles.user}")
    private String userRole;

    public User findById(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow(() -> new UserNotFoundException(id));
    }

    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }

    @Transactional
    public User register(User user) {
        user.setRoles(Collections.singletonList(roleService.findRoleByName(userRole)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void verifyThatUserExists(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
    }
}
