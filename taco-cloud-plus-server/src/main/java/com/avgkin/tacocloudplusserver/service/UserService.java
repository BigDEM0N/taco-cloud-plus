package com.avgkin.tacocloudplusserver.service;

import com.avgkin.entity.dto.RegistrationRequestDTO;
import com.avgkin.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    User getByUsername(String username);
    User registerUser(RegistrationRequestDTO requestDTO);
    String check();

}
