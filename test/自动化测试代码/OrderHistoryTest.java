package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class OrderHistoryTest extends BaseTest {

    // 测试默认加载订单历史
    @Test
    public void testDefaultOrderHistoryLoad() {
    	loginAsBuyer();

        // 验证表格是否显示订单数据
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"buyerOrder\"]/div/div[4]/div[2]/table/tbody/tr"));
        assertTrue("订单历史表格没有加载订单数据", rows.size() > 0);

        // 打印订单数据
        for (WebElement row : rows) {
            System.out.println(row.getText());
        }
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
    }
}
