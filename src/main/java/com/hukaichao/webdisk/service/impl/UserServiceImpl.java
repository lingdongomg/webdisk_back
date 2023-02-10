package com.hukaichao.webdisk.service.impl;

import com.hukaichao.webdisk.model.User;
import com.hukaichao.webdisk.repository.UserRepository;
import com.hukaichao.webdisk.service.HadoopService;
import com.hukaichao.webdisk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HadoopService hadoopService;

    @Override
    public Boolean checkUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUsername());
        return userOptional.isPresent() && userOptional.get().getPassword().equals(user.getPassword());
    }

    @Override
    public Boolean addUser(User user) {
        if(userRepository.existsById(user.getUsername())){
            return false;
        }
        userRepository.save(user);
        hadoopService.mkdirFolder("",user.getUsername());
        return true;
    }
}

