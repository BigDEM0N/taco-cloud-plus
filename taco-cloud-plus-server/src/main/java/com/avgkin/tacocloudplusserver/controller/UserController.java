package com.avgkin.tacocloudplusserver.controller;

import com.avgkin.entity.dto.LoginRequestDTO;
import com.avgkin.entity.dto.RegistrationRequestDTO;
import com.avgkin.entity.dto.UserDTO;
import com.avgkin.entity.po.User;
import com.avgkin.tacocloudplusserver.result.CommonResult;
import com.avgkin.tacocloudplusserver.result.CommonResults;
import com.avgkin.tacocloudplusserver.service.UserService;
import com.avgkin.tacocloudplusserver.utils.JwtUtil;
import com.avgkin.tacocloudplusserver.utils.RedisUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController{
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtUtil jwtUtil;
    private RedisUtil redisUtil;

    @Autowired
    public UserController(AuthenticationManager authenticationManager,UserService userService,JwtUtil jwtUtil,RedisUtil redisUtil){
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.redisUtil = redisUtil;
    }
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody LoginRequestDTO requestDTO, HttpServletResponse response){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(requestDTO.getUsername(),requestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtil.getToken(userDetails.getUsername());
        Collection<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        System.out.println(authorities);
        //这里要查两次数据库，一次是springsecurity验证，一次查询id，todo 改进为一次
        User user = userService.getByUsername(userDetails.getUsername());
        UserDTO userDTO = new UserDTO(user.getId(),user.getUsername(),authorities);
        redisUtil.putKv("auth",userDTO.getUsername(),userDTO);
        response.setHeader("Token",jwtToken);
        return CommonResults.success(userDetails.getUsername());
    }

    @PostMapping("/register")
    public CommonResult<User> register(@RequestBody RegistrationRequestDTO requestDTO, HttpServletResponse response){
        User user = userService.registerUser(requestDTO);
        String jwtToken = jwtUtil.getToken(user.getUsername());
        response.setHeader("Token",jwtToken);
        return CommonResults.success(user);
    }

    @GetMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public CommonResult<Void> logout(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        redisUtil.remove("auth",((UserDetails)authentication.getPrincipal()).getUsername());
        return CommonResults.success();
    }

    @GetMapping("/check")
    @PreAuthorize("hasRole('USER')")
    public CommonResult<Void> check(){
        userService.check();
        return CommonResults.success();
    }
}
