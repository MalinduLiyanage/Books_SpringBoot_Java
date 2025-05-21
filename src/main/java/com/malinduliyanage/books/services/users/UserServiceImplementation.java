package com.malinduliyanage.books.services.users;

import com.malinduliyanage.books.constants.CommonResponse;
import com.malinduliyanage.books.constants.UserResponses;
import com.malinduliyanage.books.dtos.requests.users.AddUserRequest;
import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.entities.UserEntity;
import com.malinduliyanage.books.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public BaseResponse<String> signupUser(AddUserRequest request) {

        try {
            String email = request.getEmail();

            UserEntity existingUser = userRepository.findByEmail(email);

            if (existingUser != null) {
                return new BaseResponse<>(HttpStatus.OK, UserResponses.USER_EXIST.getValue());
            }

            String hashedPassword = passwordEncoder.encode(request.getPassword());

            UserEntity newUser = new UserEntity();

            newUser.setEmail(request.getEmail());
            newUser.setPassword(hashedPassword);

            userRepository.save(newUser);

            return new BaseResponse<>(HttpStatus.OK, UserResponses.USER_CREATED.getValue());

        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR.getValue());
        }
    }
}
