package com.malinduliyanage.books.services.users;

import com.malinduliyanage.books.constants.CommonResponse;
import com.malinduliyanage.books.constants.UserResponses;
import com.malinduliyanage.books.dtos.requests.users.AddUserRequest;
import com.malinduliyanage.books.dtos.requests.users.LoginRequest;
import com.malinduliyanage.books.dtos.responses.BaseResponse;
import com.malinduliyanage.books.dtos.responses.users.LoginUserResponse;
import com.malinduliyanage.books.entities.UserEntity;
import com.malinduliyanage.books.helpers.jwt.JwtHelper;
import com.malinduliyanage.books.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
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

    public BaseResponse<LoginUserResponse> loginUser(LoginRequest request) {
        try {

            UserEntity existingUser = userRepository.findByEmail(request.getEmail());

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String token = JwtHelper.generateToken(request.getEmail());

            return new BaseResponse<LoginUserResponse>(HttpStatus.OK, new LoginUserResponse(request.getEmail(), token));

        } catch (BadCredentialsException e) {
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, UserResponses.USER_NO_EXIST.getValue());

        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR.getValue());
        }
    }
}
