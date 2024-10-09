package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertFalse;

public class RemoveProductTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\DELL\\Desktop\\22\\软件质量测试\\edgedriver_win64\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.get("http://localhost:8082/#/login");
        
        // 登录
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("david");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("adminpass000");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();
    
        // 登录成功后页面应自动跳转到 http://localhost:8082/#/dashboard/good
        String expectedUrl = "http://localhost:8082/#/dashboard/good";
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        
        driver.get("http://localhost:8082/#/dashboard/good");
    }

    @Test
    public void testRemoveProduct() {
        // 使用显式等待确保商品列表加载完成
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String productXpath = "//*[@id=\"app\"]/div/section/section/main/div/div/div[1]/div[3]/table/tbody/tr[5]/td[2]/div";

        // 等待商品元素可见
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productXpath)));

        // 点击商品下架按钮
        String removeButtonXpath = "//*[@id=\"app\"]/div/section/section/main/div/div/div[1]/div[5]/div[2]/table/tbody/tr[5]/td[6]/div/div/button[2]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(removeButtonXpath))).click();

        // 刷新页面，确保操作生效
        driver.navigate().refresh();
    }

}
