package ru.job4j.accident;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("$2a$10$SVylsHUwxWCOYi8CCC72gOSu8Fth7FY0TSAXXmiJtLSdECZjsOZ0y");
        System.out.println(pwd);
    }
}
