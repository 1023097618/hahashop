package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RegistrationTest extends BaseTest {

	@Test
    public void testValidRegistration() {
        driver.get("http://localhost:8081/#/register");

        // 输入有效的注册信息：账号、密码、真实姓名、手机号、手机验证码（留空）、默认地址
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("lisi"); // 账号
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("321"); // 密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[3]/div/div/input")).sendKeys("张三"); // 真实姓名
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[4]/div/div/input")).sendKeys("13800138000"); // 手机号
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[5]/div/div/input")).sendKeys(""); // 手机验证码（不填）
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[6]/div/div/input")).sendKeys("上海市浦东新区花木路388号"); // 默认地址
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();


        String currentUrl = driver.getCurrentUrl();
        assertTrue("用户名为空时未显示错误提示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());

    }

    @Test
    public void testInvalidUsernameEmpty() {
        driver.get("http://localhost:8081/#/register");

        // 输入空的用户名
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("321");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[3]/div/div/input")).sendKeys("张三");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[4]/div/div/input")).sendKeys("13800138000");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[5]/div/div/input")).sendKeys(""); // 手机验证码不填
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[6]/div/div/input")).sendKeys("上海市浦东新区花木路388号");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证用户名为空时的错误提示
        assertTrue("用户名为空时未显示错误提示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    @Test
    public void testInvalidPasswordEmpty() {
        driver.get("http://localhost:8081/#/register");

        // 输入空的密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("lisi");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[3]/div/div/input")).sendKeys("张三");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[4]/div/div/input")).sendKeys("13800138000");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[5]/div/div/input")).sendKeys(""); // 手机验证码不填
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[6]/div/div/input")).sendKeys("上海市浦东新区花木路388号");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证密码为空时的错误提示
        assertTrue("密码为空时未显示错误提示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    @Test
    public void testInvalidPhoneFormat() {
        driver.get("http://localhost:8081/#/register");

        // 输入错误的电话格式
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("lisi");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("321");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[3]/div/div/input")).sendKeys("13800138abc");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[4]/div/div/input")).sendKeys("上海市浦东新区");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[5]/div/div/input")).sendKeys(""); // 手机验证码不填
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[6]/div/div/input")).sendKeys("上海市浦东新区花木路388号");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证电话格式错误时的提示
        assertTrue("电话格式错误时未显示错误提示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    @Test
    public void testInvalidLocationEmpty() {
        driver.get("http://localhost:8081/#/register");

        // 输入空的交易地点
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("lisi");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("321");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[3]/div/div/input")).sendKeys("13800138000");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[4]/div/div/input")).sendKeys(""); // 默认地址为空
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[5]/div/div/input")).sendKeys(""); // 手机验证码不填
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证交易地点为空时的错误提示
        assertTrue("交易地点为空时未显示错误提示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }
}
