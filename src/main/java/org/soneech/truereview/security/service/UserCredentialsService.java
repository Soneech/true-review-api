package org.soneech.truereview.security.service;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.model.User;
import org.soneech.truereview.repository.UserRepository;
import org.soneech.truereview.security.UserCredentials;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCredentialsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        Optional<User> foundUser = userRepository.findByEmail(userEmail);
        if (foundUser.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь с такой почтой не найден");
        }

        return new UserCredentials(foundUser.get());
    }
}
