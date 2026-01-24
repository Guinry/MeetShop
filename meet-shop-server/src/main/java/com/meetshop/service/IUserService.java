package com.meetshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meetshop.dto.LoginFormDTO;
import com.meetshop.dto.Result;
import com.meetshop.entity.User;

import javax.servlet.http.HttpSession;

public interface IUserService extends IService<User> {

    /**
     * 发送验证码
     * @param phone 手机号
     * @param session session
     */
    Result sendCode(String phone, HttpSession session);

    /**
     * 登录功能
     * @param loginForm 登录参数，包含手机号、验证码；或者手机号、密码
     * @param session session
     */
    Result login(LoginFormDTO loginForm, HttpSession session);
}
