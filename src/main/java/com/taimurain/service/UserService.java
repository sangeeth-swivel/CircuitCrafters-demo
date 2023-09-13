package com.taimurain.service;

import com.taimurain.domain.entity.User;
import com.taimurain.domain.request.UserRequestDto;
import com.taimurain.exception.TaimurainApplicationException;
import com.taimurain.exception.UserExistException;
import com.taimurain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserRequestDto userRequestDto) {
        try {
            checkMobileNoExist(userRequestDto.getMobileNo());
            userRepository.save(new User(userRequestDto));
            log.debug("Successfully added user data.");
        } catch (DataAccessException e) {
            throw new TaimurainApplicationException("Failed to save user info in database.", e);
        }
    }

    public void checkMobileNoExist(String mobileNo) {
        try {
            Optional<User> optionalUser = userRepository.findByMobileNo(mobileNo);
            if (optionalUser.isPresent())
                throw new UserExistException("Mobile number already registered.");
        } catch (DataAccessException e) {
            throw new TaimurainApplicationException("Failed to get user by mobileNo from database.", e);
        }
    }
}
