package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ViewCustomerInfoTest extends BaseTest {
    @Test
    public void testViewExistingCustomerInfo() {
    	loginAsSeller();
    	
    	WebDriverWait wait1 = new WebDriverWait(driver, 10);
    	WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/section/section/aside/div/ul/li[3]")));
    	element.click();
    	
        // 等待结果加载
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"userview\"]/div[3]/div[4]/div[2]/table/tbody/tr[1]")));

        // 验证客户信息是否显示
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"userview\"]/div[3]/div[4]/div[2]/table/tbody/tr[1]"));
        assertTrue("客户信息未加载", rows.size() > 0);
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
