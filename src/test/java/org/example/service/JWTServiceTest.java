package org.example.service;

import org.example.ApplicationBootStrap;
import org.example.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootStrap.class)
public class JWTServiceTest {
    @Autowired
    private JWTService jwtService;

    @Test
    public void generateTokenTest() {
        User user = new User();
        user.setId(1);
        user.setFirstName("jane");
        user.setLastName("wang");
        String token = jwtService.generateToken(user);

        String[] array = token.split("\\.");
        boolean bool = array.length == 3 ? true : false;
        assertTrue(bool);
    }
}
