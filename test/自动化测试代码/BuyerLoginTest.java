package TT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
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
public class BuyerLoginTest extends BaseTest {

    private final String username;
    private final String password;
    private final boolean expectSuccess;

    public BuyerLoginTest(String username, String password, boolean expectSuccess) {
        this.username = username;
        this.password = password;
        this.expectSuccess = expectSuccess;
    }

    // 从 CSV 文件加载测试数据
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        String csvFilePath = "test99/TT/BuyerLoginTest.csv"; // CSV 文件路径（相对于项目根目录）
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
                boolean expectSuccess = Boolean.parseBoolean(split[2].trim());
                data.add(new Object[]{username, password, expectSuccess});
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

        if (expectSuccess) {
            // 验证登录成功
            String expectedUrl = "http://localhost:8081/#/dashboard/order";
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.urlToBe(expectedUrl));

            String currentUrl = driver.getCurrentUrl();
            assertTrue("URL不匹配，登录未成功", currentUrl.equals(expectedUrl));
        } else {
            // 验证登录失败，错误提示是否显示
            assertTrue("错误提示未显示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
        }
    }
}
