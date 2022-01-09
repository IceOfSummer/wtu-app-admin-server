package pers.xds.wtuapp.redis.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DeSen Xu
 * @date 2021-12-28 22:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppInfo {

    private String versionName;

    private int versionCode;

    private int size;

    /**
     * 版本号至少为多少时可以安装升级, 若客户端版本号小于该值, 提醒用户重新下载最新安装包
     */
    private Integer minVersionCode;

}
