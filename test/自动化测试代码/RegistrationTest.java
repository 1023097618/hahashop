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
public class RegistrationTest extends BaseTest {

    private final String username;
    private final String password;
    private final String realName;
    private final String phoneNumber;
    private final String verificationCode;
    private final String address;
    private final boolean expectedResult;

    public RegistrationTest(String username, String password, String realName, String phoneNumber, String verificationCode, String address, boolean expectedResult) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
        this.address = address;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        String csvFilePath = "test99/TT/RegistrationTest.csv"; // CSV 文件路径
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
                String realName = split[2].trim();
                String phoneNumber = split[3].trim();
                String verificationCode = split[4].trim();
                String address = split[5].trim();
                boolean expectedResult = Boolean.parseBoolean(split[6].trim());
                data.add(new Object[]{username, password, realName, phoneNumber, verificationCode, address, expectedResult});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Test
    public void testRegistration() {
        driver.get("http://localhost:8081/#/register");

        // 填写注册信息
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[3]/div/div/input")).sendKeys(realName);
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[4]/div/div/input")).sendKeys(phoneNumber);
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[5]/div/div/input")).sendKeys(verificationCode);
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[6]/div/div/input")).sendKeys(address);
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证注册结果
        WebDriverWait wait = new WebDriverWait(driver, 10);

        if (expectedResult) {
            // 预期成功：验证跳转到主页
            String expectedUrl = "http://localhost:8081/#/dashboard";
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            String currentUrl = driver.getCurrentUrl();
            assertTrue("注册成功后没有跳转到正确的页面", currentUrl.equals(expectedUrl));
        } else {
            // 预期失败：验证错误提示
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]")));
            assertTrue("注册失败时应显示错误提示", errorMessage.isDisplayed());
        }
    }
}
