package TT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PublishProductTest extends BaseTest {

    private final String productName;
    private final String productPrice;
    private final String productStock;
    private final String productDescription;

    public PublishProductTest(String productName, String productPrice, String productStock, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        String csvFilePath = "test99/TT/PublishProductTest.csv"; // CSV 文件路径
        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true; // 跳过标题行
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] split = line.split(",");
                String productName = split[0].trim();
                String productPrice = split[1].trim();
                String productStock = split[2].trim();
                String productDescription = split[3].trim();
                data.add(new Object[]{productName, productPrice, productStock, productDescription});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Test
    public void testPublishProduct() {
        loginAsSeller();

        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[3]/div[5]/div[1]/table/thead/tr/th[8]/div/div/button")));
        element.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")));

        // 填写商品信息
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")).sendKeys(productName);
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[2]/div/div/input")).sendKeys(productPrice);
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[4]/div/div/input")).sendKeys(productStock);
        driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[7]/div[1]")).sendKeys(productDescription);

        // 上传图片
        WebElement uploadElement = driver.findElement(By.xpath("//*[@id='goods']/div[6]/div/div[2]/div/form/div[3]/div/div/div[1]/div/input"));
        uploadElement.sendKeys("C://Users//DELL//Desktop//测试//OIP-C.jpg"); // 替换为图片的绝对路径

        // 点击发布按钮
        WebDriverWait wait5 = new WebDriverWait(driver, 10);
        WebElement publishButton = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[3]/span/button[2]")));
        publishButton.click();

        // 等待元素消失
        WebDriverWait waitDisappear = new WebDriverWait(driver, 10);
        boolean isGone = waitDisappear.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"goods\"]/div[6]/div")));

        // 验证是否消失
        assertTrue("商品发布后，发布界面未消失", isGone);
    }

    // 辅助方法，用于登录卖家
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
