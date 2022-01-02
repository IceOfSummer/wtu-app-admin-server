package pers.xds.wtuapp.web;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * @author DeSen Xu
 * @date 2021-12-29 15:11
 */
public class FileTest {

    @Test
    public void test() throws FileNotFoundException {
        File file = new File("D:\\Project\\JavaProject2\\Wtu-App-Admin-Server\\app\\hotUpdate");
        System.out.println(Arrays.toString(file.list()));
    }
}
