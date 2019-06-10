package com.mloine.auth.auths.service;

import com.mloine.auth.auths.dao.LoginMapper;
import com.mloine.auth.auths.entity.User;
import com.mloine.auth.auths.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: LoginServiceImpl
 * @Description:TOO
 * @Author:mloine
 * @Date：2019/6/617:18
 **/
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginMapper loginMapper;

    @Override
    public User getUserById(Integer id) {
        return loginMapper.getDeptById (id);
    }
}
