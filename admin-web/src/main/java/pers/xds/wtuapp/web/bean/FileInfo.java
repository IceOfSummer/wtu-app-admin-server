package pers.xds.wtuapp.web.bean;

import lombok.Data;

import java.io.File;

/**
 * @author DeSen Xu
 * @date 2022-01-02 11:35
 */
@Data
public class FileInfo {

    private String fileName;

    private String createTime;

    /**
     * 大小 kb
     */
    private int size;

    public FileInfo(File file) {
        this.fileName = file.getName();
        this.createTime = String.valueOf(file.lastModified());
        // 换算为KB
        this.size = (int) (file.length() / 1024);
    }
}
