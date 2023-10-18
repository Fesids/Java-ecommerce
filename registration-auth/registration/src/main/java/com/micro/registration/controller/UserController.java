package com.micro.registration.controller;

import com.micro.registration.DTO.AuthDTO;
import com.micro.registration.DTO.UserDTO;
import com.micro.registration.UTILS.GeneralUtils;
import com.micro.registration.configurations.JwtService;
import com.micro.registration.configurations.auth.AuthService;
import com.micro.registration.services.UserService;
import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/api/v1/auth/register/{role}")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO, @PathVariable("role") String role){

        try{
            URI uri = GeneralUtils.toUri("/register/");
            var user = authService.register(userDTO, role);

            return ResponseEntity.ok().body(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Failed to create user");
        }

    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO userDTO){
        try{
            var res = authService.login(userDTO);
            return ResponseEntity.ok().body(res);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Failed to login");
        }
    }

    @GetMapping("/detail/{username}")
    public ResponseEntity<?> getByUsrrname(@PathVariable("username") String username){
        return ResponseEntity.ok().body(userService.retrieveByUsername(username));
    }

    @GetMapping("/detail/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok().body(userService.retrieveByEmail(email));
    }



    @GetMapping("/teste")
    public String teste(){
        return "teste";
    }






}
