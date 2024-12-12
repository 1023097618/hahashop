# Project Description
This is an open-source e-commerce project primarily built with Vue and Spring Boot for the frontend and backend! If you find this project helpful, please give us a free ⭐Star.

## GitHub Repository
[https://github.com/1023097618/hahashop](https://github.com/1023097618/hahashop)

## API Documentation
[https://apifox.com/apidoc/shared-78461873-8311-486a-ae3c-9dab32a3e23f](https://apifox.com/apidoc/shared-78461873-8311-486a-ae3c-9dab32a3e23f)

---

## Prerequisites
- Ensure that your computer has the Java environment variables configured (Java 17 is recommended).

---

## Quick Start
This project provides a one-click startup feature. Simply double-click the `start.bat` file in the root directory to start both the frontend and backend programs.

---

## Requirements for Running `start.bat`
- Ensure that your computer has the Java environment variables configured (Java 17 is recommended).

---

## Notes
- If the frontend webpage shows a network error after using `start.bat`, it may be because the backend server has not fully started yet. Please wait a moment and refresh the page after the backend server is up and running.
- The default buyer account and password are both `buyer`. The default seller account and password are both `seller`.

---

## Custom Code Execution
If you want to modify and run the code yourself, follow these steps:

### Frontend Project: `hahashop-page`
1. Open a terminal and navigate to the `hahashop-page` directory.
2. Run the following commands to install dependencies and start the project:

``` bash
mvn -v
```
2. **Set JAVA_HOME**  
If the Java version is not 17, set the `JAVA_HOME` environment variable to the installation directory of Java 17.

3. **One-Click Packaging**  
Navigate to the `hahashop-page` directory and double-click the `localhost.bat` file to complete the packaging process.

---

## Features
### Password Reset
If you need to reset your password, follow these steps:
1. Open the Command Prompt (CMD).
2. Navigate to the directory where the `hahashop-serve` file is located.
3. Enter the following command to reset the password:
``` bash
java -Dfile.encoding=GBK -jar target/hahashop-server-0.0.1-SNAPSHOT.jar reset-password
```
4. After resetting, the default user credentials are:
- Username: `admin`
- Password: `123`

---

## Project Demo

### Buyer Browsing Products Interface
![Buyer Browsing Products](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E4%B9%B0%E5%AE%B6%E6%B5%8F%E8%A7%88%E5%95%86%E5%93%81.png)

### Buyer Filling in User Information Interface
![Buyer Filling Information](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E4%B9%B0%E5%AE%B6%E5%A1%AB%E5%86%99%E4%BF%A1%E6%81%AF.png "Buyer Filling Information Interface")

### Login Interface
![Seller Login](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E5%8D%96%E5%AE%B6%E7%99%BB%E5%BD%95.png)

### Password Modification Interface
![Seller Modifying Password](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E5%8D%96%E5%AE%B6%E4%BF%AE%E6%94%B9%E5%AF%86%E7%A0%81.png)

### Viewing Potential Buyers' Information Interface
![Viewing Potential Buyers' Information](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E6%9F%A5%E7%9C%8B%E6%84%8F%E5%90%91%E8%B4%AD%E4%B9%B0%E4%BA%BA%E4%BF%A1%E6%81%AF.png)

### Selecting Potential Buyers
![Selecting Potential Buyers](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E9%80%89%E6%8B%A9%E6%84%8F%E5%90%91%E8%B4%AD%E4%B9%B0%E4%BA%BA%E7%95%8C%E9%9D%A2.png)

### Automatically Freezing Products After Selecting Buyers
![Freezing Products](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E5%86%BB%E7%BB%93%E5%95%86%E5%93%81.png)

### Removing Products
![Removing Products](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E6%92%A4%E4%B8%8B%E5%95%86%E5%93%81.png)

### Viewing Historical Products
![Viewing Historical Products](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E6%9F%A5%E7%9C%8B%E5%8E%86%E5%8F%B2%E5%95%86%E5%93%81.png)

### Adding New Products
![Adding New Products](https://gitee.com/zhishidiannaoka/hahashop/raw/master/2235020429-额外成果物/1-images/%E6%B7%BB%E5%8A%A0%E6%96%B0%E7%9A%84%E5%95%86%E5%93%81.png)