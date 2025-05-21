package com.malinduliyanage.books.services.users;

import com.malinduliyanage.books.entities.UserEntity;
import com.malinduliyanage.books.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = repository.findByEmail(email);
        if (user == null) {
//            throw new NotFoundException(String.format("User does not exist, email: %s", email));
        }

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

}
