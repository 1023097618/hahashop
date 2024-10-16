package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class SubmitOrderTest extends BaseTest {

    @Test
    public void testSubmitOrder1() {
        // 买家进入商品页面，无需登录
        driver.get("http://localhost:8080/#/");

        // 假设买家选择商品，点击购买
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[3]/div/div/div/div")).click();

        // 买家填写个人信息
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("张三");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("13812345678");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("北京市朝阳区某某街道");

        // 提交订单
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click(); // 点击提交订单按钮

        loginAsSeller();
        driver.get("http://localhost:8080/#/dashboard/good/manager");
        
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[3]/div[3]/table/tbody/tr/td[5]/div/button")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/div[3]/table/tbody/tr")).isDisplayed());
    }
    
    @Test
    public void testSubmitOrder2() {
        // 买家进入商品页面，无需登录
        driver.get("http://localhost:8080/#/");

        // 假设买家选择商品，点击购买
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[3]/div/div/div/div")).click();

        // 买家填写个人信息
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("13812345678");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("北京市朝阳区某某街道");

        // 提交订单
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click(); // 点击提交订单按钮

        loginAsSeller();
        driver.get("http://localhost:8080/#/dashboard/good/manager");
        
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[3]/div[3]/table/tbody/tr/td[5]/div/button")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/div[3]/table/tbody/tr[2]")).isDisplayed());
    }
    
    @Test
    public void testSubmitOrder3() {
        // 买家进入商品页面，无需登录
        driver.get("http://localhost:8080/#/");

        // 假设买家选择商品，点击购买
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[3]/div/div/div/div")).click();

        // 买家填写个人信息
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("张三");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("北京市朝阳区某某街道");

        // 提交订单
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click(); // 点击提交订单按钮

        loginAsSeller();
        driver.get("http://localhost:8080/#/dashboard/good/manager");
        
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[3]/div[3]/table/tbody/tr/td[5]/div/button")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/div[3]/table/tbody/tr[3]")).isDisplayed());
    }
    
    @Test
    public void testSubmitOrder4() {
        // 买家进入商品页面，无需登录
        driver.get("http://localhost:8080/#/");

        // 假设买家选择商品，点击购买
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[3]/div/div/div/div")).click();

        // 买家填写个人信息
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("张三");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("13812345678");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("");

        // 提交订单
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click(); // 点击提交订单按钮

        loginAsSeller();
        driver.get("http://localhost:8080/#/dashboard/good/manager");
        
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[3]/div[3]/table/tbody/tr/td[5]/div/button")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/div[3]/table/tbody/tr[4]")).isDisplayed());
    }
    
    @Test
    public void testSubmitOrder5() {
        // 买家进入商品页面，无需登录
        driver.get("http://localhost:8080/#/");

        // 假设买家选择商品，点击购买
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[3]/div/div/div/div")).click();

        // 买家填写个人信息
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("");

        // 提交订单
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click(); // 点击提交订单按钮

        loginAsSeller();
        driver.get("http://localhost:8080/#/dashboard/good/manager");
        
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[3]/div[3]/table/tbody/tr/td[5]/div/button")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/div[3]/table/tbody/tr[5]")).isDisplayed());
    }
    
    // 辅助方法，用于登录卖家
    private void loginAsSeller() {
    	driver.get("http://localhost:8080/#/login");

        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("admin");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();
        
        // 登录成功后页面应自动跳转到 http://localhost:8080/#/dashboard/good/manager
        String expectedUrl = "http://localhost:8080/#/dashboard/good/manager";
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }
}
