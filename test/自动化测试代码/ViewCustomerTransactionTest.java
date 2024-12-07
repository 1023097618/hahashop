package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ViewCustomerTransactionTest extends BaseTest {

    @Test
    public void testViewExistingCustomerTransaction() {
        loginAsSeller();

        // 等待并点击“客户管理”菜单
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement customerMenuElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/section/section/aside/div/ul/li[3]")));
        new Actions(driver).moveToElement(customerMenuElement).click().perform();

        // 验证跳转到客户页面
        String expectedUrl = "http://localhost:8081/#/dashboard/user";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        // 点击某个客户的“查看交易记录”按钮
        WebElement viewTransactionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"userview\"]/div[3]/div[5]/div[2]/table/tbody/tr[2]/td[4]/div/div/button")));
        viewTransactionButton.click();

        // 等待交易记录加载
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"userview\"]/div[4]/div/div[2]/div/div[4]/div[2]/table/tbody/tr")));

        // 验证交易记录是否显示
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"userview\"]/div[4]/div/div[2]/div/div[4]/div[2]/table/tbody/tr"));
        assertTrue("客户交易记录未加载", rows.size() > 0);
    }

    @Test
    public void testViewNonExistingCustomerTransaction() {
        loginAsSeller();

        // 等待并点击“客户管理”菜单
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement customerMenuElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app\"]/div/section/section/aside/div/ul/li[3]")));
        new Actions(driver).moveToElement(customerMenuElement).click().perform();

        // 验证跳转到客户页面
        String expectedUrl = "http://localhost:8081/#/dashboard/user";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        // 点击第一个客户的“查看交易记录”按钮
        WebElement viewTransactionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"userview\"]/div[3]/div[5]/div[2]/table/tbody/tr[1]/td[4]/div/div/button")));
        viewTransactionButton.click();

        // 等待页面响应
        WebDriverWait waitForNoDataMessage = new WebDriverWait(driver, 10);
        
        // 查找“暂无数据”提示
        WebElement noDataMessage = waitForNoDataMessage.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"userview\"]/div[4]/div/div[2]/div/div[3]/div/span")));

        // 验证是否显示“暂无数据”提示信息
        String noDataText = noDataMessage.getText();
        assertEquals("未正确提示暂无数据", "暂无数据", noDataText);
    }

    // 辅助方法：登录为卖家
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
