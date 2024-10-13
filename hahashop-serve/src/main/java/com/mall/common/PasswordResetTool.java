package com.mall.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Component
public class PasswordResetTool implements CommandLineRunner {

    private static final String DB_URL = "jdbc:sqlite:./test.db"; // SQLite 数据库文件路径
    private static final String RESET_PASSWORD_SQL = "UPDATE User SET password = ? WHERE username = 'admin'";

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && "reset-password".equals(args[0])) {
            resetAdminPassword();  // 调用重置密码方法
        } else {
            System.out.println("未提供 'reset-password' 参数，未执行任何操作。");
        }
    }

    // 重置 admin 用户的密码
    private void resetAdminPassword() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(RESET_PASSWORD_SQL)) {

            pstmt.setString(1, "123"); // 设置新密码
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Admin 用户密码已重置为：123");
            } else {
                System.out.println("未找到 admin 用户，密码重置失败。");
            }

        } catch (Exception e) {
            System.out.println("密码重置过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}