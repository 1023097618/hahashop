package TT;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ModifyProductTest extends BaseTest {

    @Test
    public void testModifyProduct() {
        loginAsSeller();
        driver.get("http://localhost:8080/#/dashboard/good/manager");

        // 显式等待，等待“修改”按钮出现
        WebDriverWait wait = new WebDriverWait(driver, 10); // 最多等待10秒
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"goods\"]/div[3]/div[5]/div[2]/table/tbody/tr/td[6]/div/div/button[1]")));

        // 点击修改按钮
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[3]/div[5]/div[2]/table/tbody/tr/td[6]/div/div/button[1]")).click();

        // 修改商品的名称、描述和价格
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[4]/div/div[2]/form/div[1]/div/div/input")).clear(); // 清空原有内容
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[4]/div/div[2]/form/div[1]/div/div/input")).sendKeys("修改后的商品名称");

        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[4]/div/div[2]/form/div[2]/div/div/input")).clear(); // 清空原有内容
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[4]/div/div[2]/form/div[2]/div/div/input")).sendKeys("这是修改后的商品描述");

        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[4]/div/div[2]/form/div[3]/div/div/input")).clear(); // 清空原有价格
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[4]/div/div[2]/form/div[3]/div/div/input")).sendKeys("200");

        // 提交修改
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[4]/div/div[3]/span/button[2]")).click();

        // 等待修改后的商品列表刷新
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"goods\"]/div[3]/div[3]/table/tbody/tr/td[2]/div")));

        // 获取修改后商品名称并进行验证
        String actualProductName = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[3]/div[3]/table/tbody/tr/td[2]/div")).getText();
        String expectedProductName = "修改后的商品名称";

        // 验证商品名称是否正确
        assertEquals("商品修改失败，名称不匹配！", expectedProductName, actualProductName);
    }

    // 辅助方法，用于登录卖家账户
    private void loginAsSeller() {
        driver.get("http://localhost:8080/#/login");

        driver.findElement(By.xpath("//*[@id='app']/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("admin"); // 输入卖家用户名
        driver.findElement(By.xpath("//*[@id='app']/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("1"); // 输入卖家密码
        driver.findElement(By.xpath("//*[@id='app']/section/main/div/div[2]/button")).click(); // 点击登录按钮
    }
}
