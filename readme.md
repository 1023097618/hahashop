github地址：https://github.com/1023097618/hahashop

接口文档地址：https://apifox.com/apidoc/shared-78461873-8311-486a-ae3c-9dab32a3e23f/api-224359793

--- 
# 项目说明 
这是一个开源的商城项目，主要使用了vue+springboot作为前后端！如果您觉得这个项目对您有帮助，请为我们点一颗免费的⭐Star。 

## 快速启动 
本项目提供了一键启动功能，您只需双击本目录下的 `start.bat` 文件，即可启动前后端程序。 
## 正常启动start.bat前提条件 
- 请确保您的计算机已配置好 Java 环境变量（推荐使用 Java 17）。
## 注意事项 
- 如果在使用 `start.bat` 启动后，前端网页显示网络错误，这可能是因为后端服务器尚未完全启动。请稍等片刻，待后端服务器启动完成后刷新页面。
- 默认买家账号密码均为buyer。卖家账号密码均为seller。
## 自定义代码运行 
如果您希望对代码进行自定义修改并运行，请按照以下步骤操作： 
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

### 汇报时ppt存放地址：2235020429-额外成果物
### 前端笔记、汇报时思路存放地址：2211080131-额外成果物


## 项目演示
### 买家浏览商品界面

![买家浏览商品](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-%E9%A2%9D%E5%A4%96%E6%88%90%E6%9E%9C%E7%89%A9/3-image/%E6%B5%8F%E8%A7%88%E5%95%86%E5%93%81%E7%95%8C%E9%9D%A2.png)

### 买家注册界面

![买家注册界面](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-%E9%A2%9D%E5%A4%96%E6%88%90%E6%9E%9C%E7%89%A9/2-images/%E4%B9%B0%E5%AE%B6%E6%B3%A8%E5%86%8C%E7%95%8C%E9%9D%A2.png)

### 买家填写用户信息界面

![买家填写信息](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E4%B9%B0%E5%AE%B6%E5%A1%AB%E5%86%99%E4%BF%A1%E6%81%AF.png "买家填写信息界面")

### 买家查看历史订单

![买家查看历史订单](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-%E9%A2%9D%E5%A4%96%E6%88%90%E6%9E%9C%E7%89%A9/2-images/%E4%B9%B0%E5%AE%B6%E6%9F%A5%E7%9C%8B%E5%8E%86%E5%8F%B2%E8%AE%A2%E5%8D%95.png)

### 登录界面

![卖家登录](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-%E9%A2%9D%E5%A4%96%E6%88%90%E6%9E%9C%E7%89%A9/2-images/%E7%99%BB%E5%BD%95%E7%95%8C%E9%9D%A2.png)

### 修改密码界面

![卖家修改密码](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-%E9%A2%9D%E5%A4%96%E6%88%90%E6%9E%9C%E7%89%A9/2-images/%E4%BF%AE%E6%94%B9%E5%AF%86%E7%A0%81%E7%95%8C%E9%9D%A2.png)

### 查看意向购买人信息界面

![查看意向购买人信息](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E6%9F%A5%E7%9C%8B%E6%84%8F%E5%90%91%E8%B4%AD%E4%B9%B0%E4%BA%BA%E4%BF%A1%E6%81%AF.png)


### 查看历史商品

![查看历史商品](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-%E9%A2%9D%E5%A4%96%E6%88%90%E6%9E%9C%E7%89%A9/2-images/%E6%9F%A5%E7%9C%8B%E5%8E%86%E5%8F%B2%E5%95%86%E5%93%81%E7%95%8C%E9%9D%A2.png)

### 添加新商品

![添加新商品](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-%E9%A2%9D%E5%A4%96%E6%88%90%E6%9E%9C%E7%89%A9/2-images/%E5%8D%96%E5%AE%B6%E4%B8%8A%E4%BC%A0%E5%95%86%E5%93%81.png)

### 卖家查看买家注册信息

![买家注册信息](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-%E9%A2%9D%E5%A4%96%E6%88%90%E6%9E%9C%E7%89%A9/2-images/%E6%9F%A5%E7%9C%8B%E4%B9%B0%E5%AE%B6%E6%B3%A8%E5%86%8C%E4%BF%A1%E6%81%AF.png)

### 查看某个买家的购买历史

![购买历史](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-%E9%A2%9D%E5%A4%96%E6%88%90%E6%9E%9C%E7%89%A9/2-images/%E6%9F%A5%E7%9C%8B%E6%9F%90%E4%B8%AA%E4%B9%B0%E5%AE%B6%E7%9A%84%E8%B4%AD%E4%B9%B0%E8%AE%B0%E5%BD%95.png)
