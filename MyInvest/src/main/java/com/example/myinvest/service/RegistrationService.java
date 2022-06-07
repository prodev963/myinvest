package com.example.myinvest.service;

import com.example.myinvest.entity.UserEntity;
import com.example.myinvest.entity.UserRole;
import com.example.myinvest.model.receive.LoginReceiveModel;
import com.example.myinvest.model.receive.RegistrationReceiveModel;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class RegistrationService implements BaseService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiry.date}")
    private String jwtExpiryDate;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiResponse addUser(
            RegistrationReceiveModel registrationReceiveModel
    ) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(registrationReceiveModel.getUsername());
        if (optionalUserEntity.isPresent())
            return USER_EXIST;

        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(registrationReceiveModel.getFullName());
        userEntity.setUsername(registrationReceiveModel.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registrationReceiveModel.getPassword()));
        userEntity.setBirthday(registrationReceiveModel.getBirthday());
        userEntity.setGender(registrationReceiveModel.getGender());
        userEntity.setUserRole(UserRole.USER);

        userRepository.save(userEntity);

        return SUCCESS;
    }

    public ApiResponse login(
            LoginReceiveModel userSignInReceiveModel
    ) {
        Optional<UserEntity> optionalUserEntity
                = userRepository.findByUsername(userSignInReceiveModel.getUsername());

        if (optionalUserEntity.isEmpty())
            return USER_NOT_FOUND;

        if (!passwordEncoder.matches(userSignInReceiveModel.getPassword(),
                optionalUserEntity.get().getPassword()))
            return WRONG_PASSWORD;

        String token = this.generateToken(optionalUserEntity.get());
        LOGIN_SUC.setData(token);

        return LOGIN_SUC;
    }

    private String generateToken(UserEntity userEntity) {
        try {
            return Jwts.builder().signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiryDate)))
                    .setSubject(userEntity.getUsername())
                    .compact();
        } catch (Exception e) {
            return null;
        }
    }
}
