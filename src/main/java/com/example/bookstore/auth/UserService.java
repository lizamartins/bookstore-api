package com.example.bookstore.auth;

import com.example.bookstore.dtos.RegisterRecordDto;
import com.example.bookstore.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel register(RegisterRecordDto registerDto) {
        if (this.userRepository.findByLogin(registerDto.login()) != null)
            throw new UserAlreadyExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        UserModel newUser = new UserModel(registerDto.login(), encryptedPassword, registerDto.role());

        return userRepository.save(newUser);
    }
}
