package com.study.springsecurity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode("password");
        // $2a$10$9anpUz9nMwE5f8bsjdwAJeR.HoNy9ccvfzegtRQn9JPfcqAPYDlD2
        String pw = "$2a$10$9anpUz9nMwE5f8bsjdwAJeR.HoNy9ccvfzegtRQn9JPfcqAPYDlD2";
        String pw2 = "$2a$10$ij5L5gkThdn9tTuuYDTCo.vKHJRz2.U8eCa7Z3ZeikhXUySAaN0Pi";

        Assertions.assertTrue(encoder.matches("password", encoded));
        Assertions.assertTrue(encoder.matches("password", pw));
        Assertions.assertTrue(encoder.matches("password", pw2));

        System.out.println(encoded);
    }

}
