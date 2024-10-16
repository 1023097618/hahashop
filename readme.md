github地址：https://github.com/1023097618/hahashop
--- 
# 项目说明 感谢您对本项目的关注！如果您觉得这个项目对您有帮助，请为我们点一颗免费的⭐Star。 
## 前提条件 - 请确保您的计算机已配置好 Java 环境变量（推荐使用 Java 17）。 
## 快速启动 本项目提供了一键启动功能，您只需双击本目录下的 `start.bat` 文件，即可启动前后端程序。 
## 注意事项 - 如果在使用 `start.bat` 启动后，前端网页显示网络错误，这可能是因为后端服务器尚未完全启动。请稍等片刻，待后端服务器启动完成后刷新页面。
## 自定义代码运行 如果您希望对代码进行自定义修改并运行，请按照以下步骤操作： 
### 前端项目：hahashop-page 
1. 打开终端并导航至 `hahashop-page` 目录。 
2. 运行以下命令以安装依赖并启动项目： 
3. 
```bash 
npm install 
npm run serve 
``` 
### 后端项目：hahashop-serve 
1. 使用 IntelliJ IDEA 打开 `hahashop-serve` 项目。 
2. 确保 `pom.xml` 文件中的依赖已更新。 
3. 运行 `MallApplication.java` 文件以启动后端服务。 
 ## 功能说明 
 ### 密码重置功能 如果您需要重置密码，请按照以下步骤操作：
1. 打开命令提示符（CMD）。 
2. 导航至 `hahashop-serve` 文件所在的目录。 
3. 输入以下命令以重置密码： 
```bash 
java -Dfile.encoding=GBK -jar target/hahashop-server-0.0.1-SNAPSHOT.jar reset-password
 ``` 
4. 重置后，默认用户凭证为： 
- 用户名：`admin` 
- 密码：`123` 
---
