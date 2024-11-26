package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SubmitOrderTest extends BaseTest {

    // 买家需要登录才能提交订单
    @Test
    public void testSubmitOrder1() {
        loginAsBuyer();  // 登录买家
                
        // 假设买家选择商品，点击购买
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[3]/div/div/div/div")).click();

        // 买家填写个人信息
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("张三");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("13812345678");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("北京市朝阳区某某街道");

        // 提交订单
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click(); // 点击提交订单按钮

        // 验证订单是否提交成功，检查是否跳转到订单成功页面
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("/orderSuccess"));

        String currentUrl = driver.getCurrentUrl();
        assertTrue("页面没有跳转到订单成功页面", currentUrl.contains("/orderSuccess"));
    }

    @Test
    public void testSubmitOrder2() {
        loginAsBuyer();  // 登录买家

        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[3]/div/div/div/div")).click();
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("13812345678");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("北京市朝阳区某某街道");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click();

        // 验证用户名为空时的错误提示
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]"))); // 假设错误提示元素路径
        assertTrue("用户名为空时未显示错误提示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    @Test
    public void testSubmitOrder3() {
        loginAsBuyer();  // 登录买家

        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[3]/div/div/div/div")).click();
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("张三");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("北京市朝阳区某某街道");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click();

        // 验证密码为空时的错误提示
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]"))); // 假设错误提示元素路径
        assertTrue("密码为空时未显示错误提示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    @Test
    public void testSubmitOrder4() {
        loginAsBuyer();  // 登录买家

        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[3]/div/div/div/div")).click();
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("张三");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("13812345678");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click();

        // 验证地址为空时的错误提示
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]"))); // 假设错误提示元素路径
        assertTrue("地址为空时未显示错误提示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    @Test
    public void testSubmitOrder5() {
        loginAsBuyer();  // 登录买家

        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[3]/div/div/div/div")).click();
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click();

        // 验证所有字段为空时的错误提示
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]"))); // 假设错误提示元素路径
        assertTrue("所有字段为空时未显示错误提示", driver.findElement(By.xpath("/html/body/div[1]")).isDisplayed());
    }

    // 辅助方法，用于登录买家
    private void loginAsBuyer() {
        driver.get("http://localhost:8081/#/login"); // 打开登录页面

        // 输入用户名和密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("buyer");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("buyer");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证是否跳转到订单页面
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/#/dashboard/order"));

        // 点击主页按钮
        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\\\"app\\\"]/div/section/header/div/div[1]/div[1]")));
        element.click();

        // 等待主页加载完成
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/#/"));

        // 验证是否成功跳转到主页
        String currentUrl = driver.getCurrentUrl();
        assertTrue("未成功跳转到主页", currentUrl.equals("http://localhost:8081/#/"));
    }

}
