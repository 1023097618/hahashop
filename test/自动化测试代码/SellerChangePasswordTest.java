package TT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SellerChangePasswordTest extends BaseTest {

    private final String currentPassword;
    private final String newPassword;
    private final String expectedUrl;
    private final boolean expectedError;

    public SellerChangePasswordTest(String currentPassword, String newPassword, String expectedUrl, boolean expectedError) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.expectedUrl = expectedUrl;
        this.expectedError = expectedError;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        String csvFilePath = "test99/TT/SellerChangePasswordTest.csv"; // CSV 文件路径
        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true; // 跳过标题行
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] split = line.split(",");
                String currentPassword = split[0].trim();
                String newPassword = split[1].trim();
                String expectedUrl = split[2].trim();
                boolean expectedError = Boolean.parseBoolean(split[3].trim());
                data.add(new Object[]{currentPassword, newPassword, expectedUrl, expectedError});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Test
    public void testChangePassword() {
        loginAsSeller();
        driver.get("http://localhost:8081/#/dashboard/changePassword");

        // 输入当前密码和新密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[1]/form/div[1]/div/div/input")).sendKeys(currentPassword);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[1]/form/div[2]/div/div/input")).sendKeys(newPassword);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[2]/button")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        if (expectedError) {
            // 验证错误提示是否显示
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]")));
            assertTrue("错误提示未显示", errorMessage.isDisplayed());
        } else {
            // 验证页面是否跳转到登录页面
            wait.until(ExpectedConditions.urlContains("/login"));
            String currentUrl = driver.getCurrentUrl();
            assertTrue("页面没有跳转到登录页面", currentUrl.equals(expectedUrl));
        }
    }

    // 辅助方法，用于登录商家
    private void loginAsSeller() {
        driver.get("http://localhost:8081/#/login");

        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("seller");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("seller");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();
        
        // 登录成功后页面应自动跳转
        String expectedUrl = "http://localhost:8081/#/dashboard/good/manager";
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }
}
