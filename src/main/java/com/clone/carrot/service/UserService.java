package com.clone.carrot.service;

import com.clone.carrot.domain.User;
import com.clone.carrot.dto.UserRequest;
import com.clone.carrot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByPhone(String phone) {
        User user =  userRepository.findUserByPhone(phone).orElseThrow(()->new IllegalArgumentException("신규유저"));
        return user;
    }


    public User getUserByCode(String code) {
        return userRepository.findUserByCode(code);
    }

    public String randomNumber(){
        Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        return String.valueOf(generator.nextInt(1000000) % 1000000);
    }

    public User joinUser(User user) {
        userRepository.save(user);
        return getUserByPhone(user.getPhone());
    }

    @Transactional
    public void updateUser(UserRequest.update update) {
        User user = getUserByCode(update.getUserCode());
        user.update(update);
        userRepository.save(user);
    }
}
