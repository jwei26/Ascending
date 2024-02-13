package org.example.controller;

import org.example.model.User;
import org.example.service.JWTService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    JWTService jwtService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity userLogin(@RequestBody User user) {
        try{
            User u = userService.getUserByCredientials(user.getEmail(), user.getPassword());
            if(u == null) {
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
            }
            return ResponseEntity.ok().body(jwtService.generateToken(u));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
