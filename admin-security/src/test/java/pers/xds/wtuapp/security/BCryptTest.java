package pers.xds.wtuapp.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author DeSen Xu
 * @date 2021-12-22 22:18
 */
public class BCryptTest {

    @Test
    public void test() {
        String abc123 = new BCryptPasswordEncoder().encode("abc123");
        System.out.println(abc123);
    }
}
