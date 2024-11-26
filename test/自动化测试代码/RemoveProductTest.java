package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class RemoveProductTest extends BaseTest {

    @Test
    public void testRemoveProductSuccess() {
        // 登录为卖家
        loginAsSeller();

        // 等待商品管理页面加载并找到下架按钮
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        WebElement changeButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"goods\"]/div[4]/ul/li[2]"))); // 换页
            changeButton.click();
            
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"goods\"]/div[3]/div[5]/div[2]/table/tbody/tr[2]/td[8]/div/div/button[2]"))); // 商品的下架按钮
        removeButton.click();

        // 验证商品是否从列表中移除
        boolean isRemoved = wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//*[@id=\"goods\"]/div[3]/div[4]/div[2]/table/tbody/tr[2]")));
        assertTrue("商品未成功下架", isRemoved);
    }

    // 辅助方法：登录为卖家
    private void loginAsSeller() {
        driver.get("http://localhost:8081/#/login"); // 打开登录页面

        // 输入用户名和密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("seller");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("seller");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证是否跳转到商品管理页面
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/#/dashboard/good/manager"));
    }
}
