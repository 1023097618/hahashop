package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProductHistoryTest extends BaseTest {
    // 测试默认加载商品历史
    @Test
    public void testDefaultProductHistoryLoad() {
    	loginAsSeller();

    	// 等待菜单可见
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement menuElement = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"app\"]/div/section/section/aside/div/ul/li[1]/ul/div/li[2]")));

        // 使用 Actions 模拟点击
        Actions actions = new Actions(driver);
        actions.moveToElement(menuElement).click().perform();

        // 验证跳转是否成功
        String expectedUrl = "http://localhost:8081/#/dashboard/good/history";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));


        // 验证表格是否显示商品记录
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"history\"]/div[3]/div[4]/div[2]/table/tbody/tr[1]"));
        assertTrue("商品历史表格没有加载商品数据", rows.size() > 0);

        // 打印商品记录
        for (WebElement row : rows) {
            System.out.println(row.getText());
        }
    }

    // 辅助方法，用于登录买家
    private void loginAsSeller() {
        driver.get("http://localhost:8081/#/login"); // 打开登录页面

        // 输入用户名和密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("seller");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("seller");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证是否跳转到订单页面
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/#/dashboard/good/manager"));
    }
}
