package com.java.jwt.api.controller;

import com.java.jwt.api.entity.AuthRequest;
import com.java.jwt.api.entity.Register;
import com.java.jwt.api.entity.User;
import com.java.jwt.api.service.CustomUserDetailsService;
import com.java.jwt.api.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping("/")
    public String welcome(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return "Welcome to my world !! "+jwtUtil.extractUsername(authorizationHeader);
    }

    @GetMapping("/logout")
    public void logout() {
        System.out.println("h");
    }
    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
    @GetMapping("/user")
    public String user() {
        return "User";
    }

    @PostMapping("/auth")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @PostMapping("/register")
    public String register(@RequestBody Register register) {
        User user = new User();
        BeanUtils.copyProperties(register, user);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        try {
            userDetailsService.saveUser(user);
            return "Successfully Registered";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unable to register "+register.getEmail();
    }
}