package test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.Assert.assertTrue;

public class UpdateProductInfoTest {
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
    }

    @Test
    public void testUpdateProductInfo() {
        // 使用显式等待确保商品列表加载完成
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String productXpath = "//*[@id=\"app\"]/div/section/section/main/div/div/div[1]/div[3]/table/tbody/tr[5]/td[2]/div";

        // 等待商品元素可见
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productXpath)));

        // 查找并选择要修改的商品
        WebElement editButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[1]/div[5]/div[2]/table/tbody/tr[5]/td[6]/div/div/button[1]"));
        
        // 滚动页面到编辑按钮并等待按钮可点击
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();

        // 修改商品信息
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[2]/div/div[2]/form/div[1]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[2]/div/div[2]/form/div[1]/div/div/input")).sendKeys("更新后的商品名称");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[2]/div/div[2]/form/div[2]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[2]/div/div[2]/form/div[2]/div/div/input")).sendKeys("200");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[2]/div/div[2]/form/div[3]/div/div/input")).clear();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[2]/div/div[2]/form/div[3]/div/div/input")).sendKeys("updated_image.jpg");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[2]/div/div[2]/form/div[4]/div/div/textarea")).clear();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[2]/div/div[2]/form/div[4]/div/div/textarea")).sendKeys("更新后的商品备注");

        // 提交修改
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[2]/div/div[3]/span/button[2]")).click();

        // 刷新页面以验证修改
        driver.navigate().refresh();

        // 再次等待页面加载，确保商品信息已更新
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productXpath)));

        // 验证商品信息是否已更新
        String updatedProductName = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[1]/div[3]/table/tbody/tr[5]/td[2]/div")).getText();
        String updatedProductPrice = driver.findElement(By.xpath("//*[@id=\"app\"]/div/section/section/main/div/div/div[1]/div[3]/table/tbody/tr[5]/td[3]/div")).getText();

        // 断言修改后的商品信息是否正确显示
        assertTrue(updatedProductName.equals("更新后的商品名称"));
        assertTrue(updatedProductPrice.equals("200"));
    }
}
