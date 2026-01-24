package com.meetshop.controller;


import com.meetshop.dto.LoginFormDTO;
import com.meetshop.dto.Result;
import com.meetshop.dto.UserDTO;
import com.meetshop.entity.UserInfo;
import com.meetshop.service.IUserInfoService;
import com.meetshop.service.IUserService;
import com.meetshop.utils.UserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 用户相关接口
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    private IUserInfoService userInfoService;

    /**
     * 发送验证码
     * @param phone 手机号
     * @param session session
     */
    @PostMapping("/code")
    @ApiOperation("发送验证码")
    public Result sendCode(@RequestParam("phone") String phone, HttpSession session) {
        log.info("发送验证码：{}", phone);
        return userService.sendCode(phone, session);
    }

    /**
     * 登录功能
     * @param loginForm 登录参数，包含手机号、验证码；或者手机号、密码
     * @param session session
     */
    @PostMapping("/login")
    @ApiOperation("登录功能")
    public Result login(@RequestBody LoginFormDTO loginForm, HttpSession session){
        log.info("用户登录");
        return userService.login(loginForm, session);
    }

    /**
     * 登出功能
     * @return 无
     */
    @PostMapping("/logout")
    public Result logout(){
        // TODO 实现登出功能
        return Result.fail("功能未完成");
    }

    /**
     * 获取当前登录用户的信息
     * @return 用户信息
     */
    @GetMapping("/me")
    @ApiOperation("获取当前登录用户信息")
    public Result me(){
        log.info("获取当前登录用户信息");
        UserDTO user = UserHolder.getUser();
        return Result.ok(user);
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long userId){
        // 查询详情
        UserInfo info = userInfoService.getById(userId);
        if (info == null) {
            // 没有详情，应该是第一次查看详情
            return Result.ok();
        }
        info.setCreateTime(null);
        info.setUpdateTime(null);
        // 返回
        return Result.ok(info);
    }
}
