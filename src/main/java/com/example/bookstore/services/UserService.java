package com.example.bookstore.services;

import com.example.bookstore.dtos.LoginRecordDto;
import com.example.bookstore.dtos.RegisterRecordDto;
import com.example.bookstore.exceptions.UserAlreadyExistsException;
import com.example.bookstore.exceptions.UserNotFoundException;
import com.example.bookstore.models.UserModel;
import com.example.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticateManager;

    public Object login(LoginRecordDto loginDto) {
        if (this.userRepository.findByLogin(loginDto.login()) == null) {
            throw new UserNotFoundException();
        }
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.password());
        var auth = this.authenticateManager.authenticate(usernamePassword);
        return auth;
    }

    public Object register(RegisterRecordDto registerDto) {
        if (this.userRepository.findByLogin(registerDto.login()) != null)
            throw new UserAlreadyExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        UserModel newUser = new UserModel(registerDto.login(), encryptedPassword, registerDto.role());

        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login);
    }
}
