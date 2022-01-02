package pers.xds.wtuapp.web;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author DeSen Xu
 * @date 2021-12-29 15:11
 */
public class FileTest {

    @Test
    public void test()  {
        File file = new File("D:\\Project\\JavaProject2\\Wtu-App-Admin-Server\\app\\hotUpdate");
        File[] files = file.listFiles();
        assert files != null;
        for (File file1 : files) {
            System.out.println(file1.lastModified());
        }
    }
}
