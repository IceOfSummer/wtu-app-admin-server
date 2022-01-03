package pers.xds.wtuapp.redis.bean;

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
public class AppInfo {

    private String versionName;

    private int versionCode;

    private int size;
}
