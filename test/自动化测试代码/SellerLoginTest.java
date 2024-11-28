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
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class SellerLoginTest extends BaseTest {

    private final String username;
    private final String password;
    private final String expectedUrl;
    private final boolean expectedError;

    public SellerLoginTest(String username, String password, String expectedUrl, boolean expectedError) {
        this.username = username;
        this.password = password;
        this.expectedUrl = expectedUrl;
        this.expectedError = expectedError;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        String csvFilePath = "test99/TT/SellerLoginTest.csv"; // CSV 文件路径
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
                String username = split[0].trim();
                String password = split[1].trim();
                String expectedUrl = split.length > 2 ? split[2].trim() : "";
                boolean expectedError = Boolean.parseBoolean(split[3].trim());
                data.add(new Object[]{username, password, expectedUrl, expectedError});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Test
    public void testLogin() {
        driver.get("http://localhost:8081/#/login");

        // 输入用户名和密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        if (expectedError) {
            // 验证错误提示是否显示
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]")));
            assertTrue("错误提示未显示", errorMessage.isDisplayed());
        } else {
            // 验证页面是否跳转到期望的 URL
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            String currentUrl = driver.getCurrentUrl();
            assertTrue("URL不匹配，登录未成功", currentUrl.equals(expectedUrl));
        }
    }
}
