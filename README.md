# wtu-app-admin-server

wtu-app-admin的后端服务

[前端仓库](https://github.com/HuPeng333/wtu-app-admin)

[APP仓库](https://github.com/HuPeng333/WTU-APP)

## 启动
### 1. **克隆代码**
### 2. **打包**

建议跳过测试打包

```shell
mvn clean package -D maven.test.skip=true
```
### 3.启动项目
*本项目是一个SpringBoot项目*
```
java -jar wtu-app-admin-server.jar
```

## 注意事项
### 关于文件管理
在打包后请在jar包所在目录下创建一个名称为**app**的文件夹, 再按照如下结构创建对应文件夹

项目结构:
```
|-- wtu-app-admin-server.jar
|-- app
    |-- full
        |-- android
        |-- IOS
    |-- hotUpdate
```
这些文件夹用于存放APP的安装包

### 配置项
使用如下配置允许前端服务器跨域访问
```yaml
web:
  corsAllowedServer: ["http://localhost:8080", "http://localhost:63342"]
```

## 其它功能介绍
### 1. 提供新版本安装
访问[/download/android]()即可直接安装安卓最新版本安装包
