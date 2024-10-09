package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class SubmitBuyerInfoTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\DELL\\Desktop\\22\\软件质量测试\\edgedriver_win64\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("http://localhost:8082/#/");
    }

    @Test
    public void testSubmitBuyerInfo() {
        // 浏览商品，点击购买
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div/div[4]/div/div/div[1]/img")).click();
        
        // 填写买家信息
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[1]/div/div/input")).sendKeys("John Doe");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[2]/div/div/input")).sendKeys("1234567890");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[3]/div/div/input")).sendKeys("123 Buyer Street");
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[2]/form/div[4]/div/div/textarea")).sendKeys("123");

        // 提交买家信息
        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/div/div/div[3]/span/button[2]")).click();

        driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/header/div/div[3]/button")).click();

        // 输入卖家用户名和密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("david");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("adminpass000");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 登录成功后页面应自动跳转到 http://localhost:8082/#/dashboard/good
        String expectedUrl = "http://localhost:8082/#/dashboard/good";
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[1]/div[3]/table/tbody/tr[4]/td[5]/div/button")).click();
        
        // 验证买家信息是否正确显示
        // 根据你界面的实际情况查找元素并验证
        String buyerName = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[4]/div/div[2]/div/div[3]/table/tbody/tr/td[4]/div")).getText();
        String buyerPhone = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[4]/div/div[2]/div/div[3]/table/tbody/tr/td[1]/div")).getText();
        String buyerAddress = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[4]/div/div[2]/div/div[3]/table/tbody/tr/td[2]/div")).getText();
        String remark = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[4]/div/div[2]/div/div[3]/table/tbody/tr/td[3]/div")).getText();

        
        // 确保填入的买家信息出现在 dashboard/good 页面
        assertTrue(buyerName.equals("John Doe"));
        assertTrue(buyerPhone.equals("1234567890"));
        assertTrue(buyerAddress.equals("123 Buyer Street"));
        assertTrue(remark.equals("123"));
    }
}
