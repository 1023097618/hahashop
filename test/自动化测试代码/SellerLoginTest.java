package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SellerLoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        driver.get("http://localhost:8082/#/login");

        // 输入有效的用户名和密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();
        
        // 登录成功后页面应自动跳转到 http://localhost:8082/#/dashboard/good/manager
        String expectedUrl = "http://localhost:8082/#/dashboard/good/manager";
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        
        // 验证是否跳转到正确的URL
        String currentUrl = driver.getCurrentUrl();
        assertTrue("URL不匹配，登录未成功", currentUrl.equals(expectedUrl));
    }

    @Test
    public void testInvalidLogin1() {
        driver.get("http://localhost:8082/#/login");

        // 输入无效的用户名
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("wronguser");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证错误信息是否显示
        assertTrue("错误提示未显示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    @Test
    public void testInvalidLogin2() {
        driver.get("http://localhost:8082/#/login");

        // 输入正确的用户名，但密码错误
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("2");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证错误信息是否显示
        assertTrue("错误提示未显示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    @Test
    public void testInvalidLogin3_emptyUsername() {
        driver.get("http://localhost:8082/#/login");

        // 输入空的用户名和密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证错误提示信息是否显示
        assertTrue("错误提示未显示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    @Test
    public void testInvalidLogin4_emptyPassword() {
        driver.get("http://localhost:8082/#/login");

        // 输入有效的用户名，但密码为空
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证错误提示信息是否显示
        assertTrue("错误提示未显示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    @Test
    public void testInvalidLogin5_blankFields() {
        driver.get("http://localhost:8082/#/login");

        // 输入空的用户名和密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证错误提示信息是否显示
        assertTrue("错误提示未显示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }
}
