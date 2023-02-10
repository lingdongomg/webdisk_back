package com.hukaichao.webdisk.service;

import com.hukaichao.webdisk.model.User;

public interface UserService {
    Boolean checkUser(User user);
    Boolean addUser(User user);
}
