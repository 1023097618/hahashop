package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;

public class ProductSearchTest extends BaseTest {

    @Test
    public void testSearchByName() {
        driver.get("http://localhost:8081/#/");

        // 输入有效的商品名称
        driver.findElement(By.xpath("//*[@id=\"searchBox\"]/div/input")).sendKeys("沙发");
        driver.findElement(By.xpath("//*[@id=\"searchBox\"]/div/div[2]/button")).click();

        // 定位元素
        By elementLocator = By.xpath("//*[@id=\"layoutview\"]/section/main/div[4]/div/div[1]");

        // 使用 WebDriverWait 等待元素可见，最多等待10秒
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

        // 检查元素是否可见
        boolean isDisplayed = element.isDisplayed();
        Assert.assertTrue("元素未显示", isDisplayed); // 如果元素未显示则抛出异常
    }

    @Test
    public void testSearchByCategory() {
        driver.get("http://localhost:8081/#/");

        // 选择商品分类
        driver.findElement(By.xpath("//*[@id=\"searchBox\"]/div/div[1]/div/div[1]/input")).sendKeys("居家 / 沙发");
        
        // 定位元素
        By elementLocator = By.xpath("//*[@id=\"layoutview\"]/section/main/div[4]/div/div[1]");

        // 使用 WebDriverWait 等待元素可见，最多等待10秒
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

        // 检查元素是否可见
        boolean isDisplayed = element.isDisplayed();
        Assert.assertTrue("元素未显示", isDisplayed); // 如果元素未显示则抛出异常
    }

    @Test
    public void testSearchNoResults() {
        driver.get("http://localhost:8081/#/");

        // 输入不存在的商品名称
        driver.findElement(By.xpath("//*[@id=\"searchBox\"]/div/input")).sendKeys("Galaxy Z Fold 100");
        driver.findElement(By.xpath("//*[@id=\"searchBox\"]/div/div[2]/button")).click();

     // 通过 findElements 来判断元素是否存在
        boolean isElementPresent = !driver.findElements(By.xpath("//*[@id=\"layoutview\"]/section/main/div[4]/div/div[1]")).isEmpty();

        // 断言该元素未显示
        Assert.assertFalse("该元素应未显示", isElementPresent);
    }

    @Test
    public void testSearchEmptyQuery() {
        driver.get("http://localhost:8081/#/");

        // 输入空的搜索词
        driver.findElement(By.xpath("//*[@id=\"searchBox\"]/div/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"searchBox\"]/div/div[2]/button")).click();

        // 等待搜索结果
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // 验证是否显示所有商品（空搜索查询）
        assertTrue("空搜索时显示商品列表", driver.findElement(By.xpath("//*[@id=\"layoutview\"]/section/main/div[4]/div/div[1]/div/div/div[1]/div/div/div")).isDisplayed());
    }
}
