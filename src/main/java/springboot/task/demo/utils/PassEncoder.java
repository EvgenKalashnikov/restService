package springboot.task.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassEncoder {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("1"));
    }
}
